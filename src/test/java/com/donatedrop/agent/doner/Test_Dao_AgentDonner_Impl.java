/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.agent.doner;

import com.donatedrop.agent.admin.model.AgentRequest;
import com.donatedrop.agent.donner.Dao_AgentDonner_I;
import com.donatedrop.agent.donner.models.DonnerRequestToAgent;
import com.donatedrop.agent.donner.models.RequestSearchReview;
import com.donatedrop.agent.models.RequestNote;
import com.donatedrop.agent.models.RequestReviewRequest;
import com.donatedrop.agent.models.StatusType;
import com.donatedrop.other.DumpDao;
import com.donatedrop.other.DumpData;
import com.donatedrop.util.DateUtil;
import com.donatedrop.util.StringUtil;
import org.hibernate.event.internal.DefaultPersistOnFlushEventListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author G7
 */
@SpringBootTest
public class Test_Dao_AgentDonner_Impl {

//    11196

    @Autowired
    DumpDao dumpDao;

    @Autowired
    Dao_AgentDonner_I dao_agentDonner_i;

    @Test
    @Order(0)
    public void testLayerWiring() {
        System.out.println("\n" + dao_agentDonner_i + "\n");
    }

    @Test
    @Order(1)
    public void testSaveRequestDonnerToAgent() {
        String userID = dumpDao.getNotRequestedDonnerToAgentUsers(0, 5).get(0).toString();
        DonnerRequestToAgent donnerRequestToAgent = new DonnerRequestToAgent();
        donnerRequestToAgent.setUserIdDonner(userID);
        donnerRequestToAgent.setNoteDonner("I am from your home town!");
        Map<String, String> result = dao_agentDonner_i.saveRequest(donnerRequestToAgent);
        System.out.println("\n" + result + "\n");
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
    }

    //refactor : have to set another user ID.
    @Test
    @Order(1)
    public void testFindOne() {
        String requestID = "23001";
        DonnerRequestToAgent donnerRequestToAgentSaved = dao_agentDonner_i.findOneRequestById(requestID);
        System.out.println("\n" + donnerRequestToAgentSaved.toString() + "\n");
        assertNotNull(donnerRequestToAgentSaved);
    }

    // refactor : automatic user id
    @Test
    @Order(1)
    public void testFindOneRequestUserID() {
        String userID = "11148";
        DonnerRequestToAgent donnerRequestToAgentSaved
                = dao_agentDonner_i.findOneRequestUserID(userID);
        System.out.println("\n" + donnerRequestToAgentSaved.toString() + "\n");
        assertNotNull(donnerRequestToAgentSaved);
    }

    // refactor : automatic user id
    @Test
    @Order(1)
    public void testDeleteRequestByUserID() {
        String userID = "11148";
        Map<String, String> result
                = dao_agentDonner_i.deleteRequestByUserID(userID);
        System.out.println("\n" + result.toString() + "\n");
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
    }

    @Test
    @Order(2)
    public void testReviewDonnerRequest() {
//        String id = dumpDao.getUsers(0, 10).get(0).getId().toString();
        int max = 5;
        String requestID = dumpDao.getAgentDonnersRequests(0, 5).get(1).getId().toString();
        System.out.println("requestID : " + requestID);
        String reviewValue = StatusType.ACCEPT;
        RequestReviewRequest reviewRequest = new RequestReviewRequest(requestID, reviewValue);

        Map<String, String> result = dao_agentDonner_i.reviewDonnerRequest(reviewRequest);
        System.out.println("\nResult : \n" + result);
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
        DonnerRequestToAgent donnerRequestToAgent = dao_agentDonner_i.findOneRequestById(requestID);
        System.out.println("\nRequest Status : \n" + donnerRequestToAgent.getStatus());
        assertEquals(donnerRequestToAgent.getStatus(), reviewValue);
    }


    @Test
    @Order(2)
    public void testGetDonnerToAgentRequestToReview() {
        RequestSearchReview requestSearchReview =
//                new RequestSearchReview(0, 5, "phonenumber", "%%", StatusType.ZERO);
                new RequestSearchReview(0, 5, "phonenumber", "%013%", StatusType.ZERO);//2
//                new RequestSearchReview(0, 20, "email", "%%", StatusType.ZERO);
        requestSearchReview.setUserIdAgent("11196");
        dao_agentDonner_i.getDonnerToAgentRequestToReview(requestSearchReview).forEach(donnerToAgentRequestReview -> {
            System.out.println(donnerToAgentRequestReview.toString());
        });
    }

    @Test
    @Order(3)
    public void testGetDonnerToAgentRequestToReviewCount() {
        RequestSearchReview requestSearchReview =
                new RequestSearchReview(0, 5, "phonenumber", "%013%", StatusType.ZERO);
        requestSearchReview.setUserIdAgent("11196");
        Map<String, String> result = dao_agentDonner_i.getDonnerToAgentRequestReviewCount(requestSearchReview);
        System.out.println(result);
    }

    @Test
    @Order(4)
    public void testUpdateNoteAgent() {
        int max = 5;
        String note = "Please tell me about ur UnionName-Bap-Dadar nam!";
        String requestID = dumpDao.getAgentDonnersRequests(0, 5).get(1).getId().toString();
        System.out.println("requestID : " + requestID);
        RequestNote requestNote = new RequestNote(requestID, note);

        Map<String, String> result = dao_agentDonner_i.updateAgentNote(requestNote);
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));

        String requestNoteFormDB = dao_agentDonner_i.findOneRequestById(requestID).getNoteAgent();
        System.out.println("\n" + requestNoteFormDB + "\n");
        assertEquals(requestNoteFormDB, note);
    }

    @Test
    @Order(5)
    public void testUpdateNoteDonner() {
        int max = 5;
        String note = "I am from ur home village!";
        String requestID = dumpDao.getAgentDonnersRequests(0, 5).get(1).getId().toString();
        System.out.println("requestID : " + requestID);
        RequestNote requestNote = new RequestNote(requestID, note);

        Map<String, String> result = dao_agentDonner_i.updateNoteDonner(requestNote);
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));

        String requestNoteFormDB = dao_agentDonner_i.findOneRequestById(requestID).getNoteDonner();
        System.out.println("\n" + requestNoteFormDB + "\n");
        assertEquals(requestNoteFormDB, note);
    }

    @Test
    @Order(6)
    public void testUpdateNoteAgentPersonal() {
        int max = 5;
        String note = "i will approve, after meeting with him!";
        String requestID = dumpDao.getAgentDonnersRequests(0, 5).get(1).getId().toString();
        System.out.println("requestID : " + requestID);
        RequestNote requestNote = new RequestNote(requestID, note);

        Map<String, String> result = dao_agentDonner_i.updateNoteAgentPersonal(requestNote);
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));

        String requestNoteFormDB = dao_agentDonner_i.findOneRequestById(requestID).getNoteAgentPersonal();
        System.out.println("\n" + requestNoteFormDB + "\n");
        assertEquals(requestNoteFormDB, note);
    }

    //    insert
//    @Test
    public void testInsertMultipleDonnerToAgentRequest() {
        String userID = dumpDao.getNotRequestedDonnerToAgentUsers(0, 5).get(0).toString();

        dumpDao.getNotRequestedDonnerToAgentUsers(0, 100).forEach(s -> {
            DonnerRequestToAgent donnerRequestToAgent = new DonnerRequestToAgent();
            donnerRequestToAgent.setUserIdDonner(s.toString());
            donnerRequestToAgent.setNoteDonner(DumpData.getNote());
            Map<String, String> result = dao_agentDonner_i.saveRequest(donnerRequestToAgent);
            System.out.println("\n" + result + "\n");
            assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
        });

    }

    @Test
    public void test_SetAcceptedAgentUserIDtoDonnerRequest() {
        dumpDao.updateDonnerToAgentRequest();
    }
}// class
