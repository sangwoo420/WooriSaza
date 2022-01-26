package project.woori_saza.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.dto.PaidFormRequestDto;
import project.woori_saza.model.dto.PaidFormResponseDto;
import project.woori_saza.model.service.PaidFormService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/paidForm")
public class PaidFormController {

    @Autowired
    PaidFormService paidFormService;

    @ApiOperation(value = "결제 인증 폼 조회", notes = "결제 인증 폼을 조회한다.", response = Map.class)
    @GetMapping("/{paidFormId}")
    public ResponseEntity<Map<String, Object>> getPaidForm(@PathVariable Long paidFormId) {
        Map<String, Object> result = new HashMap<>();
        PaidFormResponseDto paidForm = null;
        HttpStatus httpStatus = null;
        try {
            paidForm = paidFormService.getPaidForm(paidFormId);
            httpStatus = HttpStatus.OK;
        } catch (RuntimeException e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        result.put("paidForm", paidForm);
        return new ResponseEntity<Map<String, Object>>(result, httpStatus);
    }

    @ApiOperation(value = "결제 인증 폼 작성", notes = "결제 인증 폼을 작성한다.")
    @PostMapping
    public ResponseEntity<Map<String, Object>> insert(@RequestBody PaidFormRequestDto paidFormRequestDto) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        try {
            paidFormService.insertPaidForm(paidFormRequestDto);
            httpStatus = HttpStatus.OK;
            result.put("success", true);
        } catch (RuntimeException e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String, Object>>(result, httpStatus);
    }

    @ApiOperation(value = "결제 인증 폼 수정", notes = "결제 인증 폼을 수정한다.", response = Map.class)
    @PutMapping("/{paidFormId}")
    public ResponseEntity<Map<String, Object>> updatePaidForm(@PathVariable("paidFormId") Long paidFormId, @RequestBody PaidFormRequestDto paidFormRequestDto) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        try {
            paidFormService.updatePaidForm(paidFormId, paidFormRequestDto);
            httpStatus = HttpStatus.OK;
            result.put("success", true);
        } catch (RuntimeException e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String, Object>>(result, httpStatus);
    }

    @ApiOperation(value = "후기 삭제", notes = "파티원들의 후기를 삭제한다.", response = Map.class)
    @DeleteMapping("/{paidFormId}")
    public ResponseEntity<Map<String, Object>> deletePaidForm(@PathVariable("paidFormId") Long paidFormId) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        try {
            paidFormService.deletePaidForm(paidFormId);
            httpStatus = HttpStatus.OK;
            result.put("success", true);
        } catch (RuntimeException e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String, Object>>(result, httpStatus);
    }

}