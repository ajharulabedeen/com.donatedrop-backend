/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.geocode;

import com.donatedrop.geocode.models.DistrictsEngName;
import com.donatedrop.geocode.models.DivisionsEngName;
import com.donatedrop.geocode.models.UnionsEngName;
import com.donatedrop.geocode.models.UpzillaEngName;

import java.util.List;

/**
 *
 * @author G7
 */
public interface Service_GeoCode_I {

    List<DistrictsEngName> getDistricts(String divID);

    List<DivisionsEngName> getDivisions();

    List<UnionsEngName> getUnions(String upzID);

    List<UpzillaEngName> getUpzillas(String distID);
    
}
