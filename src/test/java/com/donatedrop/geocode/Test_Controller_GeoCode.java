/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.geocode;

import com.donatedrop.geocode.models.DistrictsEngName;
import com.donatedrop.geocode.models.DivisionsEngName;
import com.donatedrop.geocode.models.UnionsEngName;
import com.donatedrop.geocode.models.UpzillaEngName;
import com.donatedrop.profile.model.ProfileBasic;
import com.donatedrop.util.Utils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

/**
 * @author G7
 */
@SpringBootTest
public class Test_Controller_GeoCode extends AbstractTest {

    public Test_Controller_GeoCode() {
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void test_getDivisions() throws Exception {
        final String uriDivisions = "/public/geocode/divisions";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uriDivisions)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        List<DivisionsEngName> divisions = Arrays.asList(super.mapFromJson(content, DivisionsEngName[].class));
        divisions.forEach(s -> System.out.println(s));
        assertTrue(divisions.size() == 8);
    }

    @Test
    public void test_getDistricts() throws Exception {
        String divID = "3";//Khulna > 10 dist.
        String uri = "/public/geocode/districts?divID=" + divID;
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        List<DistrictsEngName> districtsEngNames = Arrays.asList(super.mapFromJson(content, DistrictsEngName[].class));
        districtsEngNames.forEach(s -> System.out.println(s));
        assert (districtsEngNames.size() == 10);
    }

    @Test
    public void test_getUpzillas() throws Exception {
        String distID = "27";//khulna, id=27 has 9 districts
        String uri = "/public/geocode/upzillas?distID=" + distID;
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        List<UpzillaEngName> upzillaEngNames = Arrays.asList(super.mapFromJson(content, UpzillaEngName[].class));
        upzillaEngNames.forEach(s -> System.out.println(s));
        assert (upzillaEngNames.size() == 9);
    }

    @Test
    public void test_getUnions() throws Exception {
        String distID = "211";//Dumuria, id=211 has 14 unions
        String uri = "/public/geocode/unions?upzID=" + distID;
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        List<UnionsEngName> unionsEngNameList = Arrays.asList(super.mapFromJson(content, UnionsEngName[].class));
        unionsEngNameList.forEach(s -> System.out.println(s));
        assert (unionsEngNameList.size() == 14);
    }


//    @Test
//    public void testSave() throws Exception {
//
//        String uri = "/public/profile/basic/save";
//
//        ProfileBasic profileBasic = new ProfileBasic();
//        profileBasic.setName("Khan Ajharul Abedeen");
//        profileBasic.setGender("Male");
//        profileBasic.setBlood_Group("A+");
//        profileBasic.setAvailable("0");
//        profileBasic.setMaritalStatus("NO");
//        profileBasic.setProfession("Freelance");
//        profileBasic.setCare_of("Khan Atiar Rahman.");
//        profileBasic.setUserId(Utils.getLoggedUserID());
//
////        MvcResult mvcResult = mvc.perform(
////                MockMvcRequestBuilders.post(uri)
////                        .contentType(MediaType.APPLICATION_JSON_VALUE)).content(super).andReturn();
//
//        String inputJson = super.mapToJson(profileBasic);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        System.out.println(content);
//
////        List<DistrictsEngName> districtsEngNames = Arrays.asList(super.mapFromJson(content, DistrictsEngName[].class));
////        districtsEngNames.forEach(s -> System.out.println(s));
////        assert (districtsEngNames.size() == 10);
//    }

}
