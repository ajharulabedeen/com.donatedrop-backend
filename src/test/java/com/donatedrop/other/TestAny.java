package com.donatedrop.other;

import com.donatedrop.geocode.Dao_GeoCode_I;
import com.donatedrop.geocode.models.DistrictsEngName;
import com.donatedrop.geocode.models.DivisionsEngName;
import com.donatedrop.geocode.models.UnionsEngName;
import com.donatedrop.geocode.models.UpzillaEngName;
import com.donatedrop.util.AddressType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class TestAny {

    @Autowired
    DumpDao daoDump;

    @Test
    public void getDumpDate() {
        System.out.println("\n");
        for (int x = 0; x < 100; x++) {
            System.out.println(DumpData.getDate());
        }
        System.out.println("\n");
    }

    @Test
    public void getDumpDaoGetAddressString() {
        System.out.println(daoDump.getAddressString(AddressType.EMERGENCY.toString()));
    }

    @Test
    public void testEnum() {
        System.out.println(AddressType.PERMANENT);
    }

    @Test
    public void testBCruserCreationypt() {
        System.out.println("Test!");
        int i = 0;
        while (i < 10) {
            String password = "password";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);
            System.out.println(hashedPassword);
            i++;
        }
    }

    @Test
    public void testGetPhoneNumber() {
        for (int x = 0; x < 50; x++) {
            System.out.println(DumpData.getPhoneNumber());
        }
    }

    @Test
    public void testGetName() {
        for (int x = 0; x < 50; x++) {
            System.out.println(DumpData.getName());
        }
    }

    @Test
    public void testGetAdd() {
        for (int x = 0; x < 5; x++) {
            System.out.println(DumpData.getAddress());
        }
    }

    @Test
    public void testGetStreetAddress() {
        System.out.println(DumpData.getStreetAddress());
    }

    @Test
    public void testGenerelDao() {
        daoDump.getAllUsers().forEach(user -> System.out.println(user.getId()));
    }

    @Test
    public void testGenerelDaoGetAllProfileBasics() {
        System.out.println("\n");
        daoDump.getAllProfileBasic(10, 15).forEach(user -> System.out.println(user.getGender()));
    }

    @Test
    public void testGetUsers() {
        daoDump.getUsers(0, 10).forEach(user -> System.out.println(user.getId()));
    }

    @Test
    public void testAgentRequestReview() {
        daoDump.getAllAgentRequestReview(0, 30, "present_div", "%%").forEach(agentRequestReview -> {
//            String addr = agentRequestReview.getPermanentStreet()
//                    + " " + agentRequestReview.getPermanentUnion()
//                    + ", " + agentRequestReview.getPermanentUpz()
//                    + ", " + agentRequestReview.getPermanentDist()
//                    + ", " + agentRequestReview.getPermanentDiv();
//            System.out.println(addr);
            System.out.println(agentRequestReview.getUsername());
            System.out.println("-------------------------------");
        });
    }

    @Test
    public void testGetAllAgentRequestReviewPhoneNumber() {
        daoDump.getAllAgentRequestReviewPhoneNumber(0, 30, "present_div", "%81%").forEach(agentRequestToReview -> {
            System.out.println(agentRequestToReview);
        });
    }

    @Test
    public void testGetNotRequestedDonnerToAgentUsers() {
        daoDump.getNotRequestedDonnerToAgentUsers(0, 30).forEach(agentRequestToReview -> {
            System.out.println(agentRequestToReview);
        });
        System.out.println("\nTotal Count : " + daoDump.getNotRequestedDonnerToAgentUsersCount() + "\n");
    }


    @Test
    public void testGetAcceptedUserIDAsAgent() {
        daoDump.getAcceptedUserIDAsAgent(0, 30).forEach(userID -> {
            System.out.println(userID);
        });
    }

}// class
