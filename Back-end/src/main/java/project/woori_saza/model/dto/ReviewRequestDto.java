package project.woori_saza.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import project.woori_saza.model.domain.Review;
import project.woori_saza.model.domain.UserProfile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewRequestDto {

    @ApiModelProperty(value = "후기 내용", example = "content")
    private String content;
    @ApiModelProperty(value = "후기 점수", example = "score")
    private int score;
    @ApiModelProperty(value = "후기 대상자", example = "toUser")
    private String toUser;
    @ApiModelProperty(value = "후기 작성자", example = "fromUser")
    private String fromUser;

    @Builder
    public ReviewRequestDto(String content, int score, String toUser, String fromUser) {
        this.content = content;
        this.score = score;
        this.toUser = toUser;
        this.fromUser = fromUser;
    }

    public Review toEntity() {
        return Review.builder()
                .content(content)
                .score(score)
                .build();
    }

}