package project.woori_saza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.PartyDto;
import project.woori_saza.model.dto.PartyResponseDto;
import project.woori_saza.model.service.PartyService;
import project.woori_saza.model.service.PartyServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/party")
public class PartyController {
    @Autowired
    PartyService partyService;

    //내 파티리스트 전체 조회
    //  @ApiOperation(value="파티 리스트",notes = "사용자의 파티 리스트를 반환다.",response = Map.class)
    @PostMapping("/")
    public ResponseEntity<List<PartyResponseDto>> GetPartyList(@RequestBody Map<String,String> params){
        List<PartyResponseDto> list= partyService.getPartyList(params.get("id"));
        return new ResponseEntity<List<PartyResponseDto>>(list, HttpStatus.OK);
    }


    // 내 파티 상세조회 (클릭 후)
//    @PostMapping("/{partyId}")
//    public ResponseEntity<PartyDto> GetPartyOne(@PathVariable("partyId")Long partyId,@RequestBody Map<String,String> params){
//        PartyDto party=partyService.getPartyOne(partyId,params.get("id"));
//            return new ResponseEntity<>(party,HttpStatus.OK);
//    }

    //파티리스트 디테일 리스트 조회
    @PostMapping("/{partyId}")
    public ResponseEntity<List<PartyDto>> getDetailList(@PathVariable("partyId")Long partyId){
        List<PartyDto> party=partyService.getDetailList(partyId);
        return new ResponseEntity<List<PartyDto>>(party,HttpStatus.OK);
    }


    //파티리스트 삭제
    @DeleteMapping("/{partyId}")
    public ResponseEntity<Map<String, Object>> deleteParty(@PathVariable Long partyId){
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        try{
            partyService.deleteParty(partyId);
            httpStatus = HttpStatus.OK;
            result.put("success", true);
        }catch (RuntimeException e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String,Object>>(result, httpStatus);
    }
}

