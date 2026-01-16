package org.example.projectkafka.service;

import org.example.projectkafka.model.BusLocation;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BusLocationStore {

    private final Map<String, BusLocation> locations = new ConcurrentHashMap<>();

    // Ajoute/met à jour une position
    public void update(BusLocation location) {
        locations.put(location.getBusId(), location);
    }

    // Récupère tous les bus
    public Collection<BusLocation> findAll() {
        return locations.values();
    }

    // Récupère un bus par ID
    public BusLocation findByBusId(String busId) {
        return locations.get(busId);
    }
}