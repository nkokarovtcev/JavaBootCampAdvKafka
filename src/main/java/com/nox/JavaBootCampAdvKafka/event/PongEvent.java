package com.nox.JavaBootCampAdvKafka.event;

import com.nox.JavaBootCampAdvKafka.entity.OutMessageEvent;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
public class PongEvent extends ApplicationEvent {
    private final UUID eventId;
    private final OutMessageEvent message;

    public PongEvent(Object source, UUID eventId, OutMessageEvent message) {
        super(source);
        this.eventId = eventId;
        this.message = message;
    }
}
