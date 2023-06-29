/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.AjustesUsuario;
import Logica.Clases.Usuario;
import Persistencia.Conexion;
import Persistencia.Sesion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author joaco
 */
public class AjustesController implements Initializable {

    @FXML
    private RadioButton fullhd;
    @FXML
    private ToggleGroup resolucion;
    @FXML
    private RadioButton normal;
    @FXML
    private Button botonGuardar;
    @FXML
    private RadioButton oscura;
    @FXML
    private ToggleGroup apariencia;
    @FXML
    private RadioButton clara;
    @FXML
    private RadioButton espaniol;
    @FXML
    private ToggleGroup idioma;
    @FXML
    private RadioButton ingles;
    
    private AjustesUsuario ajustesModificar = Sesion.getInstance().getUsuario().getAjustes();
    
    private Usuario usuarioSesion = Sesion.getInstance().getUsuario();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickFullHD(ActionEvent event) {
      
    }

    @FXML
    private void clickNormal(ActionEvent event) {
      
    }

    @FXML
    private void clickGuardar(ActionEvent event) {
        if(fullhd.isSelected()){
            Sesion.getInstance().setIsFullHD(true);
            this.ajustesModificar.setFullHD(true);
           
        }else if(normal.isSelected()){
            Sesion.getInstance().setIsFullHD(false);
            this.ajustesModificar.setFullHD(false);
            
        }
        
        
        if(oscura.isSelected()){
            Sesion.getInstance().setIsDarkMode(true);
            this.ajustesModificar.setDarkMode(true);
        }else if (clara.isSelected()){
            Sesion.getInstance().setIsDarkMode(false);
            this.ajustesModificar.setDarkMode(false);
        }
        
        
        try { 
                 this.usuarioSesion.setAjustes(ajustesModificar);
                Conexion.getInstance().merge(usuarioSesion);
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Información");
                        alert.setHeaderText(null);
                        alert.setContentText("Se ha persistido la configuración con exito!");
                        alert.showAndWait();
                } catch (Exception e) {
                    // Manejo de la excepción
                    e.printStackTrace();
                }
    }
    
}
