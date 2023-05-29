/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Persistencia.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author joaco
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane contentAPane;
    @FXML
    private ComboBox<String> selectorVista;

    private Map<String, String> vistas = new HashMap<>(); // mapa de vistas
    
    private String fxml;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/subirError.fxml"));
            Parent nuevaVista = loader.load();
            SubirErrorController subirErrorController = (SubirErrorController)loader.getController();
            subirErrorController.setPantalla("modificar");
            contentAPane.getChildren().setAll(nuevaVista);
     
        }catch(IOException e){
            System.out.println(e.getMessage());
        
        }
          
          
          
        vistas.put("Subir Error", "/fxml/subirError.fxml");
        vistas.put("Vista 2", "/fxml/Vista2.fxml");
        vistas.put("Admin", "/fxml/AdminDashboard.fxml");
        
        // Agregar elementos al ComboBox
        selectorVista.getItems().addAll("Subir Error", "Vista 2", "Admin");
        vistas.put("Detalle Solucion", "/fxml/detalleSolucion.fxml");
        
        // Agregar elementos al ComboBox
        selectorVista.getItems().addAll("Subir Error", "Vista 2", "Admin","Detalle Solucion");
        
        // Listener para cambio de selección en el ComboBox
        selectorVista.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             fxml = vistas.get(newValue); // Obtener ruta del fxml correspondiente al valor seleccionado
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxml));
                Parent nuevaVista = loader.load();
                
                if(fxml.equals("/fxml/subirError.fxml")){
                    SubirErrorController subirErrorController = (SubirErrorController)loader.getController();
                    List<Logica.Clases.Error> errores = Conexion.getInstance().listaErrores(new ArrayList(),"201");
                if(errores != null){
                    System.out.println("llega");
                    subirErrorController.setErrorModificar(errores.get(0));
                }else{
                    System.out.println("no llega");
                }
                    subirErrorController.setTipoPantalla("Modificar Error");
                }
                
                contentAPane.getChildren().setAll(nuevaVista);
                
                
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        });
          
          
          
          
    }    
    
    
        
    
}
