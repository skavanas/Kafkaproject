package org.example.projectkafka.service;

import org.example.projectkafka.model.PenaltyEvent;
import org.springframework.stereotype.Service;

@Service
public class SchoolManagementService {


    public void applyPenalty(PenaltyEvent event) {
        System.out.println("PÉNALITÉ appliquée : " + event.getReason());
    }
}
