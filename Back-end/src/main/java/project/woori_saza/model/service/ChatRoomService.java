package project.woori_saza.model.service;

import org.springframework.data.redis.listener.ChannelTopic;
import project.woori_saza.model.domain.*;

import java.util.List;

public interface ChatRoomService {

    void init();
    // 사용자의 모든 채팅방 조회
    List<String> findAllRoom(String profileId);
    // 특정 채팅방 조회 -> 어떤 채팅방인지 찾아주기만함 (이름설정)
    ChatRoom findRoomByRoomID(String roomId);
    // 채팅방 생성 -> article에서 생성
    ChatRoom createChatRoom(Article article);
    // 채팅-유저 조인 생성 + 입장
    ChatRoomJoin createChatRoomJoin(ChatRoom chatRoom, UserProfile userProfile);
    ChannelTopic getTopic(String roomId);

    // 채팅방 입장
//    ChatRoom enterChatRoom(ChatRoom chatRoom);

}