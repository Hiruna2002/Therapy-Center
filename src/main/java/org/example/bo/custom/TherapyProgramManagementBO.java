package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.TherapyProgramsDTO;
import org.example.entity.TherapyPrograms;

import java.io.IOException;
import java.util.List;

public interface TherapyProgramManagementBO extends SuperBO {
    boolean saveTherapy(TherapyProgramsDTO therapyProgramsDTO) throws IOException;

    boolean deleteTherapyProgram(String id) throws IOException;

    boolean updateTherapyProgram(TherapyProgramsDTO therapyProgramsDTO) throws IOException;

    List<TherapyProgramsDTO> getTherapyPrograms() throws IOException;

    String getLastId() throws IOException;
}
