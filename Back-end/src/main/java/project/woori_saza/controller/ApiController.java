package project.woori_saza.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    RestTemplate restTemplate;

    @ApiOperation(value = "스웨거 테스트", notes = "스웨거를 테스트합니다.")
    @GetMapping("/test")
    public String hello() {
        return "hello";
    }

    @PostMapping("/kauth")
    public Object getKakaoToken(@RequestBody String code) {

        return restTemplate.postForObject("https://kauth.kakao.com/oauth/token", code, Map.class);
    }

    @GetMapping("/validation")
    public Object validateUrl(@RequestParam String sublink) {
        UriComponents uri = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host("www.virustotal.com")
                .port(443)
                .path("vtapi/v2/url/report")
                .queryParam("apikey", "68614fc23b4200870247adf446056257420f8c45e525776827cf027c589e0cee")
                .queryParam("resource", sublink + "com")
                .queryParam("allinfo", false)
                .queryParam("scan", 0)
                .build();
        return restTemplate.getForObject(uri.toUri(), Map.class);
    }


    @GetMapping("/thumbnail")
    public Object getThumbnail(@RequestParam String url) {
        UriComponents uri = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host("api.urlmeta.org")
                .port(443)
                .queryParam("url", url)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)" +
                " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        headers.setBasicAuth("dG9teTk3MjlAbmF2ZXIuY29tOjhUYkdka2MxVnE3bnBYTzcyMkpC");
        HttpEntity request = new HttpEntity<>(headers);
        System.out.println(uri.toUriString());
        return restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, Map.class);
    }
}
