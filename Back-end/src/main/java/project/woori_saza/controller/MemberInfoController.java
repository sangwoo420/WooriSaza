package project.woori_saza.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.dto.MemberInfoRequestDto;
import project.woori_saza.model.service.MemberInfoService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/memberinfo")
@Api("멤버 컨트롤러")
public class MemberInfoController {

    @Autowired
    MemberInfoService memberInfoService;


    @ApiOperation(value="파티원 신청서 작성",notes="파티원이 신청서를 작성한다.")
    @PostMapping
    public ResponseEntity<Map<String,Object>> insertMemberInfo(@RequestBody @ApiParam( value="사용자가 작성하는 파티에 대한 정보",required = true) MemberInfoRequestDto memberInfoRequestDto){
        Map<String,Object>result=new HashMap<>();
        HttpStatus status=null;
        System.out.println("찍어보자"+memberInfoRequestDto.getPartyId()+" "+memberInfoRequestDto.getProfileId());
        try{
            memberInfoService.insertMemberInfo(memberInfoRequestDto);
            status=HttpStatus.OK;
            result.put("success", true);
        }catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String, Object>>(result, status);
    }


    @ApiOperation(value="파티원 파티참가 취소",notes="파티원이 파티를 나간다.")
    @DeleteMapping("/{memberinfoId}")
    public ResponseEntity<Map<String, Object>> deleteMemberInfo(@PathVariable  @ApiParam(value="memberinfo 아이디",required = true) Long memberinfoId){
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        try{
            memberInfoService.deleteMemberInfo(memberinfoId);
            httpStatus = HttpStatus.OK;
            result.put("success", true);
        }catch (RuntimeException e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String,Object>>(result, httpStatus);

    }

}
