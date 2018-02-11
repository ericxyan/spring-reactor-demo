package com.spring.reactor.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class NotificationConsumer implements Consumer<Event<NotificationData>> {

    @Autowired
    private NotificationService notificationService;

    @Override
    public void accept(Event<NotificationData> notificationDataEvent) {
        NotificationData data = notificationDataEvent.getData();

        try {
            notificationService.init(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
