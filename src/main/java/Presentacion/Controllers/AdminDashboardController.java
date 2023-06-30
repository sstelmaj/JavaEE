/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Controladores.ErrorController;
import Logica.Controladores.SolucionController;
import Logica.Controladores.UsuarioController;
import Logica.DTOs.CantidadPorFecha;
import Persistencia.Sesion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
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
    private Button btnDescargar;
    @FXML
    private AnchorPane graphsPane;
    @FXML
    private PieChart pieRatio;
    @FXML
    private LineChart lineRecord;
    @FXML
    private CategoryAxis lineXAxis;
    @FXML
    private NumberAxis lineYAxis;

    private AnchorPane panelContent;
    @FXML
    private AnchorPane anchorRoot;
    
    private boolean mostrarGraficasInicial = false;
    /**
     * Initializes the controller class.
     */
    public void setPanelContent(AnchorPane pane) {
        this.panelContent = pane;
        //anclamos el anchor de la vista al anchor content por el tema de la jerarquia
        panelContent.setRightAnchor(anchorRoot, 0.0);
        panelContent.setLeftAnchor(anchorRoot, 0.0);
        panelContent.setTopAnchor(anchorRoot, 0.0);
        panelContent.setBottomAnchor(anchorRoot, 0.0);
        //   anchor1.setPrefWidth(900);


    }
            
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
         graphsPane.getChildren().set(0, lineRecord);
         lineRecord.setLayoutX(580.0);
         if(Sesion.getInstance().isIsFullHD()){
             lineRecord.setLayoutX(760.0);
             lineRecord.setPrefWidth(700.0);
            lineRecord.setPrefHeight(710.0);

         }
    //    graphsPane.setRight(lineRecord);
        
        pieRatio = new PieChart();
        pieRatio.setClockwise(true);
        pieRatio.setStartAngle(90);
        graphsPane.getChildren().set(1, pieRatio);
        
        if(Sesion.getInstance().isIsFullHD()){
             pieRatio.setLayoutX(39.0);
             pieRatio.setPrefWidth(700.0);
             pieRatio.setPrefHeight(710.0);

         }
     //   graphsPane.setLeft(pieRatio);
         graphsPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
        loadPieChartData();
        loadLineChartData();
        
        
       
        pieRatio.setOnMouseEntered(event -> {
           loadPieChartData();
           
            this.mostrarGraficasInicial = true;
            System.out.println("Mouse está encima del panel");
        });
        
        lineRecord.setOnMouseEntered(event -> {
          
            loadLineChartData();
            this.mostrarGraficasInicial = true;
            System.out.println("Mouse está encima del panel");
        });
        
        
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
    
 
    
    private void loadPieChartData(){
        int cantidadErrores = ErrorController.getInstance().obtenerCantidadErrores();
        int cantidadSoluciones = SolucionController.getInstance().obtenerCantidadSoluciones();
        ObservableList<PieChart.Data> pieRatioData = FXCollections.observableArrayList(
            new PieChart.Data("Errores Publicados (" + cantidadErrores + ")", (double) cantidadErrores),
            new PieChart.Data("Soluciones Publicadas (" + cantidadSoluciones + ")", (double) cantidadSoluciones)
        );
        pieRatio.setData(pieRatioData);
        pieRatio.setTitle("Proporcion Error/Solucion");
    }
    private void loadLineChartData(){
        List<CantidadPorFecha> erroresSemanales = ErrorController.getInstance().obtenerCantidadErroresSemanalesPorFecha("2023-06-28");
        List<CantidadPorFecha> solucionesSemanales = SolucionController.getInstance().obtenerCantidadSolucionesSemanalesPorFecha("2023-06-28");
        List<CantidadPorFecha> usuariosSemanales = UsuarioController.getInstance().obtenerCantidadUsuariosSemanalesPorFecha("2023-06-28");
        
        ObservableList<XYChart.Series> lineRecordData = FXCollections.observableArrayList(
            new XYChart.Series("Errores Publicados", parseListToObservableData(erroresSemanales)),
            new XYChart.Series("Soluciones Publicadas", parseListToObservableData(solucionesSemanales)),
            new XYChart.Series("Usuarios registrados", parseListToObservableData(usuariosSemanales))
        );
        lineRecord.setData(lineRecordData);
        sortCategoryAxis(erroresSemanales, solucionesSemanales, usuariosSemanales);
    }
    
    private ObservableList<XYChart.Data> parseListToObservableData(List<CantidadPorFecha> lista){
        ObservableList<XYChart.Data> ol = FXCollections.observableArrayList();
        lista.forEach(item -> ol.add(new XYChart.Data(item.getStringFechaSubida(), item.getIntCantidad())));
        return ol;
    }
    private void sortCategoryAxis(List<CantidadPorFecha> ...listas){
        ObservableList<String> categories = FXCollections.observableArrayList();
        for (List<CantidadPorFecha> lista : listas) {
            for (CantidadPorFecha item : lista) {
                String fecha = item.getStringFechaSubida();
                if (!categories.contains(fecha)) 
                    categories.add(fecha);
            }
        }
        
        Collections.sort(categories);
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
