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
import javax.persistence.Lob;
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
@Table(name = "profile_abouts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfileAbouts.findAll", query = "SELECT p FROM ProfileAbouts p")
    , @NamedQuery(name = "ProfileAbouts.findById", query = "SELECT p FROM ProfileAbouts p WHERE p.id = :id")
    , @NamedQuery(name = "ProfileAbouts.findByUserId", query = "SELECT p FROM ProfileAbouts p WHERE p.userId = :userId")
    , @NamedQuery(name = "ProfileAbouts.findByCreatedAt", query = "SELECT p FROM ProfileAbouts p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "ProfileAbouts.findByUpdatedAt", query = "SELECT p FROM ProfileAbouts p WHERE p.updatedAt = :updatedAt")})
public class ProfileAbouts implements Serializable {

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
    @Lob
//    @Size(min = 1, max = 16777215)
    @Column(name = "about_me")
    private String aboutMe;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public ProfileAbouts() {
    }

    public ProfileAbouts(Long id) {
        this.id = id;
    }

    public ProfileAbouts(Long id, String userId, String aboutMe) {
        this.id = id;
        this.userId = userId;
        this.aboutMe = aboutMe;
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

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
        if (!(object instanceof ProfileAbouts)) {
            return false;
        }
        ProfileAbouts other = (ProfileAbouts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.donatedrop.profile.ProfileAbouts[ id=" + id + " ]";
    }
    
}
