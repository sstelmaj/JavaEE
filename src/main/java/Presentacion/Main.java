/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Presentacion;

import java.awt.GraphicsEnvironment;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
//import static javafx.application.Aplication.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 * 
 * @author joaco
 */
public class Main extends Application { //iniciador

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
            loader.setLocation(Main.class.getResource("/fxml/Dashboard.fxml"));
            Pane ventana = (Pane) loader.load();
            
            //Show the scene containing the root layout
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();
                    
        }catch(IOException e){
            System.out.println(e.getMessage());
        
        }
        
    }
    public static void main(String[] args){
        launch(args);
        
    }
    
}
