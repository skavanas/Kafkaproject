package org.example.projectkafka.controller;

import org.example.projectkafka.model.NotificationEvent;
import org.example.projectkafka.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/parent/{parentId}")
    public List<NotificationEvent> getByParent(@PathVariable String parentId) {
        System.out.println("📞 API: Récupération notifications pour " + parentId);
        return notificationService.findByParentId(parentId);
    }

    @DeleteMapping("/parent/{parentId}")
    public String clearNotifications(@PathVariable String parentId) {
        notificationService.clearNotifications(parentId);
        return "Notifications cleared for " + parentId;
    }

    @GetMapping("/counts")
    public Map<String, Integer> getNotificationCounts() {
        return notificationService.getAllNotificationCounts();
    }
}