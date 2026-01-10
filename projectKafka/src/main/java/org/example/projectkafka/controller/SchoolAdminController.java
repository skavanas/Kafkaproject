package org.example.projectkafka.controller;

import org.example.projectkafka.model.PenaltyEvent;
import org.example.projectkafka.service.PenaltyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class SchoolAdminController {

    private final PenaltyService penaltyService;

    public SchoolAdminController(PenaltyService penaltyService) {
        this.penaltyService = penaltyService;
    }

    @GetMapping("/penalties")
    public List<PenaltyEvent> getAllPenalties() {
        return penaltyService.findAll();
    }

    @PostMapping("/penalties/{penaltyId}/validate")
    public void validatePenalty(@PathVariable String penaltyId) {
        penaltyService.validate(penaltyId);
    }
}
