/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.agent.admin.model;

import com.donatedrop.models.Address;
import com.donatedrop.profile.model.PhoneNumber;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Dell
 */
@Entity
@Table(name = "agent_request_review")
@XmlRootElement
//@NamedQueries({
//        @NamedQuery(name = "AgentRequestReview.findAll", query = "SELECT a FROM AgentRequestToReview a")
//        , @NamedQuery(name = "AgentRequestReview.findByProfileId", query = "SELECT a FROM AgentRequestReview a WHERE a.profileId = :profileId")
//        , @NamedQuery(name = "AgentRequestReview.findByAvailable", query = "SELECT a FROM AgentRequestReview a WHERE a.available = :available")
//        , @NamedQuery(name = "AgentRequestReview.findByBirthDate", query = "SELECT a FROM AgentRequestReview a WHERE a.birthDate = :birthDate")
//        , @NamedQuery(name = "AgentRequestReview.findByBloodGroup", query = "SELECT a FROM AgentRequestReview a WHERE a.bloodGroup = :bloodGroup")
//        , @NamedQuery(name = "AgentRequestReview.findByCareOf", query = "SELECT a FROM AgentRequestReview a WHERE a.careOf = :careOf")
//        , @NamedQuery(name = "AgentRequestReview.findByEmail", query = "SELECT a FROM AgentRequestReview a WHERE a.email = :email")
//        , @NamedQuery(name = "AgentRequestReview.findByGender", query = "SELECT a FROM AgentRequestReview a WHERE a.gender = :gender")
//        , @NamedQuery(name = "AgentRequestReview.findByMaritalStatus", query = "SELECT a FROM AgentRequestReview a WHERE a.maritalStatus = :maritalStatus")
//        , @NamedQuery(name = "AgentRequestReview.findByName", query = "SELECT a FROM AgentRequestReview a WHERE a.name = :name")
//        , @NamedQuery(name = "AgentRequestReview.findByProfession", query = "SELECT a FROM AgentRequestReview a WHERE a.profession = :profession")
//        , @NamedQuery(name = "AgentRequestReview.findByReligion", query = "SELECT a FROM AgentRequestReview a WHERE a.religion = :religion")
//        , @NamedQuery(name = "AgentRequestReview.findByUserId", query = "SELECT a FROM AgentRequestReview a WHERE a.userId = :userId")
//        , @NamedQuery(name = "AgentRequestReview.findById", query = "SELECT a FROM AgentRequestReview a WHERE a.id = :id")
//        , @NamedQuery(name = "AgentRequestReview.findByAcceptDate", query = "SELECT a FROM AgentRequestReview a WHERE a.acceptDate = :acceptDate")
//        , @NamedQuery(name = "AgentRequestReview.findByFreezeDate", query = "SELECT a FROM AgentRequestReview a WHERE a.freezeDate = :freezeDate")
//        , @NamedQuery(name = "AgentRequestReview.findByRejectDate", query = "SELECT a FROM AgentRequestReview a WHERE a.rejectDate = :rejectDate")
//        , @NamedQuery(name = "AgentRequestReview.findByRequestDate", query = "SELECT a FROM AgentRequestReview a WHERE a.requestDate = :requestDate")
//        , @NamedQuery(name = "AgentRequestReview.findByStatus", query = "SELECT a FROM AgentRequestReview a WHERE a.status = :status")
//        , @NamedQuery(name = "AgentRequestReview.findByUsername", query = "SELECT a FROM AgentRequestReview a WHERE a.username = :username")})
public class AgentRequestToReview implements Serializable {

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
    @Column(name = "agent_request_id")
    private long id;
    @Column(name = "accept_date")
    private String acceptDate;
    @Column(name = "freeze_date")
    private String freezeDate;
    @Lob
    @Column(name = "note_admin")
    private String noteAdmin;
    @Lob
    @Column(name = "note_applicant")
    private String noteApplicant;
    @Lob
    @Column(name = "note_personal")
    private String notePersonal;
    @Column(name = "reject_date")
    private String rejectDate;
    @Column(name = "request_date")
    private String requestDate;
    @Column(name = "status")
    private String status;
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


    public AgentRequestToReview() {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getFreezeDate() {
        return freezeDate;
    }

    public void setFreezeDate(String freezeDate) {
        this.freezeDate = freezeDate;
    }

    public String getNoteAdmin() {
        return noteAdmin;
    }

    public void setNoteAdmin(String noteAdmin) {
        this.noteAdmin = noteAdmin;
    }

    public String getNoteApplicant() {
        return noteApplicant;
    }

    public void setNoteApplicant(String noteApplicant) {
        this.noteApplicant = noteApplicant;
    }

    public String getNotePersonal() {
        return notePersonal;
    }

    public void setNotePersonal(String notePersonal) {
        this.notePersonal = notePersonal;
    }

    public String getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(String rejectDate) {
        this.rejectDate = rejectDate;
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
        return "AgentRequestToReview{" +
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
                ", id=" + id +
                ", acceptDate='" + acceptDate + '\'' +
                ", freezeDate='" + freezeDate + '\'' +
                ", noteAdmin='" + noteAdmin + '\'' +
                ", noteApplicant='" + noteApplicant + '\'' +
                ", notePersonal='" + notePersonal + '\'' +
                ", rejectDate='" + rejectDate + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", status='" + status + '\'' +
                ", username='" + username + '\'' +
                ", phone_number=" + phone_number +
                ", addressList=" + addressList +
                '}';
    }
}
