package com.donatedrop.util;

import java.util.Random;

public class Utils {

    public static String getLoggedUserID() {
//        return "15";
//        return "14294";
        return "11186";//http://localhost:4200/user/agent/dashboard
                        //donner assingment check.
//        return "12417";// to check my_posts
//        return "11196"; // getDonnerToAgentRequestToReview and count.
//        return "11186"; // commentUserId='11186' for commentWithUserInfo
//        return "11173"; // commentUserId='11186' for commentWithUserInfo
    }

    public static String getLoggedUserEmailID() {
        return "cse1301096@gmail.com";
    }

    public static String getRandomCode(int length) {
        String randomCode = "";
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            int type = rand.nextInt(1);
            if (type == 0) {
                randomCode += Integer.toString(rand.nextInt(10));
            } else if (type == 1) {
//                randomCode += rand.n;
            }
        }
        return randomCode;
    }

}// class
