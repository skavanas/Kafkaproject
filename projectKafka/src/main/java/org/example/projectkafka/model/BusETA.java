package org.example.projectkafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusETA {
    private String busId;
    private String stopId;
    private long minutesRemaining;

    public BusETA(String busId, long eta) {
        this.busId = busId;
        this.minutesRemaining = eta;
    }
}
