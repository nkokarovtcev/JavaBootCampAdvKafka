package com.nox.JavaBootCampAdvKafka.controller;

import com.nox.JavaBootCampAdvKafka.service.PingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ping-pong")
@RequiredArgsConstructor
public class PingPongController {
    private final PingService pingService;

    @PostMapping("ping/start")
    public void pingStart() {
        pingService.pingStart();
    }

    @PostMapping("ping/stop")
    public void pingStop() {
        pingService.pingStop();
    }

    @PostMapping("ping")
    public void ping() {
        pingService.sendPing();
    }
}
