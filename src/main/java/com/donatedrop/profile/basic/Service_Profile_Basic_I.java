package com.donatedrop.profile.basic;

import com.donatedrop.models.Address;
import com.donatedrop.profile.model.EmergencyContact;
import com.donatedrop.profile.model.PhoneNumber;
import com.donatedrop.profile.model.ProfileBasic;

import java.util.Map;

public interface Service_Profile_Basic_I {

    /**
     * @return id after save the id will be returned.
     */
    public Map<String, String> save(ProfileBasic profileBasic);

    /**
     * @param userId where('user_id', $userId)->first();.
     * @return ProfileBasic here one profile basic will be returned.
     * @apiNote no child will be returned.
     */
    public ProfileBasic findOneByUser(String userId);

    /**
     * @param userId
     * @return Map Not Exist : {status=false}; Exist : {status=true}
     */
    public Map<String, String> basicExist(String userId);

    /**
     * @return boolean status will be returned as "0" or "1".
     * @apiNote this will update the profile basic information, no phone, education, or other required. Have to set userID from controller.
     */
    public Map<String, String> update(ProfileBasic profileBasicUpdate);

    /**
     * @param phoneNumber
     * @param userID
     * @return success status.
     * @apiNote STATUS OK  and ID of the phone number for successfull Save else and STATUS FAIL.
     */
    public Map<String, String> addPhoneNumber(PhoneNumber phoneNumber, String userID);

    public Map<String, String> deletePhoneNumber(String phoneNumberID, String userID);

    /**
     * @param address PK of the Address.
     * @return Map<String       ,       String> = {"status","OK"} -/- {"status","FAIL"}
     */
    public Map<String, String> updatePresentAddress(Address address, String userID);

    /**
     * @param address PK of the Address.
     * @return Map<String   ,   String> = {"status","OK"} -/- {"status","FAIL"}
     */
    public Map<String, String> updatePermanentAddress(Address address, String userID);

    public Map<String, String> addEmergencyContact(EmergencyContact emergencyContact, String userID);

    public Map<String, String> deleteEmergencyContact(String emergencyContactID, String userID);

    public Map<String, String> updateEmergencyContact(EmergencyContact emergencyContactUpdate, String userID);

    public ProfileBasic getProfileBasicByUserID(String userID);

    public ProfileBasic profileCheckingByUserID(String userID);
}
