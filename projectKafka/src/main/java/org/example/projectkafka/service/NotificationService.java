package org.example.projectkafka.service;

import org.example.projectkafka.model.NotificationEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NotificationService {

    // ✅ Stockage EN MÉMOIRE (pas en base de données)
    private final Map<String, List<NotificationEvent>> notificationStore = new ConcurrentHashMap<>();

    @KafkaListener(topics = "notification-topic", groupId = "notification-storage-group")
    public void consumeNotification(NotificationEvent event) {
        System.out.println("🔔 Notification reçue pour parent " + event.getParentId() + ": " + event.getMessage());

        notificationStore
                .computeIfAbsent(event.getParentId(), k -> new ArrayList<>())
                .add(event);

        System.out.println("✅ Total notifications pour " + event.getParentId() + ": " +
                notificationStore.get(event.getParentId()).size());
    }

    public List<NotificationEvent> findByParentId(String parentId) {
        List<NotificationEvent> notifications = notificationStore.getOrDefault(parentId, new ArrayList<>());
        System.out.println("📋 Récupération de " + notifications.size() + " notifications pour " + parentId);
        return notifications;
    }

    public void clearNotifications(String parentId) {
        notificationStore.remove(parentId);
        System.out.println("🗑️ Notifications supprimées pour " + parentId);
    }

    public Map<String, Integer> getAllNotificationCounts() {
        Map<String, Integer> counts = new HashMap<>();
        notificationStore.forEach((key, value) -> counts.put(key, value.size()));
        return counts;
    }
}