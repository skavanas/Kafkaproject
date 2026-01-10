package org.example.projectkafka.service;

import org.example.projectkafka.model.NotificationEvent;
import org.example.projectkafka.model.PenaltyEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PenaltyConsumer {


    private final SchoolManagementService schoolService;


    public PenaltyConsumer(SchoolManagementService schoolService) {
        this.schoolService = schoolService;
    }


    @KafkaListener(topics = "notification-topic", groupId = "penalty-group")
    public void consume(NotificationEvent event) {


        PenaltyEvent penalty = new PenaltyEvent(
                event.getParentId(),
                "Parent absent lors de l'arrivée du bus"
        );


        schoolService.applyPenalty(penalty);
    }
}