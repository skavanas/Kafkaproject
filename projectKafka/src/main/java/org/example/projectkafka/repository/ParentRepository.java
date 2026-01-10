package org.example.projectkafka.repository;

import org.example.projectkafka.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParentRepository extends JpaRepository<Parent, String> {


    List<Parent> findByBusIdAndStopId(String busId, String stopId);
}
