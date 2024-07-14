package com.nox.JavaBootCampAdvKafka.service;

import com.nox.JavaBootCampAdvKafka.dto.PingMessageDto;
import com.nox.JavaBootCampAdvKafka.dto.PongMessageDto;
import com.nox.JavaBootCampAdvKafka.entity.OutMessageEvent;
import com.nox.JavaBootCampAdvKafka.event.PongEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.nox.JavaBootCampAdvKafka.kafka.Topics.PING_OUT_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class PongService {
    private final OutMessageEventService outMessageEventService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @KafkaListener(topics = PING_OUT_TOPIC,  groupId = "${spring.kafka.consumer.group-id}")
    @SneakyThrows
    public void consumePing(String message) {
        log.info("ping-message-received: %s".formatted(message));
        PingMessageDto pingMessage = new PingMessageDto();
        pingMessage.setChainId(UUID.fromString(message));
        OutMessageEvent outMessageEvent = preparePong(pingMessage);
        PongEvent pongEvent = new PongEvent(this, outMessageEvent.getId(), outMessageEvent);
        applicationEventPublisher.publishEvent(pongEvent);
    }

    @Transactional
    public OutMessageEvent preparePong(PingMessageDto pingMessage) {
        PongMessageDto pongMessage = new PongMessageDto();
        pongMessage.setChainId(pingMessage.getChainId());
        return outMessageEventService.savePongMessage(pongMessage);
    }
}
