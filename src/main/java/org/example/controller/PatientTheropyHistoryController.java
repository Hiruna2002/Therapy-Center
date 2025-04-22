package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.bo.BOFactory;
import org.example.bo.SuperBO;
import org.example.bo.custom.PatientTheropyHistoryBO;
import org.example.bo.custom.TherapySessionSchedulingBO;
import org.example.dto.TherapySessionsDTO;
import org.example.tm.TherapySessionTm;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PatientTheropyHistoryController implements Initializable {

    public TableView<TherapySessionTm> tblTherapySession;
    public TableColumn<TherapySessionTm,Integer> colTherapy;
    public TableColumn<TherapySessionTm,Integer> colPatient;
    public TableColumn<TherapySessionTm,Integer> colProgram;
    public TableColumn<TherapySessionTm,Double> colCost;
    public TableColumn<TherapySessionTm,String> colDescription;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTherapy.setCellValueFactory(new PropertyValueFactory<>("therapy"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        loadTables();
    }

    private void loadTables() {
        PatientTheropyHistoryBO patientTheropyHistoryBO = (PatientTheropyHistoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENT_HISTORY);
        try {
            List<TherapySessionsDTO> all = patientTheropyHistoryBO.getAll();

            ObservableList<TherapySessionTm> objects = FXCollections.observableArrayList();

            for (TherapySessionsDTO therapySessionsDTO : all) {
                objects.add(new TherapySessionTm(therapySessionsDTO.getTherapy(), therapySessionsDTO.getPatient(), therapySessionsDTO.getProgram(), therapySessionsDTO.hashCode(), therapySessionsDTO.getDescription()));
            }

            tblTherapySession.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

}
