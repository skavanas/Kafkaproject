package org.example.projectkafka.controller;

import org.example.projectkafka.model.BusETA;
import org.example.projectkafka.model.NotificationEvent;
import org.example.projectkafka.model.PenaltyEvent;
import org.example.projectkafka.service.ParentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping("/{parentId}/eta")
    public BusETA getBusETA(@PathVariable String parentId) {
        return parentService.getBusETA(parentId);
    }

    @GetMapping("/{parentId}/notifications")
    public List<NotificationEvent> getNotifications(@PathVariable String parentId) {
        return parentService.getNotifications(parentId);
    }

    @GetMapping("/{parentId}/penalties")
    public List<PenaltyEvent> getPenalties(@PathVariable String parentId) {
        return parentService.getPenalties(parentId);
    }
}
