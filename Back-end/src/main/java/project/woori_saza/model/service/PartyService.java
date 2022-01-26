package project.woori_saza.model.service;


import project.woori_saza.model.dto.MemberInfoRequestDto;
import project.woori_saza.model.dto.PartyDto;
import project.woori_saza.model.dto.PartyResponseDto;

import java.util.List;

public interface PartyService {

     List<PartyResponseDto> getPartyList(String id); //내 모든 파티리스트
//     PartyDto getPartyOne(Long partyId, String userId); //파티리스트 상세 한개
     List<PartyDto> getDetailList(Long id); //파티 디테일 리스트
     void deleteParty(Long partyId); //삭제
     void insertApplyForm(MemberInfoRequestDto memberInfoRequestDto); //신청서 작성


}
