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
    @OneToOne(mappedBy = "party")
    @OneToMany(mappedBy = "comment")
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

    //FK

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}
