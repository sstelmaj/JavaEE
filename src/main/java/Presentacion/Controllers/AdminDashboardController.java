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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
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
    private Button btnDescargar;
    @FXML
    private BorderPane graphsPane;
    @FXML
    private PieChart pieRatio;
    @FXML
    private LineChart lineRecord;
    @FXML
    private CategoryAxis lineXAxis;
    @FXML
    private NumberAxis lineYAxis;

    /**
     * Initializes the controller class.
     */
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnExportar.setOnAction(event -> mostrarVentanaExportar());
        btnImportar.setOnAction(event -> mostrarVentanaImportar());
        
        lineXAxis = new CategoryAxis();
        lineYAxis = new NumberAxis();
        
        lineRecord = new LineChart(lineXAxis, lineYAxis);
        graphsPane.setRight(lineRecord);
        pieRatio = new PieChart();
        pieRatio.setClockwise(true);
        pieRatio.setStartAngle(90);
        graphsPane.setLeft(pieRatio);
        
        loadChartsData();
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
    
    private void loadChartsData(){
        lineXAxis.setLabel("Fecha");
        lineYAxis.setLabel("Cantidad");
        
        lineRecord.setTitle("Publicaciones en 2023");
        ObservableList<XYChart.Series> lineRecordData = FXCollections.observableArrayList(
            new XYChart.Series("Errores Publicados", FXCollections.observableArrayList(
                new XYChart.Data("21/06", 52),
                new XYChart.Data("22/06", 66),
                new XYChart.Data("23/06", 43),
                new XYChart.Data("24/06", 50),
                new XYChart.Data("25/06", 15),
                new XYChart.Data("26/06", 35)
            )),
            new XYChart.Series("Soluciones Publicados", FXCollections.observableArrayList(
                new XYChart.Data("21/06", 10),
                new XYChart.Data("22/06", 8),
                new XYChart.Data("23/06", 7),
                new XYChart.Data("24/06", 10),
                new XYChart.Data("25/06", 5),
                new XYChart.Data("26/06", 17)
            )),
            new XYChart.Series("Usuarios registrados", FXCollections.observableArrayList(
                new XYChart.Data("21/06", 12),
                new XYChart.Data("22/06", 15),
                new XYChart.Data("23/06", 5),
                new XYChart.Data("24/06", 50),
                new XYChart.Data("25/06", 23),
                new XYChart.Data("26/06", 43)
            ))
        );
        lineRecord.setData(lineRecordData);
        
        ObservableList<PieChart.Data> pieRatioData = FXCollections.observableArrayList(
            new PieChart.Data("Errores Publicados", 600),
            new PieChart.Data("Soluciones Publicados", 400)
        );
        pieRatio.setData(pieRatioData);
        pieRatio.setTitle("Proporcion Error/Solucion");
    }
}
