package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.ChatRoom;

public interface ChatRoomRepo extends JpaRepository<ChatRoom, String> {

}