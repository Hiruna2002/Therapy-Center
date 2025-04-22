package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PatientsDTO {
    private int id;
    private String name;
    private String duration;
    private double cost;
    private String description;

    public PatientsDTO(int id, String name, String duration, double cost, String description) {
            this.id=id;
            this.name=name;
            this.duration=duration;
            this.cost=cost;
            this.description=description;
    }

    public PatientsDTO(String name, String duration, double cost, String description) {
        this.name=name;
        this.duration=duration;
        this.cost=cost;
        this.description=description;
    }
}
