/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.security.verification;

import com.donatedrop.mail.EmailServiceImpl;
import com.donatedrop.mail.GMail;
import com.donatedrop.util.StringUtil;
import com.donatedrop.util.Utils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author G7
 */
@RestController
@RequestMapping("public/user/vefication")
public class ControllerVerification {

    @Autowired
    public EmailServiceImpl emailService;
    @Autowired
    public SimpleMailMessage template;

    String varification;

    @RequestMapping(value = "/sendcode", method = RequestMethod.GET)
    public Map<String, String> sendVerficationCode() {
        Map<String, String> response = new HashMap<>();
        String s = RandomStringUtils.randomAlphabetic(3);
        try {
            emailService.sendSimpleMessage(
                    Utils.getLoggedUserEmailID(),
                    "Veification Code", //subject
                    "verification code : "+"21261");
            response.put(StringUtil.STATUS, StringUtil.OK);
        } catch (Exception e) {
            response.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return response;
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public String test(@RequestParam String code) {
        System.out.println("\n--------------Verify----------------\n");
        System.out.println("\nVarification : " + varification);
        System.out.println("\nCode : " + code);
        String status;
        if (varification.equals(code)) {
            status = "Success";
        } else {
            status = "FAIL";
        }
        return status + "\n" + new Date().toString();
    }

}
