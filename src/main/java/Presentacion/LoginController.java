/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion;

import Logica.Controladores.UsuarioController;
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
    private Label statusLabel;

    @FXML
    public void login(ActionEvent event) {
        String mail = usernameField.getText();
        String password = passwordField.getText();
        
        if (UsuarioController.getInstance().iniciarSesion(mail, password) == true){
            statusLabel.setText("Has iniciado sesión !!");
        } else {
            statusLabel.setText("Mail o contraseña incorrectos !!");
        }
    }
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }  
    
}
