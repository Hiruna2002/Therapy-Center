package org.example.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapistTm {
    private int id;
    private String name;
    private String status;
    private String program;

    public TherapistTm(String name, String status, String program) {
        this.name=name;
        this.status=status;
        this.program=program;
    }
}
