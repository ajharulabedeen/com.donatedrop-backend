package com.donatedrop.profile.basic;

import com.donatedrop.models.Address;
import com.donatedrop.profile.model.EmergencyContact;
import com.donatedrop.profile.model.PhoneNumber;
import com.donatedrop.profile.model.ProfileBasic;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Service_Profile_Basic_Impl implements Service_Profile_Basic_I {

    @Autowired
    Dao_Profile_Basic_I dao_profile_basic_i;

    @Override
    public Map<String, String> save(ProfileBasic profileBasic) {
//        if (profileBasic.getAddress_permanent() == null) {
//            profileBasic.setAddress_permanent(new Address());
//        }
//        if (profileBasic.getAddress_present() == null) {
//            profileBasic.setAddress_present(new Address());
//        }
        return dao_profile_basic_i.save(profileBasic);
    }

    @Override
    public ProfileBasic findOneByUser(String userId) {
        return dao_profile_basic_i.findOneByUserIDWithChild(userId);
    }

    @Override
    public Map<String, String> basicExist(String userId) {
        return dao_profile_basic_i.basicExist(userId);
    }

    @Override
    public Map<String, String> update(ProfileBasic profileBasic) {
        return dao_profile_basic_i.update(profileBasic);
    }

    @Override
    public Map<String, String> addPhoneNumber(PhoneNumber phoneNumber, String userID) {
        return dao_profile_basic_i.addPhoneNumber(phoneNumber, userID);
    }

    @Override
    public Map<String, String> deletePhoneNumber(String phoneNumberID, String userID) {
        return dao_profile_basic_i.deletePhoneNumber(phoneNumberID, userID);
    }

    @Override
    public Map<String, String> updatePresentAddress(Address address, String userID) {
        return dao_profile_basic_i.updatePresentAddress(address, userID);
    }

    @Override
    public Map<String, String> updatePermanentAddress(Address address, String userID) {
        return dao_profile_basic_i.updatePermanentAddress(address, userID);
    }

    @Override
    public Map<String, String> addEmergencyContact(EmergencyContact emergencyContact, String userID) {
        return dao_profile_basic_i.addEmergencyContact(emergencyContact, userID);
    }

    @Override
    public Map<String, String> deleteEmergencyContact(String emergencyContactID, String userID) {
        return dao_profile_basic_i.deleteEmergencyContact(emergencyContactID, userID);
    }

    @Override
    public Map<String, String> updateEmergencyContact(EmergencyContact emergencyContactUpdate, String userID) {
        return dao_profile_basic_i.updateEmergencyContact(emergencyContactUpdate, userID);
    }

    @Override
    public ProfileBasic getProfileBasicByUserID(String userID) {
        return dao_profile_basic_i.getProfileBasicByUserID(userID);
    }

    @Override
    public ProfileBasic profileCheckingByUserID(String userID) {
        return dao_profile_basic_i.profileCheckingByUserID(userID);
    }

}
