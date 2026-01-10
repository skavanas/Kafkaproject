package org.example.projectkafka.repository;

import org.example.projectkafka.model.NotificationEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository
        extends JpaRepository<NotificationEvent, String> {

    List<NotificationEvent> findByParentId(String parentId);
}
