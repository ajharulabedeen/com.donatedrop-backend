package com.donatedrop.agent.admin;

import com.donatedrop.agent.admin.model.AgentRequest;
import com.donatedrop.agent.models.StatusType;
import com.donatedrop.agent.admin.model.RequestApplicantNote;
import com.donatedrop.agent.admin.model.RequestAdminNote;
import com.donatedrop.agent.models.RequestReviewRequest;
import com.donatedrop.agent.donner.models.RequestSearchReview;
import com.donatedrop.agent.admin.model.AgentRequestToReview;
import com.donatedrop.agent.admin.model.RequestPersonalNote;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.donatedrop.agent.admin.models.*;
import com.donatedrop.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service_AgentAdmin_Impl implements Service_AgentAdmin_I {

    @Autowired
    Dao_AgentAdmin_I dao_agent_i;

    /**
     * @param agentRequest
     * @return
     * @apiNote for successful save, saved id will be back. OK status
     */
    @Override
    public Map<String, String> saveRequest(AgentRequest agentRequest) {
        return dao_agent_i.saveRequest(agentRequest);
    }

    @Override
    public Map<String, String> deleteRequest(String userID) {
        return dao_agent_i.deleteRequest(userID);
    }

    /**
     * @param reviewRequest
     * @return
     * @apiNote 1 = approved, 0=not reviewed, -1= rejected.
     */
    @Override
    public Map<String, String> reviewRequest(RequestReviewRequest reviewRequest) {
        Map<String, String> result = new HashMap<>();
        if (reviewRequest.getValue().equals(StatusType.ACCEPT)
                || reviewRequest.getValue().equals(StatusType.REJECT)
                || reviewRequest.getValue().equals(StatusType.FREEZE)) {

            return dao_agent_i.reviewRequest(reviewRequest);
        } else {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.MESSAGE, StringUtil.UNAUTHERIZEDVALUE);
            return result;
        }
    }

    /**
     *
     */
    @Override
    public List<AgentRequest> getAgentRequests(int start, int max) {
        return dao_agent_i.getAgentRequests(start, max);
    }

    @Override
    public List<AgentRequestToReview> getAgentRequestsToReview(RequestSearchReview requestGetAgentRequestsReview) {
        return dao_agent_i.getAgentRequestsToReview(requestGetAgentRequestsReview);
    }

    @Override
    public Map<String, String> getAgentRequestsToReviewCount(String column, String key, String statusType) {
        return dao_agent_i.getAgentRequestsToReviewCount(column, key, statusType);
    }

    @Override
    public Map<String, String> updateAdminNote(RequestAdminNote requestAdminNote) {
        return dao_agent_i.updateAdminNote(requestAdminNote);
    }

    @Override
    public Map<String, String> updateApplicantNote(RequestApplicantNote requestApplicantNote) {
        return dao_agent_i.updateApplicantNote(requestApplicantNote);
    }

    @Override
    public Map<String, String> updatePersonalNote(RequestPersonalNote requestPersonalNote) {
        return dao_agent_i.updatePersonalNote(requestPersonalNote);
    }

}
