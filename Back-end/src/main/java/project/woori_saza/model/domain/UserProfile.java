package project.woori_saza.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UserProfile {

    @Id
    @Column(name="profile_id")
    private String id;

    @NotNull
    private String address;

    @NotNull
    private String nickname;

    @NotNull
    private LocalDateTime joinDate;

    private String pic;

    private Integer score;

    private Integer cnt;

//    @JsonIgnore
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MemberInfo> memberInfos = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="auth_id")
    private UserAuth userAuth;

//    @JsonIgnore
    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Review> reviewsFrom = new ArrayList<>();

 //   @JsonIgnore
    @OneToMany(mappedBy = "toUser", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Review> reviewsTo = new ArrayList<>();

//    @JsonIgnore
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Qna> qnas = new ArrayList<>();

//    @JsonIgnore
    @OneToMany(mappedBy = "userProfile")
    private List<Comment> comments = new ArrayList<>();

//    @JsonIgnore
    @OneToMany(mappedBy = "userProfile")
    private List<Article> articles = new ArrayList<>();

//    @JsonIgnore
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Zzim> zzims = new ArrayList<>();

}
