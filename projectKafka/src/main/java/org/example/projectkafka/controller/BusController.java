package org.example.projectkafka.controller;

import org.example.projectkafka.model.BusLocation;
import org.example.projectkafka.service.BusLocationProducer;
import org.example.projectkafka.service.BusLocationStore;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    private final BusLocationProducer producer;
    private final BusLocationStore store;

    public BusController(BusLocationProducer producer, BusLocationStore store) {
        this.producer = producer;
        this.store = store;
    }

    //  POSITION GPS (simulateur GPS)
    @PostMapping("/location")
    public String sendLocation(@RequestBody BusLocation location) {
        location.setTimestamp(LocalDateTime.now());
        producer.sendLocation(location);
        return "Location sent to Kafka";
    }

    // 2TOUS LES BUS
    @GetMapping
    public Collection<BusLocation> getAllBuses() {
        return store.findAll();
    }

    //  BUS PAR ID
    @GetMapping("/{busId}")
    public BusLocation getBus(@PathVariable String busId) {
        return store.findByBusId(busId);
    }
}
