/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.Etiqueta;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author paulo
 */
public class BusquedaSolucionController implements Initializable {

    @FXML
    private Text txtTitulo;
    @FXML
    private Text txtDescripcion;
    @FXML
    private Button btnDetalle;
    private String titulo,descripcion;
    DashboardController dashboard;
    private long id;
    @FXML
    private HBox hBoxEtiquetas;
    private List<Etiqueta> etiquetas;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void setDatos(String titulo, String descripcion,long id, DashboardController dash, List<Etiqueta>etiquetas){
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.id=id;
        this.etiquetas=etiquetas;
        this.dashboard=dash;
    }
    
    public void initialize(){
        txtTitulo.setText(titulo);
        txtDescripcion.setText(descripcion);
        hBoxEtiquetas.setSpacing(6);
        for(Etiqueta e: etiquetas){
            /*Text etiqueta=new Text(e.getNombre());
            etiqueta.setFill(Color.BLACK);*/
            Label etiqueta=new Label(e.getNombre());
            etiqueta.getStyleClass().add("etiquetas");
            hBoxEtiquetas.getChildren().add(etiqueta);
        }
        
    }

    @FXML
    private void DetalleSolucion(MouseEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/detalleSolucion.fxml"));
                Parent subfileRoot = loader.load();
                //apPrincipal.getChildren().removeAll();
                //apPrincipal.getChildren().addAll(subfileRoot);
                
                DetalleSolucionController subfileController = loader.getController();
                
                subfileController.setId(id);
                //subfileController.setEscenaPrevia(btnDetalle.getScene());
                subfileController.initialize();
                this.dashboard.setVista(subfileRoot);
                
                //Scene s=new Scene(subfileRoot);
                //Stage stage=(Stage)btnDetalle.getScene().getWindow();
                //stage.setScene(s);
           
        }catch (IOException e) {
                e.printStackTrace();
            }  
    }
        
    
}
