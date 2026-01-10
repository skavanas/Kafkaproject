package org.example.projectkafka.service;

import org.example.projectkafka.model.NotificationEvent;
import org.example.projectkafka.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void notifyParents(NotificationEvent event) {
        // sauvegarde en base
        notificationRepository.save(event);

        // simulation notification
        System.out.println("NOTIFICATION : " + event.getMessage());
    }

    public List<NotificationEvent> findByParentId(String parentId) {
        return notificationRepository.findByParentId(parentId);
    }
}

