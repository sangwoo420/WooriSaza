package project.woori_saza.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

}
