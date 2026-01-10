package org.example.projectkafka.service;

import org.example.projectkafka.model.BusLocation;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TrackingConsumer {


    private final TrackingService trackingService;


    public TrackingConsumer(TrackingService trackingService) {
        this.trackingService = trackingService;
    }


    @KafkaListener(topics = "bus-location-topic", groupId = "tracking-group")
    public void consume(BusLocation location) {
        trackingService.updatePosition(location);
    }
}
