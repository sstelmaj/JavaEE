/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.Archivo;
import Logica.Clases.Solucion;
import Logica.Controladores.SolucionController;
import Presentacion.PanelCodigoSolucion;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.stage.Stage;
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
    private Text txtUsosSolucion;
    @FXML
    private Text txtFechaSolucion;
    
    private String codigoSol,codigoErr,descripcion;
    private Date fechaSol;
    private int usosSol;
    private long idSol;
    List<Archivo> archivos=null;
    @FXML
    private Button btnBack;
    private Scene escenaPrevia;
    @FXML
    private AnchorPane anchorFile;
    @FXML
    private TableView<Archivo> tablaArchivos1;
    @FXML
    private TableColumn<Archivo, String> columnArchivo;
    @FXML
    private TableColumn<Archivo, String> columnExt;
    private ObservableList<Archivo> listaArchivos;
    @FXML
    private Button botonVer;
    
    /**
     * Initializes the controller class.
     * @param id
     */
    public void setId(long id){
        this.idSol=id;
    }
     
    public void setEscenaPrevia(Scene previa){
        this.escenaPrevia=previa;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
    public void initialize(){
            //Obtengo los datos de la solucion
            Solucion solucion = SolucionController.getInstance().obtenerSolucion(idSol);
                codigoSol=solucion.getCodigo();
                descripcion=solucion.getDescripcion();
                archivos=solucion.getArchivos();
                fechaSol=solucion.getFechaSubida();
                usosSol=solucion.getPuntos();

                codigoErr=solucion.getError().getCodigo();

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
           /* for(Archivo arch:archivos){
                if(this.checkIfFileHasExtension(arch.getUrl())){
                    ImageView imageView=new ImageView(new Image(arch.getUrl()));
                    imageView.setCursor(Cursor.HAND);
                    imageView.setPreserveRatio(true);
                    tablaArchivos.addRow(0, imageView);
                }else{
                    Hyperlink link= new Hyperlink(arch.getUrl());
                    this.archivosWeb.getChildren().add(link);
                }
            }*/
            listaArchivos=FXCollections.observableArrayList();
            this.columnArchivo.setCellValueFactory(new PropertyValueFactory("nombre"));
            this.columnExt.setCellValueFactory(new PropertyValueFactory("extension"));
            for(Archivo arch:archivos){
                if(this.checkIfFileHasExtension(arch.getExtension())){
                    if(arch.getContenidoByte()!=null){
                        Image imgEnBytes=new Image(new ByteArrayInputStream(arch.getContenidoByte()));
                        ImageView imageView=new ImageView(imgEnBytes);
                        imageView.setCursor(Cursor.HAND);
                        imageView.setPreserveRatio(true);
                        tablaArchivos.addRow(1, imageView);
                    }else{
                        ImageView imageView=new ImageView(new Image(arch.getUrl()));
                        imageView.setCursor(Cursor.HAND);
                        imageView.setPreserveRatio(true);
                        //Ver como cargar dinamicamente
                        tablaArchivos.addRow(0, imageView);
                    }
                }else{
                    this.listaArchivos.add(arch);                   
                }
                this.tablaArchivos1.setItems(listaArchivos);
                
                /*else{
                    Hyperlink link= new Hyperlink(arch.getUrl());
                    link.setOnAction(event ->{
                        try {
                            Desktop.getDesktop().browse(new URI(arch.getUrl()));
                        }catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                    this.archivosWeb.getChildren().add(link);
                }*/
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
            txtFechaSolucion.setText(txtFechaSolucion.getText()+new SimpleDateFormat("dd-MM-yyyy").format(this.fechaSol));


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
    
    @FXML
    private void mostrarArchivos(){
        Archivo archivoTemp=this.tablaArchivos1.getSelectionModel().getSelectedItem();
        if(!archivoTemp.getExtension().equals("web")){
            try {
                File archivo = File.createTempFile(archivoTemp.getNombre(), "." + archivoTemp.getExtension());
                try (FileOutputStream fileOuputStream = new FileOutputStream(archivo)) {
                    fileOuputStream.write(archivoTemp.getContenidoByte());
                }
                archivo.deleteOnExit();
                Desktop.getDesktop().open(archivo);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }else{
            try {
                Desktop.getDesktop().browse(new URI(archivoTemp.getUrl()));
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /*private void agregarArchivoATabla(){
        int fil =tablaArchivos.getRowCount();
        int col = tablaArchivos.getColumnCount();
        int espacios= fil*col;
        if(espacios==tablaArchivos.getChildren().size()){
            tablaArchivos.addRow(fil, nodes);
        }
    }*/

    @FXML
    private void regresar(ActionEvent event) {
        if(this.escenaPrevia!=null){
            Stage stage=(Stage)this.btnBack.getScene().getWindow();
            stage.setScene(escenaPrevia);
        }
    }
}
