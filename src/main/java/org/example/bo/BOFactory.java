package org.example.bo;

import org.example.bo.custom.impl.*;

public class BOFactory {
    public static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return (boFactory==null)? boFactory = new BOFactory():boFactory;
    }

    public enum BOTypes {
        USER,THERAPIST,THERAPY_PROGRAM,PATIENTS,THERAPY_SESSION,
        PATIENT_HISTORY,PAYMENTS,CHANGE_CREDENTIALS
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case USER -> {
                return new LoginBOImpl();
            }
            case THERAPIST -> {
                return new TherapistManagementBOImpl();
            }
            case THERAPY_PROGRAM -> {
                return new TherapyProgramManagementBOImpl();
            }
            case PATIENTS -> {
                return new PatientManagementBOImpl();
            }
            case THERAPY_SESSION -> {
                return new TherapySessionSchedulingBOImpl();
            }
            case PATIENT_HISTORY -> {
                return new PatientTheropyHistoryBOImpl();
            }
            case PAYMENTS -> {
                return new PaymentInvoiceManagementBOImpl();
            }
            case CHANGE_CREDENTIALS -> {
                return new ChangeCredentialsBOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
