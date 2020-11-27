package com.donatedrop.other;

import com.donatedrop.util.AddressType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderTest {
    @Test
    void test0() {
        System.out.println(AddressType.PERMANENT);
        System.out.println(AddressType.PRESENT);
        System.out.println(AddressType.OTHER);
        System.out.println(AddressType.values().length);
        System.out.println("M0");
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(3)
    void test1() {
        System.out.println("1");
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(1)
    void test2() {
        System.out.println("2");
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(2)
    void test3() {
        System.out.println("3");
        assertEquals(2, 1 + 1);
    }

    @Test
    void test4() {
        System.out.println("4");
        assertEquals(2, 1 + 1);
    }
}
