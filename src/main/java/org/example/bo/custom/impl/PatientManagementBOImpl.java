package org.example.bo.custom.impl;

import org.example.bo.custom.PatientManagementBO;
import org.example.dao.DAOFactory;
import org.example.dao.SuperDAO;
import org.example.dao.custom.PatientsDAO;
import org.example.dto.PatientsDTO;
import org.example.entity.Patients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientManagementBOImpl implements PatientManagementBO {
    PatientsDAO patientsDAO = (PatientsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENTS);
    @Override
    public boolean savePatient(PatientsDTO patientsDTO) throws IOException {
        return patientsDAO.save(new Patients(patientsDTO.getName(),patientsDTO.getDuration(),patientsDTO.getCost(),patientsDTO.getDescription()));
    }

    @Override
    public boolean updatePatient(PatientsDTO patientsDTO) throws IOException {
        return patientsDAO.update(new Patients(patientsDTO.getId(),patientsDTO.getName(),patientsDTO.getDuration(),patientsDTO.getCost(),patientsDTO.getDescription()));
    }

    @Override
    public List<PatientsDTO> getAllPatients() throws IOException {
        List<Patients> all = patientsDAO.getAll();
        List<PatientsDTO> patientsDTOS = new ArrayList<>();
        for (Patients patients:all){
            patientsDTOS.add(new PatientsDTO(patients.getName(),patients.getDuration(),patients.getCost(),patients.getDescription()));
        }

        return patientsDTOS;
    }

    @Override
    public String getLastId() throws IOException {
        int lastId = patientsDAO.getLastId();
        return String.format("P%03d", lastId);
    }

    @Override
    public boolean deletePatient(String text) throws IOException {
        return patientsDAO.delete(text);
    }

    @Override
    public List<PatientsDTO> searchPatient(String text) throws IOException {
        List<Patients> search = patientsDAO.search(text);
        List<PatientsDTO> patientsDTOS = new ArrayList<>();

        for (Patients patients:search){
            patientsDTOS.add(new PatientsDTO(patients.getName(),patients.getDuration(),patients.getCost(),patients.getDescription()));
        }

        return patientsDTOS;
    }
}
