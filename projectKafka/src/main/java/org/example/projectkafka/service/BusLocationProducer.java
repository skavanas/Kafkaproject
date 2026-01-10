package org.example.projectkafka.service;

import org.example.projectkafka.model.BusLocation;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BusLocationProducer {


    private static final String TOPIC = "bus-location-topic";


    private final KafkaTemplate<String, BusLocation> kafkaTemplate;


    public BusLocationProducer(KafkaTemplate<String, BusLocation> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendLocation(BusLocation location) {
        kafkaTemplate.send(TOPIC, location.getBusId(), location);
    }
}
