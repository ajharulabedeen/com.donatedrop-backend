package com.donatedrop.other;

import com.donatedrop.geocode.Dao_GeoCode_I;
import com.donatedrop.geocode.models.DistrictsEngName;
import com.donatedrop.geocode.models.DivisionsEngName;
import com.donatedrop.geocode.models.UnionsEngName;
import com.donatedrop.geocode.models.UpzillaEngName;
import com.donatedrop.models.Address;
import com.donatedrop.profile.basic.Dao_Profile_Basic_I;
import com.donatedrop.profile.basic.Dao_Profile_Basic_Impl;
import com.donatedrop.profile.model.EmergencyContact;
import com.donatedrop.profile.model.PhoneNumber;
import com.donatedrop.profile.model.ProfileBasic;
import com.donatedrop.security.models.User;
import com.donatedrop.security.repo.UserRepository;
import com.donatedrop.util.AddressType;
import com.donatedrop.util.DateUtil;
import com.donatedrop.util.Utils;
import org.hibernate.annotations.NaturalId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class DumpDataInsertion {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Dao_Profile_Basic_I dao_profile_basic_i;

    @Autowired
    Dao_Profile_Basic_Impl dao_profile_basic_impl;

    @Autowired
    Dao_GeoCode_I dao_GeoCode_I;

    @Autowired
    DumpDao dumpDao;

    @Test
    public void testEntityManager() throws Exception {
    }

    //start : profile
    @Test
    public void testBatchProfileBasicInsert() throws Exception {
//        Working Code, will insert all.
        //        List<User> idList = userRepository.findAll();
//        List<ProfileBasic> basicList = new ArrayList<>();
//        for (Iterator<User> iterator = idList.iterator(); iterator.hasNext(); ) {
//            User user = iterator.next();
//            ProfileBasic profileBasic = getProfile(user.getId().toString());
//            basicList.add(profileBasic);
//        }
        List<User> idList = dumpDao.getUserIDNotInProfileID(100);
        List<ProfileBasic> basicList = new ArrayList<>();
        for (Iterator<User> iterator = idList.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            ProfileBasic profileBasic = getProfile(user);
            basicList.add(profileBasic);
        }
        dumpDao.insertProfileBasicBatch(basicList);
    }

    @Test
    public void deleteAllProfile() throws Exception {
//        dumpDao.deleteAll_ProfileBasic();
    }
    //end : profile

    public void insertData() {
        for (int x = 0; x < 1000; x++) {
            System.out.println(x);
            String userID = userCreation(Integer.toString(x));
//            saveProfile(userID);
        }// for
    }

    public String userCreation(String mail) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        mail = "user" + mail + "@gmail.com";
        user.setUserName(mail);
        user.setPassword(passwordEncoder.encode("123456"));
        user.setEnabled(true);
        user.setAuthorities(new ArrayList<>());
        userRepository.save(user);
        return user.getId().toString();
    }

    public ProfileBasic getProfile(User user) {
        System.out.println("\nProfile Basic Dao Test!\n");
        Random r = new Random();

        ProfileBasic profileBasic = new ProfileBasic();
        profileBasic.setName(DumpData.getName());

        List<Address> aList = new ArrayList<>();
        aList.add(getAddress(AddressType.PRESENT.toString()));
        aList.add(getAddress(AddressType.PERMANENT.toString()));
        profileBasic.setAddress(aList);

        List<EmergencyContact> emergencyContacts = new ArrayList<>();
        for (int x = 0; x < r.nextInt(4); x++) {
//            String name, String phone, String mail, String address, String relation, String addDate
            EmergencyContact emergencyContact1 = new EmergencyContact(
                    DumpData.getName() + "",        //  String name,
                    DumpData.getPhoneNumber() + "",// String phone,
                    DumpData.getName() + "@mail.com",// String mail,
                    DumpData.getAddress() + "",// String address,
                    DumpData.getRelation() + "",// String relation,
                    DateUtil.getDate() + ""// String addDate
//  DumpData.getName(),//DumpData.getName(),
//  "",//DumpData.getPhoneNumber(),
//  "",
//  DumpData.getName() + "@mail.com",
//  DumpData.getAddress(),
//  DumpData.getRelation()
            );
            emergencyContacts.add(emergencyContact1);
        }
        profileBasic.setEmergency_contact(emergencyContacts);

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for (int x = 0; x < r.nextInt(4); x++) {
            phoneNumbers.add(new PhoneNumber(DumpData.getPhoneNumber()));
        }
        profileBasic.setPhone_number(phoneNumbers);

        profileBasic.setGender(DumpData.getGender());
        profileBasic.setBlood_Group(DumpData.getBloodGroup());
        profileBasic.setAvailable(Integer.toString(r.nextInt(1)));
        profileBasic.setMaritalStatus(DumpData.getMarterialStatus());
        profileBasic.setProfession(DumpData.getProfession());
        profileBasic.setCare_of(DumpData.getName());
//        profileBasic.setBirthDate(DateUtil.getDate());
        profileBasic.setReligion(DumpData.getReligion());
        profileBasic.setUserId(user.getId().toString());
        profileBasic.setEmail(user.getUserName());
        profileBasic.setBirthDate(DateUtil.getDate());

//        dao_profile_basic_i.save(profileBasic);
        return profileBasic;
    }

    public Address getAddress(String type) {
        Random r = new Random();
        String divID = "";
        String divName = "";
        String distID = "";
        String distName = "";
        String upzID = "";
        String upzName = "";
        String unionID = "";
        String unionName = "";

        Address address = new Address();
        address.setType(type);
        try {
            List<DivisionsEngName> divisionsList = dao_GeoCode_I.getDivisions();
            int divRand = r.nextInt(divisionsList.size() - 1);
            divID = divisionsList.get(divRand).getId().toString();
            divName = divisionsList.get(divRand).getName().toString();
            address.setDivision(divName);
        } catch (Exception e) {
            System.out.println("Exception in Gettting Division!" + divID + "\n");
        }
        try {
            List<DistrictsEngName> districtsEngNameList = dao_GeoCode_I.getDistricts(divID);
            int distRand = r.nextInt(districtsEngNameList.size() - 1);
            distID = districtsEngNameList.get(distRand).getId().toString();
            distName = districtsEngNameList.get(distRand).getName().toString();
            address.setDistrict(distName);
        } catch (Exception e) {
            System.out.println("Exception in Gettting District! : " + distID + "\n");
        }
        try {
            List<UpzillaEngName> upzillaEngNameList = dao_GeoCode_I.getUpzillas(distID);
            int upzRand = r.nextInt(upzillaEngNameList.size() - 1);
            upzID = upzillaEngNameList.get(upzRand).getId().toString();
            upzName = upzillaEngNameList.get(upzRand).getName().toString();
            address.setUpzilla(upzName);
        } catch (Exception e) {
            System.out.println("Exception in Gettting Upzilla! : " + upzID + "\n");
        }
        try {
            List<UnionsEngName> unionsEngNameList = dao_GeoCode_I.getUnions(upzID);
            int unionRand = r.nextInt(unionsEngNameList.size() - 1);
            unionID = unionsEngNameList.get(unionRand).getId().toString();
            unionName = unionsEngNameList.get(unionRand).getName().toString();
            address.setUnion_ward(unionName);
        } catch (Exception e) {
            System.out.println("Exception in Gettting Unions! : " + unionID + "\n");
        }
        address.setStreet_address(DumpData.getStreetAddress());
        return address;
    }

}// class
