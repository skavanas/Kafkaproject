package org.example.projectkafka.controller;

import org.example.projectkafka.model.NotificationEvent;
import org.example.projectkafka.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/parent/{parentId}")
    public List<NotificationEvent> getByParent(@PathVariable String parentId) {
        return notificationService.findByParentId(parentId);
    }
}


