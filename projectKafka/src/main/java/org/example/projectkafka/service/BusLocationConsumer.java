package org.example.projectkafka.service;

import org.example.projectkafka.model.BusLocation;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BusLocationConsumer {

    private final BusLocationStore store;

    public BusLocationConsumer(BusLocationStore store) {
        this.store = store;
    }

    @KafkaListener(topics = "bus-location-topic", groupId = "bus-tracking-group")
    public void consume(BusLocation location) {
        System.out.println("📍 Received: " + location);

        // ⚠️ IMPORTANT : Stocke la position dans le store
        store.update(location);
    }
}