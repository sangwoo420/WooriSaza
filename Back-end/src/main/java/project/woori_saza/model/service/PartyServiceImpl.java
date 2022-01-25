package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.MemberInfo;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.MemberInfoRequestDto;
import project.woori_saza.model.dto.PartyDto;
import project.woori_saza.model.dto.PartyResponseDto;
import project.woori_saza.model.repo.MemberInfoRepo;
import project.woori_saza.model.repo.PartyRepo;
import project.woori_saza.model.repo.UserProfileRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PartyServiceImpl implements PartyService{

    @Autowired
    PartyRepo partyRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    @Autowired
    MemberInfoRepo memberInfoRepo;

    @Override
    public List<PartyResponseDto> getPartyList(String id) {
        //1.받은 userProfile로 memberinfo찾기
   //     System.out.println("id="+id);
        UserProfile userProfile=userProfileRepo.getById(id); //프로필 1개 나옴
   //     System.out.println("userprofile의 memberinfo="+userProfile.getMemberInfos()); //3개 나옴
        List<MemberInfo>memberInfos=memberInfoRepo.findAllByUserProfile(userProfile); //배열 3개일듯
    //    System.out.println("memberInfos = " + memberInfos);

        //2.그 memberInfos에 해당하는 파티들을 찾아서 parties에 넣어주기
        List<PartyResponseDto> parties=new ArrayList<>();


        for(MemberInfo memberInfo:memberInfos){
            Party party=memberInfo.getParty();
    //        System.out.println("party.getId() = " + party.getId());
    //        System.out.println("memberInfo.getId() = " + memberInfo.getId());
            PartyResponseDto partyResponseDto=new PartyResponseDto(party,memberInfo);
    //        System.out.println("memberinfo , partyResponsedto"+memberInfo.getId()+" "+partyResponseDto.getId());
            parties.add(partyResponseDto);
        }
        System.out.println("parties"+" "+parties);
        return parties;
    }

//      for(MemberInfo memberInfo:memberInfos){
//        Party party=memberInfo.getParty();
//        PartyResponseDto partyResponseDto=new PartyResponseDto(party,memberInfo);
//        System.out.println("memberinfo , partyResponsedto"+memberInfo.getId()+" "+partyResponseDto.getId());
//        parties.add(partyResponseDto);
//    }

    @Override
    public List<PartyDto> getDetailList(Long id) {
        List<PartyDto>partyDtos=new ArrayList<>();
        //1.해당 파티 찾기
        Party party=partyRepo.getById(id);
        //2.파티에 해당하는 memberinfo 리스트찾기
        List<MemberInfo>memberInfos=memberInfoRepo.findAllByParty(party);
        //3.memberinfo에 해당하는 user정보를 찾고 dto에 넣어주기 + party +memberinfo
        for(MemberInfo memberInfo:memberInfos){
           UserProfile userProfile=memberInfo.getUserProfile();
           PartyDto partyDto=new PartyDto(userProfile,party,memberInfo);
           partyDtos.add(partyDto);
        }
        return partyDtos;
    }
//    @Override
//    @Transactional
//    public String insertApplyForm(MemberInfoRequestDto memberInfoRequestDto) {
//       MemberInfo memberInfo=memberInfoRequestDto.toEntity();
//
//    }

    @Override
    @Transactional
    public void deleteParty(Long partyId) {
        partyRepo.deleteById(partyId);
    }



//    @Override
//    public String insertApplyForm(PartyApplyDto partyApplyDto) {
//
//        return null;
//    }

//    @Override
//    public PartyDto getPartyOne(Long partyId, String userId) {
//
//        //1.받은걸로 party와 userprofile찾기
//        Party party=partyRepo.getById(partyId);
//
//        //2.파티아이디와 같은 member_info찾기(리스트)
//        List<MemberInfo>memberInfos=memberInfoRepo.findAllByParty(party);
//
//        //3.리스트를 돌면서 여기에 있는 member_info와 user_profile id가 같은거 찾기 => 그럼 한개 나올듯
//        //  이제 거기에서 나온 memberinfo에있는 정보들을 dto에 넣어주기
//        for(MemberInfo memberInfo:memberInfos){
//            if(memberInfo.getUserProfile().getId().equals(userId)){
//                PartyDto partyDto = new PartyDto(party, memberInfo);
//                return partyDto;
//            }
//        }
//        return null;
//    }


}
