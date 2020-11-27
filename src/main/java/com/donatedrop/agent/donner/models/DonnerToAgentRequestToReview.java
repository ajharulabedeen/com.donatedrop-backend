/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.agent.donner.models;

import com.donatedrop.models.Address;
import com.donatedrop.profile.model.PhoneNumber;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author G7
 */
@Entity
@Table(name = "donner_to_agent_request_review")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DonnerToAgentRequestToReview.findAll", query = "SELECT d FROM DonnerToAgentRequestToReview d")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByProfileId", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.profileId = :profileId")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByAvailable", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.available = :available")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByBirthDate", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.birthDate = :birthDate")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByBloodGroup", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.bloodGroup = :bloodGroup")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByCareOf", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.careOf = :careOf")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByEmail", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.email = :email")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByGender", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.gender = :gender")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByMaritalStatus", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.maritalStatus = :maritalStatus")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByName", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.name = :name")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByProfession", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.profession = :profession")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByReligion", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.religion = :religion")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByUserId", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.userId = :userId")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByRequestDonnerToAgentId", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.requestDonnerToAgentId = :requestDonnerToAgentId")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByAcceptDate", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.acceptDate = :acceptDate")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByRejectDate", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.rejectDate = :rejectDate")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByRemoveDate", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.removeDate = :removeDate")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByRequestDate", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.requestDate = :requestDate")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByStatus", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.status = :status")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByUserIdDonner", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.userIdDonner = :userIdDonner")
        , @NamedQuery(name = "DonnerToAgentRequestToReview.findByUsername", query = "SELECT d FROM DonnerToAgentRequestToReview d WHERE d.username = :username")})

public class DonnerToAgentRequestToReview implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "profile_id")
    private long profileId;
    @Column(name = "available")
    private String available;
    @Column(name = "birth_date")
    private String birthDate;
    @Column(name = "blood_Group")
    private String bloodGroup;
    @Column(name = "care_of")
    private String careOf;
    @Column(name = "email")
    private String email;
    @Column(name = "gender")
    private String gender;
    @Column(name = "marital_status")
    private String maritalStatus;
    @Column(name = "name")
    private String name;
    @Column(name = "profession")
    private String profession;
    @Column(name = "religion")
    private String religion;
    @Column(name = "user_id")
    private String userId;
    @Basic(optional = false)
    @Column(name = "request_donner_to_agent_id")
    private long requestDonnerToAgentId;
    @Column(name = "accept_date")
    private String acceptDate;
    @Lob
    @Column(name = "note_agent")
    private String noteAgent;
    @Lob
    @Column(name = "note_agent_personal")
    private String noteAgentPersonal;
    @Lob
    @Column(name = "note_donner")
    private String noteDonner;
    @Column(name = "reject_date")
    private String rejectDate;
    @Column(name = "remove_date")
    private String removeDate;
    @Column(name = "request_date")
    private String requestDate;
    @Column(name = "status")
    private String status;
    @Column(name = "user_id_donner")
    private String userIdDonner;
    @Column(name = "USERNAME")
    private String username;

    @OneToMany
    //    @OneToMany(fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "profile_id")
    private List<PhoneNumber> phone_number;

    //    @OneToMany
    @OneToMany(fetch = FetchType.EAGER)
//    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "profile_id")
    private List<Address> addressList;

    public DonnerToAgentRequestToReview() {
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getCareOf() {
        return careOf;
    }

    public void setCareOf(String careOf) {
        this.careOf = careOf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getRequestDonnerToAgentId() {
        return requestDonnerToAgentId;
    }

    public void setRequestDonnerToAgentId(long requestDonnerToAgentId) {
        this.requestDonnerToAgentId = requestDonnerToAgentId;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getNoteAgent() {
        return noteAgent;
    }

    public void setNoteAgent(String noteAgent) {
        this.noteAgent = noteAgent;
    }

    public String getNoteAgentPersonal() {
        return noteAgentPersonal;
    }

    public void setNoteAgentPersonal(String noteAgentPersonal) {
        this.noteAgentPersonal = noteAgentPersonal;
    }

    public String getNoteDonner() {
        return noteDonner;
    }

    public void setNoteDonner(String noteDonner) {
        this.noteDonner = noteDonner;
    }

    public String getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(String rejectDate) {
        this.rejectDate = rejectDate;
    }

    public String getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(String removeDate) {
        this.removeDate = removeDate;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserIdDonner() {
        return userIdDonner;
    }

    public void setUserIdDonner(String userIdDonner) {
        this.userIdDonner = userIdDonner;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<PhoneNumber> getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(List<PhoneNumber> phone_number) {
        this.phone_number = phone_number;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @Override
    public String toString() {
        return "DonnerToAgentRequestToReview{" +
                "profileId=" + profileId +
                ", available='" + available + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", careOf='" + careOf + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", name='" + name + '\'' +
                ", profession='" + profession + '\'' +
                ", religion='" + religion + '\'' +
                ", userId='" + userId + '\'' +
                ", requestDonnerToAgentId=" + requestDonnerToAgentId +
                ", acceptDate='" + acceptDate + '\'' +
                ", noteAgent='" + noteAgent + '\'' +
                ", noteAgentPersonal='" + noteAgentPersonal + '\'' +
                ", noteDonner='" + noteDonner + '\'' +
                ", rejectDate='" + rejectDate + '\'' +
                ", removeDate='" + removeDate + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", status='" + status + '\'' +
                ", userIdDonner='" + userIdDonner + '\'' +
                ", username='" + username + '\'' +
                ", phone_number=" + phone_number +
                ", addressList=" + addressList +
                '}';
    }
}
