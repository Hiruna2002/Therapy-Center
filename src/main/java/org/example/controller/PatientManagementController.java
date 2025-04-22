package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.bo.BOFactory;
import org.example.bo.SuperBO;
import org.example.bo.custom.PatientManagementBO;
import org.example.dto.PatientsDTO;
import org.example.tm.PatientsTm;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class PatientManagementController implements Initializable {
    public JFXButton btnSave;
    public TextField txtName;
    public TextField txtDuration;
    public TextField txtCost;
    public TableView<PatientsTm> tblPatients;
    public TableColumn<PatientsTm,String> colName;
    public TableColumn<PatientsTm,String> colDuration;
    public TableColumn<PatientsTm,Double> colCost;
    public TableColumn<PatientsTm,String> colDescription;
    public TextField txtDescription;
    public Label lblId;
    public TextField txtSearch;

    PatientManagementBO patientManagementBO = (PatientManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENTS);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadTables();
        loadIds();
        resetPage();
    }

    private void resetPage() {
        txtName.clear();
        txtDescription.clear();
        txtCost.clear();
        txtDuration.clear();
        txtSearch.clear();
    }

    private void loadIds() {
        try {
            String lastId = patientManagementBO.getLastId();
            lblId.setText(lastId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadTables() {
        try {
            List<PatientsDTO> allPatients = patientManagementBO.getAllPatients();
            ObservableList<PatientsTm> objects = FXCollections.observableArrayList();

            for (PatientsDTO patientsDTO:allPatients){
                objects.add(new PatientsTm(patientsDTO.getName(),patientsDTO.getDuration(),patientsDTO.getCost(),patientsDTO.getDescription()));
            }

            tblPatients.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        boolean cname = Pattern.matches("^[A-Za-z]+$",name);

        String duration = txtDuration.getText();
        boolean cduration = Pattern.matches("^[A-Za-z0-9]+$",duration);

        String cost = txtCost.getText();
        boolean ccost= Pattern.matches("^-?\\d*\\.\\d+$",cost);

        String description = txtDescription.getText();
//        boolean cdescription = Pattern.matches("^[A-Za-z0-9]+$\n",description);
//        if (!cdescription){
//            txtDescription.setStyle("-fx-text-fill: RED");
//            return;
//        }
        String id = lblId.getText();

        if (name== null || duration == null || cost== null || description==null){
            new Alert(Alert.AlertType.ERROR,"Missing Fields.").show();
            throw new NullPointerException("Input Fields are Empty..");
        }
        if (btnSave.getText().equals("Save")){
            try {
                boolean isSave = patientManagementBO.savePatient(new PatientsDTO(name,duration,Double.parseDouble(cost),description));

                if (isSave) {
                    new Alert(Alert.AlertType.INFORMATION,"Patient Save Successfully.").show();
                    loadTables();
                    loadIds();
                    resetPage();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Patient Not Save.").show();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                String lid = id.replaceAll("^P", "");
                boolean isUpdate = patientManagementBO.updatePatient(new PatientsDTO(Integer.parseInt(lid),name, duration, Double.parseDouble(cost), description));

                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION,"Patient Update Successfully.").show();
                    loadTables();
                    loadIds();
                    resetPage();
                    btnSave.setText("Save");
                }else {
                    new Alert(Alert.AlertType.ERROR,"Patient not Updated.").show();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void tblOnAction(MouseEvent mouseEvent) {
        PatientsTm selectedItem = tblPatients.getSelectionModel().getSelectedItem();
        txtName.setText(selectedItem.getName());
        txtDuration.setText(selectedItem.getDuration());
        txtCost.setText(String.valueOf(selectedItem.getCost()));
        txtDescription.setText(selectedItem.getDescription());

        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete it?",ButtonType.NO,ButtonType.YES).showAndWait();
        if (buttonType.get()==ButtonType.YES){
            try {
                String text = lblId.getText();
                String id = text.replaceAll("^P", "");
                boolean isDelete = patientManagementBO.deletePatient(id);

                if (isDelete) {
                    new Alert(Alert.AlertType.INFORMATION,"Patient Delete Successfully.").show();
                    loadTables();
                    loadIds();
                    resetPage();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Patient not Deleted.").show();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            btnSave.setText("Update");
        }

    }

    public void searchOnAction(ActionEvent actionEvent) {
        try {
            String text = txtSearch.getText();
            List<PatientsDTO> patientsDTOS = patientManagementBO.searchPatient(text);
            ObservableList<PatientsTm> objects = FXCollections.observableArrayList();
            for (PatientsDTO patientsDTO : patientsDTOS) {
                objects.add(new PatientsTm(patientsDTO.getName(), patientsDTO.getDuration(), patientsDTO.getCost(), patientsDTO.getDescription()));
            }
            tblPatients.setItems(objects);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void resetOnAction(ActionEvent actionEvent) {
        loadTables();
        loadIds();
        resetPage();
        btnSave.setText("Save");
    }
}
