package org.example.projectkafka.service;

import org.example.projectkafka.model.NotificationEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NotificationService {

    // Stocke les notifications par parentId
    private final Map<String, List<NotificationEvent>> notificationStore = new ConcurrentHashMap<>();

    @KafkaListener(topics = "notification-topic", groupId = "notification-storage-group")
    public void consumeNotification(NotificationEvent event) {
        System.out.println("🔔 Notification reçue: " + event);

        notificationStore
                .computeIfAbsent(event.getParentId(), k -> new ArrayList<>())
                .add(event);
    }

    public List<NotificationEvent> findByParentId(String parentId) {
        return notificationStore.getOrDefault(parentId, new ArrayList<>());
    }

    public void clearNotifications(String parentId) {
        notificationStore.remove(parentId);
    }
}