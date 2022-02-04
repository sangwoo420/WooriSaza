package project.woori_saza.model.repo;//package project.woori_saza.model.repo;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.stereotype.Repository;
//import project.woori_saza.model.domain.Article;
//import project.woori_saza.model.domain.ChatRoom;
//import project.woori_saza.model.domain.ChatRoomJoin;
//import project.woori_saza.model.domain.UserProfile;
//import project.woori_saza.pubsub.RedisSubscriber;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RequiredArgsConstructor
//@Repository
//public class ChatRoomRepo {
//
//    @Autowired
//    UserProfileRepo userProfileRepo;
//
//    private final EntityManager em;
//
//    // 채팅방(topic)에 발행되는 메시지를 처리할 Listner
//    private final RedisMessageListenerContainer redisMessageListener;
//    // 구독 처리 서비스
//    private final RedisSubscriber redisSubscriber;
//    // Redis
//    private static final String CHAT_ROOMS = "CHAT_ROOM";
//    private final RedisTemplate<String, Object> redisTemplate;
//    private HashOperations<String, String, ChatRoom> opsHashChatRoom;
//    // 채팅방의 대화 메시지를 발행하기 위한 redis topic 정보. 서버별로 채팅방에 매치되는 topic정보를 Map에 넣어 roomId로 찾을수 있도록 한다.
//    private Map<String, ChannelTopic> topics;
//
//    @PostConstruct
//    public void init() {
//        opsHashChatRoom = redisTemplate.opsForHash();
//        topics = new HashMap<>();
//    }
//
//    // 내가 가진 채팅방 가져오기
//    public List<ChatRoom> findAllRoom(UserProfile userProfile) {
//        UserProfile user = userProfileRepo.getById(userProfile.getId());
//        List<ChatRoomJoin> joinList = user.getChatRoomJoinList();
//        List<ChatRoom> chatRoomList = null;
//        for (ChatRoomJoin chatRoomJoin : joinList) {
//            chatRoomList.add(chatRoomJoin.getChatRoom());
//        }
//        return chatRoomList;
//    }
//
//    // 채팅방 이름 가져오기
//    public ChatRoom findRoomByRoomID(ChatRoom chatRoom) {
//        return opsHashChatRoom.get(CHAT_ROOMS, chatRoom.getId());
//    }
//
//    // 채팅방 DB저장
//    public ChatRoom save(ChatRoom chatRoom){
//        em.persist(chatRoom);
//        return chatRoom;
//    }
//
//    public ChatRoom createChatRoom(Article article) {
//        ChatRoom chatRoom = ChatRoom.create(article.getTitle());
//        opsHashChatRoom.put(CHAT_ROOMS, chatRoom.getId(), chatRoom);
//        return chatRoom;
//    }
//
//    public ChatRoom enterChatRoom(ChatRoom chatRoom) {
//        return null;
//    }
//
//    public ChannelTopic getTopic(ChatRoom chatRoom) {
//        return null;
//    }
//}