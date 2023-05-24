/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion;

import Logica.Clases.Archivo;
import Logica.Clases.Solucion;
import Logica.Controladores.SolucionController;
import Persistencia.Conexion;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javax.swing.JInternalFrame;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

/**
 * FXML Controller class
 *
 * @author paulo
 */
public class DetalleSolucionController implements Initializable {

    @FXML
    private AnchorPane anchor1;
    @FXML
    private Accordion options;
    @FXML
    private AnchorPane optCodigo;
    @FXML
    private AnchorPane optArchivos;
    @FXML
    private AnchorPane anchorSolucion;
    @FXML
    private AnchorPane optDetalles;
    @FXML
    private TextFlow txtFlowDetalles;
    private ImageView imagen1;
    @FXML
    private GridPane tablaArchivos;
    private AnchorPane anchorPaneDestacado;
    
    @FXML
    private AnchorPane archivosWeb;
    @FXML
    private Text txtUsosSolucion;
    @FXML
    private Text txtFechaSolucion;
    
    String codigoSol,codigoErr,descripcion;
    Date fechaSol;
    int usosSol;
    long idSol;
    List<Archivo> archivos=null;
    
    /**
     * Initializes the controller class.
     * @param id
     */
     public void setId(long id){
        this.idSol=id;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void initialize(){
            //Obtengo los datos de la solucion
            List<Solucion> solucion = SolucionController.getInstance().obtenerSolucion(idSol);
            for(Solucion res: solucion){
                codigoSol=res.getCodigo();
                descripcion=res.getDescripcion();
                archivos=res.getArchivos();
                fechaSol=res.getFechaSubida();
                usosSol=res.getPuntos();

                codigoErr=res.getError_Tecnologia().getError().getCodigo();
            }

            //Frame para el codigo de error
            JInternalFrame iFrame = new PanelCodigoSolucion(codigoErr,SyntaxConstants.SYNTAX_STYLE_JAVA);
            iFrame.setPreferredSize(new Dimension(550,400));
            iFrame.setSize(20, 20);
            iFrame.setVisible(true);
            iFrame.setBorder(null);
            ((javax.swing.plaf.basic.BasicInternalFrameUI) iFrame.getUI()).setNorthPane(null);
            SwingNode swingNode = new SwingNode();

            swingNode.setContent(iFrame);
            anchor1.getChildren().add(swingNode); 

            //Frame para el codigo de solucion
            JInternalFrame iFrame2 = new PanelCodigoSolucion(codigoSol,SyntaxConstants.SYNTAX_STYLE_JAVA);
            iFrame2.setPreferredSize(new Dimension(550,400));
            iFrame2.setSize(20, 20);
            iFrame2.setVisible(true);
            iFrame2.setBorder(null);
            ((javax.swing.plaf.basic.BasicInternalFrameUI) iFrame2.getUI()).setNorthPane(null);
            SwingNode swingNode2 = new SwingNode();

            swingNode2.setContent(iFrame2);
            optCodigo.getChildren().add(swingNode2);

           //Cargo las imagenes en el GridPane
            for(Archivo arch:archivos){
                if(this.checkIfFileHasExtension(arch.getUrl())){
                    ImageView imageView=new ImageView(new Image(arch.getUrl()));
                    imageView.setCursor(Cursor.HAND);
                    imageView.setPreserveRatio(true);
                    tablaArchivos.addRow(0, imageView);
                }else{
                    WebView webView=new WebView();
                    WebEngine webEngine = webView.getEngine();
                    webEngine.load(arch.getUrl());
                    archivosWeb.getChildren().add(webView);
                }
            }



            //Cargar datos en panel de detalles
            Text tituloDetalles=new Text("Estos son los detalles de la solucion \n");
            Text detalles=new Text("Creado por: Persona 1 \nDescripcion: "+descripcion);
            tituloDetalles.setFill(Color.BLACK);
            tituloDetalles.setFont(Font.font("Helvetica", FontPosture.ITALIC, 19));
            detalles.setFill(Color.GRAY);
            detalles.setFont(Font.font("Calibri", FontPosture.ITALIC, 15));
            txtFlowDetalles.getChildren().addAll(tituloDetalles,detalles);
            tablaArchivos.setGridLinesVisible(true);

            txtUsosSolucion.setText("Usos: "+usosSol);
            txtFechaSolucion.setText(txtFechaSolucion.getText()+fechaSol.toString());


            //Creo el popup para expandir imagenes
            for (Node node : tablaArchivos.getChildren()) {
                if (node instanceof ImageView) {
                    ImageView imageView = (ImageView) node;
                    imageView.setOnMouseClicked(event -> {
                        Popup popup = new Popup();
                        ImageView popupImageView = new ImageView(imageView.getImage());
                        popupImageView.setFitWidth(500);
                        popupImageView.setPreserveRatio(true);
                        popup.getContent().add(popupImageView);

                        //Obtengo la escena para deshabilitar el click del raton
                        Scene scene = tablaArchivos.getScene();
                        scene.getRoot().setDisable(true);


                        popup.show(tablaArchivos.getScene().getWindow());
                        popup.setOnHidden(hiddenEvent -> {
                            popup.hide();
                            scene.getRoot().setDisable(false);
                        });
                    });              
                }
            }

            // Agregar un ChangeListener a la propiedad widthProperty y heightProperty del GridPane
        tablaArchivos.widthProperty().addListener((obs, oldVal, newVal) -> {
            for (Node node : tablaArchivos.getChildren()) {
                if(node instanceof ImageView){
                    ImageView imageView = (ImageView) node;
                    imageView.setFitWidth(newVal.doubleValue() / 3);
                }
            }
        });

        tablaArchivos.heightProperty().addListener((var obs, var oldVal, var newVal) -> {
            for (Node node : tablaArchivos.getChildren()) {
                if(node instanceof ImageView){
                    ImageView imageView = (ImageView) node;
                    imageView.setFitHeight(newVal.doubleValue() / 3);
                }
            }
        });
    }
    
    private boolean checkIfFileHasExtension(String s) {
        String[] extensiones={"jpg","png","jpeg","webx"};
        return Arrays.stream(extensiones).anyMatch(entry -> s.endsWith(entry));
    }
    
    /*private void agregarArchivoATabla(){
        int fil =tablaArchivos.getRowCount();
        int col = tablaArchivos.getColumnCount();
        int espacios= fil*col;
        if(espacios==tablaArchivos.getChildren().size()){
            tablaArchivos.addRow(fil, nodes);
        }
    }*/
}
