package org.example.projectkafka.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parent {


    @Id
    private String parentId;


    private String name;


    private String busId; // bus utilisé
    private String stopId; // arrêt du parent
}
