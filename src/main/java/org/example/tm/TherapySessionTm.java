package org.example.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapySessionTm {
    private int therapy;
    private int patient;
    private int program;
    private double cost;
    private String description;
}
