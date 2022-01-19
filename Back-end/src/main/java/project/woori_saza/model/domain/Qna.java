package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Qna {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private int id;

    private String category;

    private String content;

    private String title;

    private String comment;

    @ElementCollection
    private List<String> pic=new ArrayList<>();

    //profile_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_id")
    UserProfile userProfile;

}
