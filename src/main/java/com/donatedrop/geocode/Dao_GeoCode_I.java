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
public interface Dao_GeoCode_I {

    public List<DivisionsEngName> getDivisions();

    public List<DistrictsEngName> getDistricts(String divID);

    public List<UpzillaEngName> getUpzillas(String distID);

    public List<UnionsEngName> getUnions(String upzID);

}
