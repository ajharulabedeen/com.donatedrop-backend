package com.donatedrop.history;

import com.donatedrop.models.Address;
import com.donatedrop.other.DumpDao;
import com.donatedrop.other.DumpData;
import com.donatedrop.profile.basic.Dao_Profile_Basic_I;
import com.donatedrop.profile.model.EmergencyContact;
import com.donatedrop.profile.model.PhoneNumber;
import com.donatedrop.profile.model.ProfileBasic;
import com.donatedrop.security.models.User;
import com.donatedrop.util.DateUtil;
import com.donatedrop.util.StringUtil;
import com.donatedrop.util.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//import static org.junit.jupiter.api.Assertions;//not working
import static org.junit.Assert.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class Test_Dao_History_Impl {

    @Autowired
    Dao_History_I dao_history_i;

    @Autowired
    Dao_Profile_Basic_I dao_profile_basic_i;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    DumpDao dumpDao;

    @Test
    @Order(-1)
    public void test0_DaoObject() {
        System.out.println("\n\n---M0---\n\n");
        assert (dao_history_i != null);
    }

    /**
     * April 27, 2020
     */
    @Test
    @Order(1)
    public void testSave() {
        Map<String, String> status = null;
//        String userID = Utils.getLoggedUserID();
        String userID = dumpDao.getUsers(0, 10).get(0).getId().toString();
        String id = "";
        // Arrange
        try {
            System.out.println("\nHistory Save\n");
            History history = new History();
            history.setUserId(userID);//will be set from service.
            history.setDate(DateUtil.getDate().toString());
            history.setLocation("Karakom,WestPoint, Dhaka.");
            history.setPatientDescription("Kidney");
            history.setRefferedBy("Mobile");
            history.setNote("Went to at night.");
//            history.setProfileBasic(dao_profile_basic_i.getProfileBasicByUserID(userID));//will be set from service
//        ACT
            status = dao_history_i.save(history);
            System.out.println(status);
            id = status.get(StringUtil.ID);
            System.out.println("\nID : " + id);
            storeID(id);
        } catch (Exception e) {
            storeID(id);
        }
////        Assert
        Assert.assertEquals(StringUtil.OK, status.get(StringUtil.STATUS));
    }

    @Test
    @Order(2)
    public void testFindOne() {
        String historyID = getID();
        History history = dao_history_i.findOne(historyID);
//        System.out.println(history.toString());//error : parent init problem
        System.out.println(history.getLocation());
        assert (history != null);
    }

    @Test
    @Order(3)
    public void testUpdate() {
        Map<String, String> result = new HashMap<>();

        String historyID = getID();
        String userID = dao_history_i.findOne(historyID).getUserId();
//        String userID = Utils.getLoggedUserID();

        System.out.println("\nHistory Update\n");
        History history = new History();
        history.setId(Long.parseLong(historyID));
        history.setUserId(userID);//will be set from service.
        history.setDate(DateUtil.getDate().toString());
        history.setLocation("Karakoram, Shimla, India!");
        history.setPatientDescription("Kidney/Heart");
        history.setRefferedBy("Mobile call!");
        history.setNote("Went to at night.");

        //unathurised update
        result = dao_history_i.update(history, userID + 1);//fail test
        System.out.println("\n>>" + result + "\n");
        Assert.assertEquals(StringUtil.FAIL, result.get(StringUtil.STATUS));
        Assert.assertEquals(StringUtil.UNAUTHERIZED, result.get(StringUtil.ERROR));

        //athurised update
        result = dao_history_i.update(history, userID);
        System.out.println("\n>>" + result + "\n");
        Assert.assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
        History historySaved = dao_history_i.findOne(historyID);
        // date test will be failed, due to second misMatch
//        assert (historySaved.getDate().toString() == history.getDate().toString());
        System.out.println(historySaved.getLocation().equals(history.getLocation())); // true
        System.out.println(historySaved.getLocation() == history.getLocation()); // false

        assert (historySaved.getLocation().equals(history.getLocation()));
        assert (historySaved.getPatientDescription().equals(history.getPatientDescription()));
        assert (historySaved.getRefferedBy().equals(history.getRefferedBy()));
        assert (historySaved.getNote().equals(history.getNote()));
    }

    @Test
    @Order(4)
    public void testGetAllHistory() {
        List<History> historyList = dao_history_i.getAllHistory("15", 1, 10);
        historyList.forEach(h -> System.out.println(h.toString()));
        assertNotNull(historyList);
    }

    @Test
    @Order(5)
    public void testGetTotalCount() {
        System.out.println("\nTotal : \n" + dao_history_i.getTotalCount("15"));
    }

    @Test
    @Order(6)
    public void testSearch() {
        List<History> historyList = dao_history_i.search("15", "note", "khulna", 0, 10);
        System.out.println("\n");
        historyList.forEach(h -> System.out.println(h.toString()));
        System.out.println("\n");
        assertNotNull(historyList);
    }

    @Test
    @Order(7)
    public void testSearchCount() {
        Map<String, Integer> count = dao_history_i.searchCount("16", "note", "%kh", 0);
        System.out.println("\nCount : " + count.get(StringUtil.COUNT) + "\n");
        assertNotNull(count.get(StringUtil.COUNT));
    }

    @Test
    @Order(8)
    public void testDelete() {
        Map<String, String> result = new HashMap<>();
        String historyID = getID();
        String userID = dao_history_i.findOne(historyID).getUserId();
//        String userID = Utils.getLoggedUserID();

        //        unautherised
        result = dao_history_i.delete(historyID, userID + 1);
        System.out.println("\n>>" + result + "\n");
        Assert.assertEquals(StringUtil.FAIL, result.get(StringUtil.STATUS));
        Assert.assertEquals(StringUtil.UNAUTHERIZED, result.get(StringUtil.ERROR));

        //        autherised
        result = dao_history_i.delete(historyID, userID);
        System.out.println("\n>>" + result + "\n");
        Assert.assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));

        assert (dao_history_i.findOne(historyID) == null);
    }

    //    start : other
//    @Test
    public void getAllHistory() {
        dumpDao.getAllHistory(0, 100).forEach(h -> System.out.println(h.toString()));
    }

//    @Test
    public void updateAllHistory() {
        dumpDao.updateAllHistory();
    }
//    end : other


    //    Helpers :
//    @Test
//    @Transactional
    public void saveManyHistory() {
        Map<String, String> status = new HashMap<>();
        String userID = "15";
        List<User> allUsers = dumpDao.getAllUsers();
        Random random = new Random();
        for (int x = 0; x < 1000; x++) {
            System.out.println("Loop Counter : " + x);
            userID = allUsers.get(random.nextInt(allUsers.size() - 1)).getId().toString();
            int numberOfHistory = random.nextInt(20);
            for (int y = 0; y < numberOfHistory + 1; y++) {
                System.out.println("\nHistory Save\n");
                History history = new History();
                history.setUserId(userID);//will be set from service.
                history.setDate(DateUtil.getDate().toString());
                history.setLocation(DumpData.getLocation());
                history.setPatientDescription(DumpData.getPatientDescription());
                history.setRefferedBy(DumpData.getRefferedBy());
                history.setNote(DumpData.getNote());
                history.setUserId(userID);
                dao_history_i.save(history);
                System.out.println(history.getId());
            }//for
        }
//        bug : data are not being save
//        for (int x = 0; x < 100; x++) {
//            System.out.println("\nHistory Save\n");
//            History history = new History();
//            history.setUserId(userID);//will be set from service.
//            history.setDate(DateUtil.getDate().toString());
//            history.setLocation(DumpData.getLocation());
//            history.setPatientDescription(DumpData.getPatientDescription());
//            history.setRefferedBy(DumpData.getRefferedBy());
//            history.setNote(DumpData.getNote());
//            history.setUserId(userID);
//            entityManager.persist(history);
//            System.out.println(history.getId());
//        }

//        bug : data are not being save
//        History history = new History();
//        history.setUserId(userID);//will be set from service.
//        history.setDate(DateUtil.getDate().toString());
//        history.setLocation(DumpData.getLocation());
//        history.setPatientDescription(DumpData.getPatientDescription());
//        history.setRefferedBy(DumpData.getRefferedBy());
//        history.setNote(DumpData.getNote());
//        history.setUserId(userID);
//        entityManager.persist(history);
//        System.out.println(history.getId());
        //working, insertion of history, just for one.
//        for (int x = 0; x < 10; x++) {
//            System.out.println("\nHistory Save\n");
//            History history = new History();
//            history.setUserId(userID);//will be set from service.
//            history.setDate(DateUtil.getDate().toString());
//            history.setLocation(DumpData.getLocation());
//            history.setPatientDescription(DumpData.getPatientDescription());
//            history.setRefferedBy(DumpData.getRefferedBy());
//            history.setNote(DumpData.getNote());
//            history.setUserId(userID);
//            dao_history_i.save(history);
//            System.out.println(history.getId());
//        }//for
    }


    /**
     * will store the last save id, that can be used for later for other method.
     * Though there is question does it, right to a result from unit test, as
     * main goal of the unit test is to keep the test as much as possible
     * independent.
     *
     * @param id
     */
    public void storeID(String id) {
        try {
            FileWriter myWriter = new FileWriter("history_dao.txt");
            myWriter.write(id);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getID() {
        String id = "";
        try {
            File myObj = new File("history_dao.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                id = data;
//                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return id;
    }

}
