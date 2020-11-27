package com.donatedrop.other;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderTest_2 {
    @Test
    @Order(0)
    void test0() {
        System.out.println("0");
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(1)
    void test1() {
        System.out.println("1");
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(2)
    void test2() {
        System.out.println("2");
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(3)
    void test3() {
        System.out.println("3");
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(4)
    void test4() {
        System.out.println("4");
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(5)
    void test5() {
        System.out.println("5");
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(9)
    void test9() {
        System.out.println("9");
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(10)
    void test10() {
        System.out.println("10");
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(-1)
    void test11() {
        System.out.println("11");
        assertEquals(2, 1 + 1);
    }

}
