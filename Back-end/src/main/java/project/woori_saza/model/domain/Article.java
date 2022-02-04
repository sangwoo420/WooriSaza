package project.woori_saza.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    @Column(length = 50000)
    private String link;

    @ElementCollection
    private List<String> pic;

    // referencing area

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;


    @Enumerated(EnumType.STRING)
    private Tag tag;

    @Enumerated(EnumType.STRING)
    private Category category;

    // referenced area

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Zzim> zzims = new ArrayList<>();

    @OneToOne(mappedBy = "article", cascade = CascadeType.ALL)
    private ChatRoom chatRoom;

//    @Builder
//    public Article(Long id, String title, String content, LocalDateTime createdAt, String link, List<String> pic, UserProfile userProfile, Party party, Tag tag, Category category, List<Comment> comments, List<Zzim> zzims) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.createdAt = createdAt;
//        this.link = link;
//        this.pic = pic;
//        this.userProfile = userProfile;
//        this.party = party;
//        this.tag = tag;
//        this.category = category;
//        this.comments = comments;
//        this.zzims = zzims;
//    }
}
