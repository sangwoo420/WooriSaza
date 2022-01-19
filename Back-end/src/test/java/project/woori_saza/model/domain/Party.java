package project.woori_saza.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@Setter
public class Party {

    @Id
    @GeneratedValue
    @Column(name = "party_id")
    private Long id;

    private boolean formChecked;

    @OneToOne(mappedBy = "party")
    private PaidForm paidForm;

    @OneToOne(mappedBy = "article")
    private Article article;

    @OneToMany(mappedBy = "party")
    private List<MemberInfo> memberInfos = new ArrayList<>();

}
