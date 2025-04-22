package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.TherapistsDTO;

import java.io.IOException;
import java.util.List;

public interface TherapistManagementBO extends SuperBO {
    boolean saveTherapist(TherapistsDTO therapistsDTO) throws IOException;

    boolean updateTherapist(TherapistsDTO therapistsDTO) throws IOException;

    List<TherapistsDTO> getAllTherapist() throws IOException;

    String getLastId() throws IOException;

    boolean deleteTherapist(String id) throws IOException;

    List getAllProgram() throws IOException;

}
