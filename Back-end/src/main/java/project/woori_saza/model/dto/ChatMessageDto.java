package project.woori_saza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.ChatMessage;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {

    private Long id;
    private String msg_content;
    private LocalDateTime msg_time;
    private String roomId;
    private String profileId;

    public ChatMessageDto(ChatMessage chatMessage){
        this.id = chatMessage.getId();
        this.msg_content = chatMessage.getMsg_content();
        this.msg_time = chatMessage.getMsg_time();
        this.roomId = chatMessage.getChatRoom().getId();
        this.profileId = chatMessage.getUserProfile().getId();
    }
}
