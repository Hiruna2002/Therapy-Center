package org.example.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.SuperBO;
import org.example.bo.custom.ChangeCredentialsBO;
import org.example.bo.exception.InvalidCredentialsException;
import org.example.dto.UsersDTO;
import org.example.util.PasswordUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ChangeCredentialsController implements Initializable {

    public TextField txtName;
    public PasswordField txtPassword;
    public JFXComboBox cmbJobRole;
    ChangeCredentialsBO changeCredentialsBO = (ChangeCredentialsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CHANGE_CREDENTIALS);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Object> objects = FXCollections.observableArrayList();
        objects.add("Admin");
        objects.add("Receptionist");
        cmbJobRole.setItems(objects);
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        boolean cname = Pattern.matches("^[A-Za-z]+$",name);
        if (!cname){
            txtName.setStyle("-fx-text-fill: RED");
            return;
        }
        String password = txtPassword.getText();
        boolean cpassword = Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",password);
        if (!cpassword){
            txtPassword.setStyle("-fx-text-fill: RED");
            return;
        }
        String hashPassword = PasswordUtil.hashPassword(password);
        String role = cmbJobRole.getValue().toString();

        if (name== null || password == null || role== null){
            new Alert(Alert.AlertType.ERROR,"Missing Fields.").show();
            throw new NullPointerException("Inputs Fields are Empty..");
        }
        try {
            boolean isSave = changeCredentialsBO.saveCredentials(new UsersDTO(name, hashPassword, role));
            if (isSave){
                new Alert(Alert.AlertType.INFORMATION,"User Credentials Saved Successfully.").show();
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();
            } else {
                new Alert(Alert.AlertType.ERROR,"User Credentials Not Saved.").show();
                throw new InvalidCredentialsException("Invalid Credentials..");
            }
        } catch (IOException|InvalidCredentialsException e) {
            throw new RuntimeException(e);
        }
    }

}
