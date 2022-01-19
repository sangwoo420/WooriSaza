package project.woori_saza.model.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Comment {

    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private Long id;

    private String content;

    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private UserProfile profileId;
}
