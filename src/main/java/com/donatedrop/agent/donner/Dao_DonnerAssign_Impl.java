package com.donatedrop.agent.donner;

import com.donatedrop.agent.admin.model.AgentRequestToReview;
import com.donatedrop.agent.donner.models.DonnerAssingShow;
import com.donatedrop.agent.donner.models.DonnerAssingment;
import com.donatedrop.agent.donner.models.DonnerRequestToAgent;
import com.donatedrop.agent.donner.models.RequestSearchDonnerAssing;
import com.donatedrop.agent.models.StatusType;
import com.donatedrop.profile.basic.Dao_Profile_Basic_I;
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

@Component
@Transactional
public class Dao_DonnerAssign_Impl implements Dao_DonnerAssign_I {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Map<String, String> save(DonnerAssingment donnerAssingment) {
        Map<String, String> result = new HashMap<>();
        try {
            donnerAssingment.setAssingDate(DateUtil.getDate());
            donnerAssingment.setBloodManageStatus(StatusType.ZERO);
            entityManager.persist(donnerAssingment);
            result.put(StringUtil.STATUS, StringUtil.OK);
            result.put(StringUtil.MESSAGE, StringUtil.SAVE);
            result.put(StringUtil.ID, donnerAssingment.getId().toString());
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
    public DonnerAssingment findOne(String donnerAssingmentID) {
        return entityManager.find(DonnerAssingment.class, new Long(donnerAssingmentID));
    }

    @Override
    public Map<String, String> delete(String donnerAssingmentID) {
        Map<String, String> result = new HashMap<>();
        try {
            DonnerAssingment donnerAssingment
                    = entityManager.find(DonnerAssingment.class, new Long(donnerAssingmentID));
            entityManager.remove(donnerAssingment);
            result.put(StringUtil.STATUS, StringUtil.OK);
            result.put(StringUtil.MESSAGE, StringUtil.DELETE);
        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.MESSAGE, StringUtil.UNKNOWN);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<String, String> complete(String donnerAssingmentID) {
        Map<String, String> result = new HashMap<>();
        try {
            DonnerAssingment donnerAssingment
                    = entityManager.find(DonnerAssingment.class, new Long(donnerAssingmentID));
            donnerAssingment.setBloodManageStatus(StringUtil.COMPLETE);
            entityManager.merge(donnerAssingment);
            result.put(StringUtil.STATUS, StringUtil.OK);
            result.put(StringUtil.MESSAGE, StringUtil.COMPLETE);
        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.MESSAGE, StringUtil.UNKNOWN);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<String, String> update(DonnerAssingment donnerAssingment) {
        Map<String, String> result = new HashMap<>();
        try {
            DonnerAssingment donnerAssingmentOld = entityManager.find(DonnerAssingment.class,
                    new Long(donnerAssingment.getId()));
            if (donnerAssingmentOld != null) {
                donnerAssingmentOld.setDonnerId(donnerAssingment.getDonnerId());
                donnerAssingmentOld.setPostId(donnerAssingment.getPostId());
                donnerAssingmentOld.setAssingNote(donnerAssingment.getAssingNote());
                donnerAssingmentOld.setAssingDate(donnerAssingment.getAssingDate());
                donnerAssingmentOld.setNeedDate(donnerAssingment.getNeedDate());
                donnerAssingmentOld.setBloodManageStatus(donnerAssingment.getBloodManageStatus());
                entityManager.merge(donnerAssingment);
                result.put(StringUtil.STATUS, StringUtil.OK);
            }
        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return result;
    }

    //    public List
    @Override
    public List<DonnerAssingShow> getAssingments(RequestSearchDonnerAssing requestSearchDonnerAssing) {
        String sql = "";
        int start = requestSearchDonnerAssing.getStart();
        int max = requestSearchDonnerAssing.getMax();
        String column = requestSearchDonnerAssing.getColumn();
        String key = requestSearchDonnerAssing.getKey();
        String status = requestSearchDonnerAssing.getStatusType();
        String agentID = requestSearchDonnerAssing.getAgentID();
//        String q = "SELECT * FROM `agent_request_review`";
        List<DonnerAssingShow> donnerAssingmentList = new ArrayList<>();
        String q = "";
        try {
//            search in one-to-many not working. question posted in stackOverFlow : https://stackoverflow.com/questions/63541626/
            if (column.equals(StringUtil.PHONENUMBER)) {
                q = "SELECT DISTINCT donner_assing_show.* FROM donner_assing_show, phonenumber "
                        + "WHERE donner_assing_show.profile_id = phonenumber.profile_id "
                        + " AND phonenumber.number LIKE '" + key + "'"
                        + " AND `donner_assing_show`.`blood_manage_status`='" + status + "'"
                        + " AND `donner_assing_show`.`agent_id`='" + agentID + "'";
            } else if (column.equals(StringUtil.ADRESS)) {
                q = "SELECT\n"
                        + "    *\n"
                        + "FROM\n"
                        + "    `address`,\n"
                        + "    `donner_assing_show`\n"
                        + "WHERE\n"
                        + "    donner_assing_show.profile_id = `address`.`profile_id` \n"
                        + "    AND\n"
                        + "    (`address`.`division` LIKE '" + key + "' \n"
                        + "     OR `address`.`district` LIKE '" + key + "' \n"
                        + "     OR `address`.`upzilla` LIKE '" + key + "'\n"
                        + "     OR `address`.`union_ward` LIKE '" + key + "')"
                        + " AND `donner_assing_show`.`blood_manage_status`='" + status + "'"
                        + " AND `donner_assing_show`.`agent_id`='" + agentID + "'";
                ;
            } else {
                q = "SELECT * FROM `donner_assing_show` WHERE `donner_assing_show`.`"
                        + column + "` LIKE '" + key + "'"
                        + " AND `donner_assing_show`.`blood_manage_status`='" + status + "'"
                        + " AND `donner_assing_show`.`agent_id`='" + agentID + "'";
            }
            donnerAssingmentList
                    = entityManager.createNativeQuery(q, DonnerAssingShow.class)
                    .setFirstResult(start)
                    .setMaxResults(max)
                    .getResultList();
            return donnerAssingmentList;

        } catch (Exception exception) {
            System.out.println("org.hibernate.exception.SQLGrammarException");
        }
        return donnerAssingmentList;
    }

    @Override
    public Map<String, String> getAssingmentsCount(RequestSearchDonnerAssing requestSearchDonnerAssing) {
        Map<String, String> result = new HashMap<>();
        String sql = "";
        int start = requestSearchDonnerAssing.getStart();
        int max = requestSearchDonnerAssing.getMax();
        String column = requestSearchDonnerAssing.getColumn();
        String key = requestSearchDonnerAssing.getKey();
        String status = requestSearchDonnerAssing.getStatusType();
        String agentID = requestSearchDonnerAssing.getAgentID();
        String q = "";
        try {
            q = "SELECT count(* ) FROM `donner_assing_show` WHERE `donner_assing_show`.`"
                    + column + "` LIKE '" + key + "'"
                    + " AND `donner_assing_show`.`blood_manage_status`='" + status + "'"
                    + " AND `donner_assing_show`.`agent_id`='" + agentID + "'";
            String count = entityManager.createNativeQuery(q).getResultList().get(0).toString();
            result.put(StringUtil.STATUS, StringUtil.OK);
            result.put(StringUtil.COUNT, count);
            return result;
        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            System.out.println("Error in Donner Assingment Counting!");
        }
        return result;
    }
}
