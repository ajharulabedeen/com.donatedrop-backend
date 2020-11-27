package com.donatedrop.profile_basic;

import com.donatedrop.models.Address;
import com.donatedrop.other.DumpDao;
import com.donatedrop.other.DumpData;
import com.donatedrop.other.TestUtil;
import com.donatedrop.profile.basic.Dao_Profile_Basic_I;
import com.donatedrop.profile.model.EmergencyContact;
import com.donatedrop.profile.model.PhoneNumber;
import com.donatedrop.profile.model.ProfileBasic;
import com.donatedrop.util.AddressType;
import com.donatedrop.util.DateUtil;
import com.donatedrop.util.StringUtil;
import com.donatedrop.util.Utils;
import org.aspectj.weaver.Dump;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class Test_Dao_Profile_Basic_Impl {

    @Autowired
    DumpDao dumpDao;

    @Autowired
    Dao_Profile_Basic_I dao_Profile_Basic_I;

    @Test
    @Order(-1)
    public void test0_DaoObject() {
        System.out.println("\n\n\n---M1---\n\n\n");
        assert (dao_Profile_Basic_I != null);
    }

    public String id = "";
    int count = 0;

    /**
     * April 5, 2020
     */
    @Test
    @Order(1)
    public void test1_save() {
        Map<String, String> status = null;

        // Arrange
        try {
            System.out.println("\nProfile Basic Dao Test!\n");
            ProfileBasic profileBasic = new ProfileBasic();
            String name = DumpData.getName();
            profileBasic.setName(name);
            profileBasic.setEmail(name.replace(" ", "") + "@gmail.com");
            profileBasic.setReligion(DumpData.getReligion());

//            profileBasic.setAddress(new Arrays.asList());
            ArrayList<Address> aList = new ArrayList<Address>(
                    Arrays.asList(
                            dumpDao.getAddress(AddressType.PERMANENT.toString()),
                            dumpDao.getAddress(AddressType.PRESENT.toString())
                    ));
            profileBasic.setAddress(aList);

            List<EmergencyContact> emergencyContacts = new ArrayList<>();
            String eName = DumpData.getName();
            EmergencyContact emergencyContact1
                    = new EmergencyContact(eName, DumpData.getPhoneNumber(), eName.replace(" ", "") + "@gmail.com",
                    dumpDao.getAddressString(AddressType.EMERGENCY.toString()), DumpData.getRelation(), DateUtil.getDate());
            eName = DumpData.getName();
            EmergencyContact emergencyContact2
                    = new EmergencyContact(eName, DumpData.getPhoneNumber(), eName.replace(" ", "") + "@gmail.com",
                    dumpDao.getAddressString(AddressType.EMERGENCY.toString()), DumpData.getRelation(), DateUtil.getDate());
            emergencyContacts.add(emergencyContact1);
            emergencyContacts.add(emergencyContact2);
            profileBasic.setEmergency_contact(emergencyContacts);

            List<PhoneNumber> phoneNumbers = Arrays.asList(
                    new PhoneNumber(DumpData.getPhoneNumber()),
                    new PhoneNumber(DumpData.getPhoneNumber()),
                    new PhoneNumber(DumpData.getPhoneNumber())
            );
            profileBasic.setPhone_number(phoneNumbers);
            profileBasic.setGender(DumpData.getGender());
            profileBasic.setBlood_Group(DumpData.getBloodGroup());
            profileBasic.setAvailable("0");
            profileBasic.setMaritalStatus(DumpData.getMarterialStatus());
            profileBasic.setProfession(DumpData.getProfession());
            profileBasic.setCare_of(DumpData.getName());
            //refactor : have to set automatic userID.
//            profileBasic.setUserId(Utils.getLoggedUserID());
            String userID = dumpDao.getUserIDNotInProfileID(1).get(0).getId().toString();
            profileBasic.setUserId(userID);
            profileBasic.setBirthDate(DateUtil.getDate());

//        ACT
            status = dao_Profile_Basic_I.save(profileBasic);
            System.out.println(status);
            id = status.get(StringUtil.ID);
            System.out.println("\nID : " + id);
            storeID(id);
            storeUserID(userID);
            storeProfileBasic(profileBasic.toString());
            System.out.println("Count : " + count++);
        } catch (Exception e) {
            storeID(id);
        }
//        Assert
        Assert.assertEquals(StringUtil.OK, status.get(StringUtil.STATUS));
    }

    @Test
    public void test_saveGetUserIdNotInserted() {
        dumpDao.getUserIDNotInProfileID(5).forEach(s -> System.out.println(s.getId() + " " + s.getUserName()));
    }

    @Test
    @Order(2)
    public void test2_basicExist() {
        //test for ProfileBasic not found
        String userID_NotExist = "12";
        Map<String, String> resultNotExist = dao_Profile_Basic_I.basicExist(userID_NotExist);
        System.out.println("\nNot Exist : " + resultNotExist + "\n");
//        assertEquals(resultNotExist.get("status"), false);
        Assert.assertEquals(StringUtil.FALSE, resultNotExist.get(StringUtil.STATUS));
        //test for ProfileBasic found
        String userID_Exist = TestUtil.userID;
        Map<String, String> resultExist = dao_Profile_Basic_I.basicExist(userID_Exist);
        System.out.println("\nExist : " + resultExist + "\n");
//        assertEquals(resultExist.get("status"), true);
        Assert.assertEquals(StringUtil.FALSE, resultNotExist.get(StringUtil.STATUS));
    }

    @Test
    @Order(3)
    public void test3_findOne() {
        String id = getID();
        ProfileBasic profileBasic = dao_Profile_Basic_I.findOne(id);

        //org.hibernate.LazyInitializationException, print will not work.
//        System.out.println("\n\n---\n" + profileBasic.toString() + "\n---\n\n");
        Assert.assertEquals(id, profileBasic.getId().toString());
    }

    @Test
    @Order(4)
    public void test4_findOneByUser() {
//        String userID = "16";
        String userID = getUserID();
        ProfileBasic profileBasic = dao_Profile_Basic_I.findOneByUserIDWithChild(userID);
        System.out.println("\nTest : \n" + profileBasic + "\n\n");
        System.out.println("\nTest : \n" + profileBasic.getPhone_number().toString() + "\n\n");
        if (profileBasic != null) {
            Assert.assertEquals(userID, profileBasic.getUserId());
        } else {
            Assert.assertEquals(StringUtil.NOT_NULL, StringUtil.NULL);
        }
    }

    @Test
    @Order(4)
    public void test4_findOneWithChild() {
        String id = getID();
        ProfileBasic profileBasic = dao_Profile_Basic_I.findOneWithChild(id);
        System.out.println("\n");
        System.out.println(profileBasic.toString());
        System.out.println("\n");
//        System.out.println(profileBasic.getPhone_number());
//        System.out.println(profileBasic.getPhone_number());
    }

    //    dependency : save profile basic.
    @Test
    @Order(5)
    public void test5_updateBasic() {
        // Arrange
        Map<String, String> result = new HashMap<>();
//        String userID = "13";
//        String userID = Utils.getLoggedUserID();
        String userID = getUserID();
        ProfileBasic profileBasicNew = new ProfileBasic();
        profileBasicNew.setUserId(userID);

        profileBasicNew.setName(">>>>Khan Ajharul Abedeen");
//        profileBasicNew.setBirthDate(DateUtil.getDate().toString());
        profileBasicNew.setGender("Male");
        profileBasicNew.setBlood_Group("A+");
        profileBasicNew.setAvailable("0");
        profileBasicNew.setMaritalStatus("NO");
        profileBasicNew.setProfession("Freelance/Remote");
        profileBasicNew.setCare_of("Khan Atiar Rahman and Dr Mahbub, Dumuria Khulna.");
        profileBasicNew.setReligion("Private");
        profileBasicNew.setEmail("Mail@mail.com");
        // Act
        result = dao_Profile_Basic_I.update(profileBasicNew);
        // Assertion
        System.out.println("\n\n" + result + "\n\n");
        Assert.assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
        //have to do more verification. by calling the full app.
        //refactor : stop child loading, need to create new method to get only basics.
        ProfileBasic profileBasicSaved = dao_Profile_Basic_I.findOneByUserIDWithChild(userID);
        Assert.assertEquals(profileBasicNew.getName(), profileBasicSaved.getName());
        Assert.assertEquals(profileBasicNew.getBirthDate(), profileBasicSaved.getBirthDate());
        Assert.assertEquals(profileBasicNew.getCare_of(), profileBasicSaved.getCare_of());
//        assertEquals("DimDim", profileBasicSaved.getCare_of());//for test the test, :P
        Assert.assertEquals(profileBasicNew.getGender(), profileBasicSaved.getGender());
        Assert.assertEquals(profileBasicNew.getMaritalStatus(), profileBasicSaved.getMaritalStatus());
        Assert.assertEquals(profileBasicNew.getProfession(), profileBasicSaved.getProfession());
        Assert.assertEquals(profileBasicNew.getBlood_Group(), profileBasicSaved.getBlood_Group());
        Assert.assertEquals(profileBasicNew.getAvailable(), profileBasicSaved.getAvailable());
        Assert.assertEquals(profileBasicNew.getReligion(), profileBasicSaved.getReligion());
        Assert.assertEquals(profileBasicNew.getEmail(), profileBasicSaved.getEmail());
    }

    //depedency : findOne By userID.
    @Test
    @Order(6)
    public void test6_presentAddressUpdate() {
//        String userID = "16";
        String userID = getUserID();
        String upzNew = "Gazipur";
        String distNew = "Dhaka";
        String divNew = "Dhaka";
        String streetAddressNew = "Road No 10";
        Address addressPresentNew = new Address();
        addressPresentNew.setDivision(divNew);
        addressPresentNew.setDistrict(distNew);
        addressPresentNew.setUpzilla(upzNew);
        addressPresentNew.setStreet_address(streetAddressNew);
        Map<String, String> result = dao_Profile_Basic_I.updatePresentAddress(addressPresentNew, userID);
        System.out.println("\n\n" + result + "\n\n");
        Assert.assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));

        Address addressPresentSaved = dao_Profile_Basic_I.findOneByUserIDWithChild(userID).getAddress().stream()
                .filter(address -> AddressType.PRESENT.toString().equals(address.getType()))
                .findAny()
                .orElse(null);

        String divSaved = addressPresentSaved.getDistrict();
        String distSaved = addressPresentSaved.getDivision();
        String upzSaved = addressPresentSaved.getDivision();
        Assert.assertEquals(divSaved, divNew);
        Assert.assertEquals(distSaved, distNew);
        Assert.assertEquals(upzSaved, upzSaved);
    }

    //depedency : findOne By userID.
    @Test
    @Order(7)
    public void test7_permanentAddressUpdate() {
//        String userID = "16";
        String userID = getUserID();
        String distNew = "Khulna";
        String divNew = "Khulna";
        Address addressPermanentNew = new Address();
        addressPermanentNew.setDivision(divNew);
        addressPermanentNew.setDistrict(distNew);
        Map<String, String> result = dao_Profile_Basic_I.updatePermanentAddress(addressPermanentNew, userID);
        System.out.println("\n\n" + result + "\n\n");
        Assert.assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));

        Address addressPermanentSaved = dao_Profile_Basic_I.findOneByUserIDWithChild(userID).getAddress().stream()
                .filter(address -> AddressType.PERMANENT.toString().equals(address.getType()))
                .findAny()
                .orElse(null);
        String divSaved = addressPermanentSaved.getDistrict();
        String distSaved = addressPermanentSaved.getDivision();
        Assert.assertEquals(divSaved, divNew);
        Assert.assertEquals(distSaved, distNew);
    }

    @Test
    @Order(8)
    public void test8_addPhoneNumber() {
//        String userID = "16";
        String userID = getUserID();
        PhoneNumber phoneNumberNew = new PhoneNumber("01910-664020");
        Map<String, String> result = dao_Profile_Basic_I.addPhoneNumber(phoneNumberNew, userID);
        System.out.println("\n\n" + result + "\n\n");
        Assert.assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
        PhoneNumber phoneNumberSaved
                = dao_Profile_Basic_I.findOneByUserIDWithChild(userID)
                .getPhone_number()
                .stream()
                .filter(p -> phoneNumberNew.getNumber().equals(p.getNumber()))
                .findAny()
                .orElse(null);
        System.out.println("\n" + phoneNumberSaved.toString() + "\n");
        Assert.assertEquals(phoneNumberSaved.getNumber(), phoneNumberNew.getNumber());
//        assertEquals(phoneNumberSaved.getNumber(), "01919");
    }

    //dependency : depend on save profile basic, cause without save it will not found any phone number to delete.
    @Test
    @Order(9)
    public void test9_deletePhoneNumber() {
        // Arrange
//        String userID = "16";
        String userID = getUserID();
        try {
            String phoneNumberID = dao_Profile_Basic_I
                    .findOneByUserIDWithChild(userID)
                    .getPhone_number()
                    .get(0)
                    .getId()
                    .toString();
            // Act
            Map<String, String> result = dao_Profile_Basic_I.deletePhoneNumber(phoneNumberID, userID);

            // Assert
            System.out.println("\n\n" + result + "\n\n");
            Assert.assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
            PhoneNumber phoneNumberSaved
                    = dao_Profile_Basic_I.findOneByUserIDWithChild(userID)
                    .getPhone_number()
                    .stream()
                    .filter(p -> phoneNumberID.equals(p.getId().toString()))
                    .findAny()
                    .orElse(null);
            if (phoneNumberSaved == null) {
                Assert.assertEquals(null, phoneNumberSaved);
            } else {
                Assert.assertEquals(StringUtil.OK, StringUtil.FAIL);
            }
        } catch (Exception e) {
            System.out.println("\n\nTest : Error in Getting Phone Number ID!\n\n");
            Assert.assertEquals(StringUtil.OK, StringUtil.FAIL);
        }
    }

    //dependency : Basic Profile Save.
    @Test
    @Order(10)
    public void test10_addEmergencyContact() {
        //arrange
        String profileBasicID = getID();
//        String userID = "";
        String userID = getUserID();
        String emergencyContactID = "";
        ProfileBasic profileBasic = dao_Profile_Basic_I.findOneWithChild(profileBasicID);
        userID = profileBasic.getUserId();

//String name, String phone, String mail, String address, String relation
        EmergencyContact emergencyContact1 = new EmergencyContact(
                "Dr Mahbub Gazi.",
                "01612-174128",
                "mahbub@mail.com",
                "Khulna, Bangladsh",
                "Uncle",
                DateUtil.getDate()
        );
        Map<String, String> result = dao_Profile_Basic_I.addEmergencyContact(emergencyContact1, userID);
        System.out.println("\n\n>>" + result + "\n\n");
        Assert.assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
    }

    //dependency : Basic Profile Save.
    @Test
    @Order(11)
    public void test11_updateEmergencyContact() {

//        Arrange
        String profileBasicID = getID();
//        String userID = "";
        String userID = getUserID();
        String emergencyContactID = "";
        ProfileBasic profileBasic = dao_Profile_Basic_I.findOneWithChild(profileBasicID);
        userID = profileBasic.getUserId();
        emergencyContactID = profileBasic.getEmergency_contact().get(0).getId().toString();

        //unatherised deletion
        EmergencyContact emergencyContact = new EmergencyContact();
        emergencyContact.setId(Long.parseLong(emergencyContactID));
        emergencyContact.setName("Mahbub Gazi");
        emergencyContact.setPhone("07171000");
        emergencyContact.setAddress("Dhaka, Dhaka");
        emergencyContact.setRelation("Mamu");
        emergencyContact.setMail("mail@mail.com");
        Map<String, String> result = dao_Profile_Basic_I.updateEmergencyContact(emergencyContact, userID + 1);

        System.out.println("\n\n>>" + result + "\n\n");
        Assert.assertEquals(StringUtil.FAIL, result.get(StringUtil.STATUS));
        Assert.assertEquals(StringUtil.UNAUTHERIZED, result.get(StringUtil.ERROR));

        //athurised deletion
        result = dao_Profile_Basic_I.updateEmergencyContact(emergencyContact, userID);
        System.out.println("\n\n>>" + result + "\n\n");
        Assert.assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
    }

    //dependency : Basic Profile Save.
    @Test
    @Order(12)
    public void test12_deleteEmergencyContact() {

        //        Arrange
        String profileBasicID = getID();
        String userID = "1";
        String emergencyContactID = "4";
        ProfileBasic profileBasic = dao_Profile_Basic_I.findOneWithChild(profileBasicID);
        userID = profileBasic.getUserId();
        emergencyContactID = profileBasic.getEmergency_contact().get(0).getId().toString();

        //        unathorised deletion
        Map<String, String> result = dao_Profile_Basic_I.deleteEmergencyContact(emergencyContactID, userID + 1);
        System.out.println("\n\n>>" + result + "\n\n");
        Assert.assertEquals(StringUtil.FAIL, result.get(StringUtil.STATUS));
        Assert.assertEquals(StringUtil.UNAUTHERIZED, result.get(StringUtil.ERROR));

        //        atherised deletion
        result = dao_Profile_Basic_I.deleteEmergencyContact(emergencyContactID, userID);
        System.out.println("\n\n>>" + result + "\n\n");
        Assert.assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
    }

    @Test
    @Order(13)
    public void test13_delete() {
        System.out.println("\n\n\n---M3---\n\n\n");
        String id = getID();
//        String id = "744";// to delete one profile, cause delete from PHPAdmin hard, due FK_PK
        Map<String, String> response = new HashMap<>();
        try {
            response = dao_Profile_Basic_I.delete(id);
        } catch (Exception e) {
            System.out.println("Test Delete Fail!");
        }
        System.out.println("\n\n" + "response (test) : " + response.toString() + "\n\n");
        Assert.assertEquals(StringUtil.OK, response.get(StringUtil.STATUS));
    }

    /**
     * to test just save of "ProfileBasic" no address or other fields.
     */
    @Test
    public void testSaveProfileBasic() {
        ProfileBasic profileBasic = new ProfileBasic();
        profileBasic.setName("Khan Ajharul Abedeen");
        profileBasic.setGender("Male");
        profileBasic.setBlood_Group("A+");
        profileBasic.setAvailable("0");
        profileBasic.setMaritalStatus("NO");
        profileBasic.setProfession("Freelance");
        profileBasic.setCare_of("Khan Atiar Rahman.");
        profileBasic.setUserId(Utils.getLoggedUserID());
        Map<String, String> map = dao_Profile_Basic_I.save(profileBasic);
        System.out.println(map);
    }

//    Helpers :

    /**
     * will store the last save id, that can be used for later for other method.
     * Though there is question does it, right to a result from unit test, as
     * main goal of the unit test is to keep the test as much as possible
     * independent.
     *
     * @param profileBasic
     */
    public void storeProfileBasic(String profileBasic) {
        try {
            FileWriter myWriter = new FileWriter("profileBasic.txt");
            myWriter.write(profileBasic);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void storeUserID(String userID) {
        try {
            FileWriter myWriter = new FileWriter("profileBasic_userID.txt");
            myWriter.write(userID);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getUserID() {
        String id = "";
        try {
            File myObj = new File("profileBasic_userID.txt");
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

    public void storeID(String id) {
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
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
            File myObj = new File("filename.txt");
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
