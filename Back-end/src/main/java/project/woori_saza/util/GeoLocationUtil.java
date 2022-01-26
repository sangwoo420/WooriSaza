package project.woori_saza.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class GeoLocationUtil {

    private final static String API_KEY = "AIzaSyBnaiR9ZGEyEe8VhfhAgQxSiGStnkmrU-w";
    private final static String PRE_URL = "https://maps.googleapis.com/maps/api/geocode/json?address="; // + URLEncoder.encode(LOCATION, "UTF-8")+"&key="+API_KEY;

    public Double[] parseLocationToLatLng(String location) {
        try {
            URL url = new URL(PRE_URL + URLEncoder.encode(location, "UTF-8") + "&key=" + API_KEY);
            InputStream is = url.openConnection().getInputStream();

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            System.out.println(">>>>>>>>>> >>>>>>>>>> InputStream Start <<<<<<<<<< <<<<<<<<<<");
            while ((inputStr = streamReader.readLine()) != null) {
                System.out.println(">>>>>>>>>>     " + inputStr);
                responseStrBuilder.append(inputStr);
            }
            System.out.println(">>>>>>>>>> >>>>>>>>>> InputStream End <<<<<<<<<< <<<<<<<<<<");

            JSONObject jo = new JSONObject(responseStrBuilder.toString());
            JSONArray results = jo.getJSONArray("results");
            String region = null;
            String province = null;
            String zip = null;
            if (results.length() > 0) {
                JSONObject jsonObject;
                jsonObject = results.getJSONObject(0);
                Double lat = jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                Double lng = jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lng");

                System.out.println("LAT:\t\t" + lat);
                System.out.println("LNG:\t\t" + lng);
                return new Double[]{lat, lng};
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Double getDistance(Double[] latlng1, Double[] latlng2){
        if(latlng1[0] == latlng2[0] && latlng1[1] == latlng2[1])
            return 0.0;

        Double radLat1 = (Math.PI * latlng1[0]) / 180;
        Double radLat2 = (Math.PI * latlng2[0]) / 180;
        Double theta = latlng1[1] - latlng2[1];
        Double radTheta = (Math.PI * theta) / 180;
        Double dist =
                Math.sin(radLat1) * Math.sin(radLat2) +
                        Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radTheta);
        if (dist > 1) dist = 1.0;

        dist = Math.acos(dist);
        dist = (dist * 180) / Math.PI;
        dist = dist * 60 * 1.1515 * 1.609344 * 1000;
        if (dist < 100) dist = Math.round(dist / 10) * 10.0;
        else dist = Math.round(dist / 100) * 100.0;

        return dist;

    }
}

//    try {
//            String API_KEY =
//            String surl = "https://maps.googleapis.com/maps/api/geocode/json?address="+ URLEncoder.encode("광주광역시 북구 면앙로6번길 48", "UTF-8")+"&key="+API_KEY;
//            URL url = new URL(surl);
//            InputStream is = url.openConnection().getInputStream();
//
//            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//
//            StringBuilder responseStrBuilder = new StringBuilder();
//            String inputStr;
//            System.out.println(">>>>>>>>>> >>>>>>>>>> InputStream Start <<<<<<<<<< <<<<<<<<<<");
//            while ((inputStr = streamReader.readLine()) != null) {
//                System.out.println(">>>>>>>>>>     "+inputStr);
//                responseStrBuilder.append(inputStr);
//            }
//            System.out.println(">>>>>>>>>> >>>>>>>>>> InputStream End <<<<<<<<<< <<<<<<<<<<");
//
//            JSONObject jo = new JSONObject(responseStrBuilder.toString());
//            JSONArray results = jo.getJSONArray("results");
//            String region = null;
//            String province = null;
//            String zip = null;
//            Map<String, String> ret = new HashMap<String, String>();
//            if(results.length() > 0) {
//                JSONObject jsonObject;
//                jsonObject = results.getJSONObject(0);
//                Double lat = jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
//                Double lng = jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
//                ret.put("lat", lat.toString());
//                ret.put("lng", lng.toString());
//                System.out.println("LAT:\t\t"+lat);
//                System.out.println("LNG:\t\t"+lng);
//                JSONArray ja = jsonObject.getJSONArray("address_components");
//                for(int l=0; l<ja.length(); l++) {
//                    JSONObject curjo = ja.getJSONObject(l);
//                    String type = curjo.getJSONArray("types").getString(0);
//                    String short_name = curjo.getString("short_name");
//                    if(type.equals("postal_code")) {
//                        System.out.println("POSTAL_CODE: "+short_name);
//                        ret.put("zip", short_name);
//                    }
//                    else if(type.equals("administrative_area_level_3")) {
//                        System.out.println("CITY: "+short_name);
//                        ret.put("city", short_name);
//                    }
//                    else if(type.equals("administrative_area_level_2")) {
//                        System.out.println("PROVINCE: "+short_name);
//                        ret.put("province", short_name);
//                    }
//                    else if(type.equals("administrative_area_level_1")) {
//                        System.out.println("REGION: "+short_name);
//                        ret.put("region", short_name);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

