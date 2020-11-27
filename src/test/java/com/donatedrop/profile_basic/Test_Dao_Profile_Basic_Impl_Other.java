package com.donatedrop.profile_basic;

import com.donatedrop.models.Address;
import com.donatedrop.other.DumpDao;
import com.donatedrop.other.DumpData;
import com.donatedrop.other.TestUtil;
import com.donatedrop.profile.basic.Dao_Profile_Basic_I;
import com.donatedrop.profile.model.EmergencyContact;
import com.donatedrop.profile.model.PhoneNumber;
import com.donatedrop.profile.model.ProfileBasic;
import com.donatedrop.util.AddressType;
import com.donatedrop.util.DateUtil;
import com.donatedrop.util.StringUtil;
import com.donatedrop.util.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.omg.IOP.ProfileIdHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class Test_Dao_Profile_Basic_Impl_Other {

    @Autowired
    DumpDao dumpDao;

    @Autowired
    Dao_Profile_Basic_I dao_Profile_Basic_I;

    @Test
    @Order(-1)
    public void test0_DaoObject() {
        System.out.println("\n\n\n---M1---\n\n\n");
        assert (dao_Profile_Basic_I != null);
    }

    @Test
    public void testProfileCheckingByUserID() {
        //tested by : 11148
        ProfileBasic profileBasic = dao_Profile_Basic_I.profileCheckingByUserID("11148");
        System.out.println(profileBasic.isProfileFound());
        System.out.println(profileBasic.getLastBloodGiven());
    }


}
