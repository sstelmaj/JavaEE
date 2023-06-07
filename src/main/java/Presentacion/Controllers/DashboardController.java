/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.Etiqueta;
import Logica.Clases.Perfil;
import Logica.Clases.Solucion;
import Logica.Clases.Usuario;
import Logica.Controladores.ErrorController;
import Logica.Controladores.EtiquetaController;
import Logica.Controladores.PerfilController;
import Logica.Controladores.SolucionController;
import Logica.Controladores.UsuarioController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    
    private static PerfilController instance = null;
    
    private boolean existeMainStage;
    
    private double newWidth;
    
    private double newHeight;
    
    private Stage mainStage;
    @FXML
    private Button botonActualizar;
    @FXML
    private Label textResAncho;
    @FXML
    private Label textResAlto;
    @FXML
    private Button botonPredeterminada;
    @FXML
    private Button botonMedidaContent;
    @FXML
    private AnchorPane anchorDash;
    
    public void setPrimaryStage(Stage mainStage){
        this.mainStage = mainStage;
        System.out.println("se setea"+ mainStage.getWidth());
        initializeWidthChangeListener();
       System.out.println(mainStage.widthProperty().toString());
    }
    
    public void setVista(String nombreVista){
        this.selectorVista.setValue(nombreVista);
    }
    
    public void setContenido(Parent vista ){
        
         contentAPane.getChildren().setAll(vista);
         
    }
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
            subirErrorController.setPanelContent(contentAPane);
            contentAPane.getChildren().setAll(nuevaVista);
            BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
            Background background = new Background(backgroundFill);
            contentAPane.setBackground(background);
            anchorDash.setRightAnchor(contentAPane,100.0);
            anchorDash.setLeftAnchor(contentAPane,contentAPane.getLayoutX());
            anchorDash.setTopAnchor(contentAPane,contentAPane.getLayoutY());
            anchorDash.setBottomAnchor(contentAPane,80.0);
        //    contentAPane.maxWidth(100.0);
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
        vistas.put("Crear Usuario", "/fxml/crearUsuario.fxml");
        vistas.put("Modificar Usuario", "/fxml/crearUsuario.fxml");
        
        // Agregar elementos al ComboBox
        selectorVista.getItems().addAll("Subir Error", "Modificar Error","Subir Solucion","Modificar Solucion",
                "Vista 2", "Detalle Solucion","Crear Etiqueta", "Admin", "Error","Crear Perfil","Modificar Perfil","Crear Usuario","Modificar Usuario");
        
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
                    crearPerfilController.setDashboard(this);
                }
                else if(newValue.equals("Modificar Usuario")){
                    CrearUsuarioController crearUsuarioController = (CrearUsuarioController)loader.getController();
                    Usuario usuario = UsuarioController.getInstance().obtenerUsuario("martinperez@gmail.com");
                    if(usuario != null){
                        System.out.println("llega");
                        crearUsuarioController.setUsuarioModificar(usuario);
                    }else{
                        System.out.println("no llega");
                    }
                    crearUsuarioController.setTipoPantalla("Modificar Usuario");
                    
                    crearUsuarioController.setDashboard(this);
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

    @FXML
    private void actualizar(ActionEvent event) {
      String widthString = String.valueOf(mainStage.getWidth());
        textResAncho.setText(widthString);
        String heightString = String.valueOf(mainStage.getHeight());
                textResAlto.setText(heightString);
    }
    
    private void initializeWidthChangeListener() {
        if (mainStage != null) {
            mainStage.widthProperty().addListener((observable, oldValue, newValue) -> {
                 this.newWidth = (double) newValue;
                String widthString = String.valueOf(newWidth);
                textResAncho.setText(widthString);
                
                contentAPane.setPrefWidth( (newWidth - 1552.0) + 1552.0) ;
            });
            mainStage.heightProperty().addListener((observable, oldValue, newValue) -> {
                this.newHeight = (double) newValue;
                String heightString = String.valueOf(newHeight);
                textResAlto.setText(heightString);
                double nuevoAlto = (newHeight - 800.0) + 700.0;
                if(nuevoAlto > 900.0){
                    nuevoAlto = 900.0;
                }
                contentAPane.setPrefHeight( nuevoAlto  );
                
            });
            
            
        }
    }

    @FXML
    private void actionPredeterminada(ActionEvent event) {
        this.mainStage.setHeight(800.0 );
        this.mainStage.setWidth(1552.0) ;
    }

    @FXML
    private void verContent(ActionEvent event) {
        System.out.println(contentAPane.getWidth());
        System.out.println(contentAPane.getHeight());
    }
        
    
}
