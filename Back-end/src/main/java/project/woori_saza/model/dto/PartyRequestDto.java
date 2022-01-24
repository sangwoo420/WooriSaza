package project.woori_saza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyRequestDto {
    private LocalDateTime deadline; //데드라인
    private String product; //상품
    private Integer totalPrice; //총가격
    private Integer totalProductCount; //총수량
    private Integer totalRecruitMember; //참여멤버

}
