package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @OneToMany(mappedBy = "chatMessage")
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoomJoin")
    private List<ChatRoomJoin> chatRoomJoinList = new ArrayList<>();

}
