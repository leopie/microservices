package com.leopie.notifier;

import com.leopie.clients.notifier.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notifications")
@Slf4j
public record NotifierController(NotifierService notifierService) {
    @PostMapping(path = "send")
    public void send(@RequestBody NotificationRequest notificationRequest) {
        log.info("Sending notification to user with id %d".formatted(notificationRequest.toCustomerId()));
        notifierService.send(notificationRequest);
    }
}
