package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.TherapySessionsDTO;

import java.io.IOException;
import java.util.List;

public interface PatientTheropyHistoryBO extends SuperBO {
    List<TherapySessionsDTO> getAll() throws IOException;
}
