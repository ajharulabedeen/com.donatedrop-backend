package com.donatedrop.history;

import com.donatedrop.profile.basic.Dao_Profile_Basic_I;
import com.donatedrop.profile.model.EmergencyContact;
import com.donatedrop.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.websocket.OnError;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class Service_History_Impl implements Service_History_I {

    @Autowired
    private Dao_History_I dao_History_I;

    @Autowired
    Dao_Profile_Basic_I dao_profile_basic_i;

    @Override
    public Map<String, String> save(History history) {
        return dao_History_I.save(history);
    }

    @Override
    public Map<String, String> update(History historyUpdate, String userID) {
        return dao_History_I.update(historyUpdate, userID);
    }

    @Override
    public History findOne(String historyID) {
        return dao_History_I.findOne(historyID);
    }

    @Override
    public Map<String, String> delete(String historyID, String userID) {
        return dao_History_I.delete(historyID, userID);
    }

    @Override
    public int getTotalCount(String userID) {
        return dao_History_I.getTotalCount(userID);
    }

    @Override
    public List<History> getAllHistory(String userID, int start, int perPage) {
        return dao_History_I.getAllHistory(userID, start, perPage);
    }

    @Override
    public List<History> search(String userID, String column, String key, int start, int perPage) {
        return dao_History_I.search(userID, column, key, start, perPage);
    }

    @Override
    public Map<String, Integer> searchCount(String userID, String column, String key, int start) {
        return dao_History_I.searchCount(userID, column, key, start);
    }

}// class
