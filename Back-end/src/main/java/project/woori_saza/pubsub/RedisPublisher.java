package project.woori_saza.pubsub;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import project.woori_saza.model.domain.ChatMessage;

@RequiredArgsConstructor
@Service
public class RedisPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    // 메시지를 작성하면 해당 메시지를 redis topic에 발행하는 기능
    // 이 서비스를 통해 메시지를 발행하면 대기하고 있던 redis 구독 서비스가 메시지를 처리
    public void publish(ChannelTopic topic, ChatMessage message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
