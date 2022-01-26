package project.woori_saza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.domain.Zzim;
import project.woori_saza.model.dto.UserProfileDto;
import project.woori_saza.model.service.ZzimService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/zzim")
public class ZzimController {

    @Autowired
    ZzimService zzimService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getZzim(@RequestParam(required = false) String profileId,
                                                       @RequestParam(required = false) String articleId) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            if(profileId != null && articleId != null){
                result.put("zzim", zzimService.getZzim(profileId, Long.parseLong(articleId)));
            } else if(profileId != null){
                result.put("zzimList", zzimService.getZzimList(profileId));
            }else{
                result.put("zzimList", zzimService.getZzimList(Long.parseLong(articleId)));
            }
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        return new ResponseEntity<Map<String, Object>>(result, status);
    }

//    @GetMapping
//    public ResponseEntity<Map<String, Object>> getZzimListWithProfile(@RequestParam String profileId) {
//        Map<String, Object> result = new HashMap<>();
//        HttpStatus status = null;
//        List<Zzim> zzimList = null;
//        try {
//            zzimList = zzimService.getZzimList(profileId);
//            status = HttpStatus.OK;
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//
//        result.put("zzimList", zzimList);
//
//        return new ResponseEntity<Map<String, Object>>(result, status);
//    }
//
//    @GetMapping
//    public ResponseEntity<Map<String, Object>> getZzimListWithArticle(@RequestParam String articleId) {
//        Map<String, Object> result = new HashMap<>();
//        HttpStatus status = null;
//        List<Zzim> zzimList = null;
//        try {
//            zzimList = zzimService.getZzimList(Long.parseLong(articleId));
//            status = HttpStatus.OK;
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//
//        result.put("zzimList", zzimList);
//
//        return new ResponseEntity<Map<String, Object>>(result, status);
//    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> insertZzim(@RequestBody Map<String, String> body) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            zzimService.insertZzim(body.get("profileid"), Long.parseLong(body.get("articleid")));
            status = HttpStatus.OK;
            result.put("success", true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }

        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteZzim(@RequestBody Map<String, String> body) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            zzimService.deleteZzim(body.get("profileid"), Long.parseLong(body.get("articleid")));
            status = HttpStatus.OK;
            result.put("success", true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }

        return new ResponseEntity<Map<String, Object>>(result, status);
    }
}
