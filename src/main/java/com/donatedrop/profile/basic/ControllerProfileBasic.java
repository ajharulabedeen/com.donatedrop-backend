/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.profile.basic;

import com.donatedrop.models.Address;
import com.donatedrop.profile.model.EmergencyContact;
import com.donatedrop.profile.model.PhoneNumber;
import com.donatedrop.profile.model.ProfileBasic;
import com.donatedrop.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.rmi.CORBA.Util;
import java.util.Date;
import java.util.Map;

/**
 * @author G-7,
 */
@RestController
@RequestMapping("/public/profile/basic/")
public class ControllerProfileBasic {

    @Autowired
    Service_Profile_Basic_I service_profile_basic_i;

    @PostMapping("save")
    public Map<String, String> save(@RequestBody ProfileBasic profileBasic) {
        return service_profile_basic_i.save(profileBasic);
    }

    //    with all details
    @GetMapping("findOneByUser")
    public ProfileBasic findOneByUser() {
        return service_profile_basic_i.findOneByUser(Utils.getLoggedUserID());
    }

    @GetMapping("exist")
    public Map<String, String> basicExist() {
        return service_profile_basic_i.basicExist(Utils.getLoggedUserID());
    }

    @PostMapping("update")
    public Map<String, String> update(@RequestBody ProfileBasic profileBasic) {
        profileBasic.setUserId(Utils.getLoggedUserID());
        System.out.println(profileBasic);
        return service_profile_basic_i.update(profileBasic);
    }

    @PostMapping("addPhoneNumber")
    public Map<String, String> addPhoneNumber(@RequestBody PhoneNumber phoneNumber) {
        return service_profile_basic_i.addPhoneNumber(phoneNumber, Utils.getLoggedUserID());
    }

    @PostMapping("deletePhoneNumber")
    public Map<String, String> deletePhoneNumber(@RequestBody PhoneNumber phoneNumber) {
        return service_profile_basic_i.deletePhoneNumber(phoneNumber.getId().toString(), Utils.getLoggedUserID());
    }

    @PostMapping("updatePresentAddress")
    public Map<String, String> updatePresentAddress(@RequestBody Address address) {
        return service_profile_basic_i.updatePresentAddress(address, Utils.getLoggedUserID());
    }

    @PostMapping("updatePermanentAddress")
    public Map<String, String> updatePermanentAddress(@RequestBody Address address) {
        return service_profile_basic_i.updatePermanentAddress(address, Utils.getLoggedUserID());
    }

    @PostMapping("addEmergencyContact")
    public Map<String, String> addEmergencyContact(@RequestBody EmergencyContact emergencyContact) {
        return service_profile_basic_i.addEmergencyContact(emergencyContact, Utils.getLoggedUserID());
    }

    @PostMapping("deleteEmergencyContact")
    public Map<String, String> deleteEmergencyContact(String emergencyContactID) {
        return service_profile_basic_i.deleteEmergencyContact(emergencyContactID, Utils.getLoggedUserID());
    }

    @PostMapping("updateEmergencyContact")
    public Map<String, String> updateEmergencyContact(@RequestBody EmergencyContact emergencyContactUpdate) {
        return service_profile_basic_i.updateEmergencyContact(emergencyContactUpdate, Utils.getLoggedUserID());
    }
}
