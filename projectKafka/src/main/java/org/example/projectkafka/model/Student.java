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
public class Student {


    @Id
    private String studentId;


    private String name;


    private String parentId;
    private String busId;
}
