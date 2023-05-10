/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion;

import Logica.Clases.Tecnologia;
import Persistencia.Conexion;
import java.awt.Dimension;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JInternalFrame;

/**
 * FXML Controller class
 *
 * @author joaco
 */
public class SubirErrorController implements Initializable {
    
    
    @FXML
    private AnchorPane anchor1;
    private WebView web1;
    @FXML
    private ScrollPane scrollConsole;
    private AnchorPane anchorConsole;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JInternalFrame iFrame = new RSTA();
        iFrame.setPreferredSize(new Dimension(550,400));
        iFrame.setSize(20, 20);
        iFrame.setVisible(true);
        iFrame.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) iFrame.getUI()).setNorthPane(null);
        SwingNode swingNode = new SwingNode();
       
        swingNode.setContent(iFrame);
         anchor1.getChildren().add(swingNode);
         
         
        try {
                JInternalFrame iFrameConsole = new RSTA();
                iFrameConsole.setPreferredSize(new Dimension(550,400));
                iFrameConsole.setSize(20, 20);
                iFrameConsole.setVisible(true);
                iFrameConsole.setBorder(null);
                ((javax.swing.plaf.basic.BasicInternalFrameUI) iFrameConsole.getUI()).setNorthPane(null);
                SwingNode swingNodeConsole = new SwingNode();

                swingNodeConsole.setContent(iFrameConsole);
                scrollConsole.setContent(swingNodeConsole);
            } catch (Exception e) {
                // Manejo de la excepción
                e.printStackTrace();
            }

         
//         try {
//            Iterator<Tecnologia> it = Conexion.getInstance().select(" FROM tecnologia", Tecnologia.class).iterator();
//            while (it.hasNext()) {
//                Tecnologia tecnologia = it.next();
//                // hacer algo con cada objeto tecnologia, por ejemplo mostrar su nombre
//                System.out.println(tecnologia.getNombre());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

      
 
      
    }    

    
}
