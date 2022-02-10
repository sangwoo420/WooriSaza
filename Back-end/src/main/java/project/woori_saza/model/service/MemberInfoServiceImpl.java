package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.*;
import project.woori_saza.model.dto.MemberInfoRequestDto;
import project.woori_saza.model.repo.*;

import java.util.List;

@Service
@Transactional
public class MemberInfoServiceImpl implements MemberInfoService{
    @Autowired
    PartyRepo partyRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    @Autowired
    MemberInfoRepo memberInfoRepo;

    @Autowired
    ChatRoomJoinRepo chatRoomJoinRepo;

    @Autowired
    ChatRoomService chatRoomService;

   @Autowired
   ChatRoomRepo chatRoomRepo;



    //회원이 신청서 작성
    @Override
    @Transactional
    public void insertMemberInfo(MemberInfoRequestDto memberInfoRequestDto) {
        //회원찾기
        UserProfile userProfile = userProfileRepo.getById(memberInfoRequestDto.getProfileId());
        //파티찾기
        Party party=partyRepo.getById(memberInfoRequestDto.getPartyId());
        //해당 파티 인원수 1명 늘려주기
        party.setCurrentRecruitMember(party.getCurrentRecruitMember()+1);
        //memberinfo에 저장
        MemberInfo memberInfo=MemberInfo.builder()
                .userProfile(userProfile)
                .party(party)
                .paidMethod(memberInfoRequestDto.getPaidMethod())
                .price(memberInfoRequestDto.getPrice())
                .amount(memberInfoRequestDto.getAmount())
                .build();
        memberInfoRepo.save(memberInfo);

        // 채팅방 입장
        List<ChatRoom> chatRoomList = chatRoomRepo.findChatRoomByArticleId(party.getArticle().getId());
        ChatRoom chatRoom = chatRoomList.get(0);
        System.out.println("맞는 채팅방?: "+chatRoom.getName());
        chatRoom.setCount(chatRoom.getCount()+1);
        ChatRoomJoin chatRoomJoin = chatRoomService.createChatRoomJoin(chatRoom, userProfile);
        chatRoomJoinRepo.save(chatRoomJoin);
    }

    //회원 삭제
    @Override
    public void deleteMemberInfo(Long memberinfoId) {
        MemberInfo memberInfo=memberInfoRepo.getById(memberinfoId);
        Party party=memberInfo.getParty();
        party.setCurrentRecruitMember(party.getCurrentRecruitMember()-1);
        memberInfoRepo.deleteById(memberinfoId);
    }
}
