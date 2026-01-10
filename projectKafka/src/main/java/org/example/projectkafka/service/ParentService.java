package org.example.projectkafka.service;

import org.example.projectkafka.model.BusETA;
import org.example.projectkafka.model.NotificationEvent;
import org.example.projectkafka.model.Parent;
import org.example.projectkafka.model.PenaltyEvent;
import org.example.projectkafka.repository.NotificationRepository;
import org.example.projectkafka.repository.ParentRepository;
import org.example.projectkafka.repository.PenaltyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ParentService {

    private final ParentRepository parentRepository;
    private final PenaltyRepository penaltyRepository;

    private final NotificationRepository notificationRepository;

    public ParentService(ParentRepository parentRepository,
                         PenaltyRepository penaltyRepository,
                         NotificationRepository notificationRepository) {
        this.parentRepository = parentRepository;
        this.penaltyRepository = penaltyRepository;
        this.notificationRepository = notificationRepository;
    }

    public List<Parent> findParentsByBusAndStop(String busId, String stopId) {
        return parentRepository.findByBusIdAndStopId(busId, stopId);
    }

    public List<PenaltyEvent> getPenalties(String parentId) {
        return penaltyRepository.findByParentId(parentId);
    }

    public BusETA getBusETA(String parentId) {
        // Récupérer le parent pour obtenir son busId et stopId
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent non trouvé avec l'id: " + parentId));

        // Créer et retourner un objet BusETA avec les informations du parent
        // Note: Vous devrez ajuster cette logique selon votre implémentation réelle
        // Par exemple, vous pourriez avoir besoin d'un BusRepository pour obtenir l'ETA réel
        BusETA busETA = new BusETA();
        busETA.setBusId(parent.getBusId());
        busETA.setStopId(parent.getStopId());
        // busETA.setEstimatedArrivalTime(...); // À définir selon votre logique métier

        return busETA;
    }

    public List<NotificationEvent> getNotifications(String parentId) {
        return notificationRepository.findByParentId(parentId);
    }
}
