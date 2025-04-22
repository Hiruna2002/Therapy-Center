package org.example.dao;

import org.example.dao.custom.TherapistDAO;
import org.example.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        USER,THERAPIST,THERAPIST_PROGRAM,PATIENTS,THERAPY_SESSION,PAYMENTS
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case USER -> {
                return new UserDAOImpl();
            }
            case THERAPIST -> {
                return new TherapistDAOImpl();
            }
            case THERAPIST_PROGRAM -> {
                return new TherapyProgramsDAOImpl();
            }
            case PATIENTS -> {
                return new PatientsDAOImpl();
            }
            case THERAPY_SESSION -> {
                return new TherapySessionsDAOImpl();
            }
            case PAYMENTS -> {
                return new PaymentsDAOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
