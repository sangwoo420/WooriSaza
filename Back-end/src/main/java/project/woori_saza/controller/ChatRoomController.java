package project.woori_saza.controller;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.domain.ChatMessage;
import project.woori_saza.model.domain.ChatRoom;
import project.woori_saza.model.domain.MessageType;
import project.woori_saza.model.dto.ChatMessageDto;
import project.woori_saza.model.dto.ChatRoomDto;
import project.woori_saza.model.service.ChatRoomService;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    @Autowired
    ChatRoomService chatRoomService;

    // 내 채팅방 목록 띄우기
    @GetMapping("/room/{profileId}")
            public ResponseEntity<Map<String, Object>> rooms(@PathVariable String profileId) {
                Map<String, Object> result = new HashMap<>();
                List<String> roomIdList = null;
                List<ChatRoomDto> roomList = new ArrayList<>();
                HttpStatus httpStatus = null;

                try{
                    roomIdList = chatRoomService.findAllRoom(profileId);
                    for (String roomId : roomIdList) {
                        roomList.add(chatRoomService.findRoomByRoomID(roomId));
                    }
                    httpStatus = HttpStatus.OK;
                }catch (RuntimeException e) {
                    httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        result.put("roomList", roomList);

        return new ResponseEntity<>(result, httpStatus);
    }

    // 채팅방 입장
    @GetMapping("/room/enter/{roomId}")
    public ResponseEntity<Map<String, Object>> roomDetail(@PathVariable String roomId) {
        Map<String, Object> result = new HashMap<>();
        ChatRoomDto chatRoom = null;
        HttpStatus httpStatus = null;

        try{
            // 방들어가기
            chatRoom = chatRoomService.findRoomByRoomID(roomId);
            System.out.println("roomName: " + chatRoom.getName());
            // TODO: 과거 채팅 내역 보여주기
            ChatMessage msg = ChatMessage.createChatMessage(chatRoom, MessageType.CHAT, "HIII", "나", LocalDateTime.now());
            ChatMessageDto msgDto = new ChatMessageDto(msg);
            chatRoom.getMsgList().add(msgDto);
            System.out.println("msgList: "+chatRoom.getMsgList());
            httpStatus = HttpStatus.OK;
        }catch (RuntimeException e) {
            e.printStackTrace();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        result.put("chatRoom", chatRoom);

        return new ResponseEntity<>(result, httpStatus);
    }



}
