package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private Integer id;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private int score;

    // review_from_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserProfile fromUser;

    // review_to_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserProfile toUser;

}
