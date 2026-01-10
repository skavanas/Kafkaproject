package org.example.projectkafka.service;

import org.example.projectkafka.model.BusETA;
import org.example.projectkafka.model.BusLocation;
import org.springframework.stereotype.Service;

@Service
public class EtaService {


    public BusETA calculateETA(BusLocation location) {
// simplification pédagogique
        long eta = 10 - (location.getTimestamp().getMinute() % 10);
        return new BusETA(location.getBusId(), eta);
    }
}
