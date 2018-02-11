package com.spring.reactor.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.bus.Event;
import reactor.fn.Consumer;

import java.util.concurrent.CountDownLatch;

@Service
public class Receiver implements Consumer<Event<Integer>> {
    public static final Logger log = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    CountDownLatch latch;

    RestTemplate template = new RestTemplate();

    @Override
    public void accept(Event<Integer> event) {
        QuoteResource quoteResource = template.getForObject(
                "http://gturnquist-quoters.cfapps.io/api/random",
                QuoteResource.class);

        log.info("Quote {}: {}", event.getData(), quoteResource.getValue().getQuote());

        latch.countDown();
    }
}
