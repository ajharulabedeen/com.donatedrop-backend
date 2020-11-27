/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.geocode;

import java.util.List;

import com.donatedrop.geocode.models.DistrictsEngName;
import com.donatedrop.geocode.models.DivisionsEngName;
import com.donatedrop.geocode.models.UnionsEngName;
import com.donatedrop.geocode.models.UpzillaEngName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author G7
 */
@Component
public class Service_GeoCode_Impl implements Service_GeoCode_I {

    @Autowired
    private Dao_GeoCode_I dao_GeoCode_I;
    
    @Override
    public List<DivisionsEngName> getDivisions() {
        return dao_GeoCode_I.getDivisions();
    }

    @Override
    public List<DistrictsEngName> getDistricts(String divID) {
        return dao_GeoCode_I.getDistricts(divID);
    }

    @Override
    public List<UpzillaEngName> getUpzillas(String distID) {
            return dao_GeoCode_I.getUpzillas(distID);
    }

    @Override
    public List<UnionsEngName> getUnions(String upzID) {
           return dao_GeoCode_I.getUnions(upzID);
    }
}
