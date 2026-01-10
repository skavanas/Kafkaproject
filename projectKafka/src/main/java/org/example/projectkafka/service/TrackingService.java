package org.example.projectkafka.service;

import org.example.projectkafka.model.BusLocation;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TrackingService {


    private final Map<String, BusLocation> lastPositions = new ConcurrentHashMap<>();


    public void updatePosition(BusLocation location) {
        lastPositions.put(location.getBusId(), location);
    }


    public BusLocation getLastPosition(String busId) {
        return lastPositions.get(busId);
    }
}
