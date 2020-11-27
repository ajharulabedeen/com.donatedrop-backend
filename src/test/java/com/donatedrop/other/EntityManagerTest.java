package com.donatedrop.other;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntityManagerTest {

    @Autowired
    EntityManager entityManager;

    @Test
    @Order(1)
    public void testEntityManager() {
        System.out.println("\n\nentityManager : " + entityManager.toString() + "\n\n");
        assert (entityManager != null);
    }


}
