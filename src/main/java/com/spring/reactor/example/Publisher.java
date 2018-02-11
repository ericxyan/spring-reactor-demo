package com.spring.reactor.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class Publisher {
    public static final Logger log = LoggerFactory.getLogger(Publisher.class);
    @Autowired
    EventBus eventBus;

    @Autowired
    CountDownLatch latch;

    public void publishQuotes(int numberOfQuotes) throws InterruptedException {
        long start = System.currentTimeMillis();

        AtomicInteger counter = new AtomicInteger(1);

        for (int i = 0; i < numberOfQuotes; i++) {
            eventBus.notify(Topic.QUOTE, Event.wrap(counter.getAndIncrement()));
        }

        latch.await();

        long end = System.currentTimeMillis();

        long elapsed = end - start;

        log.info("Elapsed time: {}ms", elapsed);
        log.info("Average time: {}", elapsed / numberOfQuotes);
    }
}
