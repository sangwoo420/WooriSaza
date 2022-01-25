package project.woori_saza.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private int score;

    // review_from_id
    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfile fromUser;

    // review_to_id
    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfile toUser;

    @Builder
    public Review(String content, LocalDateTime date, UserProfile toUser, UserProfile fromUser) {
        this.content = content;
        this.date = date;
        this.toUser = toUser;
        this.fromUser = fromUser;
    }

}
