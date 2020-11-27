package com.donatedrop.agent.donner;

import com.donatedrop.agent.donner.models.RequestSearchReview;
import com.donatedrop.agent.admin.model.*;
import com.donatedrop.agent.donner.models.DonnerRequestToAgent;
import com.donatedrop.agent.donner.models.DonnerToAgentRequestToReview;
import com.donatedrop.agent.models.RequestNote;
import com.donatedrop.agent.models.RequestReviewRequest;
import com.donatedrop.agent.models.StatusType;
import com.donatedrop.util.StringUtil;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service_AgentDonner_Impl implements Service_AgentDonner_I {

    @Autowired
    Dao_AgentDonner_I agentDonner_I;

    public Map<String, String> saveRequest(DonnerRequestToAgent donnerRequestToAgent) {
        return agentDonner_I.saveRequest(donnerRequestToAgent);
    }

    public DonnerRequestToAgent findOneRequestById(String donnerAgentRequestID) {
        return agentDonner_I.findOneRequestById(donnerAgentRequestID);
    }

    public DonnerRequestToAgent findOneRequestUserID(String id) {
        return agentDonner_I.findOneRequestUserID(id);
    }

    public Map<String, String> deleteRequestByUserID(String userID) {
        return agentDonner_I.deleteRequestByUserID(userID);
    }

    public Map<String, String> reviewDonnerRequest(RequestReviewRequest reviewRequest) {
        Map<String, String> result = new HashMap<>();
        if (reviewRequest.getValue().equals(StatusType.ACCEPT)
                || reviewRequest.getValue().equals(StatusType.REJECT)
                || reviewRequest.getValue().equals(StatusType.REMOVE)) {

            return agentDonner_I.reviewDonnerRequest(reviewRequest);
        } else {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.MESSAGE, StringUtil.UNAUTHERIZEDVALUE);
            return result;
        }
    }

    public List<DonnerToAgentRequestToReview> getDonnerToAgentRequestToReview(RequestSearchReview requestSearchReview) {
        return agentDonner_I.getDonnerToAgentRequestToReview(requestSearchReview);
    }

    public Map<String, String> getDonnerToAgentRequestToReviewCount(RequestSearchReview requestSearchReview) {
        return agentDonner_I.getDonnerToAgentRequestReviewCount(requestSearchReview);
    }

    public Map<String, String> updateAgentNote(RequestNote requestNote) {
        return agentDonner_I.updateAgentNote(requestNote);
    }

    public Map<String, String> updateNoteDonner(RequestNote requestNote) {
        return agentDonner_I.updateNoteDonner(requestNote);
    }

    public Map<String, String> updateNoteAgentPersonal(RequestNote requestNote) {
        return agentDonner_I.updateNoteAgentPersonal(requestNote);
    }

}
