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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author G7
 */
@RestController
@RequestMapping("/public/geocode")
public class Controller_GeoCode {

    @Autowired
    private Service_GeoCode_I service_GeoCode_I;

    @GetMapping(value = "divisions")
    public List<DivisionsEngName> getDivisions() {
        return service_GeoCode_I.getDivisions();
    }

    @PostMapping(value = "districts")
    public List<DistrictsEngName> getDistricts(@RequestParam String divID) {
        return service_GeoCode_I.getDistricts(divID);
    }

    @PostMapping(value = "upzillas")
    public List<UpzillaEngName> getDivisions(@RequestParam String distID) {
        return service_GeoCode_I.getUpzillas(distID);
    }

    @PostMapping(value = "unions")
    public List<UnionsEngName> getUnions(@RequestParam String upzID) {
        return service_GeoCode_I.getUnions(upzID);
    }

}
