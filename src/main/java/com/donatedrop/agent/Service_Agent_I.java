package com.donatedrop.agent;

import com.donatedrop.agent.models.AgentBasic;
import com.donatedrop.agent.models.UserPublicContact;

public interface Service_Agent_I {
    public UserPublicContact getUserPublicContact(String userID);
}
