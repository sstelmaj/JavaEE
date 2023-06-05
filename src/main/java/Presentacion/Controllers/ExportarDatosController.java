package Presentacion.Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *| 
 * @author Usuario
 */
public class ExportarDatosController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }  

    @FXML
    private Button btnError;

    @FXML
    private Button btnSolucion;

    // Métodos de evento para los botones
    @FXML
    public void exportErrores(ActionEvent event) {
        // Lógica para exportar la tabla 1 en formato Excel
        closePopup();
    }

    @FXML
    public void exportSoluciones(ActionEvent event) {
        // Lógica para exportar la tabla 2 en formato Excel
        closePopup();
    }

    // Método para cerrar la ventana emergente
    private void closePopup() {
        Stage stage = (Stage) btnError.getScene().getWindow();
        stage.close();
    }
    
}
