/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label statusLabel;

    @FXML
    public void login(ActionEvent event) {
        String nombre = usernameField.getText();
        String contrasenia = passwordField.getText();

        // Aquí puedes validar las credenciales del usuario y hacer otras tareas relacionadas con el inicio de sesión
        statusLabel.setText("Inicio de sesión exitoso!");
    }
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
}
