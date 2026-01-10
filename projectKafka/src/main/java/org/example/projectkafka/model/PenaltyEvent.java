package org.example.projectkafka.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PenaltyEvent {
    @Id
    private String parentId;
    private String reason;

    public void setValidated(boolean b) {
    }
}
