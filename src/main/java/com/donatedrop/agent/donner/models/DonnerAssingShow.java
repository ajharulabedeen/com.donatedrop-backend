/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.agent.donner.models;

import com.donatedrop.models.Address;
import com.donatedrop.profile.model.PhoneNumber;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Dell
 */
@Entity
@Table(name = "donner_assing_show")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DonnerAssingShow.findAll", query = "SELECT d FROM DonnerAssingShow d")
        , @NamedQuery(name = "DonnerAssingShow.findByDonnerAssingmentId", query = "SELECT d FROM DonnerAssingShow d WHERE d.donnerAssingmentId = :donnerAssingmentId")
        , @NamedQuery(name = "DonnerAssingShow.findByAgentId", query = "SELECT d FROM DonnerAssingShow d WHERE d.agentId = :agentId")
        , @NamedQuery(name = "DonnerAssingShow.findByDonnerId", query = "SELECT d FROM DonnerAssingShow d WHERE d.donnerId = :donnerId")
        , @NamedQuery(name = "DonnerAssingShow.findByAssingDate", query = "SELECT d FROM DonnerAssingShow d WHERE d.assingDate = :assingDate")
        , @NamedQuery(name = "DonnerAssingShow.findByNeedDate", query = "SELECT d FROM DonnerAssingShow d WHERE d.needDate = :needDate")
        , @NamedQuery(name = "DonnerAssingShow.findByPostId", query = "SELECT d FROM DonnerAssingShow d WHERE d.postId = :postId")
        , @NamedQuery(name = "DonnerAssingShow.findByBloodManageStatus", query = "SELECT d FROM DonnerAssingShow d WHERE d.bloodManageStatus = :bloodManageStatus")
        , @NamedQuery(name = "DonnerAssingShow.findByProfileId", query = "SELECT d FROM DonnerAssingShow d WHERE d.profileId = :profileId")
        , @NamedQuery(name = "DonnerAssingShow.findByAvailable", query = "SELECT d FROM DonnerAssingShow d WHERE d.available = :available")
        , @NamedQuery(name = "DonnerAssingShow.findByBirthDate", query = "SELECT d FROM DonnerAssingShow d WHERE d.birthDate = :birthDate")
        , @NamedQuery(name = "DonnerAssingShow.findByBloodGroup", query = "SELECT d FROM DonnerAssingShow d WHERE d.bloodGroup = :bloodGroup")
        , @NamedQuery(name = "DonnerAssingShow.findByCareOf", query = "SELECT d FROM DonnerAssingShow d WHERE d.careOf = :careOf")
        , @NamedQuery(name = "DonnerAssingShow.findByEmail", query = "SELECT d FROM DonnerAssingShow d WHERE d.email = :email")
        , @NamedQuery(name = "DonnerAssingShow.findByGender", query = "SELECT d FROM DonnerAssingShow d WHERE d.gender = :gender")
        , @NamedQuery(name = "DonnerAssingShow.findByMaritalStatus", query = "SELECT d FROM DonnerAssingShow d WHERE d.maritalStatus = :maritalStatus")
        , @NamedQuery(name = "DonnerAssingShow.findByName", query = "SELECT d FROM DonnerAssingShow d WHERE d.name = :name")
        , @NamedQuery(name = "DonnerAssingShow.findByProfession", query = "SELECT d FROM DonnerAssingShow d WHERE d.profession = :profession")
        , @NamedQuery(name = "DonnerAssingShow.findByReligion", query = "SELECT d FROM DonnerAssingShow d WHERE d.religion = :religion")
        , @NamedQuery(name = "DonnerAssingShow.findByUserId", query = "SELECT d FROM DonnerAssingShow d WHERE d.userId = :userId")})
public class DonnerAssingShow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "donner_assingment_id")
    private long donnerAssingmentId;


    @Size(max = 255)
    @Column(name = "agent_id")
    private String agentId;
    @Size(max = 255)
    @Column(name = "donner_id")
    private String donnerId;
    @Size(max = 255)
    @Column(name = "assing_date")
    private String assingDate;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "assing_note")
    private String assingNote;
    @Size(max = 255)
    @Column(name = "need_date")
    private String needDate;
    @Size(max = 255)
    @Column(name = "post_id")
    private String postId;
    @Size(max = 255)
    @Column(name = "blood_manage_status")
    private String bloodManageStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "profile_id")
    private long profileId;
    @Size(max = 255)
    @Column(name = "available")
    private String available;
    @Size(max = 255)
    @Column(name = "birth_date")
    private String birthDate;
    @Size(max = 255)
    @Column(name = "blood_Group")
    private String bloodGroup;
    @Size(max = 255)
    @Column(name = "care_of")
    private String careOf;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "gender")
    private String gender;
    @Size(max = 255)
    @Column(name = "marital_status")
    private String maritalStatus;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "profession")
    private String profession;
    @Size(max = 255)
    @Column(name = "religion")
    private String religion;
    @Size(max = 255)
    @Column(name = "user_id")
    private String userId;


    public DonnerAssingShow() {
    }

    public long getDonnerAssingmentId() {
        return donnerAssingmentId;
    }

    public void setDonnerAssingmentId(long donnerAssingmentId) {
        this.donnerAssingmentId = donnerAssingmentId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getDonnerId() {
        return donnerId;
    }

    public void setDonnerId(String donnerId) {
        this.donnerId = donnerId;
    }

    public String getAssingDate() {
        return assingDate;
    }

    public void setAssingDate(String assingDate) {
        this.assingDate = assingDate;
    }

    public String getAssingNote() {
        return assingNote;
    }

    public void setAssingNote(String assingNote) {
        this.assingNote = assingNote;
    }

    public String getNeedDate() {
        return needDate;
    }

    public void setNeedDate(String needDate) {
        this.needDate = needDate;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getBloodManageStatus() {
        return bloodManageStatus;
    }

    public void setBloodManageStatus(String bloodManageStatus) {
        this.bloodManageStatus = bloodManageStatus;
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

    @Override
    public String toString() {
        return "DonnerAssingShow{" +
                "donnerAssingmentId=" + donnerAssingmentId +
                ", agentId='" + agentId + '\'' +
                ", donnerId='" + donnerId + '\'' +
                ", assingDate='" + assingDate + '\'' +
                ", assingNote='" + assingNote + '\'' +
                ", needDate='" + needDate + '\'' +
                ", postId='" + postId + '\'' +
                ", bloodManageStatus='" + bloodManageStatus + '\'' +
                ", profileId=" + profileId +
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
                '}';
    }
}
