package com.donatedrop.agent.models;

import com.donatedrop.profile.model.PhoneNumber;

import java.util.List;

public class UserPublicContact {
    private String contactType;
    private String name;
    private String email;
    private List<PhoneNumber> phoneNumbers;

    public UserPublicContact() {

    }

    public UserPublicContact(String contactType, String name, String email, List<PhoneNumber> phoneNumbers) {
        this.contactType = contactType;
        this.name = name;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return "UserContact{" +
                "contactType='" + contactType + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
