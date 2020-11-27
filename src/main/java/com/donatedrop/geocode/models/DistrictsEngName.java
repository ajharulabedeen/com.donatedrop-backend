/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.geocode.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author G7
 */
@Entity
@ToString
@Table(name = "zzna_divisions_engname")
public class DistrictsEngName {
    @Getter
    @Setter
    @Id
    private Integer id;
    @Getter
    @Setter
    private String name;
}
