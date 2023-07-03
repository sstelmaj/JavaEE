/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.AjustesUsuario;
import Logica.Clases.Usuario;
import Logica.Controladores.ErrorController;
import Logica.Controladores.SolucionController;
import Logica.Controladores.UsuarioController;
import Logica.DTOs.CantidadPorFecha;
import Persistencia.Conexion;
import Persistencia.Sesion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.util.Duration;
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
    @FXML
    private Button botonAjustes;
    @FXML
    private AnchorPane anchorConfig;
    @FXML
    private RadioButton ingles;
    @FXML
    private ToggleGroup idioma;
    @FXML
    private RadioButton espaniol;
    @FXML
    private Label txtIdioma;
    @FXML
    private RadioButton clara;
    @FXML
    private ToggleGroup apariencia;
    @FXML
    private RadioButton oscura;
    @FXML
    private Label txtApariencia;
    @FXML
    private Button botonGuardar;
    @FXML
    private RadioButton normal;
    @FXML
    private ToggleGroup resolucion;
    @FXML
    private RadioButton fullhd;
    @FXML
    private Label txtResolucion;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label txtAjustes;
    @FXML
    private Button btnDesactivar;

    private AjustesUsuario ajustesModificar = Sesion.getInstance().getUsuario().getAjustes();

    private Usuario usuarioSesion = Sesion.getInstance().getUsuario();

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

        anchorConfig.setStyle("-fx-background-color: #c6c6c6; -fx-background-radius: 10px;");
        Image imageAjustes = new Image("/recursos/engranaje.png", 18.0, 18.0, true, true);

        botonAjustes.setGraphic(new ImageView(imageAjustes));

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
       if (Sesion.getInstance().isIsFullHD()) {
            lineRecord.setLayoutX(760.0);
            lineRecord.setPrefWidth(700.0);
            lineRecord.setPrefHeight(710.0);

            Platform.runLater(() -> {
                double ancho = anchorRoot.getWidth();
                double altura = anchorRoot.getHeight();
                System.out.println(ancho);
                System.out.println(altura);
            });
        } else {
            Platform.runLater(() -> {
                double ancho = anchorRoot.getWidth();
                double altura = anchorRoot.getHeight();
                System.out.println("an"+ancho);
                System.out.println(altura);
            });
        }
        //    graphsPane.setRight(lineRecord);

        pieRatio = new PieChart();
        pieRatio.setClockwise(true);
        pieRatio.setStartAngle(90);
        graphsPane.getChildren().set(1, pieRatio);

        if (Sesion.getInstance().isIsFullHD()) {
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

    private void loadPieChartData() {
        int cantidadErrores = ErrorController.getInstance().obtenerCantidadErrores();
        int cantidadSoluciones = SolucionController.getInstance().obtenerCantidadSoluciones();
        ObservableList<PieChart.Data> pieRatioData = FXCollections.observableArrayList(
                new PieChart.Data("Errores Publicados (" + cantidadErrores + ")", (double) cantidadErrores),
                new PieChart.Data("Soluciones Publicadas (" + cantidadSoluciones + ")", (double) cantidadSoluciones)
        );
        pieRatio.setData(pieRatioData);
        pieRatio.setTitle("Proporcion Error/Solucion");

        double pieL = pieRatio.getLayoutX();
        double pieW = pieRatio.getWidth();
        double pieH = pieRatio.getHeight();

        System.out.println("pie lay" + pieL);
        System.out.println("pie w" + pieW);
        System.out.println("pie h" + pieH);
    }

    private void loadLineChartData() {
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

        double lineL = lineRecord.getLayoutX();
        double lineW = lineRecord.getWidth();
        double lineH = lineRecord.getHeight();

        System.out.println("line L" + lineL);
        System.out.println("line w" + lineW);
        System.out.println("line h" + lineH);
    }

    private ObservableList<XYChart.Data> parseListToObservableData(List<CantidadPorFecha> lista) {
        ObservableList<XYChart.Data> ol = FXCollections.observableArrayList();
        lista.forEach(item -> ol.add(new XYChart.Data(item.getStringFechaSubida(), item.getIntCantidad())));
        return ol;
    }

    private void sortCategoryAxis(List<CantidadPorFecha>... listas) {
        ObservableList<String> categories = FXCollections.observableArrayList();
        for (List<CantidadPorFecha> lista : listas) {
            for (CantidadPorFecha item : lista) {
                String fecha = item.getStringFechaSubida();
                if (!categories.contains(fecha)) {
                    categories.add(fecha);
                }
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
        
        boolean isFullHD = Sesion.getInstance().isIsFullHD();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar graficas");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image", "*.png"));

   //     Stage stage = (Stage) btnDescargar.getScene().getWindow();
   //     Stage stage = (Stage) anchorRoot.getScene().getWindow();
   
        Stage stage = Sesion.getInstance().getStagePrincipal();
        File file = fileChooser.showSaveDialog(stage);
       SnapshotParameters params = new SnapshotParameters();
     


     
        WritableImage image = graphsPane.snapshot(null, null);
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
        
        if(isFullHD){
            Sesion.getInstance().setIsFullHD(false);
            Sesion.getInstance().setIsFullHD(true);
        }else{
            Sesion.getInstance().setIsFullHD(true);
            Sesion.getInstance().setIsFullHD(false);
        }
           

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText("Las graficas se han descargado exitosamente.");
        
        alert.showAndWait();
        

      
        
        anchorRoot.setPrefWidth(1666.0);
        anchorRoot.setPrefWidth(877.0);
       anchorRoot.requestLayout();
    }

    @FXML
    private void clickAjustes(ActionEvent event) {
        anchorConfig.setVisible(true);
        
        if(Sesion.getInstance().isIsFullHD()){
            fullhd.setSelected(true);
        }else{
            normal.setSelected(true);
        }
        
        if(Sesion.getInstance().isIsDarkMode()){
            oscura.setSelected(true);
        }else{
            clara.setSelected(true);
        }
        
        
        
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), anchorConfig);
        fadeTransition.setFromValue(0); // Opacidad inicial de 0 (invisible)
        fadeTransition.setToValue(1); // Opacidad final de 1 (totalmente visible)

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), anchorConfig);
        translateTransition.setFromY(-anchorConfig.getHeight()); // Posición inicial fuera de la pantalla, arriba
        translateTransition.setToY(0); // Posición final en la posición original, abajo

        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, translateTransition);
        parallelTransition.setCycleCount(1); // Ejecutar la animación solo una vez

        parallelTransition.play();
    }

    @FXML
    private void clickGuardar(ActionEvent event) {
        if (fullhd.isSelected()) {
            Sesion.getInstance().setIsFullHD(true);
            this.ajustesModificar.setFullHD(true);

            lineRecord.setLayoutX(760.0);
            lineRecord.setPrefWidth(700.0);
            lineRecord.setPrefHeight(710.0);

            pieRatio.setLayoutX(39.0);
            pieRatio.setPrefWidth(700.0);
            pieRatio.setPrefHeight(710.0);

            // anchorBotones.getStyleClass().add("paneBotonesLarge");
        } else if (normal.isSelected()) {
            Sesion.getInstance().setIsFullHD(false);
            this.ajustesModificar.setFullHD(false);

            pieRatio.setLayoutX(39.0);
            pieRatio.setPrefWidth(500.0);
            pieRatio.setPrefHeight(400.0);

            lineRecord.setLayoutX(580.0);
            lineRecord.setPrefWidth(500.0);
            lineRecord.setPrefHeight(400.0);

            //anchorBotones.getStyleClass().remove("paneBotonesLarge");
        }

        if (oscura.isSelected()) {
            Sesion.getInstance().setIsDarkMode(true);
            this.ajustesModificar.setDarkMode(true);

            AdminDashboardRootController.getInstance().setDarkMode(true);

        } else if (clara.isSelected()) {

            Sesion.getInstance().setIsDarkMode(false);
            this.ajustesModificar.setDarkMode(false);
            AdminDashboardRootController.getInstance().setDarkMode(false);

        }
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), anchorConfig);
        fadeTransition.setFromValue(1); // Opacidad inicial de 1 (totalmente visible)
        fadeTransition.setToValue(0); // Opacidad final de 0 (invisible)

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), anchorConfig);
        translateTransition.setFromY(0); // Posición inicial en la posición original, abajo
        translateTransition.setToY(-anchorConfig.getHeight()); // Posición final fuera de la pantalla, arriba

        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, translateTransition);
        parallelTransition.setCycleCount(1); // Ejecutar la animación solo una vez

        parallelTransition.play();

        try {
            this.usuarioSesion.setAjustes(ajustesModificar);
            Conexion.getInstance().merge(usuarioSesion);

        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace();
        }
    }

    @FXML
    private void clickNormal(ActionEvent event) {
    }

    @FXML
    private void clickFullHD(ActionEvent event) {
    }

    @FXML
    private void cancelarAjustes(ActionEvent event) {
        
         FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), anchorConfig);
        fadeTransition.setFromValue(1); // Opacidad inicial de 1 (totalmente visible)
        fadeTransition.setToValue(0); // Opacidad final de 0 (invisible)

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), anchorConfig);
        translateTransition.setFromY(0); // Posición inicial en la posición original, abajo
        translateTransition.setToY(-anchorConfig.getHeight()); // Posición final fuera de la pantalla, arriba

        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, translateTransition);
        parallelTransition.setCycleCount(1); // Ejecutar la animación solo una vez

        parallelTransition.play();
    }

    @FXML
    private void clickDesactivar(ActionEvent event) {
    }

}
