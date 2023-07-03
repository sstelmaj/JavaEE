/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Presentacion;

import Logica.Clases.AjustesUsuario;
import Logica.Clases.Perfil;
import Logica.Clases.Usuario;
import Logica.Controladores.PerfilController;
import Logica.Controladores.UsuarioController;
import Persistencia.Conexion;
import Presentacion.Controllers.LoginController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 * 
 * @author joaco
 */
public class Main extends Application { //iniciador
    private static Stage stage;
    @Override
    public void start(Stage primaryStage){
        //UI manager para darle un toque mas moderno a los componentes de swing
        try {
           UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        try{
            if (!UsuarioController.getInstance().existeUserAdmin()){
                if (!UsuarioController.getInstance().existePerflAdmin()) {
                    Perfil perfilAdmin = new Perfil();
                    perfilAdmin.setCrearUsuario(true);
                    perfilAdmin.setDesactivar(true);
                    perfilAdmin.setModificar(true);
                    perfilAdmin.setSubir(true);
                    perfilAdmin.setNombre("Administrador");
                    Conexion.getInstance().persist(perfilAdmin);
                }
                if (UsuarioController.getInstance().obtenerAjustes() == null){
                    AjustesUsuario ajustes = new AjustesUsuario();
                    ajustes.setDarkMode(false);
                    ajustes.setFullHD(true);
                    ajustes.setEnglish(false);
                    Conexion.getInstance().persist(ajustes);
                } 
                
                AjustesUsuario ajustes = UsuarioController.getInstance().obtenerAjustes();
                
                Perfil perfilAdmin = PerfilController.getInstance().obtenerPerfil("Administrador");
                
                Usuario usuarioAdmin = new Usuario();
                usuarioAdmin.setActive(true);
                usuarioAdmin.setAjustes(ajustes);
                usuarioAdmin.setApellido("Admin");
                usuarioAdmin.setIsActive(true);
                usuarioAdmin.setMail("admin@mail.com");
                usuarioAdmin.setNombre("Admin");
                usuarioAdmin.setPassword("admin");
                usuarioAdmin.setPerfil(perfilAdmin);
                
                Conexion.getInstance().persist(usuarioAdmin);
            }
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/Login.fxml"));
            AnchorPane ventana = (AnchorPane) loader.load();
            LoginController dashboardController = (LoginController)loader.getController();
            
            //Show the scene containing the root layout
            Scene scene = new Scene(ventana);
            primaryStage.setTitle("Login");
            //primaryStage.setResizable(true);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            //primaryStage.setFullScreen(true);
            //se le pasa el primary stage para poder controlar las resoluciones
            
            primaryStage.show();
            primaryStage.centerOnScreen();
        //    dashboardController.setPrimaryStage(primaryStage);
            this.stage = primaryStage;
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args){
        launch(args);
        
    }
    public static Stage getStage(){
        return stage;
    }
    
    
    
}
