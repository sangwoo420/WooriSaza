package project.woori_saza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.ChatRoom;

import java.io.Serializable;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDto implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

    private String id;
    private Long article_id;

    public ChatRoomDto(ChatRoom chatRoom){
        this.id = chatRoom.getId();
        this.article_id = chatRoom.getArticle().getId();
    }

}
