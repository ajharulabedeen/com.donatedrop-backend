package com.donatedrop.agent.doner;

import com.donatedrop.agent.donner.Dao_AgentDonner_I;
import com.donatedrop.agent.donner.Dao_DonnerAssign_I;
import com.donatedrop.agent.donner.models.DonnerAssingment;
import com.donatedrop.agent.donner.models.DonnerRequestToAgent;
import com.donatedrop.agent.donner.models.RequestSearchDonnerAssing;
import com.donatedrop.agent.donner.models.RequestSearchReview;
import com.donatedrop.agent.models.StatusType;
import com.donatedrop.other.DumpDao;
import com.donatedrop.other.DumpData;
import com.donatedrop.util.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


import java.util.Map;
import java.util.Random;

@SpringBootTest
public class Test_Dao_DonnerAssign_Impl {

    @Autowired
    DumpDao dumpDao;

    @Autowired
    Dao_DonnerAssign_I dao_donnerAssign_i;

    @Test
    public void testSaveDonnerAssingment() {
//        for (int x = 0; x < 10; x++) {
        Random random = new Random();
        String agentUserID = dumpDao.getAgentRequest(0, 100, StringUtil.ACCEPT)
                .get(random.nextInt(7)).getUserID();
        String donnerUserID
                = dumpDao.getDonnerOfAAgent(0, 100, agentUserID).get(random.nextInt(15)).getUserIdDonner();

        DonnerAssingment donnerAssingment = new DonnerAssingment();
        donnerAssingment.setAgentId(agentUserID);
        donnerAssingment.setDonnerId(donnerUserID);
        donnerAssingment.setNeedDate(DumpData.getDate());
        Map<String, String> result = dao_donnerAssign_i.save(donnerAssingment);
//        Map<String, String> result = null;
        System.out.println("\n" + result + "\n");
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
//        } //for (int x = 0; x < 10; x++) {

    }

    @Test
    public void testFindOneByID() {
        DonnerAssingment donnerAssingment = dao_donnerAssign_i.findOne("21440");
        System.out.println(donnerAssingment.toString());
        assertNotNull(donnerAssingment);
    }

    @Test
    public void testUpdate() {
        DonnerAssingment donnerAssingmentOld = dao_donnerAssign_i.findOne("21440");
        String newNote = "By emial confirmation--------------!";
        donnerAssingmentOld.setAssingNote(newNote);
        Map<String, String> result = dao_donnerAssign_i.update(donnerAssingmentOld);
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
        DonnerAssingment donnerAssingmentNew = dao_donnerAssign_i.findOne("21440");
        assertEquals(donnerAssingmentNew.getAssingNote(), newNote);
        System.out.println(donnerAssingmentOld.toString());
        System.out.println(donnerAssingmentNew.toString());
    }

    @Test
    public void testDelete() {
        Map<String, String> result = dao_donnerAssign_i.delete("21438");
        assertEquals(StringUtil.OK, result.get(StringUtil.STATUS));
        System.out.println(result);
    }

    @Test
    public void testGetAssingments() {
        RequestSearchDonnerAssing requestSearchReview =
//                new RequestSearchDonnerAssing(0, 5, "phonenumber", "%%", StatusType.ZERO);
//                new RequestSearchDonnerAssing(0, 5, "phonenumber", "%013%", StatusType.ZERO);//2
                new RequestSearchDonnerAssing(0, 20, "email", "%103%", StatusType.ZERO);
        requestSearchReview.setAgentID("11186");
        System.out.println("\nrequestSearchReview : " + requestSearchReview.toString());
        dao_donnerAssign_i.getAssingments(requestSearchReview).forEach(donnerToAgentRequestReview -> {
            System.out.println(donnerToAgentRequestReview.toString());
        });
    }

    @Test
    public void testGetAssingmentsCount() {
        RequestSearchDonnerAssing requestSearchReview =
//                new RequestSearchDonnerAssing(0, 5, "phonenumber", "%%", StatusType.ZERO);
//                new RequestSearchDonnerAssing(0, 5, "phonenumber", "%013%", StatusType.ZERO);//2
                new RequestSearchDonnerAssing(0, 20, "email", "%%", StatusType.ZERO);
        requestSearchReview.setAgentID("11186");
        System.out.println("\nrequestSearchReview : " + requestSearchReview.toString());
        Map<String, String> countMap = dao_donnerAssign_i.getAssingmentsCount(requestSearchReview);
        System.out.println(countMap);
    }

}
