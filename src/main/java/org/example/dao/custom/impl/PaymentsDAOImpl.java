package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.PaymentsDAO;
import org.example.entity.Payments;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public class PaymentsDAOImpl implements PaymentsDAO {
    @Override
    public boolean save(Payments payments) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(payments);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Payments payments) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(payments);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public int getLastId() throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        List list = session.createQuery("FROM Payments ").list();

        if (list.isEmpty()){
            return 1;
        }

        return (int) session.createQuery("SELECT pa.id FROM Payments pa ORDER BY pa.id DESC ").setMaxResults(1).uniqueResult();
    }

    @Override
    public boolean delete(String text) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        Payments payments = session.get(Payments.class, text);
        session.remove(payments);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<Payments> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        return session.createQuery("from Payments ").list();
    }

    @Override
    public Payments getbyId(int text) throws IOException {
        return null;
    }

    @Override
    public List getAllId() throws IOException {
        return null;
    }
}
