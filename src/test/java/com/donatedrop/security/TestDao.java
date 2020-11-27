package com.donatedrop.security;

import org.hibernate.tuple.entity.EntityTuplizer;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TestDao {

    @PersistenceContext
    EntityManager entityManager;

    @org.junit.jupiter.api.Test
    public void testSaveUsers(){

    }

}
