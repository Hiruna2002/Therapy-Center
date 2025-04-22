package org.example.bo.custom.impl;

import org.example.bo.custom.TherapistManagementBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.TherapistDAO;
import org.example.dao.custom.TherapyProgramsDAO;
import org.example.dto.TherapistsDTO;
import org.example.entity.Therapists;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TherapistManagementBOImpl implements TherapistManagementBO {
    TherapistDAO therapist = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPIST);
    TherapyProgramsDAO therapyProgramsDAO = (TherapyProgramsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPIST_PROGRAM);
    @Override
    public boolean saveTherapist(TherapistsDTO therapistsDTO) throws IOException {
        return therapist.save(new Therapists(therapistsDTO.getName(),therapistsDTO.getStatus(),therapistsDTO.getProgram()));
    }

    @Override
    public boolean updateTherapist(TherapistsDTO therapistsDTO) throws IOException {
        return therapist.update(new Therapists(therapistsDTO.getId(),therapistsDTO.getName(),therapistsDTO.getStatus(),therapistsDTO.getProgram()));
    }

    @Override
    public List<TherapistsDTO> getAllTherapist() throws IOException {
        List<Therapists> all = therapist.getAll();
        List<TherapistsDTO> therapistsDTOS = new ArrayList<>();
        for (Therapists therapists:all){
            therapistsDTOS.add(new TherapistsDTO(therapists.getName(),therapists.getStatus(),therapists.getProgram()));
        }

        return therapistsDTOS;

    }

    @Override
    public String getLastId() throws IOException {
        int lastId = therapist.getLastId();
        return String.format("T%03d",lastId);
    }

    @Override
    public boolean deleteTherapist(String id) throws IOException {
        return therapist.delete(id);
    }

    @Override
    public List<String> getAllProgram() throws IOException {
        return therapyProgramsDAO.getAllPrograms();

    }
}
