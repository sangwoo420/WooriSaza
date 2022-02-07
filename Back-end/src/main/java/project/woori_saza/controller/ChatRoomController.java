package project.woori_saza.controller;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.domain.ChatRoom;
import project.woori_saza.model.dto.ChatRoomDto;
import project.woori_saza.model.service.ChatRoomService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                List<String> roomList = null;
                HttpStatus httpStatus = null;

                try{
                    roomList = chatRoomService.findAllRoom(profileId);
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
            System.out.println("msgLis: "+chatRoom.getChatMessageList());
            httpStatus = HttpStatus.OK;
        }catch (RuntimeException e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        result.put("chatRoom", chatRoom);

        return new ResponseEntity<>(result, httpStatus);
    }



}
