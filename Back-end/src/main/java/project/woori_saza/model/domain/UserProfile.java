package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.namespace.QName;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    @Id
    @Column(name="profile_id")
    private String id;

    private String address;

    private String nickname;

    private LocalDateTime joinDate;

    private String pic;

    private int score;

    private int cnt;


    @OneToMany(mappedBy = "userProfile")
    private List<MemberInfo> memberInfos = new ArrayList<>();

    @OneToOne(mappedBy = "userProfile")
    private UserAuth userAuth;

    @OneToMany(mappedBy = "fromUser")
    private List<Review> reviewsFrom = new ArrayList<>();

    @OneToMany(mappedBy = "toUser")
    private List<Review> reviewsTo = new ArrayList<>();

    @OneToMany(mappedBy = "userProfile")
    private List<Qna> qnas = new ArrayList<>();

    @OneToMany(mappedBy = "userProfile")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "userProfile")
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "userProfile")
    private List<Zzim> zzims = new ArrayList<>();

}
