/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.agent.admin.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author G7
 */
@Entity
@Table(name = "agent_request")
public class AgentRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "agent_request_id")
    private Long id;

    @Column(name = "user_id", unique = true)
    private String userID;

    @Column(name = "request_date")
    private String requestDate;

    @Column(name = "status")
    private String status;

    @Column(name = "accept_date")
    private String acceptDate;

    @Column(name = "reject_date")
    private String rejectDate;

    @Column(name = "freeze_date")
    private String freezeDate;

    @Lob
    @Column(name = "note_applicant")
    private String noteApplicant = "note applicant!";

    @Lob
    @Column(name = "note_admin")
    private String noteAdmin = "note admin";

    @Lob
    @Column(name = "note_personal")
    private String notePersonal = "note personal";

    public Long getId() {
        return id;
    }

    public AgentRequest() {
    }

    public AgentRequest(String profileID, String requestDate, String status) {
        this.userID = profileID;
        this.requestDate = requestDate;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getNoteApplicant() {
        return noteApplicant;
    }

    public void setNoteApplicant(String noteApplicant) {
        this.noteApplicant = noteApplicant;
    }

    public String getNoteAdmin() {
        return noteAdmin;
    }

    public void setNoteAdmin(String noteAdmin) {
        this.noteAdmin = noteAdmin;
    }

    public String getNotePersonal() {
        return notePersonal;
    }

    public void setNotePersonal(String notePersonal) {
        this.notePersonal = notePersonal;
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
        if (!(object instanceof AgentRequest)) {
            return false;
        }
        AgentRequest other = (AgentRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(String rejectDate) {
        this.rejectDate = rejectDate;
    }

    public String getFreezeDate() {
        return freezeDate;
    }

    public void setFreezeDate(String freezeDate) {
        this.freezeDate = freezeDate;
    }

    @Override
    public String toString() {
        return "AgentRequest{" +
                "id=" + id +
                ", userID='" + userID + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", status='" + status + '\'' +
                ", acceptDate='" + acceptDate + '\'' +
                ", rejectDate='" + rejectDate + '\'' +
                ", freezeDate='" + freezeDate + '\'' +
                ", noteApplicant='" + noteApplicant + '\'' +
                ", noteAdmin='" + noteAdmin + '\'' +
                ", notePersonal='" + notePersonal + '\'' +
                '}';
    }
}
