package org.example.projectkafka.service;

import org.example.projectkafka.model.BusLocation;
import org.example.projectkafka.service.BusLocationStore;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BusLocationConsumer {

    private final BusLocationStore store;

    public BusLocationConsumer(BusLocationStore store) {
        this.store = store;
    }

    @KafkaListener(topics = "bus-location-topic", groupId = "tracking-group")
    public void consume(BusLocation location) {
        System.out.println("Position reçue : " + location);
        store.save(location);
    }
}
