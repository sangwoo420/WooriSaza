package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfo {

    @Id
    @Column(name="member_info_id")
    @GeneratedValue
    private Long id;

    private boolean isBoss;

    private int amount;

    private int price;

    private boolean isConfirmed;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_id")
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="party_id")
    private Party party;

    
}
