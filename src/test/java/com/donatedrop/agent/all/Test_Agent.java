package com.donatedrop.agent.all;

import com.donatedrop.agent.Dao_Agent_I;
import com.donatedrop.agent.Service_Agent_I;
import com.donatedrop.agent.admin.Dao_AgentAdmin_I;
import com.donatedrop.agent.admin.model.AgentRequest;
import com.donatedrop.agent.admin.model.RequestAdminNote;
import com.donatedrop.agent.admin.model.RequestApplicantNote;
import com.donatedrop.agent.admin.model.RequestPersonalNote;
import com.donatedrop.agent.donner.models.RequestSearchReview;
import com.donatedrop.agent.models.AgentBasic;
import com.donatedrop.agent.models.RequestReviewRequest;
import com.donatedrop.agent.models.StatusType;
import com.donatedrop.agent.models.UserPublicContact;
import com.donatedrop.other.DumpDao;
import com.donatedrop.other.TestUtil;
import com.donatedrop.util.DateUtil;
import com.donatedrop.util.StringUtil;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.NoResultException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class Test_Agent {

    @Autowired
    Dao_Agent_I dao_agent_i;

    @Autowired
    Service_Agent_I service_agent_i;

    @Autowired
    DumpDao dumpDao;

    @Test
    @Order(1)
    public void testObject() {
        System.out.println("\nM2\n");
        System.out.println("\ndao_agent_i : " + dao_agent_i.toString() + "\n");
    }

    @Test
    @Order(1)
    public void testGetAgentOfAUser() {
//        AgentBasic agentBasic = dao_agent_i.getAgentBasic("11214");
        try {
            AgentBasic agentBasic = dao_agent_i.getAgentBasic("11214");
            System.out.println("\n" + agentBasic.toString());
        } catch (NoResultException noResultException) {
            System.out.println("NO DATA FOUND");
        } catch (Exception e) {
            System.out.println("Exception Happned!");
        }
    }

    @Test
    public void testGetUserPublicContact() {
//        UserPublicContact userPublicContact = service_agent_i.getUserPublicContact("11214");//agent
//        UserPublicContact userPublicContact = service_agent_i.getUserPublicContact("11236");//agent
        UserPublicContact userPublicContact = service_agent_i.getUserPublicContact("11148");//no agent -> personal
//        UserPublicContact userPublicContact = service_agent_i.getUserPublicContact("1123");//agent
        System.out.println(userPublicContact.toString());
    }

    @Test
    public  void lastBloodDonated(){
        dumpDao.lastBloodDonated();
    }

}
