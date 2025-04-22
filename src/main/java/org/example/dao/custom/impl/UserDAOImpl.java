package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.UserDAO;
import org.example.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    public List checkUser(Users users) throws IOException {
        System.out.println(users.getName());
        System.out.println(users.getPassword());
        System.out.println(users.getJobRole());
        Session session = FactoryConfiguration.getInstance().openSession();
        List<Users> list = session.
                createQuery("from Users u where u.name = :name AND u.jobRole = :role", Users.class).setParameter("name", users.getName()).
                setParameter("role", users.getJobRole()).list();

        return list;

    }

    @Override
    public boolean save(Users users) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(users);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Users users) throws IOException {
        return false;
    }

    @Override
    public int getLastId() throws IOException {
        return 0;
    }

    @Override
    public boolean delete(String id) throws IOException {
        return false;
    }

    @Override
    public List<Users> getAll() throws IOException {
        return null;
    }

    @Override
    public Users getbyId(int text) throws IOException {
        return null;
    }

    @Override
    public List getAllId() throws IOException {
        return null;
    }
}
