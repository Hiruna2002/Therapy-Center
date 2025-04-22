package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.example.bo.BOFactory;
import org.example.bo.custom.TherapistManagementBO;
import org.example.dto.TherapistsDTO;
import org.example.tm.TherapistTm;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class TherapistManagementController implements Initializable {
    public TextField txtName;
    public TextField txtStatus;
    public TableView<TherapistTm> tblTherapist;
    public TableColumn<TherapistTm,String> colName;
    public TableColumn<TherapistTm,String> colStatus;
    public TableColumn<TherapistTm,String> colProgram;
    public ComboBox<String> cmbProgram;
    public JFXButton btnSave;
    public Label lblId;

    TherapistManagementBO therapistManagementBO = (TherapistManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPIST);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTables();
        setId();
        setValues();
    }

    private void setValues() {
        try {
            List<String> allProgram = therapistManagementBO.getAllProgram();
            ObservableList<String> objects = FXCollections.observableArrayList();
            for (String program:allProgram){
                objects.add(program);
            }
            cmbProgram.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setId() {
        try {
            String lastId = therapistManagementBO.getLastId();
            lblId.setText(lastId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadTables() {
        try {
            List<TherapistsDTO> allTherapist = therapistManagementBO.getAllTherapist();
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
            ObservableList<TherapistTm> objects = FXCollections.observableArrayList();

            for (TherapistsDTO therapistsDTO:allTherapist){
                objects.add(new TherapistTm(therapistsDTO.getName(),therapistsDTO.getStatus(),therapistsDTO.getProgram()));
            }

            tblTherapist.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {

        String name = txtName.getText();
        boolean cname = Pattern.matches("^[A-Za-z]+$",name);
        if (!cname){
            txtName.setStyle("-fx-text-fill: RED");
            return;
        }
        String status = txtStatus.getText();
//        boolean cstatus = Pattern.matches("^[A-Za-z0-9]+$\n",status);
//        if (!cstatus){
//            txtStatus.setStyle("-fx-text-fill: RED");
//            return;
//        }
        String program = cmbProgram.getValue().toString();
        String id = lblId.getText();

        if (name== null || status == null || program== null || id==null){
            new Alert(Alert.AlertType.ERROR,"Missing Fields.").show();
            throw new RuntimeException("Input Fields are Empty..");
        }
        if (btnSave.getText().equals("Save")){
            try {
                boolean isSave = therapistManagementBO.saveTherapist(new TherapistsDTO(name, status, program));
                if (isSave) {
                    new Alert(Alert.AlertType.INFORMATION,"Therapist Save Successfully.").show();
                    refreshPage();
                    loadTables();
                    setId();
                } else {
                    new Alert(Alert.AlertType.WARNING,"Therapist not Save.").show();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (btnSave.getText().equals("Update")) {
            try {
                String lastId = id.replaceAll("^T", "");

                boolean isUpdate = therapistManagementBO.updateTherapist(new TherapistsDTO(Integer.parseInt(lastId),name, status, program));

                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION,"Therapist Update SuccessFully.").show();
                    refreshPage();
                    loadTables();
                    setId();
                } else {
                    new Alert(Alert.AlertType.WARNING,"Therapist Not Updated.").show();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void refreshPage() {
        txtName.clear();
        txtStatus.clear();
        cmbProgram.setValue("");
        btnSave.setText("Save");
    }

    public void tblOnAction(MouseEvent mouseEvent) {
        try {
            TherapistTm selectedItem = tblTherapist.getSelectionModel().getSelectedItem();
            txtName.setText(selectedItem.getName());
            txtStatus.setText(selectedItem.getStatus());
            cmbProgram.setValue(selectedItem.getProgram());

            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you Want to Delete it?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                try {

                    String text = lblId.getText();
                    String id = text.replaceAll("^T", "");
                    boolean isDelete = therapistManagementBO.deleteTherapist(id);
                    if (isDelete) {
                        new Alert(Alert.AlertType.INFORMATION, "Therapist Delete SuccessFully.").show();
                        refreshPage();
                        loadTables();
                        setId();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Therapist Not Deleted.").show();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                btnSave.setText("Update");
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.WARNING,"Please Select Table Row.").show();
        }

    }

    public void refreshOnAction(ActionEvent actionEvent) {
        refreshPage();
        loadTables();
        setId();
    }

    public void generateReportOnAction(ActionEvent actionEvent) throws JRException, IOException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/MentalHospital.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mental_database", "root", "PHW#84#jeor");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,connection);
        JasperViewer.viewReport(jasperPrint);
    }
}
