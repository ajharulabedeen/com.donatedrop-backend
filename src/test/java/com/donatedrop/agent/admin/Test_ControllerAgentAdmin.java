/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.agent.admin;

import com.donatedrop.agent.donner.models.RequestSearchReview;
import com.donatedrop.agent.admin.model.RequestApplicantNote;
import com.donatedrop.agent.admin.model.AgentRequest;
import com.donatedrop.agent.admin.model.RequestPersonalNote;
import com.donatedrop.agent.models.RequestReviewRequest;
import com.donatedrop.agent.admin.model.RequestAdminNote;
import com.donatedrop.agent.admin.model.AgentRequestToReview;
import com.donatedrop.agent.models.StatusType;
import com.donatedrop.history.*;
import com.donatedrop.geocode.AbstractTest;
import com.donatedrop.other.DumpDao;
import com.donatedrop.other.TestUtil;
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
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author G7
 */
@SpringBootTest
public class Test_ControllerAgentAdmin extends AbstractTest {

    @Autowired
    DumpDao dumpDao;

    @Autowired
    Dao_AgentAdmin_I dao_agent_i;

    @Autowired
    Dao_History_I dao_history_i;

    public Test_ControllerAgentAdmin() {
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    //    String uri = "/public/user/agent/admin/saveRequest";
    @Test
    @Order(1)
    public void testSave() throws Exception {
        //      arrange
        String uri = "/public/user/agent/admin/saveRequest";
        System.out.println("\nAgent Request Save\n");
        BigInteger userIDBigInteger = dumpDao.getNotRequestedAsAgentUsers(0, 5).get(0);
        Integer userID = ((BigInteger) userIDBigInteger).intValue();
        System.out.println("userID : " + userID);
        AgentRequest agentRequest = new AgentRequest();
        agentRequest.setUserID(userID.toString());
        agentRequest.setRequestDate(DateUtil.getDate().toString());
        agentRequest.setStatus("0");
        agentRequest.setNoteApplicant("Test Note!");

        String inputJson = super.mapToJson(agentRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
//      act
        System.out.println(content);

//      assert
        Map<String, String> map = super.mapFromJson(content, Map.class);
        assertEquals(StringUtil.OK, map.get(StringUtil.STATUS));
        if (map.get(StringUtil.STATUS).equals(StringUtil.OK)) {
            storeID(userID.toString());
        }
    }

    //        String uri = "/public/user/agent/admin/deleteRequest?userID=" + userID;
    @Test
    @Order(-2)
    public void testDelete() throws Exception {
        String userID = getID();
        String uri = "/public/user/agent/admin/deleteRequest?userID=" + userID;
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Map<String, String> map = super.mapFromJson(content, Map.class);
        System.out.println("\nAgent Request: \n" + map + "\n");
        assertEquals(StringUtil.OK, map.get(StringUtil.STATUS));
    }

    //        String uri = "/public/user/agent/admin/reviewRequest";
    @Test
    @Order(3)
    public void testReviewRequestWithRigthtValue() throws Exception {
        //     arrange
        String requestID = dumpDao.getAgentAdminRequests(0, 5).get(0).getId().toString();
        System.out.println("requestID : " + requestID);
        String value = StatusType.FREEZE;
        String uri = "/public/user/agent/admin/reviewRequest";
        RequestReviewRequest reviewRequest = new RequestReviewRequest(requestID, value);
//      act
        String inputJson = super.mapToJson(reviewRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Map<String, String> map = super.mapFromJson(content, Map.class);
        System.out.println("\nAgent Request Review: \n" + map + "\n");
        assertEquals(StringUtil.OK, map.get(StringUtil.STATUS));
//        further verification can be done by reading the agent request.
        AgentRequest agentRequest = dao_agent_i.getOneAgentRequest(requestID);
        System.out.println("\nRequest Status : \n" + agentRequest.getStatus());
        assertEquals(agentRequest.getStatus(), value);
    }

    @Test
    @Order(4)
    public void testReviewRequestWithWrongValue() throws Exception {
//     arrange
        String requestID = dumpDao.getAgentAdminRequests(0, 5).get(0).getId().toString();
        System.out.println("requestID : " + requestID);
        String valueWrong = "StatusType";
        String uri = "/public/user/agent/admin/reviewRequest";
        RequestReviewRequest reviewRequest = new RequestReviewRequest(requestID, valueWrong);
//      act
        String inputJsonWrong = super.mapToJson(reviewRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJsonWrong)).andReturn();
//assert
        int statusWrong = mvcResult.getResponse().getStatus();
        assertEquals(200, statusWrong);
        String contentWrong = mvcResult.getResponse().getContentAsString();
        Map<String, String> map = super.mapFromJson(contentWrong, Map.class);

        System.out.println("\nAgent Request Review(Wrong): \n" + map + "\n");
        assertEquals(StringUtil.FAIL, map.get(StringUtil.STATUS));

        //        further verification can be done by reading the agent request.
        AgentRequest agentRequest = dao_agent_i.getOneAgentRequest(requestID);
        System.out.println("\nRequest Status : \n" + agentRequest.getStatus());
        assertNotEquals(agentRequest.getStatus(), valueWrong);

    }

    //        String uri = "/public/user/agent/admin/getAgentRequestsToReview";
    @Test
    @Order(3)
    public void testGet_ACCEPT_AgentRequestsToReview() throws Exception {
//     arrange
        String uri = "/public/user/agent/admin/getAgentRequestsToReview";
        RequestSearchReview requestGetAgentRequests
                = new RequestSearchReview(0, 30, "phonenumber", "%1%", "0");
//                = new RequestSearchReview(0, 30, "phonenumber", "%1%", "ACCEPT");
//                = new RequestSearchReview(0, 30, "username", "%1%", "ACCEPT");
//                = new RequestSearchReview(0, 30, "name", "%kh%", StatusType.ACCEPT);
//      act
        String inputJson = super.mapToJson(requestGetAgentRequests);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        List<AgentRequestToReview> agentRequestListToReviews = Arrays.asList(super.mapFromJson(content, AgentRequestToReview[].class));
        agentRequestListToReviews.forEach(agentRequestToReview -> System.out.println(agentRequestToReview));

//        further verification can be done by reading the agent request.
    }

    @Test
    @Order(3)
    public void testGet_REJECT_AgentRequestsToReview() throws Exception {
//     arrange
        String uri = "/public/user/agent/admin/getAgentRequestsToReview";
        RequestSearchReview requestGetAgentRequests
//                = new RequestSearchReview(0, 30, "username", "%1%", "ACCEPT");
                = new RequestSearchReview(0, 30, "name", "%%", StatusType.REJECT);
//      act
        String inputJson = super.mapToJson(requestGetAgentRequests);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
//        System.out.println("\n" + content + "\n");
        List<AgentRequestToReview> agentRequestListToReviews = Arrays.asList(super.mapFromJson(content, AgentRequestToReview[].class));
        agentRequestListToReviews.forEach(agentRequestToReview -> System.out.println(agentRequestToReview));

//        further verification can be done by reading the agent request.
    }

    //        String uri = "/public/user/agent/admin/getAgentRequestsToReviewCount";
    @Test
    @Order(4)
    public void testGetAgentRequestsToReviewCount() throws Exception {
//     arrange
        String uri = "/public/user/agent/admin/getAgentRequestsToReviewCount";
        RequestSearchReview requestGetAgentRequests
                = new RequestSearchReview(0, 30, "username", "%%", StatusType.ACCEPT);
//                = new RequestSearchReview(0, 30, "phonenumber", "%%", StatusType.ACCEPT);
//      act
        String inputJson = super.mapToJson(requestGetAgentRequests);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Map<String, String> result = super.mapFromJson(content, Map.class);
        System.out.println("\n" + result + "\n");

//        further verification can be done by reading the agent request.
    }

    //    http://localhost:8080/public/user/agent/admin/updateAdminNote
//    @org.junit.jupiter.api.Test
    @Test
    public void testUpdateAdminNote() throws Exception {
//     arrange
        String uri = "/public/user/agent/admin/updateAdminNote";
        String requestID = dao_agent_i.getAgentRequests(0, 10).get(0).getId().toString();
        String note = "Controller : ADMIN NOTE : Please provide additional Documents!";
        System.out.println(requestID);
        RequestAdminNote adminNote = new RequestAdminNote(requestID, note);
//      act
        String inputJson = super.mapToJson(adminNote);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Map<String, String> result = super.mapFromJson(content, Map.class);
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
        assertEquals(note, dao_agent_i.getOneAgentRequest(requestID).getNoteAdmin());
    }

    //    http://localhost:8080/public/user/agent/admin/updateApplicantNote
    @Test
    public void testUpdateApplicantNote() throws Exception {
//     arrange
        String uri = "/public/user/agent/admin/updateApplicantNote";
        String requestID = dao_agent_i.getAgentRequests(0, 10).get(0).getId().toString();
        String note = "Controller : updateApplicantNote NOTE : Please provide additional Documents!";
        System.out.println(requestID);
        RequestApplicantNote applicantNote = new RequestApplicantNote(requestID, note);
//      act
        String inputJson = super.mapToJson(applicantNote);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Map<String, String> result = super.mapFromJson(content, Map.class);
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
        assertEquals(note, dao_agent_i.getOneAgentRequest(requestID).getNoteApplicant());
    }

    //    http://localhost:8080/public/user/agent/admin/updatePersonalNote
    @Test
    public void testupdatePersonalNote() throws Exception {
//     arrange
        String uri = "/public/user/agent/admin/updatePersonalNote";
        String requestID = dao_agent_i.getAgentRequests(0, 10).get(0).getId().toString();
        String note = "Controller : update Personal NOTE : Please provide additional Documents!";
        System.out.println(requestID);
        RequestPersonalNote personalNote = new RequestPersonalNote(requestID, note);
//      act
        String inputJson = super.mapToJson(personalNote);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Map<String, String> result = super.mapFromJson(content, Map.class);
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
        assertEquals(note, dao_agent_i.getOneAgentRequest(requestID).getNotePersonal());
    }


//    Helpers --------------------------------

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
            FileWriter myWriter = new FileWriter("history_controller.txt");
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
            File myObj = new File("history_controller.txt");
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
