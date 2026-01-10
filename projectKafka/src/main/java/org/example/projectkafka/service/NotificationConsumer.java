package org.example.projectkafka.service;

import org.example.projectkafka.model.BusETA;
import org.example.projectkafka.model.NotificationEvent;

import org.example.projectkafka.model.Parent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationConsumer {


    private final ParentService parentService;
    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;


    public NotificationConsumer(ParentService parentService,
                                KafkaTemplate<String, NotificationEvent> kafkaTemplate) {
        this.parentService = parentService;
        this.kafkaTemplate = kafkaTemplate;
    }


    @KafkaListener(topics = "bus-eta-topic", groupId = "notification-group")
    public void consume(BusETA eta) {


        if (eta.getMinutesRemaining() <= 5) {


            List<Parent> parents = parentService.findParentsByBusAndStop(
                    eta.getBusId(), eta.getStopId()
            );


            parents.forEach(parent -> {
                NotificationEvent event = new NotificationEvent(
                        parent.getParentId(),
                        "Le bus arrive dans 5 minutes"
                );
                kafkaTemplate.send("notification-topic", parent.getParentId(), event);
            });
        }
    }
}