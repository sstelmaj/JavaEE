/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Controladores.ErrorController;
import Logica.Controladores.SolucionController;
import Logica.Controladores.UsuarioController;
import Logica.DTOs.CantidadPorMes;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

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
        lineXAxis.setLabel("Fecha");
        lineYAxis = new NumberAxis();
        lineYAxis.setLabel("Cantidad");
        
        lineRecord = new LineChart(lineXAxis, lineYAxis);
        lineRecord.setTitle("Publicaciones en 2023");
        graphsPane.setRight(lineRecord);
        
        pieRatio = new PieChart();
        pieRatio.setClockwise(true);
        pieRatio.setStartAngle(90);
        graphsPane.setLeft(pieRatio);
        
        loadPieChartData();
        loadLineChartData();
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
            popupStage.setOnHidden(e -> {
                loadPieChartData();
                loadLineChartData();
            });
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
    
    private void loadPieChartData(){
        int cantidadErrores = ErrorController.getInstance().obtenerCantidadErrores();
        int cantidadSoluciones = SolucionController.getInstance().obtenerCantidadSoluciones();
        ObservableList<PieChart.Data> pieRatioData = FXCollections.observableArrayList(
            new PieChart.Data("Errores Publicados (" + cantidadErrores + ")", (double) cantidadErrores),
            new PieChart.Data("Soluciones Publicados (" + cantidadSoluciones + ")", (double) cantidadSoluciones)
        );
        pieRatio.setData(pieRatioData);
        pieRatio.setTitle("Proporcion Error/Solucion");
    }
    private void loadLineChartData(){
        List<CantidadPorMes> erroresSemanales = ErrorController.getInstance().obtenerCantidadErroresMensuales();
        List<CantidadPorMes> solucionesSemanales = SolucionController.getInstance().obtenerCantidadSolucionesMensuales();
        List<CantidadPorMes> usuariosSemanales = UsuarioController.getInstance().obtenerCantidadUsuariosMensuales();
        
        ObservableList<XYChart.Series> lineRecordData = FXCollections.observableArrayList(
            new XYChart.Series("Errores Publicados", parseListToObservableData(erroresSemanales)),
            new XYChart.Series("Soluciones Publicados", parseListToObservableData(solucionesSemanales)),
            new XYChart.Series("Usuarios registrados", parseListToObservableData(usuariosSemanales))
        );
        lineRecord.setData(lineRecordData);
        sortCategoryAxis(erroresSemanales, solucionesSemanales, usuariosSemanales);
    }
    
    private ObservableList<XYChart.Data> parseListToObservableData(List<CantidadPorMes> lista){
        ObservableList<XYChart.Data> ol = FXCollections.observableArrayList();
        lista.forEach(item -> ol.add(new XYChart.Data(item.getMes(), item.getCantidad())));
        return ol;
    }
    private void sortCategoryAxis(List<CantidadPorMes> ...listas){
        ObservableList<String> categories = FXCollections.observableArrayList();
        for (List<CantidadPorMes> lista : listas) {
            for (CantidadPorMes item : lista) {
                if (!categories.contains(item.getMes())) 
                    categories.add(item.getMes());
            }
        }
        
        categories.forEach(System.out::println);
        var XAxis = (CategoryAxis) lineRecord.getXAxis();
        XAxis.setCategories(categories);
        XAxis.setAutoRanging(true);
    }

    @FXML
    private void handleDownloadGraphs(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar graficas");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image", "*.png"));

        Stage stage = (Stage) btnDescargar.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        WritableImage image = graphsPane.snapshot(null, null);
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText("Las graficas se han descargado exitosamente.");
        alert.showAndWait();
    }
}
