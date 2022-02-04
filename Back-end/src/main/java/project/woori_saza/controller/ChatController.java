package project.woori_saza.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import project.woori_saza.model.domain.ChatMessage;
import project.woori_saza.model.domain.ChatRoom;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.ChatMessageDto;
import project.woori_saza.model.repo.ChatMessageRepo;
import project.woori_saza.model.repo.ChatRoomRepo;
import project.woori_saza.model.repo.UserProfileRepo;
import project.woori_saza.model.service.ChatRoomService;
import project.woori_saza.pubsub.RedisPublisher;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final RedisPublisher redisPublisher;
    private final ChatRoomService chatRoomService;

    @Autowired
    ChatMessageRepo chatMessageRepo;

    @Autowired
    ChatRoomRepo chatRoomRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
//         ENTER -> 처음 입장 -> 못하겠음^^..
//        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
//            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
//        }

        // 타입으로 처리할 때
//        if(ChatMessage.MessageType.CHAT.equals(message.getType())){
//            System.out.println(message.getSender());
//            System.out.println(message.getMessage());
//            System.out.println(message.getRoomId());
//        }

        // TODO: dto로 받아온 채팅 메시지 DB저장
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setType(message.getType());
        chatMessage.setContent(message.getContent());
        chatMessage.setTime(LocalDateTime.now());
        ChatRoom chatRoom = chatRoomRepo.getById(message.getRoomId());
        chatMessage.setChatRoom(chatRoom);
        UserProfile userProfile = userProfileRepo.findByNickname(message.getSender());
        chatMessage.setUserProfile(userProfile);
        chatMessageRepo.save(chatMessage);

        // Websocket에 발행된 메시지를 redis로 발행한다(publish)
        redisPublisher.publish(chatRoomService.getTopic(chatRoom.getId()), chatMessage);
    }
}
