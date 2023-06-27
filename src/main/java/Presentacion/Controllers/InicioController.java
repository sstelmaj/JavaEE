/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.AjustesUsuario;
import Logica.Clases.Usuario;
import Persistencia.Sesion;
import Presentacion.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
   

    public void setPanelContent(AnchorPane panelContent) {
        this.panelContent = panelContent;
        
        panelContent.setRightAnchor(root,0.0);
        panelContent.setLeftAnchor(root,0.0);
        panelContent.setTopAnchor(root,0.0);
        panelContent.setBottomAnchor(root,0.0);
        
        
// resize based on the scene
       
        root.setTopAnchor(anchorImage, 14.0);
        root.setBottomAnchor(anchorImage, 231.0);
        root.setLeftAnchor(anchorImage, 453.0);
        root.setRightAnchor(anchorImage, 453.0);
            
        imagen.fitWidthProperty().bind(anchorImage.widthProperty());
        imagen.fitHeightProperty().bind(anchorImage.heightProperty());
        
        
        var widthObservable = Bindings.selectDouble(panelContent.sceneProperty(), "width");

       
        widthObservable.addListener((obs, oldWidth, newWidth) -> {
            double panelWidth = newWidth.doubleValue();
            
             System.out.println(panelWidth);
             System.out.println(panelContent.getWidth());
            
            if (panelWidth == 1920.0) {
               anchorBotones.getStyleClass().add("paneBotonesLarge");
               
            } else if (panelWidth == 1536.0){
                System.out.println("es aca");
               anchorBotones.getStyleClass().remove("paneBotonesLarge");
                
            }
        });
       
        
       
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        root.setStyle("-fx-background-color: #14151e;");
        
        //..\recursos\MaleLogsDBMesa_de_trabajo_1.png
        
        Image image = new Image("/recursos/bug.png",150.0, 121.0, true, true);
        
        botonError.setGraphic(new ImageView(image));
        
        Image imageLlave = new Image("/recursos/llave1.png",121.0, 110.0, true, true);
        
        botonSolucion.setGraphic(new ImageView(imageLlave));
        
        Image imageAjustes = new Image("/recursos/engranaje.png",100.0, 110.0, true, true);
        
        botonAjustes.setGraphic(new ImageView(imageAjustes));
        
        Image imageCerrar = new Image("/recursos/cerrar.png",100.0, 110.0, true, true);
        
        botonCerrarSesion.setGraphic(new ImageView(imageCerrar));
        
        
        
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
        FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("/fxml/ajustes.fxml"));
        Parent nuevaVista = loader.load();
        dashboardController.getAnchorPane().getChildren().setAll(nuevaVista);
    }
    
}
