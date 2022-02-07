package project.woori_saza.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import project.woori_saza.model.domain.ChatMessage;
import project.woori_saza.model.domain.ChatRoom;
import project.woori_saza.model.domain.MessageType;
import project.woori_saza.model.dto.ChatMessageDto;
import project.woori_saza.model.repo.ChatMessageRepo;
import project.woori_saza.model.repo.ChatRoomRepo;
import project.woori_saza.model.repo.UserProfileRepo;
import project.woori_saza.model.service.ChatRoomService;
import project.woori_saza.pubsub.RedisPublisher;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
@Slf4j
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
    public void message(ChatMessageDto message, @RequestParam String userNickname) {

        // TODO: dto로 받아온 채팅 메시지 DB저장
        ChatRoom chatRoom = chatRoomRepo.getById(message.getRoomId());
        ChatMessage chatMessage = ChatMessage.createChatMessage(chatRoom, message.getType(), message.getContent(), userNickname,LocalDateTime.now());
        log.info(">>>>>>>>>채팅 메시지<<<<<<<<<");
        if (MessageType.ENTER.equals(message.getType())) {
            message.setContent(userNickname + "님이 입장하셨습니다.");
        }else if(MessageType.QUIT.equals(message.getType())){
            message.setContent(userNickname + "님이 퇴장하셨습니다.");
            // TODO: 퇴장 만들기
//            chatService.deleteById(message.getChatRoom());
        }
        chatMessageRepo.save(chatMessage);
        chatRoom.addChatMessages(chatMessage);

        // Websocket에 발행된 메시지를 redis로 발행한다(publish)
        redisPublisher.publish(chatRoomService.getTopic(chatRoom.getId()), chatMessage);
    }
}
