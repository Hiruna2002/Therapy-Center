package org.example.bo.custom.impl;

import org.example.bo.custom.TherapyProgramManagementBO;
import org.example.dao.DAOFactory;
import org.example.dao.SuperDAO;
import org.example.dao.custom.TherapyProgramsDAO;
import org.example.dto.TherapyProgramsDTO;
import org.example.entity.TherapyPrograms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TherapyProgramManagementBOImpl implements TherapyProgramManagementBO {

    TherapyProgramsDAO therapyProgram = (TherapyProgramsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPIST_PROGRAM);
    @Override
    public boolean saveTherapy(TherapyProgramsDTO therapyProgramsDTO) throws IOException {
        return therapyProgram.save(new TherapyPrograms(therapyProgramsDTO.getName(),therapyProgramsDTO.getDuration(),therapyProgramsDTO.getCost(),therapyProgramsDTO.getDescription()));
    }

    @Override
    public boolean deleteTherapyProgram(String id) throws IOException {
        return therapyProgram.delete(id);
    }

    @Override
    public boolean updateTherapyProgram(TherapyProgramsDTO therapyProgramsDTO) throws IOException {
        return therapyProgram.update(new TherapyPrograms(therapyProgramsDTO.getId(),therapyProgramsDTO.getName(),therapyProgramsDTO.getDuration(),therapyProgramsDTO.getCost(),therapyProgramsDTO.getDescription()));
    }

    @Override
    public List<TherapyProgramsDTO> getTherapyPrograms() throws IOException {
        List<TherapyPrograms> all = therapyProgram.getAll();
        List<TherapyProgramsDTO> therapyProgramsDTOS=new ArrayList<>();
        for (TherapyPrograms therapyPrograms:all){
            therapyProgramsDTOS.add(new TherapyProgramsDTO(therapyPrograms.getName(),therapyPrograms.getDuration(),therapyPrograms.getCost(),therapyPrograms.getDescription()));
        }

        return therapyProgramsDTOS;
    }

    @Override
    public String getLastId() throws IOException {
        int lastId = therapyProgram.getLastId();
        return String.format("TP%03d",lastId);
    }
}
