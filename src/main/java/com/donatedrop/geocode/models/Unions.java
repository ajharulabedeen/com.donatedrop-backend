/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.geocode.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author G7
 */
@Entity
@Table(name = "unions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unions.findAll", query = "SELECT u FROM Unions u")
    , @NamedQuery(name = "Unions.findById", query = "SELECT u FROM Unions u WHERE u.id = :id")
    , @NamedQuery(name = "Unions.findByName", query = "SELECT u FROM Unions u WHERE u.name = :name")
    , @NamedQuery(name = "Unions.findByBnName", query = "SELECT u FROM Unions u WHERE u.bnName = :bnName")
    , @NamedQuery(name = "Unions.findByUrl", query = "SELECT u FROM Unions u WHERE u.url = :url")})
public class Unions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 25)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 25)
    @Column(name = "bn_name")
    private String bnName;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 50)
    @Column(name = "url")
    private String url;
    @JoinColumn(name = "upazilla_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Upazilas upazillaId;

    public Unions() {
    }

    public Unions(Integer id) {
        this.id = id;
    }

    public Unions(Integer id, String name, String bnName, String url) {
        this.id = id;
        this.name = name;
        this.bnName = bnName;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBnName() {
        return bnName;
    }

    public void setBnName(String bnName) {
        this.bnName = bnName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Upazilas getUpazillaId() {
        return upazillaId;
    }

    public void setUpazillaId(Upazilas upazillaId) {
        this.upazillaId = upazillaId;
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
        if (!(object instanceof Unions)) {
            return false;
        }
        Unions other = (Unions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.donatedrop.models.Unions[ id=" + id + " ]";
    }
    
}
