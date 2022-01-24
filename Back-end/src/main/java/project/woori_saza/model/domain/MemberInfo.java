package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfo {

    @Id
    @Column(name="member_info_id")
    @GeneratedValue
    @NotNull
    private Long id;

    @Column(columnDefinition = "boolean default false")
    private Boolean isBoss; //파티장 여부

    @NotNull
    private Integer amount; // 파티원 선택수량

    @NotNull
    private Integer price; // 파티원 부담금액

    private Boolean isConfirmed; //파티원 구매확정여부


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_id")
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="party_id")
    private Party party;

}
