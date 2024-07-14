package com.nox.JavaBootCampAdvKafka.entity;

import com.nox.JavaBootCampAdvKafka.dto.MessageType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class OutMessageEvent {
    @Id
    private UUID id;
    private UUID chainId;
    @Enumerated(EnumType.STRING)
    private MessageType messageType;
    private LocalDateTime sentAt;
    private LocalDateTime createdAt;
    private String status;
}
