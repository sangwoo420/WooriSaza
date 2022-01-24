package project.woori_saza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.MemberInfo;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.domain.UserProfile;

import java.time.LocalDateTime;

//파티 상세정보에 보여줄 dto
@Data
@NoArgsConstructor
@AllArgsConstructor
 public class PartyDto {
    //userProfile
    private String address; //주소

    private String nickname; //닉네임

    private LocalDateTime joinDate; //가입일

    private String pic; //사진

    private Integer score; //점수

    //party
    private String title; //제목
    private String product; //상품
    private LocalDateTime deadline; //진행기간

    //memberinfo
    private Boolean isBoss; //파티장 여부
 //   private Integer totalcount; //내가 선택한 인분 =>
    private Integer totalamount; // 내가 선택 총 수량
    private Integer price; // 결제금액

    private Boolean isConfirmed; //파티원 구매확정여부

    public PartyDto(UserProfile userProfile,Party party, MemberInfo memberInfo){
        address=userProfile.getAddress();
        nickname=userProfile.getNickname();
        joinDate=userProfile.getJoinDate();
        pic=userProfile.getPic();
        score=userProfile.getScore();

        title=party.getArticle().getTitle();
        product=party.getProduct();
        deadline=party.getDeadline();
        isBoss=memberInfo.getIsBoss();
        // totalcount=
        totalamount=memberInfo.getAmount();
        isConfirmed=memberInfo.getIsConfirmed();
        price=memberInfo.getPrice();
    }

}