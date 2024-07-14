package com.nox.JavaBootCampAdvKafka.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.nox.JavaBootCampAdvKafka.kafka.Topics.PONG_OUT_TOPIC;

@Component
@RequiredArgsConstructor
public class PongProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CompletableFuture<SendResult<String, String>> sendPong(UUID chainId) {
        return kafkaTemplate.send(PONG_OUT_TOPIC, chainId.toString());
    }
}
