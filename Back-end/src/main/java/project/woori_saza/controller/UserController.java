package project.woori_saza.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.woori_saza.model.domain.UserAuth;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.service.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        UserProfile userProfile = userService.login(params.get("id"));
        result.put("profile", userProfile);
        status = HttpStatus.ACCEPTED;

        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            UserAuth userAuth = new UserAuth(params.get("authId"),false,null);
            UserProfile userProfile = new UserProfile();
            userProfile.setId(params.get("profileId"));
            userProfile.setAddress(params.get("profileAddress"));
            userProfile.setNickname(params.get("profileNickname"));
            userProfile.setJoinDate(LocalDateTime.now());
            userProfile.setPic(params.get("profilePic"));
            userService.register(userAuth, userProfile);
            status = HttpStatus.ACCEPTED;
        } catch (RuntimeException e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(result, status);
    }
}
