package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.TherapyProgramsDAO;
import org.example.entity.TherapyPrograms;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class TherapyProgramsDAOImpl implements TherapyProgramsDAO {
    @Override
    public boolean save(TherapyPrograms therapyPrograms) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(therapyPrograms);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        TherapyPrograms therapyPrograms = session.get(TherapyPrograms.class, id);
        session.remove(therapyPrograms);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(TherapyPrograms therapyPrograms) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(therapyPrograms);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<TherapyPrograms> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();

        return session.createQuery("From TherapyPrograms").list();

    }

    @Override
    public int getLastId() throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();

        List<TherapyPrograms> fromTherapyPrograms = session.createQuery("FROM TherapyPrograms ", TherapyPrograms.class).list();
        if (fromTherapyPrograms.isEmpty()){
            return 1;
        }

        return (int) session.createQuery("SELECT tp.id FROM TherapyPrograms tp ORDER BY tp.id DESC").setMaxResults(1).uniqueResult();


    }

    @Override
    public TherapyPrograms getbyId(int program) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        TherapyPrograms therapyPrograms = session.get(TherapyPrograms.class, program);
        return therapyPrograms;
    }

    @Override
    public List getAllId() throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        return session.createQuery("SELECT tp.id FROM TherapyPrograms tp").list();
    }

    @Override
    public List<String> getAllPrograms() throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        return session.createQuery("SELECT p.name FROM TherapyPrograms p").list();
    }
}
