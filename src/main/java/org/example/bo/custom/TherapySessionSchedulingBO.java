package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.TherapySessionsDTO;

import java.io.IOException;
import java.util.List;

public interface TherapySessionSchedulingBO extends SuperBO {
    boolean saveTherapySession(TherapySessionsDTO therapySessionsDTO) throws IOException;

    boolean updateTherapySession(TherapySessionsDTO therapySessionsDTO) throws IOException;

    String getLastId() throws IOException;

    List getAllPatientId() throws IOException;

    List getAllTherapyId() throws IOException;

    List getAllProgramId() throws IOException;
}
