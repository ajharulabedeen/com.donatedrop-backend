package com.donatedrop.profile.basic;

import com.donatedrop.models.Address;
import com.donatedrop.profile.model.EmergencyContact;
import com.donatedrop.profile.model.PhoneNumber;
import com.donatedrop.profile.model.ProfileBasic;
import com.donatedrop.util.AddressType;
import com.donatedrop.util.DateUtil;
import com.donatedrop.util.StringUtil;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.*;

@Component
@Transactional
public class Dao_Profile_Basic_Impl implements Dao_Profile_Basic_I {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Map<String, String> save(ProfileBasic profileBasic) {
        String status = "";
        String id = "";
        try {
            entityManager.persist(profileBasic);
            status = StringUtil.OK;
            id = profileBasic.getId().toString();
        } catch (Exception e) {
            status = StringUtil.FAIL;
            System.out.println("Profile Save Fail!");
            //refactor : separate log file.
            e.printStackTrace();
        }
        Map<String, String> response = new HashMap<>();
        response.put(StringUtil.STATUS, status);
        if (status.equals(StringUtil.OK)) {
            response.put(StringUtil.ID, id);
        } else {
            response.put(StringUtil.ID, StringUtil.NULL);
        }
        //refactor
        System.out.println(response.toString());
        return response;
    }

    @Override
    public Map<String, String> update(ProfileBasic profileBasicNew) {
        Map<String, String> result = new HashMap<>();
        ProfileBasic profileBasicOld = getProfileBasicByUserID(profileBasicNew.getUserId());
        if (profileBasicOld != null) {
            //refactor : follow emegency contact update
            profileBasicOld.setName(profileBasicNew.getName());
            profileBasicOld.setBirthDate(profileBasicNew.getBirthDate());
            profileBasicOld.setCare_of(profileBasicNew.getCare_of());
            profileBasicOld.setGender(profileBasicNew.getGender());
            profileBasicOld.setMaritalStatus(profileBasicNew.getMaritalStatus());
            profileBasicOld.setProfession(profileBasicNew.getProfession());
            profileBasicOld.setBlood_Group(profileBasicNew.getBlood_Group());
            profileBasicOld.setAvailable(profileBasicNew.getAvailable());
            profileBasicOld.setReligion(profileBasicNew.getReligion());
            profileBasicOld.setEmail(profileBasicNew.getEmail());
            try {
                entityManager.merge(profileBasicOld);
                result.put(StringUtil.STATUS, StringUtil.OK);
            } catch (Exception e) {
                System.out.println("Basic Profile Update Failed!");
                result.put(StringUtil.STATUS, StringUtil.FAIL);
            }
        } else {
            System.out.println("Basic Profile Entity Not Found!");
            result.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return result;
    }

    @Override
    public Map<String, String> delete(String id) {
        System.out.println("DAO Delete: ");
        Map<String, String> response = new HashMap<>();
        try {
            ProfileBasic profileBasic = entityManager.find(ProfileBasic.class, new Long(id));
            if (profileBasic != null) {
                entityManager.remove(profileBasic);
                response.put(StringUtil.STATUS, StringUtil.OK);
            } else {
                System.out.println("No entity found!");
                response.put(StringUtil.STATUS, StringUtil.FAIL);
            }
        } catch (Exception e) {
            System.out.println("Profile Basic Delete Fail!");
//            e.printStackTrace();
        }
//        System.out.println("response (dao) : " + response.toString());
        return response;
    }

    @Override
    public ProfileBasic findOne(String id) {
        ProfileBasic profileBasic = null;
        try {
            profileBasic = entityManager.find(ProfileBasic.class, Long.parseLong(id));
        } catch (Exception e) {
            System.out.println("Not Found!");
            e.printStackTrace();
        }
        return profileBasic;
    }

    /**
     * @param id
     * @return
     * @
     */
//    @Override
    public ProfileBasic findOneWithChild(String id) {
        ProfileBasic profileBasic = null;
        try {
            profileBasic = entityManager.find(ProfileBasic.class, Long.parseLong(id));
            if (profileBasic != null) {
                profileBasic.getAddress().forEach(c -> {
                });
                profileBasic.getEmergency_contact().forEach(c -> {
                });
                profileBasic.getPhone_number().forEach(p -> {
                });
            }
        } catch (Exception e) {
            System.out.println("Not Found!");
            e.printStackTrace();
        }
        return profileBasic;
    }

    @Override
    public ProfileBasic findOneByUserIDWithChild(String userId) {
        String sql = "SELECT *FROM profilebasic WHERE user_id =" + userId;
        ProfileBasic profileBasic = null;
        List<ProfileBasic> list = entityManager
                .createNativeQuery(sql, ProfileBasic.class)
                .getResultList();
        if (list.size() >= 1) {
            profileBasic = list.get(0);
            //to avoid lazay init error.
            profileBasic.getAddress().forEach(c -> {
            });
            profileBasic.getEmergency_contact().forEach(c -> {
            });
            profileBasic.getPhone_number().forEach(p -> {
            });
//            System.out.println(profileBasic);
        }
        return profileBasic;
    }

    /**
     * better to keep, basicExist and findOneByUser, separate. caue
     * findOneByUser has child init, can be problem, if mulitple times called.
     *
     * @param userId
     * @return
     */
    @Override
    public Map<String, String> basicExist(String userId) {
        Map<String, String> result = new HashMap<>();
        ProfileBasic profileBasic = getProfileBasicByUserID(userId);
        if (profileBasic != null) {
            result.put(StringUtil.STATUS, StringUtil.TRUE);
        } else {
            result.put(StringUtil.STATUS, StringUtil.FALSE);
        }
        return result;
    }

    @Override
    public Map<String, String> updatePresentAddress(Address addressPresentNew, String userID) {
        Map<String, String> result = new HashMap<>();
        ProfileBasic profileBasic = getProfileBasicByUserID(userID);
        if (profileBasic != null) {
            Address addressPresentOld = profileBasic.getAddress().stream()
                    .filter(address -> AddressType.PRESENT.toString().equals(address.getType()))
                    .findAny()
                    .orElse(null);
            System.out.println(addressPresentOld.toString());
            addressPresentOld.setDivision(addressPresentNew.getDivision());
            addressPresentOld.setDistrict(addressPresentNew.getDistrict());
            addressPresentOld.setUpzilla(addressPresentNew.getUpzilla());
            addressPresentOld.setUnion_ward(addressPresentNew.getUnion_ward());
            addressPresentOld.setStreet_address(addressPresentNew.getStreet_address());
            try {
                entityManager.merge(addressPresentOld);
                result.put(StringUtil.STATUS, StringUtil.OK);
            } catch (Exception e) {
                result.put(StringUtil.STATUS, StringUtil.FAIL);
            }
        } else {
//            result.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return result;
    }

    @Override
    public Map<String, String> updatePermanentAddress(Address addressPermanentNew, String userID) {
        Map<String, String> result = new HashMap<>();
        ProfileBasic profileBasic = getProfileBasicByUserID(userID);
        if (profileBasic != null) {
            Address addressPermanentOld = profileBasic.getAddress().stream()
                    .filter(address -> AddressType.PERMANENT.toString().equals(address.getType()))
                    .findAny()
                    .orElse(null);
            addressPermanentOld.setDivision(addressPermanentNew.getDivision());
            addressPermanentOld.setDistrict(addressPermanentNew.getDistrict());
            addressPermanentOld.setUpzilla(addressPermanentNew.getUpzilla());
            addressPermanentOld.setUnion_ward(addressPermanentNew.getUnion_ward());
            addressPermanentOld.setStreet_address(addressPermanentNew.getStreet_address());
            try {
                entityManager.merge(addressPermanentOld);
                result.put(StringUtil.STATUS, StringUtil.OK);
            } catch (Exception e) {
                result.put(StringUtil.STATUS, StringUtil.FAIL);
            }
        } else {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return result;
    }

    @Override
    public Map<String, String> addPhoneNumber(PhoneNumber phoneNumber, String userID) {
        Map<String, String> result = new HashMap<>();
        ProfileBasic profileBasic = getProfileBasicByUserID(userID);
        if (profileBasic != null) {
            List<PhoneNumber> phoneNumbers = profileBasic.getPhone_number();
            phoneNumbers.add(phoneNumber);
            try {
                entityManager.merge(profileBasic);
                result.put(StringUtil.STATUS, StringUtil.OK);
//                result.put(StringUtil.ID, phoneNumber.getId().toString());
                result.put(StringUtil.ID, phoneNumbers.get(phoneNumbers.size() - 1).getId().toString());
            } catch (Exception e) {
                e.printStackTrace();
                result.put(StringUtil.STATUS, StringUtil.FAIL);
            }
        } else {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return result;
    }

    @Override
    public Map<String, String> deletePhoneNumber(String phoneNumberID, String userID) {
        Map<String, String> result = new HashMap<>();
        ProfileBasic profileBasic = getProfileBasicByUserID(userID);
        if (profileBasic != null) {
            List<PhoneNumber> phoneNumbers = profileBasic.getPhone_number();
            if (phoneNumbers.size() >= 1) {
                phoneNumbers.remove(phoneNumbers
                        .stream()
                        .filter(p -> phoneNumberID.equals(p.getId().toString()))
                        .findAny()
                        .orElse(null));
                try {
                    entityManager.merge(profileBasic);
                    result.put(StringUtil.STATUS, StringUtil.OK);
                } catch (Exception e) {
                    System.out.println("Phone Number Deletion Failed!");
                    result.put(StringUtil.STATUS, StringUtil.FAIL);
                }
            }
        } else {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return result;
    }

    @Override
    public Map<String, String> addEmergencyContact(EmergencyContact emergencyContact, String userID) {
        Map<String, String> result = new HashMap<>();
        ProfileBasic profileBasic = getProfileBasicByUserID(userID);
        if (profileBasic != null) {
            try {
                emergencyContact.setProfileBasic(profileBasic);
                entityManager.persist(emergencyContact);
                result.put(StringUtil.STATUS, StringUtil.OK);
                result.put(StringUtil.ID, emergencyContact.getId().toString());
            } catch (Exception e) {
                result.put(StringUtil.STATUS, StringUtil.FAIL);
            }
        } else {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return result;
    }

    @Override
    public Map<String, String> deleteEmergencyContact(String emergencyContactID, String userID) {
        Map<String, String> result = new HashMap<>();
        try {
            EmergencyContact emergencyContact = entityManager.find(EmergencyContact.class,
                    Long.parseLong(emergencyContactID));
            //to protect one user deleting, another users information.
            if (emergencyContact.getProfileBasic().getUserId().toString().equals(userID)) {
                entityManager.remove(emergencyContact);
                result.put(StringUtil.STATUS, StringUtil.OK);
            } else {
                result.put(StringUtil.STATUS, StringUtil.FAIL);
                result.put(StringUtil.ERROR, StringUtil.UNAUTHERIZED);
            }
        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.ERROR, StringUtil.NULL);
        }
        return result;
    }

    @Override
    public Map<String, String> updateEmergencyContact(EmergencyContact emergencyContactUpdate, String userID) {
        Map<String, String> result = new HashMap<>();
        try {
            EmergencyContact emergencyContactOld = entityManager.find(EmergencyContact.class,
                    Long.parseLong(emergencyContactUpdate.getId().toString()));
            //to protect one user deleting, another users information.
            if (emergencyContactOld.getProfileBasic().getUserId().toString().equals(userID)) {
                emergencyContactUpdate.setProfileBasic(getProfileBasicByUserID(userID));
                entityManager.merge(emergencyContactUpdate);
                result.put(StringUtil.STATUS, StringUtil.OK);
            } else {
                result.put(StringUtil.STATUS, StringUtil.FAIL);
                result.put(StringUtil.ERROR, StringUtil.UNAUTHERIZED);
            }
        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.ERROR, StringUtil.NULL);
        }
        return result;
    }

    @Override
    public ProfileBasic getProfileBasicByUserID(String userID) {
        String sql = "SELECT * FROM profilebasic WHERE user_id =" + userID;
        List<ProfileBasic> list = entityManager
                .createNativeQuery(sql, ProfileBasic.class)
                .getResultList();
        ProfileBasic profileBasic = null;
        if (list.size() >= 1) {
            profileBasic = list.get(0);
        }
        return profileBasic;
    }

    @Override
    public ProfileBasic profileCheckingByUserID(String userID) {
        ProfileBasic profileBasic = new ProfileBasic();
        ProfileBasic basic = getProfileBasicByUserID(userID);
        if (basic != null) {
            profileBasic.setProfileFound(true);
            profileBasic.setName(basic.getName());
            profileBasic.setProfession(basic.getProfession());
            profileBasic.setBlood_Group(basic.getBlood_Group());
//            last days since blood given
            String lastBloodDonated = lastBloodDonated(DateUtil.getDate(), userID);
            profileBasic.setLastBloodGiven(lastBloodDonated);
        } else {
            profileBasic.setProfileFound(false);
        }
        return profileBasic;
    }

    @Override
    public String lastBloodDonated(String date, String userID) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("lastBloodDonated")
                .registerStoredProcedureParameter("user_id", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("needDate", String.class, ParameterMode.IN);

        query.setParameter("user_id", userID);
        query.setParameter("needDate", date);
        List<BigInteger> list = new ArrayList<>();
        try {
            // Execute query
            query.execute();
            list = query.getResultList();
        } finally {
            try {
                query.unwrap(ProcedureOutputs.class).release();
            } catch (Exception e) {
            }
        }
        BigInteger bigInteger = (BigInteger) list.get(0);
        int dateDiff = list.get(0).intValue();
        return Integer.toString(dateDiff);
    }


}// class

//public Map<String, String> deleteEmergencyContact(String emergencyContactID, String userID) {
//        Refactor : working Code, have to delete later.
//        profileBasic.getEmergency_contact().add(emergencyContact);
//
//        if (profileBasic != null) {
//            try {
//                entityManager.merge(profileBasic);
//                result.put(StringUtil.STATUS, StringUtil.OK);
//                String id = profileBasic
//                        .getEmergency_contact()
//                        .get(profileBasic
//                                .getEmergency_contact()
//                                .size() - 1)
//                        .getId()
//                        .toString();
////                result.put(StringUtil.ID, emergencyContact.getId().toString());
//                result.put(StringUtil.ID, id);
//            } catch (Exception e) {
//                e.printStackTrace();
//                result.put(StringUtil.STATUS, StringUtil.FAIL);
//            }
//        } else {
//            result.put(StringUtil.STATUS, StringUtil.FAIL);
//        }
//        return result;
