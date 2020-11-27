package com.donatedrop.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

public class Test_Any {

    @Test
    public void testAny() {
        for (int x = 0; x < 100; x++) {
            String s = RandomStringUtils.randomAlphanumeric(10);
            System.out.println(s);
        }
    }

}// class
