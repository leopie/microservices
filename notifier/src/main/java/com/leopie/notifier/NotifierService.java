package com.leopie.notifier;

import com.leopie.clients.notifier.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotifierService(NotifierRepository notifierRepository) {

    public void send(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .messageSent(notificationRequest.message())
                .sentAt(LocalDateTime.now())
                .build();
        notifierRepository.save(notification);
        //TODO send notification
    }
}
