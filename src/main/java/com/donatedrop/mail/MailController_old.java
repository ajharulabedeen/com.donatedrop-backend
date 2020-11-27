package com.donatedrop.mail;

//import com.baeldung.spring.mail.EmailServiceImpl;
//import com.baeldung.spring.domain.MailObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/test/mail")
public class MailController_old {

    @Autowired
    public EmailServiceImpl emailService;

    @Value("${attachment.invoice}")
    private String attachmentPath;

    @Autowired
    public SimpleMailMessage template;

    String varification;

    private static final Map<String, Map<String, String>> labels;

    static {
        labels = new HashMap<>();

        //Simple email
        Map<String, String> props = new HashMap<>();
        props.put("headerText", "Send Simple Email");
        props.put("messageLabel", "Message");
        props.put("additionalInfo", "");
        labels.put("send", props);

        //Email with template
        props = new HashMap<>();
        props.put("headerText", "Send Email Using Template");
        props.put("messageLabel", "Template Parameter");
        props.put("additionalInfo",
                "The parameter value will be added to the following message template:<br>" +
                        "<b>This is the test email template for your email:<br>'Template Parameter'</b>"
        );
        labels.put("sendTemplate", props);

        //Email with attachment
        props = new HashMap<>();
        props.put("headerText", "Send Email With Attachment");
        props.put("messageLabel", "Message");
        props.put("additionalInfo", "To make sure that you send an attachment with this email, change the value for the 'attachment.invoice' in the application.properties file to the path to the attachment.");
        labels.put("sendAttachment", props);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showEmailsPage() {
        return "emails";
    }

    @RequestMapping(value = {"/send", "/sendTemplate", "/sendAttachment"}, method = RequestMethod.GET)
    public String createMail(Model model, HttpServletRequest request) {
        String action = request.getRequestURL().substring(
                request.getRequestURL().lastIndexOf("/") + 1
        );
        Map<String, String> props = labels.get(action);
        Set<String> keys = props.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            model.addAttribute(key, props.get(key));
        }

        model.addAttribute("mailObject", new MailObject());
        return "mail/send";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String createMail(Model model,
                             @ModelAttribute("mailObject") @Valid MailObject mailObject,
                             Errors errors) {
        if (errors.hasErrors()) {
            return "mail/send";
        }
        emailService.sendSimpleMessage(mailObject.getTo(),
                mailObject.getSubject(), mailObject.getText());

        return "redirect:/home";
    }

    @RequestMapping(value = "/sendTemplate", method = RequestMethod.POST)
    public String createMailWithTemplate(Model model,
                                         @ModelAttribute("mailObject") @Valid MailObject mailObject,
                                         Errors errors) {
        if (errors.hasErrors()) {
            return "mail/send";
        }
        emailService.sendSimpleMessageUsingTemplate(mailObject.getTo(),
                mailObject.getSubject(),
                template,
                mailObject.getText());

        return "redirect:/home";
    }

    @RequestMapping(value = "/sendAttachment", method = RequestMethod.POST)
    public String createMailWithAttachment(Model model,
                                           @ModelAttribute("mailObject") @Valid MailObject mailObject, Errors errors) {
        if (errors.hasErrors()) {
            return "mail/send";
        }
        emailService.sendMessageWithAttachment(
                mailObject.getTo(),
                mailObject.getSubject(),
                mailObject.getText(),
                attachmentPath
        );

        return "redirect:/home";
    }

    //    Start : Added .......
//    @GetMapping("test")
    @RequestMapping(value = "/testm", method = RequestMethod.POST)
    public String test(@RequestBody GMail gMail)
//    @RequestMapping(value = "/testm", method = RequestMethod.POST)
//    public String test()
    {
        System.out.println("\n\n------------------------------\n\n");
        gMail.toString();
//        emailService.sendSimpleMessage("cse1301096@gmail.com", "Test", "Hello!");
        emailService.sendSimpleMessage(gMail.getReceiverName(), gMail.getSubject(), gMail.getText());
        varification = gMail.getText();
        return "AllOK!" + new Date().toString();
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public String test(@RequestParam String code) {
        System.out.println("\n--------------Verify----------------\n");
        System.out.println("\nVarification : "+varification);
        System.out.println("\nCode : "+code);
        String status;
        if (varification.equals(code)) {
            status = "Success";
        } else {
            status = "FAIL";
        }
        return status + "\n" + new Date().toString();
    }


//    End : Added .......
}