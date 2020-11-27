/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.profile.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * The type Emergency contact.
 *
 * @author G7
 */
@Entity
@NoArgsConstructor
public class EmergencyContact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String mail;
    //    @Size(max = 1024)
    @Column
    private String address;
    @Column
    private String relation;

    @Column(name = "add_date")
    String addDate;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "emergency_contact")
    private ProfileBasic profileBasic;

    public EmergencyContact(String name, String phone, String mail, String address, String relation, String addDate) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.relation = relation;
        this.addDate = addDate;
    }

    public EmergencyContact(String name, String phone, String mail, String address, String relation, String addDate, ProfileBasic profileBasic) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.relation = relation;
        this.addDate = addDate;
        this.profileBasic = profileBasic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof EmergencyContact)) {
            return false;
        }
        EmergencyContact other = (EmergencyContact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EmergencyContact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                ", relation='" + relation + '\'' +
                ", addDate='" + addDate + '\'' +
//                ", profileBasic=" + profileBasic +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public ProfileBasic getProfileBasic() {
        return profileBasic;
    }

    public void setProfileBasic(ProfileBasic profileBasic) {
        this.profileBasic = profileBasic;
    }

}
