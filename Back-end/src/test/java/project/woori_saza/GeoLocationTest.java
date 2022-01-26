package project.woori_saza;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.repo.UserProfileRepo;
import project.woori_saza.util.GeoLocationUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class GeoLocationTest {


    @Autowired
    GeoLocationUtil geoLocationUtil;

    @Test
    void geoLocationTest() {
        Double[] latlng1 = geoLocationUtil.parseLocationToLatLng("광주광역시 북구 면앙로6번길 48");
        Double[] latlng2 = geoLocationUtil.parseLocationToLatLng("전라남도 순천시 해룡면 지봉로 372-5");
        Double dist = geoLocationUtil.getDistance(latlng1, latlng2);
        System.out.println(dist);

    }
}
