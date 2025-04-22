package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double payment;
    private String status;
    @ManyToOne(cascade = CascadeType.ALL)
    private Patients patients;
    @ManyToOne(cascade = CascadeType.ALL)
    private Therapists therapists;

    public Payments(double payment, String status, Patients patients, Therapists therapists) {
        this.payment=payment;
        this.status=status;
        this.patients=patients;
        this.therapists=therapists;
    }
}
