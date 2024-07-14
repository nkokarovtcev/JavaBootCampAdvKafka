package com.nox.JavaBootCampAdvKafka.event;

import com.nox.JavaBootCampAdvKafka.dto.PingMessageStatus;
import com.nox.JavaBootCampAdvKafka.kafka.PingProducer;
import com.nox.JavaBootCampAdvKafka.service.OutMessageEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PingEventListener implements ApplicationListener<PingEvent> {
    private final PingProducer pingProducer;
    private final OutMessageEventService outMessageEventService;

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(PingEvent event) {
        outMessageEventService.updatePingStatus(event.getMessage().getChainId(), PingMessageStatus.SENT.name());
        pingProducer.sendPing(event.getMessage().getChainId());
    }
}