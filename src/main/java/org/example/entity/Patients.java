package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String duration;
    private double cost;
    private String description;
    @OneToMany(mappedBy = "patients")
    private List<TherapySessions> therapySessions;
    @OneToMany(mappedBy = "patients")
    private List<Payments> payments;

    public Patients(String name, String duration, double cost, String description) {
        this.name=name;
        this.duration=duration;
        this.cost=cost;
        this.description=description;
    }

    public Patients(int id, String name, String duration, double cost, String description) {
        this.id=id;
        this.name=name;
        this.duration=duration;
        this.cost=cost;
        this.description=description;
    }
}
