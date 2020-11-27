package com.donatedrop.agent.donner;

import com.donatedrop.agent.Service_Agent_I;
import com.donatedrop.agent.donner.models.DonnerAssingShow;
import com.donatedrop.agent.donner.models.DonnerAssingment;
import com.donatedrop.agent.donner.models.RequestSearchDonnerAssing;
import com.donatedrop.agent.models.UserPublicContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Service_DonnerAssign_Impl implements Service_DonnerAssign_I {

    @Autowired
    Dao_DonnerAssign_I dao_donnerAssign_i;

    @Override
    public Map<String, String> save(DonnerAssingment donnerAssingment) {
        return dao_donnerAssign_i.save(donnerAssingment);
    }

    @Override
    public DonnerAssingment findOne(String donnerAssingment) {
        return dao_donnerAssign_i.findOne(donnerAssingment);
    }

    @Override
    public Map<String, String> delete(String donnerAssingmentID) {
        return dao_donnerAssign_i.delete(donnerAssingmentID);
    }

    @Override
    public Map<String, String> update(DonnerAssingment donnerAssingment) {
        return dao_donnerAssign_i.update(donnerAssingment);
    }

    @Override
    public Map<String, String> complete(String donnerAssingmentID) {
        return dao_donnerAssign_i.complete(donnerAssingmentID);
    }

    @Override
    public List<DonnerAssingShow> getAssingments(RequestSearchDonnerAssing requestSearchDonnerAssing) {
        return dao_donnerAssign_i.getAssingments(requestSearchDonnerAssing);
    }

    @Override
    public Map<String, String> getAssingmentsCount(RequestSearchDonnerAssing requestSearchDonnerAssing) {
        return dao_donnerAssign_i.getAssingmentsCount(requestSearchDonnerAssing);
    }
}
