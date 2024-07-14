package com.nox.JavaBootCampAdvKafka.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class PingMessageDto {
    @NotBlank
    private UUID chainId;
}
