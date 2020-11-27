/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.geocode.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author G7
 */
@Entity
@Table(name = "divisions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Divisions.findAll", query = "SELECT d FROM Divisions d")
    , @NamedQuery(name = "Divisions.findById", query = "SELECT d FROM Divisions d WHERE d.id = :id")
    , @NamedQuery(name = "Divisions.findByName", query = "SELECT d FROM Divisions d WHERE d.name = :name")
    , @NamedQuery(name = "Divisions.findByBnName", query = "SELECT d FROM Divisions d WHERE d.bnName = :bnName")
    , @NamedQuery(name = "Divisions.findByUrl", query = "SELECT d FROM Divisions d WHERE d.url = :url")
    , @NamedQuery(name = "Divisions.engName", query = "SELECT d.id, d.name FROM Divisions d")})
public class Divisions implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "divisionId")
    private Collection<Districts> districtsCollection;

    public Divisions() {
    }

    public Divisions(Integer id) {
        this.id = id;
    }

    public Divisions(Integer id, String name, String bnName, String url) {
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

    @XmlTransient
    public Collection<Districts> getDistrictsCollection() {
        return districtsCollection;
    }

    public void setDistrictsCollection(Collection<Districts> districtsCollection) {
        this.districtsCollection = districtsCollection;
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
        if (!(object instanceof Divisions)) {
            return false;
        }
        Divisions other = (Divisions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", bnName=" + bnName + ", url=" + url;
    }
    
//    @Override
//    public String toString() {
//        return "Divisions{" + "id=" + id + ", name=" + name + ", bnName=" + bnName + ", url=" + url + ", districtsCollection=" + districtsCollection + '}';
//    }
}
