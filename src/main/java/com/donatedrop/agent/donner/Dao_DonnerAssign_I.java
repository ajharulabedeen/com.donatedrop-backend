package com.donatedrop.agent.donner;

import com.donatedrop.agent.donner.models.DonnerAssingShow;
import com.donatedrop.agent.donner.models.DonnerAssingment;
import com.donatedrop.agent.donner.models.DonnerRequestToAgent;
import com.donatedrop.agent.donner.models.RequestSearchDonnerAssing;
import com.donatedrop.profile.model.ProfileBasic;
import org.springframework.web.context.request.RequestScope;

import java.util.List;
import java.util.Map;

public interface Dao_DonnerAssign_I {
    public Map<String, String> save(DonnerAssingment donnerAssingment);

    public DonnerAssingment findOne(String donnerAssingment);

    public Map<String, String> delete(String donnerAssingmentID);

    public Map<String, String> update(DonnerAssingment donnerAssingment);

    public Map<String, String> complete(String donnerAssingmentID);

    public List<DonnerAssingShow> getAssingments(RequestSearchDonnerAssing requestSearchDonnerAssing);

    public Map<String, String> getAssingmentsCount(RequestSearchDonnerAssing requestSearchDonnerAssing);

}
