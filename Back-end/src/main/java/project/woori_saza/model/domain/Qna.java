package project.woori_saza.model.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
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
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="profile_id")
    UserProfile userProfile;

}
