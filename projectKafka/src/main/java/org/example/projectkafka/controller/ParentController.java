package org.example.projectkafka.controller;

import org.example.projectkafka.model.BusETA;
import org.example.projectkafka.model.NotificationEvent;
import org.example.projectkafka.model.Parent;
import org.example.projectkafka.model.PenaltyEvent;
import org.example.projectkafka.service.NotificationService;
import org.example.projectkafka.service.ParentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class ParentController {

    private final ParentService parentService;
    private final NotificationService notificationService;

    public ParentController(ParentService parentService,
                            NotificationService notificationService) {
        this.parentService = parentService;
        this.notificationService = notificationService;
    }

    /**
     * Récupère les informations d'un parent
     */
    @GetMapping("/{parentId}")
    public Parent getParent(@PathVariable String parentId) {
        System.out.println("📞 API: Récupération du parent " + parentId);
        return parentService.getParentById(parentId);
    }

    /**
     * Récupère l'ETA du bus pour un parent
     */
    @GetMapping("/{parentId}/eta")
    public BusETA getBusETA(@PathVariable String parentId) {
        System.out.println("📞 API: Récupération de l'ETA pour parent " + parentId);
        return parentService.getBusETA(parentId);
    }

    /**
     * Récupère les notifications d'un parent
     * Délègue à NotificationService qui gère le stockage en mémoire
     */
    @GetMapping("/{parentId}/notifications")
    public List<NotificationEvent> getNotifications(@PathVariable String parentId) {
        System.out.println("📞 API: Récupération des notifications pour parent " + parentId);
        return notificationService.findByParentId(parentId);
    }

    /**
     * Récupère les pénalités d'un parent
     */
    @GetMapping("/{parentId}/penalties")
    public List<PenaltyEvent> getPenalties(@PathVariable String parentId) {
        System.out.println("📞 API: Récupération des pénalités pour parent " + parentId);
        return parentService.getPenalties(parentId);
    }

    /**
     * Supprime les notifications d'un parent
     */
    @DeleteMapping("/{parentId}/notifications")
    public String clearNotifications(@PathVariable String parentId) {
        System.out.println("📞 API: Suppression des notifications pour parent " + parentId);
        notificationService.clearNotifications(parentId);
        return "Notifications supprimées pour " + parentId;
    }
}