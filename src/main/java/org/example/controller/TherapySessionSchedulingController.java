package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.bo.BOFactory;
import org.example.bo.custom.TherapySessionSchedulingBO;
import org.example.bo.exception.TherapySessionShedulingException;
import org.example.dto.TherapySessionsDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class  TherapySessionSchedulingController implements Initializable {
    public JFXButton btnSave;
    public TextField txtCost;
    public TextField txtDescription;
    public ComboBox<String> cmbTerapy;
    public ComboBox<String> cmbPatient;
    public Label lblId;
    public ComboBox<String> cmbProgram;
    TherapySessionSchedulingBO therapySessionSchedulingBO = (TherapySessionSchedulingBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPY_SESSION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadIds();
        refreshPage();
        setValues();
    }

    private void setValues() {
        try {
            List allPatientId = therapySessionSchedulingBO.getAllPatientId();
            ObservableList<String> objects = FXCollections.observableArrayList();
            for (Object value:allPatientId){
                objects.add(value.toString());
            }
            cmbPatient.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            List allProgramId = therapySessionSchedulingBO.getAllTherapyId();
            ObservableList<String> objects = FXCollections.observableArrayList();
            for (Object value:allProgramId){
                objects.add(value.toString());
            }
            cmbProgram.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            List allTherapyProgramId = therapySessionSchedulingBO.getAllProgramId();
            ObservableList<String> objects = FXCollections.observableArrayList();
            for (Object value:allTherapyProgramId){
                objects.add(value.toString());
            }
            cmbTerapy.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() {
        txtCost.clear();
        txtDescription.clear();
        cmbPatient.setValue("");
        cmbTerapy.setValue("");
        cmbProgram.setValue("");
    }

    private void loadIds() {
        try {
            String lastId = therapySessionSchedulingBO.getLastId();
            lblId.setText(lastId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void saveOnAction(ActionEvent actionEvent) {
        String patient = cmbPatient.getValue();
        String therapy = cmbTerapy.getValue();
        String program = cmbProgram.getValue();
        String cost = txtCost.getText();
        boolean ccost= Pattern.matches("^-?\\d*\\.\\d+$",cost);
        if (!ccost){
            txtCost.setStyle("-fx-text-fill: RED");
            return;
        }
        String description = txtDescription.getText();
//        boolean cdescription = Pattern.matches("^[A-Za-z0-9]+$\n",description);
//        if (!cdescription){
//            txtDescription.setStyle("-fx-text-fill: RED");
//            return;
//        }
        String id = lblId.getText();

        if (patient== null || therapy == null || program== null || cost==null || description==null){
            new Alert(Alert.AlertType.ERROR,"Missing Fields.").show();
            throw new NullPointerException("Input Fields are Empty..");
        }
        if (btnSave.getText().equals("Save")){
            try {
                boolean isSave = therapySessionSchedulingBO.saveTherapySession(new TherapySessionsDTO(Integer.parseInt(therapy), Integer.parseInt(patient), Integer.parseInt(program), Double.parseDouble(cost), description));

                if (isSave){
                    new Alert(Alert.AlertType.INFORMATION,"Therapy Session Save Successfully.").show();
                    refreshPage();
                    loadIds();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Therapy Session Not Save.").show();
                }
            } catch (IOException e) {
                try {
                    throw new TherapySessionShedulingException("Therapy Session sheduling Issue..");
                } catch (TherapySessionShedulingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (btnSave.getText().equals("Update")) {
            try {
                String lid = id.replaceAll("^TS", "");
                boolean isUpdate = therapySessionSchedulingBO.updateTherapySession(new TherapySessionsDTO(Integer.parseInt(lid),Integer.parseInt(therapy), Integer.parseInt(patient), Integer.parseInt(program), Double.parseDouble(cost), description));

                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION,"Therapy Session Update Successfully.").show();
                    refreshPage();
                    loadIds();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Therapy Session Not Updated.").show();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void resetOnAction(ActionEvent actionEvent) {
        refreshPage();
        loadIds();
    }

}
