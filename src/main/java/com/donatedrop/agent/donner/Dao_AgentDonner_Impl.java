/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.agent.donner;

import com.donatedrop.agent.admin.model.AgentRequest;
import com.donatedrop.agent.admin.model.AgentRequestToReview;
import com.donatedrop.agent.admin.model.RequestAdminNote;
import com.donatedrop.agent.admin.model.RequestApplicantNote;
import com.donatedrop.agent.donner.models.RequestSearchReview;
import com.donatedrop.agent.admin.model.RequestPersonalNote;
import com.donatedrop.agent.donner.models.DonnerToAgentRequestToReview;
import com.donatedrop.agent.models.StatusType;
import com.donatedrop.agent.donner.models.DonnerRequestToAgent;
import com.donatedrop.agent.models.RequestNote;
import com.donatedrop.agent.models.RequestReviewRequest;
import com.donatedrop.util.DateUtil;
import com.donatedrop.util.StringUtil;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author G7
 */
@Component
@Transactional
public class Dao_AgentDonner_Impl implements Dao_AgentDonner_I {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Map<String, String> saveRequest(DonnerRequestToAgent donnerRequestToAgent) {
        Map<String, String> result = new HashMap<>();
        try {
            donnerRequestToAgent.setRequestDate(DateUtil.getDate());
            donnerRequestToAgent.setStatus(StatusType.ZERO);
            entityManager.persist(donnerRequestToAgent);
            result.put(StringUtil.STATUS, StringUtil.OK);
            result.put(StringUtil.MESSAGE, StringUtil.SAVE);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.MESSAGE, StringUtil.DUPLICATE);
        } catch (ConstraintViolationException constraintViolationException) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.MESSAGE, StringUtil.DUPLICATE);
        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.MESSAGE, StringUtil.UNKNOWN);
        }
        return result;
    }

    @Override
    public DonnerRequestToAgent findOneRequestUserID(String userID) {
        String q = "SELECT * FROM `request_donner_to_agent` WHERE user_id_donner='" + userID + "'";
        List<DonnerRequestToAgent> list = entityManager.createNativeQuery(q, DonnerRequestToAgent.class).getResultList();
        DonnerRequestToAgent donnerRequestToAgent = null;
        if (list.size() > 0) {
            donnerRequestToAgent = list.get(0);
        }
        return donnerRequestToAgent;
    }

    @Override
    public DonnerRequestToAgent findOneRequestById(String donnerAgentRequestID) {
        return entityManager.find(DonnerRequestToAgent.class, new Long(donnerAgentRequestID));
    }

    @Override
    public Map<String, String> deleteRequestByUserID(String userID) {
        Map<String, String> result = new HashMap<>();
        String requestID = findOneRequestUserID(userID).getId().toString();
        try {
            DonnerRequestToAgent donnerRequestToAgent = entityManager.find(DonnerRequestToAgent.class, new Long(requestID));
            entityManager.remove(donnerRequestToAgent);
            result.put(StringUtil.STATUS, StringUtil.OK);
            result.put(StringUtil.MESSAGE, StringUtil.DELETE);
        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.MESSAGE, StringUtil.UNKNOWN);
        }
        return result;
    }

    @Override
    public Map<String, String> reviewDonnerRequest(RequestReviewRequest reviewRequest) {
        Map<String, String> result = new HashMap<>();
        try {
            DonnerRequestToAgent donnerRequestToAgent
                    = entityManager.find(DonnerRequestToAgent.class, new Long(reviewRequest.getRequestID()));
            if (reviewRequest.getValue().equals(StatusType.ACCEPT)) {
                donnerRequestToAgent.setAcceptDate(DateUtil.getDate());
            } else if (reviewRequest.getValue().equals(StatusType.REJECT)) {
                donnerRequestToAgent.setRejectDate(DateUtil.getDate());
            } else if (reviewRequest.getValue().equals(StatusType.REMOVE)) {
                donnerRequestToAgent.setRemoveDate(DateUtil.getDate());
            }
            donnerRequestToAgent.setStatus(reviewRequest.getValue());
            entityManager.merge(donnerRequestToAgent);
            result.put(StringUtil.STATUS, StringUtil.OK);
            result.put(StringUtil.MESSAGE, StringUtil.SAVE);
        } catch (Exception e) {
            e.printStackTrace();
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.MESSAGE, StringUtil.UNKNOWN);
        }
        return result;
    }

    @Override
    public List<DonnerToAgentRequestToReview> getDonnerToAgentRequestToReview(RequestSearchReview requestGetAgentRequestsReview) {
        int start = requestGetAgentRequestsReview.getStart();
        int max = requestGetAgentRequestsReview.getMax();
        String column = requestGetAgentRequestsReview.getColumn();
        String key = requestGetAgentRequestsReview.getKey();
        String status = requestGetAgentRequestsReview.getStatusType();
        String userIDagent = requestGetAgentRequestsReview.getUserIdAgent();
//        String q = "SELECT * FROM `donner_to_agent_request_review`";
        List<DonnerToAgentRequestToReview> donnerToAgentRequestReviews = new ArrayList<>();
        String q = "";
        try {
            if (column.equals(StringUtil.PHONENUMBER)) {
                q = "SELECT donner_to_agent_request_review.* FROM donner_to_agent_request_review, phonenumber "
                        + "WHERE donner_to_agent_request_review.profile_id = phonenumber.profile_id "
                        + " AND phonenumber.number LIKE '" + key + "'"
                        + " AND `donner_to_agent_request_review`.`status`='" + status + "'"
                        + " AND `donner_to_agent_request_review`.`user_id_agent`='" + userIDagent + "'";
            } else {
                q = "SELECT * FROM `donner_to_agent_request_review` WHERE `donner_to_agent_request_review`.`"
                        + column + "` LIKE '" + key + "'"
                        + " AND `donner_to_agent_request_review`.`status`='" + status + "'"
                        + " AND `donner_to_agent_request_review`.`user_id_agent`='" + userIDagent + "'";
            }
            donnerToAgentRequestReviews
                    = entityManager.createNativeQuery(q, DonnerToAgentRequestToReview.class)
                    .setFirstResult(start)
                    .setMaxResults(max)
                    .getResultList();
            return donnerToAgentRequestReviews;

        } catch (Exception exception) {
            System.out.println("org.hibernate.exception.SQLGrammarException");
        }
        return donnerToAgentRequestReviews;
    }

    @Override
    public Map<String, String> getDonnerToAgentRequestReviewCount(RequestSearchReview requestSearchReview) {
        Map<String, String> result = new HashMap<>();
        String q = "";
        try {
            if (requestSearchReview.getColumn().equals(StringUtil.PHONENUMBER)) {
                q = "SELECT COUNT(*) FROM donner_to_agent_request_review, phonenumber "
                        + "WHERE donner_to_agent_request_review.profile_id = phonenumber.profile_id "
                        + " AND phonenumber.number LIKE '" + requestSearchReview.getKey() + "'"
                        + " AND `donner_to_agent_request_review`.`status`='" + requestSearchReview.getStatusType() + "'"
                        + " AND `donner_to_agent_request_review`.`user_id_agent`='" + requestSearchReview.getUserIdAgent() + "'";
            } else {
                q = "SELECT COUNT(*) FROM `donner_to_agent_request_review` WHERE `donner_to_agent_request_review`.`"
                        + requestSearchReview.getColumn() + "` LIKE '" + requestSearchReview.getKey() + "'"
                        + " AND `donner_to_agent_request_review`.`status`='" + requestSearchReview.getStatusType() + "'"
                        + " AND `donner_to_agent_request_review`.`user_id_agent`='" + requestSearchReview.getUserIdAgent() + "'";
            }
            String count = entityManager.createNativeQuery(q).getResultList().get(0).toString();
            result.put(StringUtil.STATUS, StringUtil.OK);
            result.put(StringUtil.COUNT, count);
            return result;
        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            System.out.println("Error in Agent Request Counting!");
        }
        return result;
    }

    @Override
    public Map<String, String> updateAgentNote(RequestNote requestNote) {
        Map<String, String> result = new HashMap<>();
        try {
            DonnerRequestToAgent donnerRequestToAgent = entityManager.find(DonnerRequestToAgent.class,
                    new Long(requestNote.getRequestId()));
            if (donnerRequestToAgent != null) {
                donnerRequestToAgent.setNoteAgent(requestNote.getNote());
                entityManager.merge(donnerRequestToAgent);
                result.put(StringUtil.STATUS, StringUtil.OK);
            }

        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return result;
    }

    @Override
    public Map<String, String> updateNoteDonner(RequestNote requestNote) {
        Map<String, String> result = new HashMap<>();
        try {
            DonnerRequestToAgent donnerRequestToAgent = entityManager.find(DonnerRequestToAgent.class,
                    new Long(requestNote.getRequestId()));
            if (donnerRequestToAgent != null) {
                donnerRequestToAgent.setNoteDonner(requestNote.getNote());
                entityManager.merge(donnerRequestToAgent);
                result.put(StringUtil.STATUS, StringUtil.OK);
            }

        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return result;
    }

    @Override
    public Map<String, String> updateNoteAgentPersonal(RequestNote requestNote) {
        Map<String, String> result = new HashMap<>();
        try {
            DonnerRequestToAgent donnerRequestToAgent = entityManager.find(DonnerRequestToAgent.class,
                    new Long(requestNote.getRequestId()));
            if (donnerRequestToAgent != null) {
                donnerRequestToAgent.setNoteAgentPersonal(requestNote.getNote());
                entityManager.merge(donnerRequestToAgent);
                result.put(StringUtil.STATUS, StringUtil.OK);
            }

        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return result;
    }

}
