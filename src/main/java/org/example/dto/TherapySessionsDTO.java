package org.example.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapySessionsDTO {
    private int id;
    private int therapy;
    private int patient;
    private int program;
    private double cost;
    private String description;


    public TherapySessionsDTO(int therapy, int patient, int program,double cost, String description) {
        this.therapy=therapy;
        this.patient=patient;
        this.program=program;
        this.cost=cost;
        this.description=description;
    }
}
