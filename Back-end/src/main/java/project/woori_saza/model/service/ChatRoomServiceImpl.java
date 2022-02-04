package project.woori_saza.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import project.woori_saza.model.domain.*;
import project.woori_saza.model.repo.UserProfileRepo;
import project.woori_saza.pubsub.RedisSubscriber;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static project.woori_saza.model.domain.QArticle.article;

@RequiredArgsConstructor
@Service
public class ChatRoomServiceImpl implements ChatRoomService{

    @Autowired
    UserProfileRepo userProfileRepo;

    // 채팅방(topic)에 발행되는 메시지를 처리할 Listner
    private final RedisMessageListenerContainer redisMessageListener;
    // 구독 처리 서비스
    private final RedisSubscriber redisSubscriber;
    // Redis
    private static final String CHAT_ROOMS = "CHAT_ROOM";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoom> opsHashChatRoom;
    // 채팅방의 대화 메시지를 발행하기 위한 redis topic 정보. 서버별로 채팅방에 매치되는 topic정보를 Map에 넣어 roomId로 찾을수 있도록 한다.
    private Map<String, ChannelTopic> topics;

    @PostConstruct
    @Override
    public void init() {
        opsHashChatRoom = redisTemplate.opsForHash();
        topics = new HashMap<>();
    }

    // 내가 가진 채팅방 가져오기
    @Override
    public List<String> findAllRoom(String profileId) {
        UserProfile user = userProfileRepo.getById(profileId);
        List<ChatRoomJoin> joinList = user.getChatRoomJoinList();
        List<String> chatRoomList = new ArrayList<>();
        for (ChatRoomJoin chatRoomJoin : joinList) {
//            System.out.println(chatRoomJoin.getChatRoom().getName());
            // 객체로 넣으며 안됨 -> 무한참조
            chatRoomList.add(chatRoomJoin.getChatRoom().getId());
        }
        return chatRoomList;
    }

    // 채팅방 이름 가져오기
    @Override
    public ChatRoom findRoomByRoomID(String roomId) {
        return opsHashChatRoom.get(CHAT_ROOMS, roomId);
    }

    //  채팅방 생성 (article에서)
    /**
     * 채팅방 생성 : 서버간 채팅방 공유를 위해 redis hash에 저장한다.
     */
    @Override
    public ChatRoom createChatRoom(Article article) {
        ChatRoom chatRoom = ChatRoom.create(article.getTitle());
        chatRoom.setArticle(article);
        opsHashChatRoom.put(CHAT_ROOMS, chatRoom.getId(), chatRoom);
        return chatRoom;
    }

    // 채팅-유저 조인 생성 (article에서) + 채팅방 입장(enterChatRoom)
    /**
     * 채팅방 입장 : redis에 topic을 만들고 pub/sub 통신을 하기 위해 리스너를 설정한다.
     */
    @Override
    public ChatRoomJoin createChatRoomJoin(ChatRoom chatRoom, UserProfile user){
        ChatRoomJoin chatRoomJoin = ChatRoomJoin.create(chatRoom,user);

//        ChatMessage chatMessage = new ChatMessage();
//        chatMessage.setChatRoom(chatRoom);
//        chatMessage.setUserProfile(user);
//        chatMessage.setContent(user.getNickname()+"님이 입장하셨습니다.");

        ChannelTopic topic = topics.get(chatRoom.getId());
        if (topic == null)
            topic = new ChannelTopic(chatRoom.getId());
        redisMessageListener.addMessageListener(redisSubscriber, topic);
        topics.put(chatRoom.getId(), topic);

        return chatRoomJoin;
    }

    @Override
    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }

}