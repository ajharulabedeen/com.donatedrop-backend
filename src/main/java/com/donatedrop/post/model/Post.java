/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.post.model;

import com.donatedrop.profile.model.PhoneNumber;
import com.donatedrop.util.StringUtil;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

/**
 * @author Dell
 */
@Entity
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long postID;

    @Column(name = "post_user_id")
    private String postUserID;

    @Column(name = "blood_type")
    private String bloodType;

    @Lob
    @Column(name = "location")
    private String location;

    @Lob
    @Column(name = "hospital_name")
    private String hospitalName;

    @Lob
    @Column(name = "hospital_address")
    private String hospitalAddress;

    @Column(name = "patient_gender")
    private String patientGender;

    @Lob
    @Column(name = "patient_description")
    private String patientDescription;

    @Column(name = "post_date")
    private String postDate;

    @Column(name = "need_date")
    private String needDate;

    @Column(name = "quantity")
    private String quantity;

    @Lob
    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "status")
    private String status = StringUtil.ACTIVE;

    @Lob
    @Column(name = "remarks")
    private String remarks;

    @Column(name = "donner_found")
    private String donnerFound;

    @Column(name = "relation")
    private String relation;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Lob
    @Column(name = "report")
    private String report;

    @Lob
    @Column(name = "post_comment")
    private String Comments;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<PostComment> postComments;

    public Post() {
    }


    public Post(String postUserID, String bloodType, String location, String hospitalName, String hospitalAddress, String patientGender, String patientDescription, String postDate, String needDate, String quantity, String contactInfo, String status, String remarks, String donnerFound, String relation, String notes, String report, String comments) {
        this.postUserID = postUserID;
        this.bloodType = bloodType;
        this.location = location;
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.patientGender = patientGender;
        this.patientDescription = patientDescription;
        this.postDate = postDate;
        this.needDate = needDate;
        this.quantity = quantity;
        this.contactInfo = contactInfo;
        this.status = status;
        this.remarks = remarks;
        this.donnerFound = donnerFound;
        this.relation = relation;
        this.notes = notes;
        this.report = report;
        Comments = comments;
    }


    public Post(String postUserID, String bloodType, String location, String hospitalName, String hospitalAddress, String patientGender, String patientDescription, String postDate, String needDate, String quantity, String contactInfo, String status, String remarks, String donnerFound, String relation, String notes, String report, String comments, List<PostComment> postComments) {
        this.postUserID = postUserID;
        this.bloodType = bloodType;
        this.location = location;
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.patientGender = patientGender;
        this.patientDescription = patientDescription;
        this.postDate = postDate;
        this.needDate = needDate;
        this.quantity = quantity;
        this.contactInfo = contactInfo;
        this.status = status;
        this.remarks = remarks;
        this.donnerFound = donnerFound;
        this.relation = relation;
        this.notes = notes;
        this.report = report;
        Comments = comments;
        this.postComments = postComments;
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public String getPostUserID() {
        return postUserID;
    }

    public void setPostUserID(String postUserID) {
        this.postUserID = postUserID;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientDescription() {
        return patientDescription;
    }

    public void setPatientDescription(String patientDescription) {
        this.patientDescription = patientDescription;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getNeedDate() {
        return needDate;
    }

    public void setNeedDate(String needDate) {
        this.needDate = needDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDonnerFound() {
        return donnerFound;
    }

    public void setDonnerFound(String donnerFound) {
        this.donnerFound = donnerFound;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }


    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", postUserID='" + postUserID + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", location='" + location + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", hospitalAddress='" + hospitalAddress + '\'' +
                ", patientGender='" + patientGender + '\'' +
                ", patientDescription='" + patientDescription + '\'' +
                ", postDate='" + postDate + '\'' +
                ", needDate='" + needDate + '\'' +
                ", quantity='" + quantity + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                ", donnerFound='" + donnerFound + '\'' +
                ", relation='" + relation + '\'' +
                ", notes='" + notes + '\'' +
                ", report='" + report + '\'' +
                ", Comments='" + Comments + '\'' +
                ", postComments=" + postComments +
                '}';
    }
}
