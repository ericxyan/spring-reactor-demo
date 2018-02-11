package com.spring.reactor.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.bus.Event;
import reactor.bus.EventBus;

@Controller
public class NotificationController {
    public static final Logger log = LoggerFactory.getLogger(NotificationController.class);
    @Autowired
    private EventBus eventBus;

    @GetMapping("/notify/{param}")
    public void notify(@PathVariable Integer param) {
        for (int i = 0; i < param; i++) {
            NotificationData data = new NotificationData();

            data.setId(i);

            eventBus.notify(Topic.NOTIFICATION, Event.wrap(data));

            log.info("Notification {}: task submitted.", i);
        }
    }
}
