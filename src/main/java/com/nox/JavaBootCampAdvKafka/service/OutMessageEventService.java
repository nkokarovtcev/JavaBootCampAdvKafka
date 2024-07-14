package com.nox.JavaBootCampAdvKafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nox.JavaBootCampAdvKafka.dto.*;
import com.nox.JavaBootCampAdvKafka.entity.OutMessageEvent;
import com.nox.JavaBootCampAdvKafka.repository.OutMessageEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutMessageEventService {
    private final OutMessageEventRepository outMessageEventRepository;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public OutMessageEvent savePingMessage(PingMessageDto pingMessageDto) {
        OutMessageEvent pingMessage = new OutMessageEvent();
        pingMessage.setId(UUID.randomUUID());
        pingMessage.setChainId(pingMessageDto.getChainId());
        pingMessage.setMessageType(MessageType.PING);
        pingMessage.setStatus(PingMessageStatus.NEW.name());
        pingMessage.setCreatedAt(LocalDateTime.now());

        outMessageEventRepository.save(pingMessage);

        return pingMessage;
    }

    public List<OutMessageEvent> findAllByStatus(MessageType messageType, String status) {
        return outMessageEventRepository.findAllByMessageTypeAndStatus(messageType, status);
    }

    @SneakyThrows
    public OutMessageEvent savePongMessage(PongMessageDto pongMessageDto) {
        OutMessageEvent pongMessage = new OutMessageEvent();
        pongMessage.setId(UUID.randomUUID());
        pongMessage.setChainId(pongMessageDto.getChainId());
        pongMessage.setMessageType(MessageType.PONG);
        pongMessage.setStatus(PongMessageStatus.NEW.name());
        pongMessage.setCreatedAt(LocalDateTime.now());

        outMessageEventRepository.save(pongMessage);

        return pongMessage;
    }

    public void updatePingStatus(UUID chainId, String name) {
        OutMessageEvent outMessageEvent = outMessageEventRepository.findByMessageTypeAndChainId(MessageType.PING, chainId).orElseThrow();
        outMessageEvent.setStatus(name);
        outMessageEventRepository.save(outMessageEvent);
    }

    public void updatePongStatus(UUID chainId, String name) {
        OutMessageEvent outMessageEvent = outMessageEventRepository.findByMessageTypeAndChainId(MessageType.PONG, chainId).orElseThrow();
        outMessageEvent.setStatus(name);
        outMessageEventRepository.save(outMessageEvent);
    }
}

