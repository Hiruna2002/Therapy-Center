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
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.example.bo.BOFactory;
import org.example.bo.SuperBO;
import org.example.bo.custom.PaymentInvoiceManagementBO;
import org.example.bo.exception.PaymentProcessingException;
import org.example.dto.PaymentsDTO;
import org.example.entity.Payments;
import org.example.tm.PaymentTm;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class PaymentInvoiceManagementController implements Initializable {
    public JFXButton btnSave;
    public TextField txtPayment;
    public TableView<PaymentTm> tblPayment;
    public TableColumn<PaymentTm,Integer> colTherapy;
    public TableColumn<PaymentTm,Integer> colPatient;
    public TableColumn<PaymentTm,Double> colPayment;
    public TableColumn<PaymentTm,String> colStatus;
    public ComboBox<String> cmbTherapy;
    public ComboBox<String> cmbPatient;
    public TextField txtStatus;
    public Label lblId;

    PaymentInvoiceManagementBO paymentBO = (PaymentInvoiceManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENTS);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTherapy.setCellValueFactory(new PropertyValueFactory<>("therapy"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            loadTables();
            loadIds();
            setValues();
            refreshPage();
    }

    private void refreshPage() {
        txtPayment.clear();
        txtStatus.clear();
        cmbTherapy.setValue("");
        cmbPatient.setValue("");
    }

    private void setValues() {
        try {
            List allPatientsId = paymentBO.getAllPatientsId();
            ObservableList<String> objects = FXCollections.observableArrayList();
            for (Object value:allPatientsId){
                objects.add(value.toString());
            }
            cmbPatient.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            List allTherapyId = paymentBO.getAllTherapyId();
            ObservableList<String> objects = FXCollections.observableArrayList();
            for (Object value:allTherapyId){
                objects.add(value.toString());
            }
            cmbTherapy.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTables() {
        try {
            List<PaymentsDTO> allPayments = paymentBO.getAllPayments();
            ObservableList<PaymentTm> objects = FXCollections.observableArrayList();

            for (PaymentsDTO paymentsDTO:allPayments){
                objects.add(new PaymentTm(paymentsDTO.getTheropy(),paymentsDTO.getPatient(),paymentsDTO.getPayment(),paymentsDTO.getStatus()));
            }
            tblPayment.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadIds() {
        try {
            String id = paymentBO.getlastId();
            lblId.setText(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String payments = txtPayment.getText();
        boolean cpayments= Pattern.matches("^-?\\d*\\.\\d+$",payments);
        if (!cpayments){
            txtPayment.setStyle("-fx-text-fill: RED");
            return;
        }
        String status = txtStatus.getText();
//        boolean cstatus = Pattern.matches("^[A-Za-z0-9]+$\n",status);
//        if (!cstatus){
//            txtStatus.setStyle("-fx-text-fill: RED");
//            return;
//        }
        String patient = cmbPatient.getValue();
        String therapy = cmbTherapy.getValue();

        if (payments== null || status == null || patient== null || therapy==null){
            new Alert(Alert.AlertType.ERROR,"Missing Fields.").show();
            throw new NullPointerException("Input Fields are Empty..");
        }
        if (btnSave.getText().equals("Save")){
            try {
                boolean isSave = paymentBO.savePayment(new PaymentsDTO(Integer.parseInt(therapy), Integer.parseInt(patient), Double.parseDouble(payments), status));

                if (isSave) {
                    new Alert(Alert.AlertType.INFORMATION,"Payment Save Successfully.").show();
                    refreshPage();
                    loadTables();
                    loadIds();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Payment Not Saved.").show();
                }
            } catch (IOException e) {
                try {
                    throw new PaymentProcessingException("Payment not Success..");
                } catch (PaymentProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (btnSave.getText().equals("Update")) {
            try {
                String id = lblId.getText();
                String lid = id.replaceAll("^P", "");
                boolean isUpdate = paymentBO.updatePayment(new PaymentsDTO(Integer.parseInt(lid),Integer.parseInt(therapy), Integer.parseInt(patient), Double.parseDouble(payments), status));
                if (isUpdate){
                    new Alert(Alert.AlertType.INFORMATION,"Payment Update Successfully.").show();
                    refreshPage();
                    loadTables();
                    loadIds();
                    btnSave.setText("Save");
                }else {
                    new Alert(Alert.AlertType.ERROR,"Payment Not Updated.").show();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void tableOnAction(MouseEvent mouseEvent) {
        PaymentTm selectedItem = tblPayment.getSelectionModel().getSelectedItem();
        txtStatus.setText(selectedItem.getStatus());
        txtPayment.setText(String.valueOf(selectedItem.getPayment()));
        cmbTherapy.setValue(String.valueOf(selectedItem.getTherapy()));
        cmbPatient.setValue(String.valueOf(selectedItem.getPatient()));

        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Delete it?",ButtonType.NO,ButtonType.YES).showAndWait();

        if (buttonType.get()==ButtonType.YES){
            try {String text = lblId.getText();
                String id = text.replaceAll("^P", "");
                boolean isDelete = paymentBO.deletePayment(id);
                if (isDelete) {
                    new Alert(Alert.AlertType.INFORMATION,"Payment Delete Sucsessfully.").show();
                    refreshPage();
                    loadTables();
                    loadIds();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Payment Not Deleted.").show();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            btnSave.setText("Update");
        }
    }

    public void resetOnAction(ActionEvent actionEvent) {
        refreshPage();
        loadTables();
        loadIds();
        btnSave.setText("Save");
    }

    public void generateReportOnAction(ActionEvent actionEvent) {
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream("/reports/paymentsReport.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mental_database", "root", "PHW#84#jeor");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
            JasperViewer.viewReport(jasperPrint);
        }catch (JRException|SQLException e){
            e.printStackTrace();
        }
    }
}
