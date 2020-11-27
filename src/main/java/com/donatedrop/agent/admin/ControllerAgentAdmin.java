/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.agent.admin;

import com.donatedrop.agent.admin.model.AgentRequest;
import com.donatedrop.agent.admin.model.RequestApplicantNote;
import com.donatedrop.agent.admin.model.RequestAdminNote;
import com.donatedrop.agent.models.RequestReviewRequest;
import com.donatedrop.agent.donner.models.RequestSearchReview;
import com.donatedrop.agent.admin.model.AgentRequestToReview;
import com.donatedrop.agent.admin.model.RequestPersonalNote;
import java.util.List;
import java.util.Map;

//import com.donatedrop.agent.admin.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author G7
 */
@RestController
@RequestMapping("/public/user/agent/admin")
public class ControllerAgentAdmin {

    @Autowired
    Service_AgentAdmin_I service_Agent_I;

    @PostMapping("saveRequest")
    public Map<String, String> saveRequest(@RequestBody AgentRequest agentRequest) {
        return service_Agent_I.saveRequest(agentRequest);
    }

    @PostMapping("deleteRequest")
    public Map<String, String> deleteRequest(@RequestParam String userID) {
        System.out.println("Hello!");
        return service_Agent_I.deleteRequest(userID);
    }

    @PostMapping("reviewRequest")
    public Map<String, String> reviewRequest(@RequestBody RequestReviewRequest reviewRequest) {
        return service_Agent_I.reviewRequest(reviewRequest);
    }

    @PostMapping("getAgentRequestsToReview")
    public List<AgentRequestToReview> getAgentRequestsToReview(@RequestBody RequestSearchReview requestGetAgentRequestsReview) {
        System.out.println("Hello! getAgentRequestsToReview >>");
        return service_Agent_I.getAgentRequestsToReview(requestGetAgentRequestsReview);
    }

    @PostMapping("getAgentRequestsToReviewCount")
    public Map<String, String> getAgentRequestsToReviewCount(@RequestBody RequestSearchReview requestGetAgentRequests) {
        String column = requestGetAgentRequests.getColumn();
        String key = requestGetAgentRequests.getKey();
        String statusType = requestGetAgentRequests.getStatusType();
        return service_Agent_I.getAgentRequestsToReviewCount(column, key, statusType);
    }

    @PostMapping("updateAdminNote")
    public Map<String, String> updateAdminNote(@RequestBody RequestAdminNote requestAdminNote) {
        return service_Agent_I.updateAdminNote(requestAdminNote);
    }

    @PostMapping("updateApplicantNote")
    public Map<String, String> updateApplicantNote(@RequestBody RequestApplicantNote requestApplicantNote) {
        return service_Agent_I.updateApplicantNote(requestApplicantNote);
    }

    @PostMapping("updatePersonalNote")
    public Map<String, String> updatePersonalNote(@RequestBody RequestPersonalNote requestPersonalNote) {
        return service_Agent_I.updatePersonalNote(requestPersonalNote);
    }

}
