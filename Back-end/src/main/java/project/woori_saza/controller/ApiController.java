package project.woori_saza.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    @ApiOperation(value="스웨거 테스트",notes="스웨거를 테스트합니다.")
    @GetMapping("/test")
    public String hello(){
        return "hello";
    }

    @PostMapping("/kauth")
    public Object getKakaoToken(@RequestBody String code){

        return restTemplate.postForObject("https://kauth.kakao.com/oauth/token", code, Map.class);
    }

    @GetMapping("/validation")
    public Object validateUrl(@RequestParam String sublink){
        UriComponents uri = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host("www.virustotal.com")
                .port(443)
                .path("vtapi/v2/url/report")
                .queryParam("apikey", "68614fc23b4200870247adf446056257420f8c45e525776827cf027c589e0cee")
                .queryParam("resource", sublink+"com")
                .queryParam("allinfo", false)
                .queryParam("scan", 0)
                .build();
        return restTemplate.getForObject(uri.toUri(), Map.class);
    }


    @GetMapping("/thumbnail")
    public Object getThumbnail(@RequestParam String link){
        UriComponents uri = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host("api.urlmeta.org")
                .port(443)
                .queryParam("url", link)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic dG9teTk3MjlAbmF2ZXIuY29tOjhUYkdka2MxVnE3bnBYTzcyMkpC");
        return restTemplate.exchange(uri.toUri(), HttpMethod.GET, new HttpEntity(headers), Map.class);
    }
}
