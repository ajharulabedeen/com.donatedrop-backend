/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.other;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G7
 */
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodOrderTest {

    @Test
    public void test0() {
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(3)
    public void test1() {
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(1)
    public void test2() {
        assertEquals(2, 1 + 1);
    }

    @Test
    @Order(2)
    public void test3() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void test4() {
        assertEquals(2, 1 + 1);
    }

}
