package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Qna {
    @Id
    @GeneratedValue
    @Column(name = "qna_id")
    private Integer id;

    @NotNull
    private String category;

    @NotNull
    private String content;

    @NotNull
    private String title;


    private String comment;

    @ElementCollection
    private List<String> pic=new ArrayList<>();

    //profile_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_id")
    UserProfile userProfile;

}