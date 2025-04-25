package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.TherapySessionsDAO;
import org.example.entity.TherapySessions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class TherapySessionsDAOImpl implements TherapySessionsDAO {
    @Override
    public boolean save(TherapySessions therapySessions) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(therapySessions);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(TherapySessions therapySessions) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(therapySessions);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public int getLastId() throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        List<TherapySessions> fromTherapySessions = session.createQuery("FROM TherapySessions ", TherapySessions.class).list();

        if (fromTherapySessions.isEmpty()){
            return 1;
        }
        return (int) session.createQuery("SELECT ts.id FROM TherapySessions ts ORDER BY ts.id DESC").setMaxResults(1).uniqueResult();
    }

    @Override
    public boolean delete(String lid) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        TherapySessions therapySessions = session.get(TherapySessions.class, lid);

        session.remove(therapySessions);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<TherapySessions> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        return session.createQuery("from TherapySessions ").list();
    }

    @Override
    public TherapySessions getbyId(int text) throws IOException {
        return null;
    }

    @Override
    public List getAllId() throws IOException {
        return null;
    }
}
