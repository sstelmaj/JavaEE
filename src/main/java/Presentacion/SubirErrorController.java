/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion;

import Logica.Clases.Etiqueta;
import Logica.Clases.*;
import Persistencia.Conexion;
import java.awt.Dimension;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JInternalFrame;
import jdk.nashorn.api.scripting.JSObject;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

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
    @FXML
    private WebView linkWebView;
    @FXML
    private TextField linkTextFieldUrl;
    @FXML
    private Button linkButtonVisualizar;
    @FXML
    private DatePicker inputFecha;
    @FXML
    private ComboBox<String> comboTecnologia;
    @FXML
    private TextField textFieldTitulo;
    @FXML
    private TextField textFiltradoTecnologias;
    @FXML
    private Button botonCrearTecnologia;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonIngresar;
    @FXML
    private TitledPane titledPaneDescripcion;
    @FXML
    private Accordion acordion;
    @FXML
    private WebView linkWebView1;
    @FXML
    private Button linkButtonVisualizar1;
    @FXML
    private TextField linkTextFieldUrl1;
    @FXML
    private ListView<String> listaEtiquetas;
    
    private SwingNode swingNode;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        try {
            List<Tecnologia> tecnologias = Conexion.getInstance().listaTecnologias();
            comboTecnologia.getItems().clear();
            for (Tecnologia tecnologia : tecnologias) {
                comboTecnologia.getItems().add(tecnologia.getNombre());
            }
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace();
        }
        
        try {
            List<Etiqueta> etiquetas = Conexion.getInstance().listaEtiquetas();

            // Limpiar los elementos existentes en el ListView
            listaEtiquetas.getItems().clear();

            // Agregar los elementos a la lista del ListView
            for (Etiqueta etiqueta : etiquetas) {
                listaEtiquetas.getItems().add(etiqueta.getNombre());
            }
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace();
        }
        
        
        JInternalFrame iFrame = new RSTA();
        iFrame.setPreferredSize(new Dimension(550,400));
        iFrame.setSize(20, 20);
        iFrame.setVisible(true);
        iFrame.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) iFrame.getUI()).setNorthPane(null);
         swingNode = new SwingNode();
       
        swingNode.setContent(iFrame);
         anchor1.getChildren().add(swingNode);
         
         acordion.setExpandedPane(titledPaneDescripcion);
         
         
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

    @FXML
    private void click(ActionEvent event) {
        System.out.println("Hola");
         linkWebView.getEngine().load("https://github.com/");
        String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 14_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0.3 Mobile/15E148 Safari/604.1";
       
        linkWebView.getEngine().setUserAgent(userAgent);
        linkWebView.getEngine().setJavaScriptEnabled​(true);
        linkWebView.setZoom(0.75);
    }

    @FXML
    private void clickIngresar(ActionEvent event) {
    //   Logica.Clases.Error error = new Logica.Clases.Error();
       
    //   error.setTitulo(textFieldTitulo.getText());
       
       JInternalFrame internalFrame = (JInternalFrame) swingNode.getContent();
       
        if (internalFrame instanceof RSTA) {
    RSTA rsta = (RSTA) internalFrame;
    RSyntaxTextArea textArea = rsta.getTextArea();

    // Ahora puedes acceder al textArea y realizar operaciones en él
    String contenido = textArea.getText();
    System.out.print(contenido);
    // ... hacer algo con el contenido del textArea
    } else {
        // El JInternalFrame no es una instancia de RSTA, maneja el caso según tus necesidades
    }
        }

    
}
