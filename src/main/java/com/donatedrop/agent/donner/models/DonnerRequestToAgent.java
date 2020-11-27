/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.agent.donner.models;

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
@Table(name = "request_donner_to_agent")
public class DonnerRequestToAgent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_donner_to_agent_id")
    private Long id;

    @Column(name = "user_id_donner", unique = true)
    String userIdDonner;
    @Column(name = "user_id_agent")
    String userIdAgent;

    @Column(name = "request_date")
    String requestDate;
    @Column(name = "accept_date")
    String acceptDate;
    @Column(name = "reject_date")
    String rejectDate;
    @Column(name = "remove_date")
    String removeDate;
    @Column(name = "status")
    String status;
    @Lob
    @Column(name = "note_donner")
    String noteDonner;
    @Lob
    @Column(name = "note_agent")
    String noteAgent;
    @Lob
    @Column(name = "note_agent_personal")
    String noteAgentPersonal;

    public DonnerRequestToAgent() {
    }

    public DonnerRequestToAgent(Long id, String userIdDonner, String userIDAgent, String requestDate, String acceptDate, String removeDate, String status, String noteDonner, String noteAgent, String noteAgentPersonal) {
        this.id = id;
        this.userIdDonner = userIdDonner;
        this.requestDate = requestDate;
        this.acceptDate = acceptDate;
        this.removeDate = removeDate;
        this.status = status;
        this.noteDonner = noteDonner;
        this.noteAgent = noteAgent;
        this.noteAgentPersonal = noteAgentPersonal;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserIdDonner() {
        return userIdDonner;
    }

    public void setUserIdDonner(String userIdDonner) {
        this.userIdDonner = userIdDonner;
    }

    public String getUserIdAgent() {
        return userIdAgent;
    }

    public void setUserIdAgent(String userIdAgent) {
        this.userIdAgent = userIdAgent;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
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

    public String getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(String removeDate) {
        this.removeDate = removeDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoteDonner() {
        return noteDonner;
    }

    public void setNoteDonner(String noteDonner) {
        this.noteDonner = noteDonner;
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

    @Override
    public String toString() {
        return "DonnerRequestToAgent{" +
                "id=" + id +
                ", userIdDonner='" + userIdDonner + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", acceptDate='" + acceptDate + '\'' +
                ", rejectDate='" + rejectDate + '\'' +
                ", removeDate='" + removeDate + '\'' +
                ", status='" + status + '\'' +
                ", noteDonner='" + noteDonner + '\'' +
                ", noteAgent='" + noteAgent + '\'' +
                ", noteAgentPersonal='" + noteAgentPersonal + '\'' +
                '}';
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
        if (!(object instanceof DonnerRequestToAgent)) {
            return false;
        }
        DonnerRequestToAgent other = (DonnerRequestToAgent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
