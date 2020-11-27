/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.agent.donner;

import com.donatedrop.agent.donner.models.*;
import com.donatedrop.agent.models.RequestNote;
import com.donatedrop.agent.models.RequestReviewRequest;
import com.donatedrop.profile.basic.Service_Profile_Basic_I;
import com.donatedrop.profile.model.ProfileBasic;
import com.donatedrop.util.Utils;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author G7
 */
@RestController
@RequestMapping("/public/user/agent/donnerAssign")
public class Controller_DonnerAssign {

    @Autowired
    Service_DonnerAssign_I service_donnerAssign_i;

    @Autowired
    Service_Profile_Basic_I service_profile_basic_i;

    @PostMapping("getAssingments")
    public List<DonnerAssingShow>
    getAssingments(@RequestBody RequestSearchDonnerAssing requestSearchDonnerAssing) {
        requestSearchDonnerAssing.setAgentID(Utils.getLoggedUserID());
        return service_donnerAssign_i.getAssingments(requestSearchDonnerAssing);
    }

    @PostMapping("getAssingmentsCount")
    public Map<String, String>
    getAssingmentsCount(@RequestBody RequestSearchDonnerAssing requestSearchDonnerAssing) {
        requestSearchDonnerAssing.setAgentID(Utils.getLoggedUserID());
        return service_donnerAssign_i.getAssingmentsCount(requestSearchDonnerAssing);
    }

    @DeleteMapping("delete")
    public Map<String, String> delete(@RequestParam String donnerAssingmentID) {
        return service_donnerAssign_i.delete(donnerAssingmentID);
    }

    // refactor : have to add validation of ID's.
    @PostMapping("save")
    public Map<String, String> save(@RequestBody DonnerAssingment donnerAssingment) {
        donnerAssingment.setAgentId(Utils.getLoggedUserID());
        return service_donnerAssign_i.save(donnerAssingment);
    }

    // refactor : have to add validation of ID's.
    @PostMapping("edit")
    public Map<String, String> edit(@RequestBody DonnerAssingment donnerAssingment) {
        donnerAssingment.setAgentId(Utils.getLoggedUserID());
        return service_donnerAssign_i.save(donnerAssingment);
    }


    @PostMapping("complete")
    public Map<String, String> complete(@RequestParam String donnerAssingmentID) {
        return service_donnerAssign_i.complete(donnerAssingmentID);
    }

    @GetMapping("getProfileBasicByUserID")
    public ProfileBasic getProfileBasicByUserID(@RequestParam String userID) {
        return service_profile_basic_i.getProfileBasicByUserID(userID);
    }

    @GetMapping("profileCheckingByUserID")
    public ProfileBasic profileCheckingByUserID(@RequestParam String userID) {
        return service_profile_basic_i.profileCheckingByUserID(userID);
    }

    @GetMapping("profileDetails")
    public ProfileBasic getProfileDetails(@RequestParam String userID) {
        return service_profile_basic_i.findOneByUser(userID);
    }
}
