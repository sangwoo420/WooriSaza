package project.woori_saza.controller;

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
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        UserProfileDto userProfileDto = userService.login(params.get("authId")); // thirdPartyId
        result.put("profile", userProfileDto);
        status = HttpStatus.ACCEPTED;

        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            UserProfileDto userProfileDto = new UserProfileDto();
            userProfileDto.setAddress(params.get("profileAddress"));
            userProfileDto.setNickname(params.get("profileNickname"));
            userProfileDto.setJoinDate(LocalDateTime.now());
            userProfileDto.setPic(params.get("profilePic"));
            userService.register(params.get("authId"), userProfileDto);
            status = HttpStatus.ACCEPTED;
        } catch (RuntimeException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(result, status);
    }
}
