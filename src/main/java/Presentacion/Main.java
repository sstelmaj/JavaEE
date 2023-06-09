/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Presentacion;

import Presentacion.Controllers.DashboardController;
import Presentacion.Controllers.LoginController;
import Presentacion.Controllers.SubirErrorController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
        //    primaryStage.setFullScreen(true);
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
