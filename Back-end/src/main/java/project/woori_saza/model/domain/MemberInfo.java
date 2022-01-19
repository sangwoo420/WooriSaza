package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfo {

    @Id
    @Column(name="member_info_id")
    private String id;

    private boolean isBoss;

    private int amount;

    private int price;

    private boolean isConfirmed;


    @ManyToOne
    @JoinColumn(name="profile_id")
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name="party_id")
    private Party party;
    
}
