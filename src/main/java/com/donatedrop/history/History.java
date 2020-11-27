/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.history;

import com.donatedrop.profile.model.ProfileBasic;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.sql.Clob;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author G7
 */
@Entity
public class History implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "date")
    private String date;

    @Column(name = "location")
    private String location;

    @Lob
//    not working
//    @Size(min = 1, max = 16777215)
//    @Column(name = "patient_description",
//            columnDefinition = "CLOB",
//            length = 16777215)
//    @Column(name = "patient_description", columnDefinition = "CLOB")
    @Column(name = "patient_description")
    private String patientDescription;

    @Column(name = "reffered_by")
    private String refferedBy;

    @Lob
//    not working
//    @Column(name = "note", columnDefinition = "CLOB", length = 16777215)
//    @Column(name = "note", columnDefinition = "CLOB")
//    @Size(min = 1, max = 16777215)
//    @Column(name = "note")
    @Column(name = "note")
    private String note;

//    Note : Will make, in time query cause profileBasic is not required.
//    @JsonBackReference
//    @ManyToOne
//    @JoinColumn(name = "profile_id")
//    private ProfileBasic profileBasic;

    public History() {
    }

    public History(Long id, String userId, String date, String location, String patientDescription, String refferedBy, String note, ProfileBasic profileBasic) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.location = location;
        this.patientDescription = patientDescription;
        this.refferedBy = refferedBy;
        this.note = note;
//        this.profileBasic = profileBasic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPatientDescription() {
        return patientDescription;
    }

    public void setPatientDescription(String patientDescription) {
        this.patientDescription = patientDescription;
    }

    public String getRefferedBy() {
        return refferedBy;
    }

    public void setRefferedBy(String refferedBy) {
        this.refferedBy = refferedBy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

//    public ProfileBasic getProfileBasic() {
//        return profileBasic;
//    }
//
//    public void setProfileBasic(ProfileBasic profileBasic) {
//        this.profileBasic = profileBasic;
//    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", patientDescription='" + patientDescription + '\'' +
                ", refferedBy='" + refferedBy + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
