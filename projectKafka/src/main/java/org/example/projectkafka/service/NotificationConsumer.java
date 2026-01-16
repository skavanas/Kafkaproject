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
        System.out.println("⏱️ NotificationConsumer reçoit ETA: " + eta);

        if (eta.getMinutesRemaining() <= 5) {
            System.out.println("🚨 Bus proche! ETA = " + eta.getMinutesRemaining() + " min");

            List<Parent> parents = parentService.findParentsByBusAndStop(
                    eta.getBusId(), eta.getStopId()
            );

            System.out.println("👨‍👩‍👧 Parents trouvés: " + parents.size());

            parents.forEach(parent -> {
                NotificationEvent event = new NotificationEvent(
                        parent.getParentId(),
                        "🚌 Le bus " + eta.getBusId() + " arrive dans " +
                                eta.getMinutesRemaining() + " minute(s)!"
                );

                System.out.println("📤 Envoi notification vers Kafka pour: " + parent.getParentId());
                kafkaTemplate.send("notification-topic", parent.getParentId(), event);
            });
        } else {
            System.out.println("⏳ Bus encore loin: " + eta.getMinutesRemaining() + " min (pas de notification)");
        }
    }
}