/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.Archivo;
import Logica.Clases.Solucion;
import Logica.Clases.Error;
import Logica.Clases.Error_Etiqueta;
import Logica.Clases.Etiqueta;
import Logica.Controladores.ErrorController;
import Presentacion.PanelCodigoSolucion;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
public class DetalleErrorController implements Initializable {

    @FXML
    private AnchorPane anchPaneGeneral;
    @FXML
    private AnchorPane anchPaneDescrip;
    @FXML
    private AnchorPane anchPaneConsola;
    @FXML
    private AnchorPane anchPaneArchivos;
    @FXML
    private AnchorPane anchPaneRefExt;
    @FXML
    private AnchorPane anchPaneCodigo;
    @FXML
    private AnchorPane anchPaneLista;
    @FXML
    private VBox lista;
    @FXML
    private TextField buscador;
    @FXML
    private Text txtTitulo;
    @FXML
    private Text txtFechaModif;
    
    private AnchorPane apPrincipal;
    @FXML
    private TextFlow txtFlowDescripcion;
    @FXML
    private TextFlow txtFlowConsola;
    @FXML
    private GridPane tablaArchivos;
    
    private long idError;
    private String descripcion,codigo,consola;
    private Date fechaModif;
    private List<Archivo> archivos;
    private List<Error_Etiqueta> etiquetas;
    DashboardController dashboard;
    @FXML
    private AnchorPane apEtiquetas;
    @FXML
    private HBox hBoxEtiquetas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
    }
    public void initialize(){
        //List<Solucion>soluciones=ErrorController.getInstance().obtenerSolucionesDelError(151 /*idError*/);
        Error error=ErrorController.getInstance().obtenerError(151 /*idError*/);
        List<Solucion>soluciones=error.getSoluciones();
            this.descripcion=error.getDescripcion();
            this.codigo=error.getCodigo();
            this.consola=error.getConsola();
            this.archivos=error.getArchivos();
            this.fechaModif=error.getFechaSubida();
            this.etiquetas=error.getError_Etiquetas();
        
        //Frame para el codigo de error
        JInternalFrame iFrame = new PanelCodigoSolucion(this.codigo,SyntaxConstants.SYNTAX_STYLE_JAVA);
        iFrame.setPreferredSize(new Dimension(550,400));
        iFrame.setSize(20, 20);
        iFrame.setVisible(true);
        iFrame.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) iFrame.getUI()).setNorthPane(null);
        SwingNode swingNode = new SwingNode();

        swingNode.setContent(iFrame);
        anchPaneCodigo.getChildren().add(swingNode);
        
        
        //Cargo las imagenes en el GridPane
            for(Archivo arch:archivos){
                if(this.checkIfFileHasExtension(arch.getUrl())){
                    ImageView imageView=new ImageView(new Image(arch.getUrl()));
                    imageView.setCursor(Cursor.HAND);
                    imageView.setPreserveRatio(true);
                    //Ver como cargar dinamicamente
                    tablaArchivos.addRow(0, imageView);
                }else{
                    WebView webView=new WebView();
                    WebEngine webEngine = webView.getEngine();
                    webEngine.load(arch.getUrl());
                    this.anchPaneRefExt.getChildren().add(webView);
                }
            }



            //Cargar datos en panel de descripcion y consola
            Text tituloDescripcion=new Text("Descripcion del error \n");
            Text txtDescripcion=new Text("Creado por: Persona 1 \nDescripcion: "+descripcion);
            tituloDescripcion.setFill(Color.BLACK);
            tituloDescripcion.setFont(Font.font("Helvetica", FontPosture.ITALIC, 19));
            txtDescripcion.setFill(Color.GRAY);
            txtDescripcion.setFont(Font.font("Calibri", FontPosture.ITALIC, 15));
            txtFlowDescripcion.getChildren().addAll(tituloDescripcion,txtDescripcion);
            hBoxEtiquetas.setSpacing(10);
            for(Error_Etiqueta e:etiquetas){
                Text etiqueta=new Text(e.getEtiqueta().getNombre());
                etiqueta.setFill(Color.BLACK);
                hBoxEtiquetas.getChildren().add(etiqueta);
            }
            tablaArchivos.setGridLinesVisible(true);

            Text txtConsola=new Text(this.consola);
            txtConsola.setFill(Color.GRAY);
            txtConsola.setFont(Font.font("Calibri", FontPosture.ITALIC, 15));
            this.txtFlowConsola.getChildren().addAll(txtConsola);
            
            
            this.txtFechaModif.setText(txtFechaModif.getText()+this.fechaModif.toString());


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
    
        for(Solucion sol:soluciones){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/busquedaSolucion.fxml"));
                Parent subfileRoot = loader.load();
                // Obtén el controlador del archivo subfile.fxml
                BusquedaSolucionController subfileController = loader.getController();
                subfileController.setDatos("Solucion de ejemplo", sol.getDescripcion(), sol.getId(), this.dashboard, sol.getSolucion_Etiquetas());
                subfileController.initialize();

                
                lista.getChildren().add(subfileRoot);
            }catch (IOException e) {
                System.out.println(e);
            }  
        }
        
        //TODO: AGREGAR NOTA Y VER LAS NOTAS DEL ERROR
    
    }
    
    private void setError(long id){
        this.idError=id;
    }
    public void setDashboard(DashboardController dash){
        this.dashboard=dash;
    }
    
    private boolean checkIfFileHasExtension(String s) {
        String[] extensiones={"jpg","png","jpeg","webx"};
        return Arrays.stream(extensiones).anyMatch(entry -> s.endsWith(entry));
    }
}
