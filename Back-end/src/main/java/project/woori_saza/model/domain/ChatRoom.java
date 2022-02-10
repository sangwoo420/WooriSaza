package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_room")
public class ChatRoom implements Serializable{

    private static final long serialVersionUID = 6494678977089006639L;

    @Id
    @Column(name="room_id")
    private String id;

    private String name;

    private Long article_id;

    private int count;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatRoomJoin> chatRoomJoinList = new ArrayList<>();

    public static ChatRoom create(String articleName, Long articleId) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.id = UUID.randomUUID().toString();
        chatRoom.article_id = articleId;
        chatRoom.name = articleName;
        chatRoom.count = 1;
        return chatRoom;
    }

}
