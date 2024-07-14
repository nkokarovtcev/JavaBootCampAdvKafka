package com.nox.JavaBootCampAdvKafka.repository;

import com.nox.JavaBootCampAdvKafka.dto.MessageType;
import com.nox.JavaBootCampAdvKafka.entity.OutMessageEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OutMessageEventRepository extends JpaRepository<OutMessageEvent, UUID> {

    List<OutMessageEvent> findAllByMessageTypeAndStatus(MessageType messageType, String status);

    Optional<OutMessageEvent> findByMessageTypeAndChainId(MessageType messageType, UUID chainId);
}