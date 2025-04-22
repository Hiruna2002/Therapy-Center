package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapyProgramsDTO {
    private int id;
    private String name;
    private String duration;
    private double cost;
    private String description;

    public TherapyProgramsDTO(String name, String duration, double cost, String description) {
        this.name=name;
        this.duration=duration;
        this.cost=cost;
        this.description=description;
    }
}
