package com.nox.JavaBootCampAdvKafka.service;

import com.nox.JavaBootCampAdvKafka.dto.MessageType;
import com.nox.JavaBootCampAdvKafka.dto.PingMessageStatus;
import com.nox.JavaBootCampAdvKafka.event.PingEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutEventWatchdog {
    private final OutMessageEventService outMessageEventService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Async("threadPoolTaskExecutor")
    @EventListener(ApplicationReadyEvent.class)
    public void dispatchUnsentMessages() {
        outMessageEventService.findAllByStatus(MessageType.PING, PingMessageStatus.NEW.name())
                .forEach(outMessageEvent -> {
                    PingEvent pingEvent = new PingEvent(this, outMessageEvent.getId(), outMessageEvent);
                    applicationEventPublisher.publishEvent(pingEvent);
        });
    }
}
