package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dao.SuperDAO;
import org.example.entity.TherapyPrograms;

import java.io.IOException;
import java.util.List;

public interface TherapyProgramsDAO extends CrudDAO<TherapyPrograms> {

    List getAllPrograms() throws IOException;
}
