package project.woori_saza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.UserProfile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {

    private String id;

    private String address;

    private String nickname;

    private LocalDateTime joinDate;

    private String pic;

    private Integer score;

    private Integer cnt;

    public UserProfileDto(UserProfile userProfile){
        this.id = userProfile.getId();
        this.address = userProfile.getAddress();
        this.nickname = userProfile.getNickname();
        this.joinDate = userProfile.getJoinDate();
        this.pic = userProfile.getPic();
        this.score = userProfile.getScore();
        this.cnt = userProfile.getCnt();
    }
}
