package project.woori_saza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.MemberInfo;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.domain.Tag;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyResponseDto {
//    private String title; //제목
//    private Tag tag; //태그
    private Boolean isBoss; //파티장/원 유무
    private Long id; //파티 아이디
    private LocalDateTime deadline; //마감일
    private Integer totalPrice; //총금액
//    private Integer totalProductCount; //총 물건수량
    private Integer totalRecruitMember; //총 모집인원수
    private Integer currentRecruitMember; //현재 인원수
    private Integer myPrice; // 총 금액/현재 인원수

    public PartyResponseDto(Party party,MemberInfo memberInfo){
//        title= party.getArticle().getTitle();
//        tag=party.getArticle().getTag();
        isBoss=memberInfo.getIsBoss();
        id=party.getId();
        deadline=party.getDeadline();
        totalPrice=party.getTotalPrice();
  //      totalProductCount=party.getTotalProductCount();
        totalRecruitMember=party.getTotalRecruitMember();
        currentRecruitMember=party.getCurrentRecruitMember();
        myPrice=party.getTotalPrice()/totalRecruitMember;
    }
}
