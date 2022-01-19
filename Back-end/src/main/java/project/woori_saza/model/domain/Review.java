package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@AllArgsConstructor
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_id")
    private UserProfile fromUser;

    // review_to_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_id")
    private UserProfile toUser;

}
