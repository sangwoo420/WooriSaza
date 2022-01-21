package project.woori_saza.model.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Article {

    @Id
    @Column(name = "article_id")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
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

    @Builder
    public Article(Long id, String title, String content, LocalDateTime createdAt, String link, List<String> pic) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.link = link;
        this.pic = pic;
    }
}
