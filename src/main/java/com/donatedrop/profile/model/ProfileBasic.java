/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.profile.model;

import com.donatedrop.models.Address;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author G7
 */
@Entity
public class ProfileBasic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_id")
    private Long id;

    //    @Size(max = 256)
    @Column(name = "user_id")
    private String userId;

    //    @Size(max = 512)
    @Column(name = "name")
    private String name;

    //    @Size(max = 512)
    @Column(name = "birth_date")
    private String birthDate;

    //    @Size(max = 1024)
    @Column(name = "care_of")
    private String care_of;

    //    @Size(max = 512)
    @Column(name = "gender")
    private String gender;

    //    @Size(max = 256)
    @Column(name = "marital_status")
    private String maritalStatus;

    //    @Size(max = 256)
    @Column(name = "profession")
    private String profession;

    @Column(name = "blood_Group")
    private String blood_Group;

    @Column(name = "available")
    private String available;

    @Column(name = "religion")
    private String religion;

    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    private List<Address> address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    private List<PhoneNumber> phone_number;

    //    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "emergency_contact")
    @JsonManagedReference
    private List<EmergencyContact> emergency_contact;


    @Transient
    private boolean profileFound;

    @Transient
    private String lastBloodGiven;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfileBasic)) {
            return false;
        }
        ProfileBasic other = (ProfileBasic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCare_of() {
        return care_of;
    }

    public void setCare_of(String care_of) {
        this.care_of = care_of;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getBlood_Group() {
        return blood_Group;
    }

    public void setBlood_Group(String blood_Group) {
        this.blood_Group = blood_Group;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public List<PhoneNumber> getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(List<PhoneNumber> phone_number) {
        this.phone_number = phone_number;
    }

    public List<EmergencyContact> getEmergency_contact() {
        return emergency_contact;
    }

    public void setEmergency_contact(List<EmergencyContact> emergency_contact) {
        this.emergency_contact = emergency_contact;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public boolean isProfileFound() {
        return profileFound;
    }

    public void setProfileFound(boolean profileFound) {
        this.profileFound = profileFound;
    }

    public String getLastBloodGiven() {
        return lastBloodGiven;
    }

    public void setLastBloodGiven(String lastBloodGiven) {
        this.lastBloodGiven = lastBloodGiven;
    }

    @Override
    public String toString() {
        return "ProfileBasic{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", care_of='" + care_of + '\'' +
                ", gender='" + gender + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", profession='" + profession + '\'' +
                ", blood_Group='" + blood_Group + '\'' +
                ", available='" + available + '\'' +
                ", religion='" + religion + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone_number=" + phone_number +
                ", emergency_contact=" + emergency_contact +
                '}';
    }
}
