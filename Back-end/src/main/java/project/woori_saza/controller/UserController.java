package project.woori_saza.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.woori_saza.model.domain.UserAuth;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.UserProfileDto;
import project.woori_saza.model.service.UserService;
import project.woori_saza.model.service.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api("사용자 컨트롤러")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "로그인", notes = "로그인을 시도한다, 성공시 프로필 정보를 반환하고 실패시 null을 반환한다", response = Map.class)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody @ApiParam(value = "서드파티 로그인의 결과로 나온 ID", example = "{\"authid\":\"123456789\"}") Map<String, String> body) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            UserProfileDto userProfileDto = userService.login(body.get("authid")); // thirdPartyId
            result.put("profile", userProfileDto);
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserProfileDto userProfileDto) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {

            userProfileDto = userService.register(userProfileDto);
            result.put("profile", userProfileDto);

            status = HttpStatus.ACCEPTED;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(result, status);
    }
}
