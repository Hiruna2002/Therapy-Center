package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentsDTO {
    private int id;
    private int theropy;
    private int patient;
    private double payment;
    private String status;

    public PaymentsDTO(int therapy, int patient, double payment, String status) {
        this.theropy=therapy;
        this.patient=patient;
        this.payment=payment;
        this.status=status;
    }
}
