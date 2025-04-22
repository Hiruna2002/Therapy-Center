package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapistsDTO {
    private int id;
    private String name;
    private String status;
    private String program;

    public TherapistsDTO(String name, String status, String program) {
        this.name=name;
        this.status=status;
        this.program=program;
    }
}
