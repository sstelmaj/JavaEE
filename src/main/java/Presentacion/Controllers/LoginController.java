/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.Usuario;
import Logica.Controladores.UsuarioController;
import Persistencia.Sesion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    private Label statusLabel;
    @FXML
    private ImageView imageView;
    
    public void login(ActionEvent event) throws IOException {
      
        usernameField.setText("joaquin.maidana@estudiantes.utec.edu.uy");
        passwordField.setText("Passw0rd");
        
        String mail = usernameField.getText();
        String password = passwordField.getText();
             
        if (UsuarioController.getInstance().iniciarSesion(mail, password) == true){
         Sesion sesion = Sesion.getInstance();
         sesion.setUser(mail);
         Usuario user = UsuarioController.getInstance().obtenerUsuario(mail);
         sesion.setUsuario(user);
         
         if(!user.isActive()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de inicio de sesión");
                alert.setHeaderText("Inicio de sesión fallido");
                alert.setContentText("El usuario con el que intentas ingresar no está activo actualmente. Contactate con un administrador.");
                alert.showAndWait();
                usernameField.clear();
                passwordField.clear();
                
            } else {
         
            if(user.getPerfil().getNombre().equals("Administrador")){
                System.out.println("Inicia como admin");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminDashboard.fxml"));
                 Parent root = loader.load();

                 // Crea una nueva escena y asigna la escena al escenario
                 Scene scene = new Scene(root);
                 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.setTitle("Admin Dashboard");
                 stage.setMinHeight(720.0);
                 stage.setMinWidth(1260.0);
                 stage.setScene(scene);
                 //stage.setFullScreen(true);
                 stage.show();
                 stage.centerOnScreen();
            }else{
                //se crea antes para que no sea null en el dash
                 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 sesion.setStagePrincipal(stage);
                 
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
                 Parent root = loader.load();

                 // Crea una nueva escena y asigna la escena al escenario
                 Scene scene = new Scene(root);
                
                 stage.setTitle("Dashboard");
                
                 
                 if(user.getAjustes().isFullHD()){
                     sesion.setIsFullHD(true);
                 }else{
                      sesion.setIsFullHD(false);
                 }
                 
                 
                 //stage.setFullScreen(true);
                  stage.setResizable(false);
                 stage.setScene(scene);
                 stage.show();
                 stage.centerOnScreen();
                  DashboardController dashboardController=(DashboardController)loader.getController();
                    dashboardController.setStage(stage);
            } 
         }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de inicio de sesión");
            alert.setHeaderText("Inicio de sesión fallido");
            alert.setContentText("El usuario o la contraseña son incorrectos. Por favor, inténtelo de nuevo.");
            alert.showAndWait();
          
            usernameField.clear();
            passwordField.clear();
        }
        
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }  
    
}
