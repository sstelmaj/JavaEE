/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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

    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        root.setStyle("-fx-background-color: #14151e;");
        
        
    }    
    
}
