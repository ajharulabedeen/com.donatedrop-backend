/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.profile.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author G7
 */
@Entity
@Table(name = "profile_jobs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfileJobs.findAll", query = "SELECT p FROM ProfileJobs p")
    , @NamedQuery(name = "ProfileJobs.findById", query = "SELECT p FROM ProfileJobs p WHERE p.id = :id")
    , @NamedQuery(name = "ProfileJobs.findByUserId", query = "SELECT p FROM ProfileJobs p WHERE p.userId = :userId")
    , @NamedQuery(name = "ProfileJobs.findByOrganizationName", query = "SELECT p FROM ProfileJobs p WHERE p.organizationName = :organizationName")
    , @NamedQuery(name = "ProfileJobs.findByType", query = "SELECT p FROM ProfileJobs p WHERE p.type = :type")
    , @NamedQuery(name = "ProfileJobs.findByRole", query = "SELECT p FROM ProfileJobs p WHERE p.role = :role")
    , @NamedQuery(name = "ProfileJobs.findByStarted", query = "SELECT p FROM ProfileJobs p WHERE p.started = :started")
    , @NamedQuery(name = "ProfileJobs.findByLeave", query = "SELECT p FROM ProfileJobs p WHERE p.job_left = :job_left")
    , @NamedQuery(name = "ProfileJobs.findByCurrentStatus", query = "SELECT p FROM ProfileJobs p WHERE p.currentStatus = :currentStatus")
    , @NamedQuery(name = "ProfileJobs.findByCreatedAt", query = "SELECT p FROM ProfileJobs p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "ProfileJobs.findByUpdatedAt", query = "SELECT p FROM ProfileJobs p WHERE p.updatedAt = :updatedAt")})
public class ProfileJobs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 512)
    @Column(name = "user_id")
    private String userId;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 512)
    @Column(name = "organization_name")
    private String organizationName;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 512)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 512)
    @Column(name = "role")
    private String role;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 512)
    @Column(name = "started")
    private String started;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 512)
    @Column(name = "job_left")
    private String job_left;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 512)
    @Column(name = "current_status")
    private String currentStatus;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public ProfileJobs() {
    }

    public ProfileJobs(Long id) {
        this.id = id;
    }

    public ProfileJobs(Long id, String userId, String organizationName, String type, String role, String started, String job_left, String currentStatus) {
        this.id = id;
        this.userId = userId;
        this.organizationName = organizationName;
        this.type = type;
        this.role = role;
        this.started = started;
        this.job_left = job_left;
        this.currentStatus = currentStatus;
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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getJob_left() {
        return job_left;
    }

    public void setJob_left(String job_left) {
        this.job_left = job_left;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
        if (!(object instanceof ProfileJobs)) {
            return false;
        }
        ProfileJobs other = (ProfileJobs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.donatedrop.profile.ProfileJobs[ id=" + id + " ]";
    }
}
