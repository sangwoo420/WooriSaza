package project.woori_saza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.Review;
import project.woori_saza.model.domain.UserProfile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {

    private Long id;
    private String content;
    private LocalDateTime date;
    private int score;
    private String author;

    public ReviewResponseDto(Review review){
        id = review.getId();
        content = review.getContent();
        date = review.getDate();
        score = review.getScore();
        author = review.getFromUser().getNickname();
    }
}
