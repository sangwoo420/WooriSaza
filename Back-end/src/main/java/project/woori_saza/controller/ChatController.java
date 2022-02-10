package project.woori_saza.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.domain.ChatMessage;
import project.woori_saza.model.domain.ChatRoom;
import project.woori_saza.model.domain.MessageType;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.ChatMessageDto;
import project.woori_saza.model.repo.ChatMessageRepo;
import project.woori_saza.model.repo.ChatRoomRepo;
import project.woori_saza.model.repo.UserProfileRepo;
import project.woori_saza.model.service.ChatRoomService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ChatController {

    @Autowired
    ChatMessageRepo chatMessageRepo;

    @Autowired
    ChatRoomRepo chatRoomRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    private final SimpMessagingTemplate template; // 특정 브로커로 메시지 전달

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
        System.out.println(message);

        // TODO: dto로 받아온 채팅 메시지 DB저장
        ChatRoom chatRoom = chatRoomRepo.getById(message.getRoomId());

        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        ChatMessage chatMessage = ChatMessage.createChatMessage(chatRoom, message.getType(), message.getContent(), message.getSender(), time);
        message.setTime(time);

        log.info(">>>>>>>>>채팅 메시지<<<<<<<<<");
        chatMessageRepo.save(chatMessage);

        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);

    }
}
