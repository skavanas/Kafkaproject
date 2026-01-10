package org.example.projectkafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusLocation {
    private String busId;
    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;
}