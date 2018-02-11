package com.spring.reactor.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    void init(NotificationData notificationData) throws InterruptedException {
        log.info("Notification service started for ID: {}", notificationData.getId());

        Thread.sleep(5000);

        log.info("Notification service ended for ID: {}", notificationData.getId());
    }
}
