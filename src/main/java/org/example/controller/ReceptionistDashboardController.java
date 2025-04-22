package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ReceptionistDashboardController {
    public AnchorPane ReceptionistAnc;
//    public Label lblName;
//    public Label lblPassword;

    public void patientOnAction(ActionEvent event) throws IOException {
        ReceptionistAnc.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/views/PatientManagementForm.fxml"));
        ReceptionistAnc.getChildren().add(load);
    }

    public void therapySessionOnAction(ActionEvent event) throws IOException {
        ReceptionistAnc.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/views/TherapySessionSchedulingForm.fxml"));
        ReceptionistAnc.getChildren().add(load);
    }

    public void paymentOnAction(ActionEvent event) throws IOException {
        ReceptionistAnc.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/views/PaymentInvoiceManagementForm.fxml"));
        ReceptionistAnc.getChildren().add(load);
    }

    public void patientHistoryOnAction(ActionEvent event) throws IOException {
        ReceptionistAnc.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/views/PatientTheropyHistoryForm.fxml"));
        ReceptionistAnc.getChildren().add(load);
    }

    public void btnLogoutOnNavigateTo(ActionEvent actionEvent) {
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/views/LoginForm.fxml"));
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


//    public void setValues(String name, String password) {
//        lblName.setText(name);
//        lblPassword.setText(password);
//    }
}
