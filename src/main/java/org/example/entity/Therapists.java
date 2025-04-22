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
public class Therapists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String status;
    private String program;
    @OneToMany(mappedBy = "therapists")
    private List<TherapySessions> therapySessions;
    @OneToMany(mappedBy = "therapists")
    private List<Payments> payments;

    public Therapists(String name, String status, String program) {
        this.name=name;
        this.status=status;
        this.program=program;
    }

    public Therapists(int id, String name, String status, String program) {
        this.id=id;
        this.name=name;
        this.status=status;
        this.program=program;
    }
}
