package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.ChatMessage;
import project.woori_saza.model.domain.ChatRoom;
import project.woori_saza.model.domain.ChatRoomJoin;

import java.util.List;

public interface ChatMessageRepo extends JpaRepository<ChatMessage, Long> {
   // List<ChatMessage> findByChatRoom(ChatRoom chatRoom);
}