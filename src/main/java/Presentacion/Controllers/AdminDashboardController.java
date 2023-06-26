/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private Button btnImportar;
    @FXML
    private Button btnExportar;
    @FXML
    private Button btnUsuarios;
    @FXML
    private PieChart pieUsuarios;
    @FXML
    private Button btnDescargar;
    @FXML
    private AreaChart<?, ?> areaRegistros;

    
    /**
     * Initializes the controller class.
     */
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnExportar.setOnAction(event -> mostrarVentanaExportar());
        btnImportar.setOnAction(event -> mostrarVentanaImportar());
    }
    
    private void mostrarVentanaExportar() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/exportarDatos.fxml"));
            Parent popupRoot = fxmlLoader.load();
            ExportarDatosController exportarDatosController = fxmlLoader.getController();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(btnExportar.getScene().getWindow());
            popupStage.setTitle("Exportar...");
            popupStage.setResizable(false);
            Scene scene = new Scene(popupRoot);
            popupStage.setScene(scene);
            popupStage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mostrarVentanaImportar() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/importarDatos.fxml"));
            Parent popupRoot = fxmlLoader.load();
            ImportarDatosController importarDatosController = fxmlLoader.getController();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(btnImportar.getScene().getWindow());
            popupStage.setTitle("Importar...");
            popupStage.setResizable(false);
            Scene scene = new Scene(popupRoot);
            popupStage.setScene(scene);
            popupStage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void gotoAdminUsuarios(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminGestionarUsuarios.fxml"));
            Parent nuevaVista = loader.load();
            
            Scene scene = new Scene(nuevaVista);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
                    
            
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    /*
    * 
    */
    
}
