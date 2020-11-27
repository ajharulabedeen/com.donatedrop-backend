/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.history;

import com.donatedrop.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author G7
 */
@RestController
@RequestMapping("/public/user/history")
public class ControllerHistory {

    @Autowired
    Service_History_I service_history_i;

    @PostMapping("save")
    public Map<String, String> save(@RequestBody History history) {
        history.setUserId(Utils.getLoggedUserID());
        return service_history_i.save(history);
    }

    @PostMapping("update")
    public Map<String, String> update(@RequestBody History history) {
        return service_history_i.update(history, Utils.getLoggedUserID());
    }

    @PostMapping("delete")
    public Map<String, String> delete(@RequestParam String historyID) {
        return service_history_i.delete(historyID, Utils.getLoggedUserID());
    }

    @PostMapping("search")
    public List<History> search(@RequestBody RequestSearch requestSearch) {
        String userID = Utils.getLoggedUserID();
        String column = requestSearch.getColumn();
        String key = requestSearch.getKey();
        int start = requestSearch.getStart();
        int perPage = requestSearch.getPerPage();
        return service_history_i.search(userID, column, key, start, perPage);
    }

    @PostMapping("searchCount")
    public Map<String, Integer> searchCount(@RequestBody RequestSearch requestSearch) {
        String userID = Utils.getLoggedUserID();
        String column = requestSearch.getColumn();
        String key = requestSearch.getKey();
        int start = requestSearch.getStart();
        int perPage = requestSearch.getPerPage();
        System.out.println(requestSearch.toString());
        return service_history_i.searchCount(userID, column, key, start);
    }

}
