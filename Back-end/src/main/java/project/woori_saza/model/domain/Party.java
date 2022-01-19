package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    private boolean formChecked;

    @OneToOne(mappedBy = "party",fetch = FetchType.LAZY)
    private PaidForm paidForm;

    @OneToOne(mappedBy = "party",fetch = FetchType.LAZY)
    private Article article;

    @OneToMany(mappedBy = "party")
    private List<MemberInfo> memberInfos = new ArrayList<>();


}
