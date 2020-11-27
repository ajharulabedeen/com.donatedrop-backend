/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.agent.donner.models;

import com.donatedrop.util.StringUtil;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * @author Dell
 */
@Entity
public class DonnerAssingment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "donner_assingment_id")
    private Long id;

    @Column(name = "agent_id")
    private String agentId;

    @Column(name = "donner_id")
    private String donnerId;

    @Column(name = "post_id")
    private String postId;

    @Lob
    @Column(name = "assing_note")
    private String assingNote;

    @Column(name = "assing_date")
    private String assingDate;

    @Column(name = "need_date")
    private String needDate;

    @Column(name = "blood_manage_status")
    private String bloodManageStatus = StringUtil.ACTIVE;

    public DonnerAssingment() {

    }

    public DonnerAssingment(String agentId, String donnerId, String postId, String assingNote, String assingDate, String needDate) {
        this.agentId = agentId;
        this.donnerId = donnerId;
        this.postId = postId;
        this.assingNote = assingNote;
        this.assingDate = assingDate;
        this.needDate = needDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getAssingNote() {
        return assingNote;
    }

    public void setAssingNote(String assingNote) {
        this.assingNote = assingNote;
    }

    public String getAssingDate() {
        return assingDate;
    }

    public void setAssingDate(String assingDate) {
        this.assingDate = assingDate;
    }

    public String getNeedDate() {
        return needDate;
    }

    public void setNeedDate(String needDate) {
        this.needDate = needDate;
    }

    public String getBloodManageStatus() {
        return bloodManageStatus;
    }

    public void setBloodManageStatus(String bloodManageStatus) {
        this.bloodManageStatus = bloodManageStatus;
    }

    @Override
    public String toString() {
        return "DonnerAssingment{" +
                "id=" + id +
                ", agentId='" + agentId + '\'' +
                ", donnerId='" + donnerId + '\'' +
                ", postId='" + postId + '\'' +
                ", assingNote='" + assingNote + '\'' +
                ", assingDate='" + assingDate + '\'' +
                ", needDate='" + needDate + '\'' +
                ", bloodManageStatus='" + bloodManageStatus + '\'' +
                '}';
    }
}
