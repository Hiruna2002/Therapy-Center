package org.example.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientsTm {
    private String name;
    private String duration;
    private double cost;
    private String description;
}
