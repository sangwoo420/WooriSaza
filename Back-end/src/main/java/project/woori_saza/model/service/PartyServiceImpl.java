package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.MemberInfo;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.ArticleResponseDto;
import project.woori_saza.model.dto.PartyResponseDto;
import project.woori_saza.model.repo.MemberInfoRepo;
import project.woori_saza.model.repo.PartyRepo;
import project.woori_saza.model.repo.UserProfileRepo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
        System.out.println("id"+id);
        UserProfile userProfile=userProfileRepo.getById(id);
        System.out.println("userprofile"+userProfile.getMemberInfos());
        List<MemberInfo>memberInfos=memberInfoRepo.findAllByUserProfile(userProfile);
        System.out.println("멤버인포확인"+memberInfos);
        //2.그 memberInfos에 해당하는 파티들을 찾아서 parties에 넣어주기
        List<Party> parties=new ArrayList<>();
        for(MemberInfo memberInfo:memberInfos){
            parties.add(partyRepo.findAllByMemberInfos(memberInfo));
        }
        return parties.stream().map(PartyResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public String updateParty(Party party) {

        Party updateparty=partyRepo.getById(party.getId());

    }


}
