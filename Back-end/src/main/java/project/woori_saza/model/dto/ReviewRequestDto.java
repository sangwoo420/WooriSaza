package project.woori_saza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.Review;
import project.woori_saza.model.domain.UserProfile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {

    private String content;
    private LocalDateTime date;
    private int score;
    private UserProfile toUser;
    private UserProfile fromUser;

    @Builder
    public ReviewRequestDto(String content, LocalDateTime date, UserProfile toUser, UserProfile fromUser) {
        this.content = content;
        this.date = date;
        this.toUser = toUser;
        this.fromUser = fromUser;
    }

    public Review toEntity() {
        return Review.builder()
                .content(content)
                .date(date)
                .toUser(toUser)
                .fromUser(fromUser)
                .build();
    }

}
