package com.donatedrop.agent.doner;

import com.donatedrop.agent.admin.model.AgentRequest;
import com.donatedrop.agent.admin.model.AgentRequestToReview;
import com.donatedrop.agent.donner.Dao_AgentDonner_I;
import com.donatedrop.agent.donner.models.DonnerRequestToAgent;
import com.donatedrop.agent.donner.models.DonnerToAgentRequestToReview;
import com.donatedrop.agent.donner.models.RequestSearchReview;
import com.donatedrop.agent.models.RequestNote;
import com.donatedrop.agent.models.RequestReviewRequest;
import com.donatedrop.agent.models.StatusType;
import com.donatedrop.geocode.AbstractTest;
import com.donatedrop.other.DumpDao;
import com.donatedrop.util.DateUtil;
import com.donatedrop.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Order;
import org.junit.Test;
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
public class Test_Controller_AgentDonner extends AbstractTest {

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
    //"/public/user/agent/donner/saveRequest";
    public void testSveRequestDonnerToAgent() throws Exception {
        String uri = "/public/user/agent/donner/saveRequest";
        System.out.println("\nAgent Donner Request Save\n");
        BigInteger userIDBigInteger = dumpDao.getNotRequestedDonnerToAgentUsers(0, 5).get(0);
        Integer userID = ((BigInteger) userIDBigInteger).intValue();
        System.out.println("userID : " + userID);

        DonnerRequestToAgent donnerRequestToAgent = new DonnerRequestToAgent();
        donnerRequestToAgent.setUserIdDonner(userID.toString());
        donnerRequestToAgent.setRequestDate(DateUtil.getDate().toString());
        donnerRequestToAgent.setStatus("0");
        donnerRequestToAgent.setNoteDonner("I am from ur university!");

        String inputJson = super.mapToJson(donnerRequestToAgent);
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

    @Test
    @Order(1)
    //"/public/user/agent/donner/findOneRequestById";
    // RequestParam String donnerAgentRequestID
    public void testFindOneRequestById() throws Exception {
        String donnerAgentRequestID = dumpDao.getAgentDonnersRequests(0, 5).get(0).getId().toString();
        String uri = "/public/user/agent/donner/findOneRequestById?donnerAgentRequestID=" + donnerAgentRequestID;

        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        DonnerRequestToAgent donnerRequestToAgent = super.mapFromJson(content, DonnerRequestToAgent.class);
        System.out.println("\n" + donnerRequestToAgent + "\n");
        assertNotNull(donnerRequestToAgent);
    }

    @Test
    @Order(1)
    //"/public/user/agent/donner/findOneRequestUserID";
    //@RequestParam String userID
    public void testFindOneRequestUserID() throws Exception {
        String userID = dumpDao.getAgentDonnersRequests(0, 5).get(0).getUserIdDonner().toString();
        String uri = "/public/user/agent/donner/findOneRequestUserID?userID=" + userID;

        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        DonnerRequestToAgent donnerRequestToAgent = super.mapFromJson(content, DonnerRequestToAgent.class);
        System.out.println("\n" + donnerRequestToAgent + "\n");
        assertNotNull(donnerRequestToAgent);
    }

    @Test
    @Order(1)
    //"/public/user/agent/donner/deleteRequestByUserID";
    //refactor : have to implement later
    public void testDeleteRequestByUserID() {
        String url = "/public/user/agent/donner/deleteRequestByUserID";
    }

    @Test
    @Order(1)
    //"/public/user/agent/donner/reviewDonnerRequest";
    public void testReviewDonnerRequestRightValue() throws Exception {
        String uri = "/public/user/agent/donner/reviewDonnerRequest";
        int max = 5;
        String requestID = dumpDao.getAgentDonnersRequests(0, 5).get(1).getId().toString();
        System.out.println("requestID : " + requestID);
        String reviewValue = StatusType.REJECT;
        RequestReviewRequest reviewRequest = new RequestReviewRequest(requestID, reviewValue);

        String inputJson = super.mapToJson(reviewRequest);
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

        String savedStatus = dao_agentDonner_i.findOneRequestById(requestID).getStatus();
        assertEquals(reviewValue, savedStatus);
    }

    @Test
    @Order(1)
    //"/public/user/agent/donner/reviewDonnerRequest";
    public void testReviewDonnerRequestWrongValue() throws Exception {
        String uri = "/public/user/agent/donner/reviewDonnerRequest";
        int max = 5;
        String requestID = dumpDao.getAgentDonnersRequests(0, 5).get(1).getId().toString();
        System.out.println("requestID : " + requestID);
        String reviewValue = "--";
        RequestReviewRequest reviewRequest = new RequestReviewRequest(requestID, reviewValue);

        String inputJson = super.mapToJson(reviewRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
//      act
        System.out.println(content);

//      assert
        Map<String, String> map = super.mapFromJson(content, Map.class);
        assertEquals(StringUtil.FAIL, map.get(StringUtil.STATUS));
    }

    @Test
    @Order(1)
    //"/public/user/agent/donner/getDonnerToAgentRequestToReview";
    public void testGetDonnerToAgentRequestToReview() throws Exception {
        String uri = "/public/user/agent/donner/getDonnerToAgentRequestToReview";
        RequestSearchReview requestSearchReview =
                new RequestSearchReview(0, 5, "username", "%%", StatusType.ZERO);

        //      act
        String inputJson = super.mapToJson(requestSearchReview);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
//        System.out.println("\n" + content + "\n");
        List<DonnerToAgentRequestToReview> agentRequestListToReviews = Arrays.asList(super.mapFromJson(content, DonnerToAgentRequestToReview[].class));
        agentRequestListToReviews.forEach(donnerToAgentRequestReview -> System.out.println(donnerToAgentRequestReview));
        assertTrue(agentRequestListToReviews.size() >= 0);

    }

    @Test
    @Order(1)
    //"/public/user/agent/donner/getDonnerToAgentRequestReviewCount";
    public void testGetDonnerToAgentRequestReviewCount() throws Exception {
        String uri = "/public/user/agent/donner/getDonnerToAgentRequestReviewCount";
        RequestSearchReview requestSearchReview =
                new RequestSearchReview(0, 5, "username", "%%", StatusType.ZERO);
        //      act
        String inputJson = super.mapToJson(requestSearchReview);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        //assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        //System.out.println("\n" + content + "\n");
        Map<String, String> result = super.mapFromJson(content, Map.class);
        System.out.println("\n" + result + "\n");
        assertTrue(Integer.parseInt(result.get(StringUtil.COUNT).toString()) >= 0);
    }


    @Test
    @Order(1)
    //"/public/user/agent/donner/updateAgentNote";
    public void testUpdateAgentNote() throws Exception {
        String uri = "/public/user/agent/donner/updateAgentNote";
        int max = 5;
        String note = "Please tell me about ur UnionName-SchooL Name Etc nam!";
        String requestID = dumpDao.getAgentDonnersRequests(0, 5).get(1).getId().toString();
        System.out.println("requestID : " + requestID);
        RequestNote requestNote = new RequestNote(requestID, note);

        //      act
        String inputJson = super.mapToJson(requestNote);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        //assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        //System.out.println("\n" + content + "\n");
        Map<String, String> result = super.mapFromJson(content, Map.class);
        System.out.println("\n" + result + "\n");
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));

        String requestNoteFormDB = dao_agentDonner_i.findOneRequestById(requestID).getNoteAgent();
        System.out.println("\n" + requestNoteFormDB + "\n");
        assertEquals(requestNoteFormDB, note);
    }

    @Test
    @Order(1)
    //"/public/user/agent/donner/updateNoteDonner";
    public void testUpdateNoteDonner() throws Exception {
        String uri = "/public/user/agent/donner/updateNoteDonner";
        int max = 5;
        String note = "I am from ur home village and school!";
        String requestID = dumpDao.getAgentDonnersRequests(0, 5).get(1).getId().toString();
        System.out.println("requestID : " + requestID);
        RequestNote requestNote = new RequestNote(requestID, note);

        //      act
        String inputJson = super.mapToJson(requestNote);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        //assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        Map<String, String> result = dao_agentDonner_i.updateNoteDonner(requestNote);
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));

        String requestNoteFormDB = dao_agentDonner_i.findOneRequestById(requestID).getNoteDonner();
        System.out.println("\n" + requestNoteFormDB + "\n");
        assertEquals(requestNoteFormDB, note);
    }

    @Test
    @Order(1)
//    //"/public/user/agent/donner/updateNoteAgentPersonal";
    public void testUpdateNoteAgentPersonal() throws Exception {
        String uri = "/public/user/agent/donner/updateNoteAgentPersonal";
        int max = 5;
        String note = "i will approve, after meeting with him!, telling from my school but seems false info.";
        String requestID = dumpDao.getAgentDonnersRequests(0, 5).get(1).getId().toString();
        System.out.println("requestID : " + requestID);
        RequestNote requestNote = new RequestNote(requestID, note);

        //      act
        String inputJson = super.mapToJson(requestNote);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //assert
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        Map<String, String> result = dao_agentDonner_i.updateNoteAgentPersonal(requestNote);
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));

        String requestNoteFormDB = dao_agentDonner_i.findOneRequestById(requestID).getNoteAgentPersonal();
        System.out.println("\n" + requestNoteFormDB + "\n");
        assertEquals(requestNoteFormDB, note);
    }

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
