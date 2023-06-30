package com.leopie.clients.notifier;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("notifier")
public interface NotifierClient {
    @PostMapping(path = "api/v1/notifications/send")
    void sendNotification(NotificationRequest customer);
}
