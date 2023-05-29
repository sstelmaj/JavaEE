/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private long id;
    private AnchorPane apPrincipal;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void setDatos(String titulo, String descripcion,long id){
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.id=id;
    }
    
    public void initialize(AnchorPane ap){
        txtTitulo.setText(titulo);
        txtDescripcion.setText(descripcion);
        apPrincipal=ap;
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
                subfileController.setEscenaPrevia(btnDetalle.getScene());
                subfileController.initialize();
                
                Scene s=new Scene(subfileRoot);
                Stage stage=(Stage)btnDetalle.getScene().getWindow();
                stage.setScene(s);
           
        }catch (IOException e) {
                e.printStackTrace();
            }  
    }
        
    
}
