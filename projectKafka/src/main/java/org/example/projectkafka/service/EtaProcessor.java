package org.example.projectkafka.service;

import org.example.projectkafka.model.BusETA;
import org.example.projectkafka.model.BusLocation;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EtaProcessor {


    private static final String ETA_TOPIC = "bus-eta-topic";


    private final EtaService etaService;
    private final KafkaTemplate<String, BusETA> kafkaTemplate;


    public EtaProcessor(EtaService etaService, KafkaTemplate<String, BusETA> kafkaTemplate) {
        this.etaService = etaService;
        this.kafkaTemplate = kafkaTemplate;
    }


    @KafkaListener(topics = "bus-location-topic", groupId = "eta-group")
    public void process(BusLocation location) {
        BusETA eta = etaService.calculateETA(location);
        kafkaTemplate.send(ETA_TOPIC, eta.getBusId(), eta);
    }
}
