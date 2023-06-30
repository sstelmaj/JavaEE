/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.AjustesUsuario;
import Logica.Clases.Etiqueta;
import Logica.Clases.Perfil;
import Logica.Clases.Solucion;
import Logica.Clases.Usuario;
import Logica.Controladores.ErrorController;
import Logica.Controladores.EtiquetaController;
import Logica.Controladores.PerfilController;
import Logica.Controladores.SolucionController;
import Logica.Controladores.UsuarioController;
import Persistencia.Sesion;
import Presentacion.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joaco
 */
public class AdminDashboardRootController implements Initializable {

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
    private Label textResAncho;
    private Label textResAlto;
    @FXML
    private AnchorPane anchorDash;
    
    private Initializable controladorAnterior =null;
    
    private Initializable controladorSiguiente =null;
    @FXML
    private Button botonAtras;
    @FXML
    private Button botonAdelante;
    @FXML
    private Button botonErrores;
    @FXML
    private Button botonSoluciones;
    @FXML
    private Button botonCerrarSesion;
    @FXML
    private Button botonInicio;
    @FXML
    private Button botonAjustes;
    
    private Stage stage;
    @FXML
    private Button btnGestionar;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setControladorAnterior(Initializable parent) {
        this.controladorAnterior = parent;
    }
    
    public void setControladorSiguiente(Initializable parent) {
        this.controladorSiguiente = parent;
    }
    
    private static AdminDashboardRootController instancia;
    
    public static AdminDashboardRootController getInstance() {
        
        return instancia;
    }
    
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
    
    public AnchorPane getAnchorPane (){
        return this.contentAPane;
    }
    private String fxml;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(Sesion.getInstance().isIsDarkMode()){   
              contentAPane.setStyle("-fx-background-color: #c6c6c6; -fx-background-radius: 10px;");

               anchorDash.setStyle("-fx-background-color: #FFFFFF;");


            }else{

               contentAPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
               anchorDash.setStyle("-fx-background-color: #c6c6c6;");
            }
       
        
        
        
        // TODO
        instancia = this;
          try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/AdminDashboard.fxml"));
            Parent nuevaVista = loader.load();
            AdminDashboardController dashController = (AdminDashboardController)loader.getController();
        //    subirErrorController.setPantalla("Subir Error");
            dashController.setPanelContent(contentAPane);
            contentAPane.getChildren().setAll(nuevaVista);
         
          
            
        
            
         
        //      contentAPane.setStyle("-fx-background-color: #14151e;");
            anchorDash.setRightAnchor(contentAPane,100.0);
            anchorDash.setLeftAnchor(contentAPane,contentAPane.getLayoutX());
            anchorDash.setTopAnchor(contentAPane,contentAPane.getLayoutY());
            anchorDash.setBottomAnchor(contentAPane,80.0);
        //    contentAPane.maxWidth(100.0);
        }catch(IOException e){
            System.out.println(e.getMessage());
        
        }
          
       
  
          
        // Agregar elementos al ComboBox
        vistas.put("Inicio", "/fxml/inicio.fxml");
        vistas.put("Subir Error", "/fxml/subirError.fxml");
        vistas.put("Modificar Error", "/fxml/subirError.fxml");
        vistas.put("Vista 2", "/fxml/Vista2.fxml");
        vistas.put("Subir Solucion", "/fxml/subirSolucion.fxml");
        vistas.put("Modificar Solucion", "/fxml/subirSolucion.fxml");
        vistas.put("Admin", "/fxml/AdminDashboard.fxml");
        vistas.put("Error", "/fxml/detalleError.fxml");
        vistas.put("Crear Etiqueta", "/fxml/crearOrganizarEtiqueta.fxml");
        vistas.put("Modificar Etiqueta", "/fxml/crearOrganizarEtiqueta.fxml");
        vistas.put("Crear Perfil", "/fxml/crearPerfil.fxml");
        vistas.put("Modificar Perfil", "/fxml/crearPerfil.fxml");
        vistas.put("Crear Usuario", "/fxml/crearUsuario.fxml");
        vistas.put("Modificar Usuario", "/fxml/crearUsuario.fxml");
        vistas.put("Prueba Filtrado", "/fxml/pruebaFiltrado.fxml");
        
        
        // Agregar elementos al ComboBox
        selectorVista.getItems().addAll("Inicio","Subir Error", "Modificar Error","Subir Solucion","Modificar Solucion",
                "Vista 2", "Detalle Solucion","Crear Etiqueta","Modificar Etiqueta", "Admin", "Error","Crear Perfil","Modificar Perfil","Crear Usuario","Modificar Usuario","Prueba Filtrado");
        
        // Listener para cambio de selección en el ComboBox
        selectorVista.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             fxml = vistas.get(newValue); // Obtener ruta del fxml correspondiente al valor seleccionado
            try{
                
                     if(Sesion.getInstance().isIsDarkMode()){   
                       contentAPane.setStyle("-fx-background-color: #c6c6c6; -fx-background-radius: 10px;");

                        anchorDash.setStyle("-fx-background-color: #FFFFFF;");


                     }else{
                        
                        contentAPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
                        anchorDash.setStyle("-fx-background-color: #c6c6c6;");
                     }
                
                
                
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxml));
                Parent nuevaVista = loader.load();
                
                if(newValue.equals("Modificar Error")){
                    SubirErrorController subirErrorController = (SubirErrorController)loader.getController();
                    List<Logica.Clases.Error> errores = ErrorController.getInstance().listaErrores(new ArrayList(),"201");
                    if(errores != null){
                        System.out.println("llega");
                        subirErrorController.setErrorModificar(errores.get(0));
                         subirErrorController.setPanelContent(contentAPane);
                    }else{
                        System.out.println("no llega");
                    }
                    subirErrorController.setTipoPantalla("Modificar Error");
                }else if(newValue.equals("Modificar Solucion")){
                    SubirSolucionController subirSolucionController = (SubirSolucionController)loader.getController();
                    Solucion soluciones = SolucionController.getInstance().obtenerSolucion(101);
                     subirSolucionController.setPanelContent(contentAPane);
                    if(soluciones != null){
                        System.out.println("llega");
                        subirSolucionController.setSolucionModificar(soluciones);
                       
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
                   
                    //detalleErrorController.initialize(contentAPane, );
                } else if(newValue.equals("Modificar Etiqueta")){
                    crearEtiquetaController crearEtiquetaController = (crearEtiquetaController)loader.getController();
                    Etiqueta etiqueta = EtiquetaController.getInstance().obtenerEtiqueta("SO");
                    if(etiqueta != null){
                        System.out.println("llega");
                        crearEtiquetaController.setEtiquetaModificar(etiqueta);
                    }else{
                        System.out.println("no llega");
                    }
                    crearEtiquetaController.setTipoPantalla("Modificar Etiqueta");
                    
                    //crearEtiquetaController.setDashboard(this);
                }
                else if(newValue.equals("Subir Error")){
                    SubirErrorController subirErrorController = (SubirErrorController)loader.getController();
                   subirErrorController.setPanelContent(contentAPane);
                   
                }
                else if(newValue.equals("Subir Solucion")){
                    SubirSolucionController subirErrorController = (SubirSolucionController)loader.getController();
                   subirErrorController.setPanelContent(contentAPane);
                   
                }
                
                
                
                
                
//                if(fxml.equals("/fxml/detalleError.fxml")){
//                    DetalleErrorController detalleErrorController=(DetalleErrorController)loader.getController();
//                    detalleErrorController.initialize(contentAPane);
//                }
                
                contentAPane.getChildren().setAll(nuevaVista);
                
                
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        });
          
          
          
          
    }    
    public void setVista(Parent nuevaVista){
        contentAPane.getChildren().setAll(nuevaVista);
    }
    


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

    private void actionPredeterminada(ActionEvent event) {
        this.mainStage.setHeight(800.0 );
        this.mainStage.setWidth(1552.0) ;
    }

    private void verContent(ActionEvent event) {
        System.out.println("ancho orign"+contentAPane.getWidth());
        System.out.println("alto origin"+contentAPane.getHeight());
        
        System.out.println(this.stage.getWidth());
        System.out.println(this.stage.getHeight());
        
        
    }

    @FXML
    private void clickAtras(ActionEvent event) {
        if(this.controladorAnterior != null){
            if (this.controladorAnterior instanceof AdminUsuariosController) {
                // El objeto es una instancia de la clase SubirErrorController
                AdminUsuariosController pruebaFiltradoController = (AdminUsuariosController) this.controladorAnterior;
                
                 contentAPane.getChildren().setAll(pruebaFiltradoController.getRoot());
                // Realiza las acciones deseadas con el controlador
            }
           
        }
    }

    @FXML
    private void clickAdelante(ActionEvent event) {
        if(this.controladorSiguiente != null){
            if (this.controladorSiguiente instanceof CrearUsuarioController) {
                // El objeto es una instancia de la clase SubirErrorController
                CrearUsuarioController detalleErrorController = (CrearUsuarioController) this.controladorSiguiente;
                
                 contentAPane.getChildren().setAll(detalleErrorController.getRoot());
                // Realiza las acciones deseadas con el controlador
            }
           
        }
    }

    @FXML
    private void clickErrores(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/paginaErrores.fxml"));
        try {
            Parent nuevaVista = loader.load();
            contentAPane.getChildren().setAll(nuevaVista);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickSoluciones(ActionEvent event) {
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
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
    private void clickInicio(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AdminDashboard.fxml"));
        Parent nuevaVista = loader.load();
        AdminDashboardController dashController = (AdminDashboardController)loader.getController();
        //    subirErrorController.setPantalla("Subir Error");
         dashController.setPanelContent(contentAPane);
         contentAPane.getChildren().setAll(nuevaVista);
    }

    @FXML
    private void clickAjustes(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("/fxml/ajustes.fxml"));
         Parent nuevaVista = loader.load();
         contentAPane.getChildren().setAll(nuevaVista);
    }

    @FXML
    private void clickGestionar(ActionEvent event) throws IOException {
        
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminGestionarUsuarios.fxml"));
        Parent nuevaVista = loader.load();
        contentAPane.getChildren().setAll(nuevaVista);
          
         
       
    }

        
    
}
