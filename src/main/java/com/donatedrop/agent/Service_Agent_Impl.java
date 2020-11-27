package com.donatedrop.agent;

import com.donatedrop.agent.models.AgentBasic;
import com.donatedrop.agent.models.UserPublicContact;
import com.donatedrop.agent.models.UserPublicContactType;
import com.donatedrop.post.Service_Post_I;
import com.donatedrop.profile.basic.Dao_Profile_Basic_I;
import com.donatedrop.profile.model.ProfileBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;

@Component
public class Service_Agent_Impl implements Service_Agent_I {
    @Autowired
    Dao_Agent_I dao_agent_i;

    @Autowired
    Dao_Profile_Basic_I dao_profile_basic_i;

    public UserPublicContact getUserPublicContact(String userID) {
        AgentBasic agentBasic = null;
        UserPublicContact userPublicContact = new UserPublicContact();
        try {
            agentBasic = dao_agent_i.getAgentBasic(userID);
            userPublicContact.setContactType(UserPublicContactType.AGENT.toString());
            userPublicContact.setName(agentBasic.getName());
            userPublicContact.setEmail(agentBasic.getEmmail());
            userPublicContact.setPhoneNumbers(agentBasic.getPhoneNumbers());
            return userPublicContact;
        } catch (NoResultException noResultException) {
            System.out.println("No Agent Found");
        } catch (Exception e) {
            System.out.println("Exception Happned!");
        }
        if (agentBasic == null) {
            ProfileBasic profileBasic = dao_profile_basic_i.findOneByUserIDWithChild(userID);
            if (profileBasic != null) {
                userPublicContact.setContactType(UserPublicContactType.PERSONAL.toString());
                userPublicContact.setName(profileBasic.getName());
                userPublicContact.setEmail(profileBasic.getEmail());
                userPublicContact.setPhoneNumbers(profileBasic.getPhone_number());
                return userPublicContact;
            } else {
                userPublicContact.setContactType(UserPublicContactType.ERROR.toString());
            }
        }
        return userPublicContact;
    }
}
