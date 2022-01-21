package project.woori_saza.model.domain;

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
    private Integer totalPrice;

    @NotNull
    private Integer totalCount;

    @NotNull
    private Integer recruitCount;

    @NotNull
    private Boolean isDone;

    @OneToOne(mappedBy = "party",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private PaidForm paidForm;

    @OneToOne(mappedBy = "party",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Article article;

    @OneToMany(mappedBy = "party", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MemberInfo> memberInfos = new ArrayList<>();

}
