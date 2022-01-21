package project.woori_saza.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Party {

    @Id
    @GeneratedValue
    @Column(name = "party_id")
    private Long id;

    @NotNull
    private Boolean formChecked;

    @NotNull
    private LocalDateTime deadline;

    @NotNull
    private String product;

    @NotNull
    private Integer totalPrice; //총금액

    @NotNull
    private Integer totalProductCount; //총 물건수량

    @NotNull
    private Integer totalRecruitMember; //총 모집인원수

    @NotNull
    private Integer currentRecruitMember; //현재 인원수

    @NotNull
    private Boolean isClosed; //마감

    @OneToOne(mappedBy = "party",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private PaidForm paidForm;

    @OneToOne(mappedBy = "party",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Article article;

    @OneToMany(mappedBy = "party", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MemberInfo> memberInfos = new ArrayList<>();

}
