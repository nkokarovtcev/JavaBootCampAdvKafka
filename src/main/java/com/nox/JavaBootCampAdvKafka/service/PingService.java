package com.nox.JavaBootCampAdvKafka.service;

import com.nox.JavaBootCampAdvKafka.dto.PingMessageDto;
import com.nox.JavaBootCampAdvKafka.dto.PingMessageStatus;
import com.nox.JavaBootCampAdvKafka.dto.PongMessageDto;
import com.nox.JavaBootCampAdvKafka.entity.OutMessageEvent;
import com.nox.JavaBootCampAdvKafka.event.PingEvent;
import com.nox.JavaBootCampAdvKafka.kafka.Topics;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PingService {
    private final OutMessageEventService outMessageEventService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private boolean doAutoPing = false;

    @Transactional
    public OutMessageEvent preparePing() {
        PingMessageDto pingMessage = new PingMessageDto();
        pingMessage.setChainId(UUID.randomUUID());
        return outMessageEventService.savePingMessage(pingMessage);
    }

    public void sendPing() {
        OutMessageEvent outMessageEvent = preparePing();
        PingEvent pingEvent = new PingEvent(this, outMessageEvent.getId(), outMessageEvent);
        applicationEventPublisher.publishEvent(pingEvent);
    }

    @Scheduled(fixedRate = 2000)
    public void autoPing() {
        if (doAutoPing) {
            sendPing();
        }
    }

    public void pingStart() {
        doAutoPing = true;
    }

    public void pingStop() {
        doAutoPing = false;
    }

    @KafkaListener(topics = Topics.PONG_OUT_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    @SneakyThrows
    public void consumePong(String message) {
        log.info("pong-message-received: %s".formatted(message));
        PongMessageDto pongMessageDto = new PongMessageDto();
        pongMessageDto.setChainId(UUID.fromString(message));
        outMessageEventService.updatePingStatus(pongMessageDto.getChainId(), PingMessageStatus.PONG_RECEIVED.name());
    }
}
