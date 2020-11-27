package com.donatedrop.other;

import com.donatedrop.geocode.Dao_GeoCode_I;
import com.donatedrop.geocode.models.DistrictsEngName;
import com.donatedrop.geocode.models.DivisionsEngName;
import com.donatedrop.geocode.models.UnionsEngName;
import com.donatedrop.geocode.models.UpzillaEngName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class DumpDataAddress {

    @Autowired
    private Dao_GeoCode_I dao_GeoCode_I;

    @Test
    public void testGenerateAddress() {
        Random r = new Random();
        for (int x = 0; x < 1000; x++) {
            String divID = "";
            String divName = "";
            String distID = "";
            String distName = "";
            String upzID = "";
            String upzName = "";
            String unionID = "";
            String unionName = "";
            try {
                List<DivisionsEngName> divisionsList = dao_GeoCode_I.getDivisions();
                int divRand = r.nextInt(divisionsList.size());
                divID = divisionsList.get(divRand).getId().toString();
                divName = divisionsList.get(divRand).getName().toString();
            } catch (Exception e) {
                System.out.println("Exception in Gettting Division!" + divID + "\n");
            }
            try {
                List<DistrictsEngName> districtsEngNameList = dao_GeoCode_I.getDistricts(divID);
                int distRand = r.nextInt(districtsEngNameList.size());
                distID = districtsEngNameList.get(distRand).getId().toString();
                distName = districtsEngNameList.get(distRand).getName().toString();
            } catch (Exception e) {
                System.out.println("Exception in Gettting District! : " + distID + "\n");
            }
            try {
                List<UpzillaEngName> upzillaEngNameList = dao_GeoCode_I.getUpzillas(distID);
                int upzRand = r.nextInt(upzillaEngNameList.size());
                upzID = upzillaEngNameList.get(upzRand).getId().toString();
                upzName = upzillaEngNameList.get(upzRand).getName().toString();
            } catch (Exception e) {
                System.out.println("Exception in Gettting Upzilla! : " + upzID + "\n");
            }
            try {
                List<UnionsEngName> unionsEngNameList = dao_GeoCode_I.getUnions(upzID);
                int unionRand = r.nextInt(unionsEngNameList.size());
                unionID = unionsEngNameList.get(unionRand).getId().toString();
                unionName = unionsEngNameList.get(unionRand).getName().toString();
            } catch (Exception e) {
                System.out.println("Exception in Gettting Unions! : " + unionID + "\n");
            }
            String address = unionName + ", " + upzName + ", " + distName + ", " + divName;
            System.out.println("\nAdd : " + "\"" + address + "\"," + "\n");
        }// for
    }

    @Test
    public void testAddress() {
        for (int x = 0; x < 50; x++) {
            System.out.println(getAddress());
        }
    }

    public String getAddress() {
        Random r = new Random();
//        System.out.println("\n" + dao_GeoCode_I.toString() + "\n");
        String divID = "";
        String divName = "";
        String distID = "";
        String distName = "";
        String upzID = "";
        String upzName = "";
        String unionID = "";
        String unionName = "";
        try {
            List<DivisionsEngName> divisionsList = dao_GeoCode_I.getDivisions();
            int divRand = r.nextInt(divisionsList.size());
            divID = divisionsList.get(divRand).getId().toString();
            divName = divisionsList.get(divRand).getName().toString();
        } catch (Exception e) {
            System.out.println("Exception in Gettting Division!" + divID + "\n");
        }
        try {
            List<DistrictsEngName> districtsEngNameList = dao_GeoCode_I.getDistricts(divID);
            int distRand = r.nextInt(districtsEngNameList.size());
            distID = districtsEngNameList.get(distRand).getId().toString();
            distName = districtsEngNameList.get(distRand).getName().toString();
        } catch (Exception e) {
            System.out.println("Exception in Gettting District! : " + distID + "\n");
        }
        try {
            List<UpzillaEngName> upzillaEngNameList = dao_GeoCode_I.getUpzillas(distID);
            int upzRand = r.nextInt(upzillaEngNameList.size());
            upzID = upzillaEngNameList.get(upzRand).getId().toString();
            upzName = upzillaEngNameList.get(upzRand).getName().toString();
        } catch (Exception e) {
            System.out.println("Exception in Gettting Upzilla! : " + upzID + "\n");
        }
        try {
            List<UnionsEngName> unionsEngNameList = dao_GeoCode_I.getUnions(upzID);
            int unionRand = r.nextInt(unionsEngNameList.size());
            unionID = unionsEngNameList.get(unionRand).getId().toString();
            unionName = unionsEngNameList.get(unionRand).getName().toString();
        } catch (Exception e) {
            System.out.println("Exception in Gettting Unions! : " + unionID + "\n");
        }
        return unionName + ", " + upzName + ", " + distName + ", " + divName;
    }


//    public String getAddress() {
//        Random r = new Random();
//        System.out.println("\n" + dao_GeoCode_I.toString() + "\n");
//
//        List<DivisionsEngName> divisionsList = dao_GeoCode_I.getDivisions();
//        int divRand = r.nextInt(divisionsList.size());
//        String divID = divisionsList.get(divRand).getId().toString();
//        String divName = divisionsList.get(divRand).getName().toString();
//
//        List<DistrictsEngName> districtsEngNameList = dao_GeoCode_I.getDistricts(divID);
//        int distRand = r.nextInt(districtsEngNameList.size());
//        String distID = districtsEngNameList.get(distRand).getId().toString();
//        String distName = districtsEngNameList.get(distRand).getName().toString();
//
//        List<UpzillaEngName> upzillaEngNameList = dao_GeoCode_I.getUpzillas(distID);
//        int upzRand = r.nextInt(upzillaEngNameList.size());
//        String upzID = upzillaEngNameList.get(upzRand).getId().toString();
//        String upzName = upzillaEngNameList.get(upzRand).getName().toString();
//
//        List<UnionsEngName> unionsEngNameList = dao_GeoCode_I.getUnions(upzID);
//        int unionRand = r.nextInt(unionsEngNameList.size());
//        String unionID = unionsEngNameList.get(unionRand).getId().toString();
//        String unionName = unionsEngNameList.get(unionRand).getName().toString();
//
//        return unionName + ", " + upzName + ", " + distName + ", " + divName;
//    }
}
