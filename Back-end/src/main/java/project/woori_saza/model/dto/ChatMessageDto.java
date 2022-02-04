package project.woori_saza.model.dto;

import lombok.Data;
import project.woori_saza.model.domain.ChatMessage;
import project.woori_saza.model.domain.MessageType;

import java.time.LocalDateTime;

@Data
public class ChatMessageDto {

    private MessageType type;
    private String content;
    private LocalDateTime time;
    private String roomId;
    private String sender;

    public ChatMessageDto(ChatMessage chatMessage) {
        this.type = chatMessage.getType();
        this.content = chatMessage.getContent();
        this.time = chatMessage.getTime();
        this.roomId = chatMessage.getChatRoom().getId();
        this.sender = chatMessage.getUserProfile().getNickname();
    }
}