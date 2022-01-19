package project.woori_saza.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private int id;

    private String content;

    private LocalDateTime date;

    private int score;

    // review_from_id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="profile_id")
    private UserProfile userprofile;
    // review_to_id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="profile_id")
    private UserProfile userprofile;


}
