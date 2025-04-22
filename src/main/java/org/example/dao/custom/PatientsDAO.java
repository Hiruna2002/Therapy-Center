package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dao.SuperDAO;
import org.example.entity.Patients;

import java.io.IOException;
import java.util.List;

public interface PatientsDAO extends CrudDAO<Patients> {
    List<Patients> search(String text) throws IOException;
}
