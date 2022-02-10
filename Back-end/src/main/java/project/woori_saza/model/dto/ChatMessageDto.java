package project.woori_saza.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.ChatMessage;
import project.woori_saza.model.domain.MessageType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {

    private MessageType type;
    private String content;
    private String sender;
    private String time;
    private String roomId;

    public ChatMessageDto(ChatMessage chatMessage) {
        this.type = chatMessage.getType();
        this.content = chatMessage.getContent();
        this.sender = chatMessage.getSender();
        this.time = chatMessage.getTime();
        this.roomId = chatMessage.getChatRoom().getId();
    }
}
