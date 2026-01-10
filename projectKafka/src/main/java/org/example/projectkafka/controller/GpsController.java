package org.example.projectkafka.controller;

import org.example.projectkafka.model.BusLocation;
import org.example.projectkafka.service.BusLocationProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/gps")
public class GpsController {


    private final BusLocationProducer producer;


    public GpsController(BusLocationProducer producer) {
        this.producer = producer;
    }


    @PostMapping("/send")
    public void send() {
        producer.sendLocation(
                new BusLocation("BUS_1", 33.5, -7.6, LocalDateTime.now())
        );
    }
}
