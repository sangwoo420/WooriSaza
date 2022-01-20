package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuth {

    @Id
    @Column(name="auth_id")
    private String id;

    @Column(columnDefinition = "boolean default false")
    private Boolean admin;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_id")
    private UserProfile userProfile;

}
