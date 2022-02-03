package project.woori_saza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.ChatRoomJoin;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomJoinDto {

    private Long id;
    private String profileId;
    private String roomId;

    public ChatRoomJoinDto(ChatRoomJoin chatRoomJoin){
        this.id = chatRoomJoin.getId();
        this.profileId = chatRoomJoin.getUserProfile().getId();
        this.roomId = chatRoomJoin.getChatRoom().getId();
    }

}
