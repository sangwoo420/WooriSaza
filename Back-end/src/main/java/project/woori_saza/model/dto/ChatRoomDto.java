package project.woori_saza.model.dto;

import lombok.Data;
import project.woori_saza.model.domain.ChatRoom;

@Data
public class ChatRoomDto extends ChatRoom {

    private String id;
    private String name;
    private int count;

    public ChatRoomDto(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.name = chatRoom.getName();
        this.count = chatRoom.getCount();
    }
}
