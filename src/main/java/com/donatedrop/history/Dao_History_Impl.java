package com.donatedrop.history;

import com.donatedrop.profile.basic.Dao_Profile_Basic_I;
import com.donatedrop.profile.model.EmergencyContact;
import com.donatedrop.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class Dao_History_Impl implements Dao_History_I {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    Dao_Profile_Basic_I dao_profile_basic_i;

    @Override
    @Transactional
    public Map<String, String> save(History history) {
        Map<String, String> status = new HashMap<>();
        try {
            entityManager.persist(history);
            status.put(StringUtil.STATUS, StringUtil.OK);
            status.put(StringUtil.ID, history.getId().toString());
        } catch (Exception e) {
            status.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return status;
    }

    @Override
    public Map<String, String> update(History historyUpdate, String userID) {
        Map<String, String> result = new HashMap<>();
        try {
            History historyOld = entityManager.find(History.class,
                    Long.parseLong(historyUpdate.getId().toString()));
            //to protect one user update, another users information.
            if (historyOld.getUserId().toString().equals(userID)) {
                entityManager.merge(historyUpdate);
                result.put(StringUtil.STATUS, StringUtil.OK);
            } else {
                result.put(StringUtil.STATUS, StringUtil.FAIL);
                result.put(StringUtil.ERROR, StringUtil.UNAUTHERIZED);
            }
        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.ERROR, StringUtil.NULL);
        }
        return result;
    }

    @Override
    public History findOne(String historyID) {
        History history = null;
        try {
            history = entityManager.find(History.class, new Long(historyID));
        } catch (Exception e) {
            System.out.println("Fail : findOne History!");
        }
        return history;
    }

    @Override
    public Map<String, String> delete(String historyID, String userID) {
        Map<String, String> result = new HashMap<>();
        try {
            History history = entityManager.find(History.class,
                    Long.parseLong(historyID));
            //to protect one user delete, another users information.
            if (history.getUserId().toString().equals(userID)) {
                entityManager.remove(history);
                result.put(StringUtil.STATUS, StringUtil.OK);
            } else {
                result.put(StringUtil.STATUS, StringUtil.FAIL);
                result.put(StringUtil.ERROR, StringUtil.UNAUTHERIZED);
            }
        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.ERROR, StringUtil.NULL);
        }
        return result;
    }

    @Override
    public List<History> getAllHistory(String userID, int start, int perPage) {
        String query = "SELECT * FROM `history` WHERE user_id =" + userID;
        return entityManager.createNativeQuery(query, History.class)
                .setFirstResult(start)
                .setMaxResults(perPage).getResultList();
    }

    @Override
    public int getTotalCount(String userID) {
        String query = "SELECT * FROM `history` WHERE user_id =" + userID;
        return entityManager.createNativeQuery(query).getResultList().size();
    }

    @Override
    public List<History> search(String userID, String column, String key, int start, int perPage) {
        String q = "SELECT * FROM history WHERE user_id=" + userID + " AND " + column + " LIKE '%" + key + "%' ORDER BY id ASC";
        return entityManager.createNativeQuery(q, History.class)
                .setFirstResult(start)
                .setMaxResults(perPage).getResultList();
    }

    @Override
    public Map<String, Integer> searchCount(String userID, String column, String key, int start) {
        Map<String, Integer> result = new HashMap<>();
        String q = "SELECT * FROM history WHERE user_id=" + userID + " AND " + column + " LIKE '" + key + "' ORDER BY id ASC";
        int count = entityManager.createNativeQuery(q, History.class)
                .setFirstResult(start)
                .getResultList().size();
        result.put(StringUtil.COUNT, new Integer(count));
        return result;
    }
}// class
