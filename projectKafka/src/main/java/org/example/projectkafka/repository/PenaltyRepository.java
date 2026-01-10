package org.example.projectkafka.repository;

import org.example.projectkafka.model.PenaltyEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenaltyRepository
        extends JpaRepository<PenaltyEvent, String> {

    List<PenaltyEvent> findByParentId(String parentId);
}

