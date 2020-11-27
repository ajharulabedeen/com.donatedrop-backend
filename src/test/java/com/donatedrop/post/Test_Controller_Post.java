/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.post;

import com.donatedrop.post.model.Post;
import com.donatedrop.post.model.MyPostSearch;
import com.donatedrop.agent.admin.model.AgentRequestToReview;
import com.donatedrop.agent.donner.models.RequestSearchReview;
import com.donatedrop.agent.models.StatusType;
import com.donatedrop.geocode.AbstractTest;
import com.donatedrop.models.Address;
import com.donatedrop.other.DumpDao;
import com.donatedrop.other.DumpData;
import com.donatedrop.profile.basic.Dao_Profile_Basic_I;
import com.donatedrop.profile.model.EmergencyContact;
import com.donatedrop.profile.model.PhoneNumber;
import com.donatedrop.profile.model.ProfileBasic;
import com.donatedrop.util.DateUtil;
import com.donatedrop.util.StringUtil;
import com.donatedrop.util.Utils;
import javafx.geometry.Pos;
import org.junit.Assert;
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
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author G7
 */
@SpringBootTest
public class Test_Controller_Post extends AbstractTest {

    @Autowired
    Dao_Post_I dao_post_i;

    @Autowired
    DumpDao dumpDao;

    public Test_Controller_Post() {
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * @throws Exception
     * @apiNote to test that profile basic will be saved, without full
     * information.
     */
    //    String uri = "/public/user/post/save";
    @Test
    @Order(1)
    public void testSave() throws Exception {
        String uri = "/public/user/post/save";

        Random random = new Random();

        Post post = new Post(
                "",
                DumpData.getBloodGroup(),
                dumpDao.getAddressString("PRESENT"),
                DumpData.getHospitalName(),
                DumpData.getHospitalAddress(),
                DumpData.getGender(),
                DumpData.getPatientDescription(),
                "20-10-10",
                DumpData.getDate(),
                DumpData.getQuantity(),
                DumpData.getPhoneNumber(),
                "0",
                DumpData.getNote(),
                "NO",
                "SON",
                DumpData.getNote(),
                "NA",
                DumpData.getNote()
        );
        System.out.println(post);
        String inputJson = super.mapToJson(post);
        System.out.println(inputJson);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        Map<String, String> map = super.mapFromJson(content, Map.class);
        assertEquals(StringUtil.OK, map.get(StringUtil.STATUS));
        if (StringUtil.OK.equals(map.get(StringUtil.STATUS))) {
            storePostID(map.get(StringUtil.ID));
            System.out.println(dao_post_i.findPostWithComments(getPostID()));
        }
    }


    @Test
    @Order(1)
    public void testUpdate() throws Exception {
        String uri = "/public/user/post/updatePost";

        Random random = new Random();

        Post post = new Post(
                "",
                DumpData.getBloodGroup(),
                dumpDao.getAddressString("PRESENT"),
                DumpData.getHospitalName(),
                DumpData.getHospitalAddress(),
                DumpData.getGender(),
                DumpData.getPatientDescription(),
                "20-10-10",
                DumpData.getDate(),
                DumpData.getQuantity(),
                DumpData.getPhoneNumber(),
                "0",
                DumpData.getNote(),
                "NO",
                "Daughter",
                DumpData.getNote(),
                "NA",
                DumpData.getNote()
        );
        post.setPostID(new Long("20618"));
        System.out.println(post);
        String inputJson = super.mapToJson(post);
        System.out.println(inputJson);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        Map<String, String> map = super.mapFromJson(content, Map.class);
        assertEquals(StringUtil.OK, map.get(StringUtil.STATUS));
//        if (StringUtil.OK.equals(map.get(StringUtil.STATUS))) {
//            storePostID(map.get(StringUtil.ID));
//            System.out.println(dao_post_i.findPostWithComments(getPostID()));
//        }
    }


    @Test
    @Order(1)
    public void testDelete() throws Exception {
        String uri = "/public/user/post/deletePost?postID=" + "20618";
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }


    @Test
    @Order(3)
    public void testGetAllPostsByAnUser() throws Exception {
//     arrange
        String uri = "/public/user/post/getAllPostsByAnUser";
        MyPostSearch postSearch = new MyPostSearch();
        postSearch.setDateType("demoData");
        postSearch.setStartDate("demoData");
        postSearch.setEndDate("demoData");
        postSearch.setStart(1);
        postSearch.setMax(10);
        postSearch.setKey("'%%'");
        postSearch.setColumn("location");
//        postSearch.setUserID("14294");
        postSearch.setOrderBy("location");
        postSearch.setOrderType("ASC");

//      act
        String inputJson = super.mapToJson(postSearch);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
//        System.out.println("\n" + content + "\n");
        List<Post> postList = Arrays.asList(super.mapFromJson(content, Post[].class));
        postList.forEach(post -> System.out.println(post.getPostID()));

//        further verification can be done by reading the agent request.
    }

    @Test
    public void testCountAllPostsByAnUser() throws Exception {
        //     arrange
        String uri = "/public/user/post/countAllPostsByAnUser";
        MyPostSearch postSearch = new MyPostSearch();
        postSearch.setDateType("demoData");
        postSearch.setStartDate("demoData");
        postSearch.setEndDate("demoData");
        postSearch.setStart(1);
        postSearch.setMax(10);
        postSearch.setKey("'%%'");
        postSearch.setColumn("location");
//        postSearch.setUserID("14294");
        postSearch.setOrderBy("location");
        postSearch.setOrderType("ASC");

//      act
        String inputJson = super.mapToJson(postSearch);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("\n" + content + "\n");

    }

    @Test
    @Order(3)
    public void testGetAllPostsByAnUserWithinDate() throws Exception {
//     arrange
        String uri = "/public/user/post/getAllPostsByAnUserWithinDate";
        MyPostSearch postSearch = new MyPostSearch();
        postSearch.setDateType("need_date");
        postSearch.setStartDate("2020-01-1");
        postSearch.setEndDate("2020-05-18");
        postSearch.setStart(1);
        postSearch.setMax(10);
        postSearch.setKey("'%%'");
        postSearch.setColumn("location");
//        postSearch.setUserID("14294");
        postSearch.setOrderBy("location");
        postSearch.setOrderType("ASC");

//      act
        String inputJson = super.mapToJson(postSearch);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
//        System.out.println("\n" + content + "\n");
        List<Post> postList = Arrays.asList(super.mapFromJson(content, Post[].class));
        postList.forEach(post -> System.out.println(post.getPostID()));

//        further verification can be done by reading the agent request.
    }

    @Test
    @Order(3)
    public void testCountAllPostsByAnUserWithinDate() throws Exception {
//     arrange
        String uri = "/public/user/post/countAllPostsByAnUserWithinDate";
        MyPostSearch postSearch = new MyPostSearch();
//        postSearch.setDateType("need_date");
        postSearch.setDateType("post_date");
        postSearch.setStartDate("2020-01-1");
        postSearch.setEndDate("2020-0-18");
        postSearch.setStart(1);
        postSearch.setMax(10);
        postSearch.setKey("'%%'");
        postSearch.setColumn("location");
//        postSearch.setUserID("14294");
        postSearch.setOrderBy("location");
        postSearch.setOrderType("ASC");

//      act
        String inputJson = super.mapToJson(postSearch);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("\n" + content + "\n");

//        further verification can be done by reading the agent request.
    }


//    //    String uri = "/public/profile/basic/save";
//    @Test
//    @Order(2)
//    public void testfullSave() throws Exception {
//
//        String uri = "/public/profile/basic/save";
//        // Arrange
//        System.out.println("\nProfile Basic Dao Test!\n");
//        ProfileBasic profileBasic = new ProfileBasic();
//        profileBasic.setName("Khan Ajharul Abedeen");
//
//        Address address_present = new Address("Khulna", "Khulna", "Dumuria", "Rudghora", "Mikshimil East");
////        profileBasic.setAddress_present(address_present);
//        Address address_permanet = new Address("Khulna", "Khulna", "Dumuria", "Rudghora", "Mikshimil East");
////        profileBasic.setAddress_permanent(address_permanet);
//
//        List<EmergencyContact> emergencyContacts = new ArrayList<>();
//        EmergencyContact emergencyContact1 = new EmergencyContact("Mahbub", "01717", "mail@mail.com", "Dumuria, Khulna", "Uncle", DateUtil.getDate());
//        EmergencyContact emergencyContact2 = new EmergencyContact("Prof. Altaf", "01717", "mail@mail.com", "Dumuria, Khulna", "Uncle", DateUtil.getDate());
//        emergencyContacts.add(emergencyContact1);
//        emergencyContacts.add(emergencyContact2);
//        profileBasic.setEmergency_contact(emergencyContacts);
//
//        List<PhoneNumber> phoneNumbers = Arrays.asList(
//                new PhoneNumber("01717034420"),
//                new PhoneNumber("01717034420"),
//                new PhoneNumber("01712034420")
//        );
//        profileBasic.setPhone_number(phoneNumbers);
//        profileBasic.setGender("Male");
//        profileBasic.setBlood_Group("A+");
//        profileBasic.setAvailable("0");
//        profileBasic.setMaritalStatus("NO");
//        profileBasic.setProfession("Freelance");
//        profileBasic.setCare_of("Khan Atiar Rahman.");
//        profileBasic.setUserId(Utils.getLoggedUserID());
//
//        String inputJson = super.mapToJson(profileBasic);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        System.out.println(content);
//        Map<String, String> map = super.mapFromJson(content, Map.class);
//        assertEquals(StringUtil.OK, map.get(StringUtil.STATUS));
//    }


//    //    String uri = "/public/profile/basic/findOneByUser";
//    @Test
//    public void testfindOneByUser() throws Exception {
//        String uri = "/public/profile/basic/findOneByUser";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        System.out.println(content);
//    }


//    //    String uri = "/public/profile/basic/exist";
//    @Test
//    public void testBasicExist() throws Exception {
//        String uri = "/public/profile/basic/exist";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        Map<String, String> result = super.mapFromJson(content, Map.class);
//        System.out.println(content);
//        assertEquals(StringUtil.TRUE, result.get(StringUtil.STATUS));
//    }


//    //    String uri = "/public/profile/basic/update";
//    @Test
//    public void testUpdate() throws Exception {
//        String uri = "/public/profile/basic/update";
//
//        // Arrange
//        String userID = Utils.getLoggedUserID();
//        ProfileBasic profileBasicNew = new ProfileBasic();
//        profileBasicNew.setUserId(userID);
//        profileBasicNew.setName("Khan Ajharul Abedeen");
////        profileBasicNew.setBirthDate(DateUtil.getDate().toString());
//        profileBasicNew.setGender("Male");
//        profileBasicNew.setBlood_Group("A+");
//        profileBasicNew.setAvailable("1");
//        profileBasicNew.setMaritalStatus("NO");
//        profileBasicNew.setProfession("Remote");
//        profileBasicNew.setCare_of("Khan Atiar Rahman");
//        profileBasicNew.setReligion("NoMo NoMo");
//        profileBasicNew.setEmail("dim@dimdim.co");
//
//        String inputJson = super.mapToJson(profileBasicNew);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        Map<String, String> result = super.mapFromJson(content, Map.class);
//        System.out.println(content);
//        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
//        Assert.assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
//        //have to do more verification. by calling the full app.
//        //refactor : stop child loading, need to create new method to get only basics.
//        ProfileBasic profileBasicSaved = dao_Profile_Basic_I.findOneByUser(userID);
//        Assert.assertEquals(profileBasicNew.getName(), profileBasicSaved.getName());
//        Assert.assertEquals(profileBasicNew.getBirthDate(), profileBasicSaved.getBirthDate());
//        Assert.assertEquals(profileBasicNew.getCare_of(), profileBasicSaved.getCare_of());
////        assertEquals("DimDim", profileBasicSaved.getCare_of());//for test the test, :P
//        Assert.assertEquals(profileBasicNew.getGender(), profileBasicSaved.getGender());
//        Assert.assertEquals(profileBasicNew.getMaritalStatus(), profileBasicSaved.getMaritalStatus());
//        Assert.assertEquals(profileBasicNew.getProfession(), profileBasicSaved.getProfession());
//        Assert.assertEquals(profileBasicNew.getBlood_Group(), profileBasicSaved.getBlood_Group());
//        Assert.assertEquals(profileBasicNew.getAvailable(), profileBasicSaved.getAvailable());
//        Assert.assertEquals(profileBasicNew.getReligion(), profileBasicSaved.getReligion());
//        Assert.assertEquals(profileBasicNew.getEmail(), profileBasicSaved.getEmail());
//    }


//    //    String uri = "/public/profile/basic/addPhoneNumber";
//    @Test
//    public void testAddPhoneNumber() throws Exception {
//        String uri = "/public/profile/basic/addPhoneNumber";
//        // Arrange
//        PhoneNumber phoneNumberNew = new PhoneNumber("01910-664020");
//        String inputJson = super.mapToJson(phoneNumberNew);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        Map<String, String> result = super.mapFromJson(content, Map.class);
//        System.out.println(content);
//        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
//    }


//    //    String uri = "/public/profile/basic/deletePhoneNumber";
//    @Test
//    public void testDeletePhoneNumber() throws Exception {
//        String uri = "/public/profile/basic/deletePhoneNumber";
//        // Arrange
//        PhoneNumber phoneNumberNew = new PhoneNumber();
//        phoneNumberNew.setId(Long.parseLong("118"));
//        String inputJson = super.mapToJson(phoneNumberNew);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        Map<String, String> result = super.mapFromJson(content, Map.class);
//        System.out.println(content);
//        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
//    }


//    @Test
////    @Order(6)
//    public void test6_presentAddressUpdate() throws Exception {
//        String uri = "/public/profile/basic/updatePresentAddress";
//
//        String divNew = "Dhaka";
//        String distNew = "Dhaka";
//        String upzNew = "Gazipur";
//        String unionNew = "Kapasia";
//        String streetAddressNew = "Road No 10";
//
//        Address addressPresentNew = new Address();
//        addressPresentNew.setDivision(divNew);
//        addressPresentNew.setDistrict(distNew);
//        addressPresentNew.setUpzilla(upzNew);
//        addressPresentNew.setUnion_ward(unionNew);
//        addressPresentNew.setStreet_address(streetAddressNew);
//
//        String inputJson = super.mapToJson(addressPresentNew);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        Map<String, String> result = super.mapFromJson(content, Map.class);
//        System.out.println(content);
//        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
//
////        Map<String, String> result = dao_Profile_Basic_I.updatePresentAddress(addressPresentNew, userID);
////        System.out.println("\n\n" + result + "\n\n");
////        Assert.assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
////
////        Address addressPresentSaved = dao_Profile_Basic_I.findOneByUser(userID).getAddress_present();
////        String divSaved = addressPresentSaved.getDistrict();
////        String distSaved = addressPresentSaved.getDivision();
////        Assert.assertEquals(divSaved, divNew);
////        Assert.assertEquals(distSaved, distNew);
//    }


//    @Test
////    @Order(6)
//    public void test7_updatePermanentAddress() throws Exception {
//        String uri = "/public/profile/basic/updatePermanentAddress";
//
//        String divNew = "Dhaka";
//        String distNew = "Dhaka";
//        String upzNew = "Gazipur";
//        String unionNew = "Kapasia";
//        String streetAddressNew = "Road No 101";
//
//        Address addressPermanentNew = new Address();
//        addressPermanentNew.setDivision(divNew);
//        addressPermanentNew.setDistrict(distNew);
//        addressPermanentNew.setUpzilla(upzNew);
//        addressPermanentNew.setUnion_ward(unionNew);
//        addressPermanentNew.setStreet_address(streetAddressNew);
//
//        String inputJson = super.mapToJson(addressPermanentNew);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        Map<String, String> result = super.mapFromJson(content, Map.class);
//        System.out.println(content);
//        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
//
////        Map<String, String> result = dao_Profile_Basic_I.updatePresentAddress(addressPresentNew, userID);
////        System.out.println("\n\n" + result + "\n\n");
////        Assert.assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
////
////active
////        Address addressPresentSaved = dao_Profile_Basic_I.findOneByUser("16").getAddress_permanent();
//        Address addressPresentSaved = new Address();
//        String divSaved = addressPresentSaved.getDistrict();
//        String distSaved = addressPresentSaved.getDivision();
//        Assert.assertEquals(divSaved, divNew);
//        Assert.assertEquals(distSaved, distNew);
//    }


//    @Test
////    @Order(10)
//    public void test10_addEmergencyContact() throws Exception {
//        String uri = "/public/profile/basic/addEmergencyContact";
//        //String name, String phone, String mail, String address, String relation
//        EmergencyContact emergencyContact1 = new EmergencyContact(
//                "KKK Bir",
//                "01612-174128",
//                "mahbub@mail.com",
//                "Khulna, Bangladsh",
//                "Uncle",
//                DateUtil.getDate()
//        );
//
//        String inputJson = super.mapToJson(emergencyContact1);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        Map<String, String> result = super.mapFromJson(content, Map.class);
//        System.out.println(content);
//        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
//
//    }


//    @Test
//    @Order(12)
//    public void test12_deleteEmergencyContact() throws Exception {
//        String emergencyContactID = "200";
//        String uri = "/public/profile/basic/deleteEmergencyContact?emergencyContactID=" + emergencyContactID;
//
////        String inputJson = super.mapToJson("");
//        MvcResult mvcResult = mvc.perform(
//                MockMvcRequestBuilders.post(uri)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
////        MvcResult mvcResult = mvc.perform(
////                MockMvcRequestBuilders.post(uri)
////                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        Map<String, String> result = super.mapFromJson(content, Map.class);
//        System.out.println(content);
//        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
//    }


//    @Test
//    @Order(11)
//    public void test11_updateEmergencyContact() throws Exception {
//////        Arrange
////        String profileBasicID = getID();
////        String userID = "";
////        String emergencyContactID = "";
////        ProfileBasic profileBasic = dao_Profile_Basic_I.findOneWithChild(profileBasicID);
////        userID = profileBasic.getUserId();
////        emergencyContactID = profileBasic.getEmergency_contact().get(0).getId().toString();
//
//        //        unatherised deletion
//        EmergencyContact emergencyContact = new EmergencyContact();
//        emergencyContact.setId(Long.parseLong("203"));
//        emergencyContact.setName("Mahbub Gazi");
//        emergencyContact.setPhone("07171000");
//        emergencyContact.setAddress("Dhaka, Dhaka");
//        emergencyContact.setRelation("Mamu");
//        emergencyContact.setMail("mail@mail.com");
//
//        String emergencyContactID = "203";
//        String uri = "/public/profile/basic/updateEmergencyContact";
////        String inputJson = super.mapToJson(emergencyContact);
////        MvcResult mvcResult = mvc.perform(
////                MockMvcRequestBuilders.post(uri)
////                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//        String inputJson = super.mapToJson(emergencyContact);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////        MvcResult mvcResult = mvc.perform(
////                MockMvcRequestBuilders.post(uri)
////                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        Map<String, String> result = super.mapFromJson(content, Map.class);
//        System.out.println(content);
//        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
//    }


    public void storePostID(String id) {
        try {
            FileWriter myWriter = new FileWriter("controller_post_ID.txt");
            myWriter.write(id);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getPostID() {
        String id = "";
        try {
            File myObj = new File("controller_post_ID.txt");
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
