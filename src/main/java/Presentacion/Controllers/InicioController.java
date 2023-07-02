/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.AjustesUsuario;
import Logica.Clases.Usuario;
import Persistencia.Conexion;
import Persistencia.Sesion;
import Presentacion.Main;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author joaco
 */
public class InicioController implements Initializable {

    @FXML
    private AnchorPane root;
    
    private AnchorPane panelContent;
    @FXML
    private ImageView imagen;
    @FXML
    private AnchorPane anchorImage;
    @FXML
    private AnchorPane anchorBotones;
    @FXML
    private Button botonError;
    @FXML
    private Button botonSolucion;
    @FXML
    private Button botonCerrarSesion;
    @FXML
    private Button botonAjustes;
    
    private DashboardController dashboardController = DashboardController.getInstance();
    @FXML
    private AnchorPane anchorConfig;
    @FXML
    private RadioButton ingles;
    @FXML
    private ToggleGroup idioma;
    @FXML
    private RadioButton espaniol;
    @FXML
    private RadioButton clara;
    @FXML
    private ToggleGroup apariencia;
    @FXML
    private RadioButton oscura;
    @FXML
    private Button botonGuardar;
    @FXML
    private RadioButton normal;
    @FXML
    private ToggleGroup resolucion;
    @FXML
    private RadioButton fullhd;
    
    private AjustesUsuario ajustesModificar = Sesion.getInstance().getUsuario().getAjustes();
    
    private Usuario usuarioSesion = Sesion.getInstance().getUsuario();
    
    private boolean mostrarAjustes = true;
    
     private static InicioController instancia;
    
    public static InicioController getInstance() {
        
        return instancia;
    }
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnDesactivar;
    @FXML
    private Label txtIdioma;
    @FXML
    private Label txtApariencia;
    @FXML
    private Label txtResolucion;
    @FXML
    private Label txtAjustes;

    public AnchorPane getRoot() {
        return root;
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }
   
    
    public void setPanelContent(AnchorPane panelContent) {
        this.panelContent = panelContent;
        
        panelContent.setRightAnchor(root,0.0);
        panelContent.setLeftAnchor(root,0.0);
        panelContent.setTopAnchor(root,0.0);
        panelContent.setBottomAnchor(root,0.0);
        
        
// resize based on the scene
        anchorConfig.setStyle("-fx-background-color: #c6c6c6; -fx-background-radius: 10px;");
        
         if(Sesion.getInstance().isIsDarkMode()){
            System.out.println("tiene modo oscuro");
            //quedaria seteado aca para que quede con el mismo fondo de la imagen
            root.setStyle("-fx-background-color: #14151e; ");
            root.getStyleClass().add("contentGeneral");         
            
        }else{
            root.setStyle("-fx-background-color: #ffffff; ");
            root.getStyleClass().add("contentGeneral");
            Image imageBackground = new Image("/recursos/MaleLogsWBMesa_de_trabajo_Blanca.png",1000.0, 1000.0, true, true);
            // Ruta de la imagen
            ImageView imagen = new ImageView(imageBackground);
            imagen.setFitHeight(376.0);
            imagen.setFitWidth(378.0);
            imagen.setPickOnBounds(true);
            imagen.setPreserveRatio(true);
            
            anchorImage.getChildren().clear();
            this.imagen = imagen;
            anchorImage.getChildren().add(imagen);
        }
       
        root.setTopAnchor(anchorImage, 14.0);
        root.setBottomAnchor(anchorImage, 231.0);
        root.setLeftAnchor(anchorImage, 453.0);
        root.setRightAnchor(anchorImage, 453.0);
            
        imagen.fitWidthProperty().bind(anchorImage.widthProperty());
        imagen.fitHeightProperty().bind(anchorImage.heightProperty());
        
        System.out.println(imagen.getImage().getWidth());
        System.out.println(imagen.getImage().getHeight());
        
        Sesion sesion = Sesion.getInstance();
        
       
        
        
        if(sesion.isIsFullHD()){
             anchorBotones.getStyleClass().add("paneBotonesLarge");
        }else{
            anchorBotones.getStyleClass().remove("paneBotonesLarge");
        }
              
               
            
               
       
       
        
       
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.instancia = this;
        
        //..\recursos\MaleLogsDBMesa_de_trabajo_1.png
        
        Image image = new Image("/recursos/bug.png",150.0, 121.0, true, true);
        
        botonError.setGraphic(new ImageView(image));
        
        Image imageLlave = new Image("/recursos/llave1.png",121.0, 110.0, true, true);
        
        botonSolucion.setGraphic(new ImageView(imageLlave));
        
        Image imageAjustes = new Image("/recursos/engranaje.png",100.0, 110.0, true, true);
        
        botonAjustes.setGraphic(new ImageView(imageAjustes));
        
        Image imageCerrar = new Image("/recursos/cerrar.png",100.0, 110.0, true, true);
        
        botonCerrarSesion.setGraphic(new ImageView(imageCerrar));
        
        
       btnDesactivar.setStyle("-fx-background-color: #d3d3d3; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10; -fx-text-fill: #ab120a;");

        // Establecer estilo al pasar el ratón por encima
        btnDesactivar.setOnMouseEntered(event -> {
            btnDesactivar.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10; -fx-text-fill: #ab120a;");
        });

        // Establecer estilo al quitar el ratón de encima
        btnDesactivar.setOnMouseExited(event -> {
            btnDesactivar.setStyle("-fx-background-color: #d3d3d3; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10; -fx-text-fill: #ab120a;");
        });
        
        Usuario user =  Sesion.getInstance().getUsuario();
        
    }    

    @FXML
    private void clickError(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/paginaErrores.fxml"));
        Parent nuevaVista = loader.load();
        dashboardController.getAnchorPane().getChildren().setAll(nuevaVista);
    }

    @FXML
    private void clickSolucion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("/fxml/paginaSoluciones.fxml"));
        Parent nuevaVista = loader.load();
        dashboardController.getAnchorPane().getChildren().setAll(nuevaVista);
    }

    @FXML
    private void clickCerrar(ActionEvent event) throws IOException {
             Platform.runLater(() -> {
            try {
                // Reiniciar el estado de la aplicación
                // ...
                
                // Cerrar la ventana actual
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();

                
                // Volver a iniciar la aplicación
                Main mainApp = new Main();
                mainApp.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    
           
    }

    @FXML
    private void clickAjustes(ActionEvent event) throws IOException {
       


       anchorConfig.setVisible(true);
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
        
         if(fullhd.isSelected()){
            Sesion.getInstance().setIsFullHD(true);
            this.ajustesModificar.setFullHD(true);
            anchorBotones.getStyleClass().add("paneBotonesLarge");
        }else if(normal.isSelected()){
            Sesion.getInstance().setIsFullHD(false);
            this.ajustesModificar.setFullHD(false);
            anchorBotones.getStyleClass().remove("paneBotonesLarge");
        }
        
        
        if(oscura.isSelected()){
            Sesion.getInstance().setIsDarkMode(true);
            this.ajustesModificar.setDarkMode(true);
             System.out.println("tiene modo oscuro");
            root.setStyle("-fx-background-color: #14151e; -fx-background-radius: 10px;");
        //    anchorConfig.setStyle("-fx-background-color: #c6c6c6; -fx-background-radius: 10px;");
            DashboardController.getInstance().setDarkMode(true);
            
            Image imageBackground = new Image("/recursos/MaleLogsDBMesa_de_trabajo_1.png",1000.0, 1000.0, true, true);
            // Ruta de la imagen
            ImageView imagen = new ImageView(imageBackground);
            imagen.setFitHeight(376.0);
            imagen.setFitWidth(378.0);
            imagen.setPickOnBounds(true);
            imagen.setPreserveRatio(true);
            
            anchorImage.getChildren().clear();
            this.imagen = imagen;
            anchorImage.getChildren().add(imagen);
            
             root.setTopAnchor(anchorImage, 14.0);
            root.setBottomAnchor(anchorImage, 231.0);
            root.setLeftAnchor(anchorImage, 453.0);
            root.setRightAnchor(anchorImage, 453.0);

            imagen.fitWidthProperty().bind(anchorImage.widthProperty());
            imagen.fitHeightProperty().bind(anchorImage.heightProperty());
            
        }else if (clara.isSelected()){
             
            Sesion.getInstance().setIsDarkMode(false);
            this.ajustesModificar.setDarkMode(false);
            DashboardController.getInstance().setDarkMode(false);
           
            root.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
        //    anchorConfig.setStyle("-fx-background-color: #c6c6c6; -fx-background-radius: 10px;");
            Image imageBackground = new Image("/recursos/MaleLogsWBMesa_de_trabajo_Blanca.png",1000.0, 1000.0, true, true);
            // Ruta de la imagen
            ImageView imagen = new ImageView(imageBackground);
            imagen.setFitHeight(376.0);
            imagen.setFitWidth(378.0);
            imagen.setPickOnBounds(true);
            imagen.setPreserveRatio(true);
            
            anchorImage.getChildren().clear();
            this.imagen = imagen;
            anchorImage.getChildren().add(imagen);
            
             root.setTopAnchor(anchorImage, 14.0);
            root.setBottomAnchor(anchorImage, 231.0);
            root.setLeftAnchor(anchorImage, 453.0);
            root.setRightAnchor(anchorImage, 453.0);

            imagen.fitWidthProperty().bind(anchorImage.widthProperty());
            imagen.fitHeightProperty().bind(anchorImage.heightProperty());
            
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
//                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                        alert.setTitle("Información");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Se ha persistido la configuración con exito!");
//                        alert.showAndWait();
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
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que deseas desactivar la cuenta?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Acciones a realizar si el usuario selecciona "Sí"
            System.out.println("El usuario seleccionó 'Sí'.");
            
            try { 
                usuarioSesion.setActive(false);
                Conexion.getInstance().merge(usuarioSesion);
                  
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                        alert2.setTitle("Información");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Se ha desactivado la cuenta con exito!");
                        alert2.showAndWait();
                        
                          Platform.runLater(() -> {
                            try {
                                // Reiniciar el estado de la aplicación
                                // ...

                                // Cerrar la ventana actual
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.close();


                                // Volver a iniciar la aplicación
                                Main mainApp = new Main();
                                mainApp.start(new Stage());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        
                } catch (Exception e) {
                    // Manejo de la excepción
                    e.printStackTrace();
                }
        } else {
            // Acciones a realizar si el usuario selecciona "No" o cierra el cuadro de diálogo
            System.out.println("El usuario seleccionó 'No' o cerró el cuadro de diálogo.");
        }
            }
    
}
