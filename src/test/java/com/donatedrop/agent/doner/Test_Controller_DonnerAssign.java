package com.donatedrop.agent.doner;

import com.donatedrop.agent.donner.Dao_AgentDonner_I;
import com.donatedrop.agent.donner.models.*;
import com.donatedrop.agent.models.RequestNote;
import com.donatedrop.agent.models.RequestReviewRequest;
import com.donatedrop.agent.models.StatusType;
import com.donatedrop.geocode.AbstractTest;
import com.donatedrop.other.DumpDao;
import com.donatedrop.other.DumpData;
import com.donatedrop.util.DateUtil;
import com.donatedrop.util.StringUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.*;

@SpringBootTest
public class Test_Controller_DonnerAssign extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Autowired
    DumpDao dumpDao;

    @Autowired
    Dao_AgentDonner_I dao_agentDonner_i;

    @Test
    @Order(1)
//    http://localhost:8080/public/user/agent/donnerAssign/getAssingments
    public void testGetDonnerToAgentRequestToReview() throws Exception {
        String uri = "/public/user/agent/donnerAssign/getAssingments";
        RequestSearchDonnerAssing requestSearchReview =
//                new RequestSearchDonnerAssing(0, 5, "phonenumber", "%%", StatusType.ZERO);
//                new RequestSearchDonnerAssing(0, 5, "phonenumber", "%013%", StatusType.ZERO);//2
                new RequestSearchDonnerAssing(0, 20, "email", "%%", StatusType.ZERO);
        requestSearchReview.setAgentID("11186");
        //      act
        String inputJson = super.mapToJson(requestSearchReview);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
//        System.out.println("\n" + content + "\n");
        List<DonnerAssingShow> donnerAssingShowsList = Arrays.asList(super.mapFromJson(content, DonnerAssingShow[].class));
        donnerAssingShowsList.forEach(donnerAssingShow -> System.out.println(donnerAssingShow));
        assertTrue(donnerAssingShowsList.size() >= 0);
    }


    @Test
    @Order(1)
//    http://localhost:8080/public/user/agent/donnerAssign/getAssingmentsCount
    public void testGetAssingmentswCount() throws Exception {
        String uri = "/public/user/agent/donnerAssign/getAssingmentsCount";
        RequestSearchDonnerAssing requestSearchReview =
//                new RequestSearchDonnerAssing(0, 5, "phonenumber", "%%", StatusType.ZERO);
//                new RequestSearchDonnerAssing(0, 5, "phonenumber", "%013%", StatusType.ZERO);//2
                new RequestSearchDonnerAssing(0, 20, "email", "%%", StatusType.ZERO);
        requestSearchReview.setAgentID("11186");
        //      act
        String inputJson = super.mapToJson(requestSearchReview);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
//        System.out.println("\n" + content + "\n");
        Map<String, String> resultMap = super.mapFromJson(content, Map.class);
        System.out.println(resultMap);
    }


    @Test
    public void testSaveAssignment() throws Exception {
        String uri = "/public/user/agent/donnerAssign/save";

        DonnerAssingment donnerAssingment = new DonnerAssingment();
        donnerAssingment.setAgentId("");
        donnerAssingment.setDonnerId("11148");
        donnerAssingment.setNeedDate(DumpData.getDate());

        String inputJson = super.mapToJson(donnerAssingment);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Map<String, String> resultMap = super.mapFromJson(content, Map.class);
        System.out.println("\n" + resultMap + "\n");
        assertEquals(StringUtil.OK, resultMap.get(StringUtil.STATUS));
    }


    @Test
    public void testDeleteAssignment() throws Exception {
//        INSERT INTO `donnerassingment` (`donner_assingment_id`, `agent_id`, `donner_id`, `assing_date`, `assing_note`, `need_date`, `post_id`, `blood_manage_status`)
//        VALUES ('21463', '11186', '12044', '2020-08-23 05:23:13', 'Note Assign', '2017-07-10', NULL, '0')

        String donnerAssingmentID = "21463";
        String uri = "/public/user/agent/donnerAssign/delete?donnerAssingmentID=" + donnerAssingmentID;

        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.delete(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Map<String, String> resultMap = super.mapFromJson(content, Map.class);
        System.out.println("\n" + resultMap + "\n");
        assertEquals(StringUtil.OK, resultMap.get(StringUtil.STATUS));
    }

    //    String uri = "/public/profile/basic/findOneByUser";
    @org.junit.Test
    public void testfindOneByUser() throws Exception {
        String userID = "11148";
        String uri = "/public/user/agent/donnerAssign/profileDetails?userID=" + userID;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }


    //    @Test
//    @Order(1)
//    //"/public/user/agent/donner/saveRequest";
//    public void testSveRequestDonnerToAgent() throws Exception {
//        String uri = "/public/user/agent/donner/saveRequest";
//        System.out.println("\nAgent Donner Request Save\n");
//        BigInteger userIDBigInteger = dumpDao.getNotRequestedDonnerToAgentUsers(0, 5).get(0);
//        Integer userID = ((BigInteger) userIDBigInteger).intValue();
//        System.out.println("userID : " + userID);
//
//        DonnerRequestToAgent donnerRequestToAgent = new DonnerRequestToAgent();
//        donnerRequestToAgent.setUserIdDonner(userID.toString());
//        donnerRequestToAgent.setRequestDate(DateUtil.getDate().toString());
//        donnerRequestToAgent.setStatus("0");
//        donnerRequestToAgent.setNoteDonner("I am from ur university!");
//
//        String inputJson = super.mapToJson(donnerRequestToAgent);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
////      act
//        System.out.println(content);
//
////      assert
//        Map<String, String> map = super.mapFromJson(content, Map.class);
//        assertEquals(StringUtil.OK, map.get(StringUtil.STATUS));
//        if (map.get(StringUtil.STATUS).equals(StringUtil.OK)) {
//            storeID(userID.toString());
//        }
//    }
//
//    @Test
//    @Order(1)
//    //"/public/user/agent/donner/findOneRequestById";
//    // RequestParam String donnerAgentRequestID
//    public void testFindOneRequestById() throws Exception {
//        String donnerAgentRequestID = dumpDao.getAgentDonnersRequests(0, 5).get(0).getId().toString();
//        String uri = "/public/user/agent/donner/findOneRequestById?donnerAgentRequestID=" + donnerAgentRequestID;
//
//        MvcResult mvcResult = mvc.perform(
//                MockMvcRequestBuilders.post(uri)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        DonnerRequestToAgent donnerRequestToAgent = super.mapFromJson(content, DonnerRequestToAgent.class);
//        System.out.println("\n" + donnerRequestToAgent + "\n");
//        assertNotNull(donnerRequestToAgent);
//    }
//
//    @Test
//    @Order(1)
//    //"/public/user/agent/donner/findOneRequestUserID";
//    //@RequestParam String userID
//    public void testFindOneRequestUserID() throws Exception {
//        String userID = dumpDao.getAgentDonnersRequests(0, 5).get(0).getUserIdDonner().toString();
//        String uri = "/public/user/agent/donner/findOneRequestUserID?userID=" + userID;
//
//        MvcResult mvcResult = mvc.perform(
//                MockMvcRequestBuilders.post(uri)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        DonnerRequestToAgent donnerRequestToAgent = super.mapFromJson(content, DonnerRequestToAgent.class);
//        System.out.println("\n" + donnerRequestToAgent + "\n");
//        assertNotNull(donnerRequestToAgent);
//    }
//
//    @Test
//    @Order(1)
//    //"/public/user/agent/donner/deleteRequestByUserID";
//    //refactor : have to implement later
//    public void testDeleteRequestByUserID() {
//        String url = "/public/user/agent/donner/deleteRequestByUserID";
//    }
//
//    @Test
//    @Order(1)
//    //"/public/user/agent/donner/reviewDonnerRequest";
//    public void testReviewDonnerRequestRightValue() throws Exception {
//        String uri = "/public/user/agent/donner/reviewDonnerRequest";
//        int max = 5;
//        String requestID = dumpDao.getAgentDonnersRequests(0, 5).get(1).getId().toString();
//        System.out.println("requestID : " + requestID);
//        String reviewValue = StatusType.REJECT;
//        RequestReviewRequest reviewRequest = new RequestReviewRequest(requestID, reviewValue);
//
//        String inputJson = super.mapToJson(reviewRequest);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
////      act
//        System.out.println(content);
//
////      assert
//        Map<String, String> map = super.mapFromJson(content, Map.class);
//        assertEquals(StringUtil.OK, map.get(StringUtil.STATUS));
//
//        String savedStatus = dao_agentDonner_i.findOneRequestById(requestID).getStatus();
//        assertEquals(reviewValue, savedStatus);
//    }
//
//    @Test
//    @Order(1)
//    //"/public/user/agent/donner/reviewDonnerRequest";
//    public void testReviewDonnerRequestWrongValue() throws Exception {
//        String uri = "/public/user/agent/donner/reviewDonnerRequest";
//        int max = 5;
//        String requestID = dumpDao.getAgentDonnersRequests(0, 5).get(1).getId().toString();
//        System.out.println("requestID : " + requestID);
//        String reviewValue = "--";
//        RequestReviewRequest reviewRequest = new RequestReviewRequest(requestID, reviewValue);
//
//        String inputJson = super.mapToJson(reviewRequest);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
////      act
//        System.out.println(content);
//
////      assert
//        Map<String, String> map = super.mapFromJson(content, Map.class);
//        assertEquals(StringUtil.FAIL, map.get(StringUtil.STATUS));
//    }
//

//
//    @Test
//    @Order(1)
//    //"/public/user/agent/donner/getDonnerToAgentRequestReviewCount";
//    public void testGetDonnerToAgentRequestReviewCount() throws Exception {
//        String uri = "/public/user/agent/donner/getDonnerToAgentRequestReviewCount";
//        RequestSearchReview requestSearchReview =
//                new RequestSearchReview(0, 5, "username", "%%", StatusType.ZERO);
//        //      act
//        String inputJson = super.mapToJson(requestSearchReview);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//        //assert
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        //System.out.println("\n" + content + "\n");
//        Map<String, String> result = super.mapFromJson(content, Map.class);
//        System.out.println("\n" + result + "\n");
//        assertTrue(Integer.parseInt(result.get(StringUtil.COUNT).toString()) >= 0);
//    }
//
//
//    @Test
//    @Order(1)
//    //"/public/user/agent/donner/updateAgentNote";
//    public void testUpdateAgentNote() throws Exception {
//        String uri = "/public/user/agent/donner/updateAgentNote";
//        int max = 5;
//        String note = "Please tell me about ur UnionName-SchooL Name Etc nam!";
//        String requestID = dumpDao.getAgentDonnersRequests(0, 5).get(1).getId().toString();
//        System.out.println("requestID : " + requestID);
//        RequestNote requestNote = new RequestNote(requestID, note);
//
//        //      act
//        String inputJson = super.mapToJson(requestNote);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//        //assert
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        //System.out.println("\n" + content + "\n");
//        Map<String, String> result = super.mapFromJson(content, Map.class);
//        System.out.println("\n" + result + "\n");
//        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
//
//        String requestNoteFormDB = dao_agentDonner_i.findOneRequestById(requestID).getNoteAgent();
//        System.out.println("\n" + requestNoteFormDB + "\n");
//        assertEquals(requestNoteFormDB, note);
//    }
//
//    @Test
//    @Order(1)
//    //"/public/user/agent/donner/updateNoteDonner";
//    public void testUpdateNoteDonner() throws Exception {
//        String uri = "/public/user/agent/donner/updateNoteDonner";
//        int max = 5;
//        String note = "I am from ur home village and school!";
//        String requestID = dumpDao.getAgentDonnersRequests(0, 5).get(1).getId().toString();
//        System.out.println("requestID : " + requestID);
//        RequestNote requestNote = new RequestNote(requestID, note);
//
//        //      act
//        String inputJson = super.mapToJson(requestNote);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//        //assert
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//
//        Map<String, String> result = dao_agentDonner_i.updateNoteDonner(requestNote);
//        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
//
//        String requestNoteFormDB = dao_agentDonner_i.findOneRequestById(requestID).getNoteDonner();
//        System.out.println("\n" + requestNoteFormDB + "\n");
//        assertEquals(requestNoteFormDB, note);
//    }
//
//    @Test
//    @Order(1)
////    //"/public/user/agent/donner/updateNoteAgentPersonal";
//    public void testUpdateNoteAgentPersonal() throws Exception {
//        String uri = "/public/user/agent/donner/updateNoteAgentPersonal";
//        int max = 5;
//        String note = "i will approve, after meeting with him!, telling from my school but seems false info.";
//        String requestID = dumpDao.getAgentDonnersRequests(0, 5).get(1).getId().toString();
//        System.out.println("requestID : " + requestID);
//        RequestNote requestNote = new RequestNote(requestID, note);
//
//        //      act
//        String inputJson = super.mapToJson(requestNote);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        //assert
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//
//        Map<String, String> result = dao_agentDonner_i.updateNoteAgentPersonal(requestNote);
//        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
//
//        String requestNoteFormDB = dao_agentDonner_i.findOneRequestById(requestID).getNoteAgentPersonal();
//        System.out.println("\n" + requestNoteFormDB + "\n");
//        assertEquals(requestNoteFormDB, note);
//    }

    // helpers : -------------------------

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
            FileWriter myWriter = new FileWriter("agent_donner_controller.txt");
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
            File myObj = new File("agent_donner_controller.txt");
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
