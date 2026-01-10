package org.example.projectkafka.service;

import org.example.projectkafka.model.PenaltyEvent;
import org.example.projectkafka.repository.PenaltyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaltyService {

    private final PenaltyRepository penaltyRepository;

    public PenaltyService(PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }

    public List<PenaltyEvent> findAll() {
        return penaltyRepository.findAll();
    }

    public void validate(String penaltyId) {
        PenaltyEvent penalty = penaltyRepository
                .findById(penaltyId)
                .orElseThrow(() -> new RuntimeException("Penalty not found"));

        penalty.setValidated(true);
        penaltyRepository.save(penalty);
    }
}
