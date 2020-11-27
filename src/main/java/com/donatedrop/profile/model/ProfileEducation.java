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
@Table(name = "profile_education")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfileEducation.findAll", query = "SELECT p FROM ProfileEducation p")
    , @NamedQuery(name = "ProfileEducation.findById", query = "SELECT p FROM ProfileEducation p WHERE p.id = :id")
    , @NamedQuery(name = "ProfileEducation.findByUserId", query = "SELECT p FROM ProfileEducation p WHERE p.userId = :userId")
    , @NamedQuery(name = "ProfileEducation.findByDegreeName", query = "SELECT p FROM ProfileEducation p WHERE p.degreeName = :degreeName")
    , @NamedQuery(name = "ProfileEducation.findByInstitueName", query = "SELECT p FROM ProfileEducation p WHERE p.institueName = :institueName")
    , @NamedQuery(name = "ProfileEducation.findByPassingYear", query = "SELECT p FROM ProfileEducation p WHERE p.passingYear = :passingYear")
    , @NamedQuery(name = "ProfileEducation.findByResult", query = "SELECT p FROM ProfileEducation p WHERE p.result = :result")
    , @NamedQuery(name = "ProfileEducation.findByCreatedAt", query = "SELECT p FROM ProfileEducation p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "ProfileEducation.findByUpdatedAt", query = "SELECT p FROM ProfileEducation p WHERE p.updatedAt = :updatedAt")})
public class ProfileEducation implements Serializable {

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
    @Column(name = "degree_name")
    private String degreeName;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 512)
    @Column(name = "institue_name")
    private String institueName;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 512)
    @Column(name = "passing_year")
    private String passingYear;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 512)
    @Column(name = "result")
    private String result;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public ProfileEducation() {
    }

    public ProfileEducation(Long id) {
        this.id = id;
    }

    public ProfileEducation(Long id, String userId, String degreeName, String institueName, String passingYear, String result) {
        this.id = id;
        this.userId = userId;
        this.degreeName = degreeName;
        this.institueName = institueName;
        this.passingYear = passingYear;
        this.result = result;
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

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getInstitueName() {
        return institueName;
    }

    public void setInstitueName(String institueName) {
        this.institueName = institueName;
    }

    public String getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(String passingYear) {
        this.passingYear = passingYear;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
        if (!(object instanceof ProfileEducation)) {
            return false;
        }
        ProfileEducation other = (ProfileEducation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.donatedrop.profile.ProfileEducation[ id=" + id + " ]";
    }
    
}
