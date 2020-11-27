/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.mail;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author G7
 */
@RestController
@RequestMapping("public/user/mail")
public class MailController {

    @Autowired
    public EmailServiceImpl emailService;
    @Autowired
    public SimpleMailMessage template;

    String varification;

    @RequestMapping(value = "/testm", method = RequestMethod.POST)
    public String test(@RequestBody GMail gMail) //    @RequestMapping(value = "/testm", method = RequestMethod.POST)
    {
        gMail.toString();
//        emailService.sendSimpleMessage("cse1301096@gmail.com", "Test", "Hello!");
        emailService.sendSimpleMessage(gMail.getReceiverName(), gMail.getSubject(), gMail.getText());
        varification = gMail.getText();
        return "AllOK!" + new Date().toString();
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
