package com.donatedrop.history;

import com.donatedrop.profile.model.EmergencyContact;

import java.util.List;
import java.util.Map;

public interface Dao_History_I {

    /**
     * @param history
     * @return
     * @apiNote have to set the user_id, of the default logged user.
     */
    Map<String, String> save(History history);

    public Map<String, String> update(History history, String userID);

    /**
     * @return
     * @apiNote parent will not be back
     */
    public History findOne(String historyID);

    public Map<String, String> delete(String historyID, String userID);

    public int getTotalCount(String userID);

    public List<History> getAllHistory(String userID, int start, int perPage);

    public List<History> search(String userID, String column, String key, int start, int perPage);

    public Map<String, Integer> searchCount(String userID, String column, String key, int start);

}
