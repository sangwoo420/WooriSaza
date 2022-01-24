package project.woori_saza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.PartyResponseDto;
import project.woori_saza.model.service.PartyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/party")
public class PartyController {
    @Autowired
    PartyService partyService;

    //리스트 전체 조회
    //  @ApiOperation(value="파티 리스트",notes = "사용자의 파티 리스트를 반환다.",response = Map.class)
    @GetMapping("/")
    public ResponseEntity<List<PartyResponseDto>> GetPartyList(@RequestParam Map<String,String> params){
        List<PartyResponseDto> list= partyService.getPartyList(params.get("id"));
        return new ResponseEntity<List<PartyResponseDto>>(list, HttpStatus.OK);
    }

    // 파티 상세조회
//    @GetMapping("/{partyId}")
//    public ResponseEntity<Party> GetPartyOne(@PathVariable("partyId")Long partyId){
//
//
//    }
    //파티리스트 수정
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> UpdateParty(@RequestBody Party party){
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        String result=partyService.updateParty(party);
        if(result=="success"){
            status = HttpStatus.ACCEPTED;
        }else{
            status=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(result, status);
    }
    //파티리스트 삭제



    //파티리스트 삭제


}
