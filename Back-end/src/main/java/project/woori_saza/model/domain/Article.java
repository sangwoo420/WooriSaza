package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @Column(name = "article_id")
    private long id;

    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String link;
    private LocalDateTime deadline;
    private int totalPrice;
    private int totalCount;
    private int recruitCount;
    private boolean isDone;
    private List<String> pic;

    // referencing area

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    @Enumerated(EnumType.STRING)
    private Tag tag;

    @Enumerated(EnumType.STRING)
    private Category category;

    // referenced area

    @OneToMany(mappedBy = "comment")
    List<Comment> comments;
}
