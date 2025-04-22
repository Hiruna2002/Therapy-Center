package org.example.bo.custom.impl;

import org.example.bo.custom.PatientTheropyHistoryBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.TherapySessionsDAO;
import org.example.dto.TherapySessionsDTO;
import org.example.entity.TherapySessions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientTheropyHistoryBOImpl implements PatientTheropyHistoryBO {
    TherapySessionsDAO therapySessionsDAO = (TherapySessionsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPY_SESSION);
    @Override
    public List<TherapySessionsDTO> getAll() throws IOException {

        List<TherapySessions> all = therapySessionsDAO.getAll();

        List<TherapySessionsDTO> therapySessionsDTOS = new ArrayList<>();

        for (TherapySessions therapySessions:all) {
            therapySessionsDTOS.add(new TherapySessionsDTO(therapySessions.getId(),therapySessions.getTherapyPrograms().getId(),therapySessions.getPatients().getId(),therapySessions.getTherapyPrograms().getId(),therapySessions.getCost(),therapySessions.getDescription()));
        }
        return therapySessionsDTOS;
    }
}
