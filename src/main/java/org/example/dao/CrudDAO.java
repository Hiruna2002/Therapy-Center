package org.example.dao;

import org.example.entity.Patients;
import org.example.entity.Therapists;
import org.example.entity.Users;

import java.io.IOException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T t) throws IOException;
    boolean update(T t) throws IOException;
    int getLastId() throws IOException;
    boolean delete(String id) throws IOException;
    List<T> getAll() throws IOException;
    T getbyId(int text) throws IOException;
    List getAllId() throws IOException;


}
