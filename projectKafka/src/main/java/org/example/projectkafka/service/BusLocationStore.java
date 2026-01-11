package org.example.projectkafka.service;

import org.example.projectkafka.model.BusLocation;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BusLocationStore {

    private final Map<String, BusLocation> busLocations = new ConcurrentHashMap<>();

    public void save(BusLocation location) {
        busLocations.put(location.getBusId(), location);
    }

    public Collection<BusLocation> findAll() {
        return busLocations.values();
    }

    public BusLocation findByBusId(String busId) {
        return busLocations.get(busId);
    }
}
