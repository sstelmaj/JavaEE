/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.Etiqueta;
import Logica.Clases.Perfil;
import Logica.Clases.Solucion;
import Logica.Controladores.ErrorController;
import Logica.Controladores.EtiquetaController;
import Logica.Controladores.PerfilController;
import Logica.Controladores.SolucionController;
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
            subirErrorController.setPantalla("Subir Error");
            contentAPane.getChildren().setAll(nuevaVista);
     
        }catch(IOException e){
            System.out.println(e.getMessage());
        
        }
          
          
          
        // Agregar elementos al ComboBox
        vistas.put("Subir Error", "/fxml/subirError.fxml");
        vistas.put("Modificar Error", "/fxml/subirError.fxml");
        vistas.put("Vista 2", "/fxml/Vista2.fxml");
        vistas.put("Subir Solucion", "/fxml/subirSolucion.fxml");
        vistas.put("Modificar Solucion", "/fxml/subirSolucion.fxml");
        vistas.put("Admin", "/fxml/AdminDashboard.fxml");
        vistas.put("Detalle Solucion", "/fxml/detalleSolucion.fxml");
        vistas.put("Error", "/fxml/detalleError.fxml");
        vistas.put("Crear Etiqueta", "/fxml/crearOrganizarEtiqueta.fxml");
        vistas.put("Crear Perfil", "/fxml/crearPerfil.fxml");
        vistas.put("Modificar Perfil", "/fxml/crearPerfil.fxml");
        
        // Agregar elementos al ComboBox
        selectorVista.getItems().addAll("Subir Error", "Modificar Error","Subir Solucion","Modificar Solucion",
                "Vista 2", "Detalle Solucion","Crear Etiqueta", "Admin", "Error","Crear Perfil","Modificar Perfil");
        
        // Listener para cambio de selección en el ComboBox
        selectorVista.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             fxml = vistas.get(newValue); // Obtener ruta del fxml correspondiente al valor seleccionado
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxml));
                Parent nuevaVista = loader.load();
                
                if(newValue.equals("Modificar Error")){
                    SubirErrorController subirErrorController = (SubirErrorController)loader.getController();
                    List<Logica.Clases.Error> errores = ErrorController.getInstance().listaErrores(new ArrayList(),"201");
                    if(errores != null){
                        System.out.println("llega");
                        subirErrorController.setErrorModificar(errores.get(0));
                    }else{
                        System.out.println("no llega");
                    }
                    subirErrorController.setTipoPantalla("Modificar Error");
                }else if(newValue.equals("Modificar Solucion")){
                    SubirSolucionController subirSolucionController = (SubirSolucionController)loader.getController();
                    List<Solucion> soluciones = SolucionController.getInstance().obtenerSolucion(101);
                    if(soluciones != null){
                        System.out.println("llega");
                        subirSolucionController.setSolucionModificar(soluciones.get(0));
                    }else{
                        System.out.println("no llega");
                    }
                    subirSolucionController.setTipoPantalla("Modificar Solucion");
                
                }else if(newValue.equals("Modificar Perfil")){
                    CrearPerfilController crearPerfilController = (CrearPerfilController)loader.getController();
                    Perfil perfil = PerfilController.getInstance().obtenerPerfil("Admin");
                    if(perfil != null){
                        System.out.println("llega");
                        crearPerfilController.setPerfilModificar(perfil);
                    }else{
                        System.out.println("no llega");
                    }
                    crearPerfilController.setTipoPantalla("Modificar Perfil");
                }
                
                
                
                
                
                if(fxml.equals("/fxml/detalleError.fxml")){
                    DetalleErrorController detalleErrorController=(DetalleErrorController)loader.getController();
                    detalleErrorController.initialize(contentAPane);
                }
                
                contentAPane.getChildren().setAll(nuevaVista);
                
                
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        });
          
          
          
          
    }    
    
    
        
    
}
