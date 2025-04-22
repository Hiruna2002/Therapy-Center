package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AdminDashboardController{
    public AnchorPane dashAnc;
//    public Label lblName;
//    public Label lblPassword;

    public void patientHistoryOnAction(ActionEvent event) throws IOException {
        dashAnc.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/views/PatientTheropyHistoryForm.fxml"));
        dashAnc.getChildren().add(load);
    }

    public void theropyProgramOnAction(ActionEvent event) throws IOException {
        dashAnc.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/views/TherapyProgramManagementForm.fxml"));
        dashAnc.getChildren().add(load);
    }

    public void therapistOnAction(ActionEvent event) throws IOException {
//        dashAnc.getChildren().clear();
//        Parent load = FXMLLoader.load(getClass().getResource("/views/TherapistManagementForm.fxml"));
//        dashAnc.getChildren().add(load);
        dashAnc.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        dashAnc.getChildren().add(load);

    }

//    public void setValue(String name, String password) {
//        lblName.setText(name);
//        lblPassword.setText(password);
//    }

    public void changeCredentialsOnAction(ActionEvent actionEvent) {
        try {
            dashAnc.getChildren().clear();
            Parent load = FXMLLoader.load(getClass().getResource("/views/ChangeCredentialsForm.fxml"));
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLogoutOnClickTo(ActionEvent actionEvent) {
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
}
