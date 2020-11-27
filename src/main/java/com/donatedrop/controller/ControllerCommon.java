/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.controller;

import com.donatedrop.agent.Service_Agent_I;
import com.donatedrop.agent.models.AgentBasic;
import com.donatedrop.agent.models.UserPublicContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.donatedrop.agent.Dao_Agent_I;

/**
 * @author Dell
 */
@RestController
@RequestMapping("/public/")
public class ControllerCommon {

    @Autowired
    Service_Agent_I service_agent_i;

    @GetMapping("getUserPublicContact")
    public UserPublicContact agentBasic(@RequestParam String userID) {
        return service_agent_i.getUserPublicContact(userID);
    }

}
