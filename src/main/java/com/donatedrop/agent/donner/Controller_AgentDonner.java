/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.agent.donner;

import com.donatedrop.agent.donner.models.DonnerRequestToAgent;
import com.donatedrop.agent.donner.models.DonnerToAgentRequestToReview;
import com.donatedrop.agent.donner.models.RequestSearchReview;
import com.donatedrop.agent.models.RequestNote;
import com.donatedrop.agent.models.RequestReviewRequest;

import java.util.List;
import java.util.Map;

import com.donatedrop.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author G7
 */
@RestController
@RequestMapping("/public/user/agent/donner")
public class Controller_AgentDonner {

    @Autowired
    Service_AgentDonner_I service_AgentDonner_I;

    @PostMapping("saveRequest")
    public Map<String, String> saveRequest(@RequestBody DonnerRequestToAgent donnerRequestToAgent) {
        return service_AgentDonner_I.saveRequest(donnerRequestToAgent);
    }

    @PostMapping("findOneRequestById")
    public DonnerRequestToAgent findOneRequestById(@RequestParam String donnerAgentRequestID) {
        return service_AgentDonner_I.findOneRequestById(donnerAgentRequestID);
    }

    @PostMapping("findOneRequestUserID")
    public DonnerRequestToAgent findOneRequestUserID(@RequestParam String userID) {
        return service_AgentDonner_I.findOneRequestUserID(userID);
    }

    //refactor : have to implement later
//    @PostMapping("deleteRequestByUserID")
//    public Map<String, String> deleteRequestByUserID(@RequestParam String userID) {
//        return service_AgentDonner_I.deleteRequestByUserID(userID);
//    }
    @PostMapping("reviewDonnerRequest")
    public Map<String, String> reviewDonnerRequest(@RequestBody RequestReviewRequest reviewRequest) {
        return service_AgentDonner_I.reviewDonnerRequest(reviewRequest);
    }

    @PostMapping("getDonnerToAgentRequestToReview")
    public List<DonnerToAgentRequestToReview> getDonnerToAgentRequestToReview(@RequestBody RequestSearchReview requestSearchReview) {
        requestSearchReview.setUserIdAgent(Utils.getLoggedUserID());
        return service_AgentDonner_I.getDonnerToAgentRequestToReview(requestSearchReview);
    }

    @PostMapping("getDonnerToAgentRequestReviewCount")
    public Map<String, String> getDonnerToAgentRequestReviewCount(@RequestBody RequestSearchReview requestSearchReview) {
        requestSearchReview.setUserIdAgent(Utils.getLoggedUserID());
        return service_AgentDonner_I.getDonnerToAgentRequestToReviewCount(requestSearchReview);
    }

    @PostMapping("updateAgentNote")
    public Map<String, String> updateAgentNote(@RequestBody RequestNote requestNote) {
        return service_AgentDonner_I.updateAgentNote(requestNote);
    }

    @PostMapping("updateNoteDonner")
    Map<String, String> updateNoteDonner(@RequestBody RequestNote requestNote) {
        return service_AgentDonner_I.updateNoteDonner(requestNote);
    }

    @PostMapping("updateNoteAgentPersonal")
    public Map<String, String> updateNoteAgentPersonal(@RequestBody RequestNote requestNote) {
        return service_AgentDonner_I.updateNoteAgentPersonal(requestNote);
    }

}
