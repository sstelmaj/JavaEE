/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.Archivo;
import Logica.Clases.Solucion;
import Logica.Controladores.SolucionController;
import Persistencia.Conexion;
import Presentacion.PanelCodigoSolucion;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.scene.input.MouseEvent;
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
    
    private String codigoSol,codigoErr,descripcion,creador;
    private Date fechaSol;
    private int usosSol;
    private long idSol;
    private Solucion solucion=null;
    List<Archivo> archivos=null;
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
    private List<Image> listaImagenes=new ArrayList();
    private int imagenesPorPagina = 9; // Número de imágenes por pagina del GridPane
    private int paginaActual = 0; 
    @FXML
    private Button botonVer;
    @FXML
    private Button prevButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button btnValorar;
    
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
        solucion = SolucionController.getInstance().obtenerSolucion(idSol);
        codigoSol=solucion.getCodigo();
        creador=solucion.getUsuario().getNombre();
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

        listaArchivos=FXCollections.observableArrayList();
        this.columnArchivo.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnExt.setCellValueFactory(new PropertyValueFactory("extension"));
        for(Archivo arch:archivos){
            if(this.checkIfFileHasExtension(arch.getExtension())){
                Image imagen=null;
                if(arch.getContenidoByte()!=null){
                    imagen=new Image(new ByteArrayInputStream(arch.getContenidoByte()));
                }else{
                    imagen=new Image(arch.getUrl());
                }
                this.listaImagenes.add(imagen);
            }else{
                this.listaArchivos.add(arch);                   
            }
        }
        //Agregar el repositorio y link a la lista de archivos
            if(solucion.getLink()!=null && !solucion.getLink().isEmpty()){
                Archivo linkTemp=new Archivo();
                linkTemp.setUrl(solucion.getLink());
                linkTemp.setNombre("Link asociado");
                linkTemp.setExtension("web");
                this.listaArchivos.add(linkTemp);
            }
        this.tablaArchivos1.setItems(listaArchivos);

        showPage(tablaArchivos);
        //Button prevButton = new Button("Anterior");
        prevButton.setOnAction(e -> {
            if (this.paginaActual > 0) {
                paginaActual--;
                showPage(tablaArchivos);
            }
        });

        //Button nextButton = new Button("Siguiente");
        nextButton.setOnAction(e -> {
            if (this.paginaActual < getNumPages() - 1) {
                this.paginaActual++;
                showPage(tablaArchivos);
            }
        });



        //Cargar datos en panel de detalles
        Text tituloDetalles=new Text("Estos son los detalles de la solucion \n");
        Text detalles=new Text("Creado por: "+this.creador+"\nDescripcion: "+descripcion);
        tituloDetalles.setFill(Color.BLACK);
        tituloDetalles.setFont(Font.font("Helvetica", FontPosture.ITALIC, 19));
        detalles.setFill(Color.GRAY);
        detalles.setFont(Font.font("Calibri", FontPosture.ITALIC, 15));
        txtFlowDetalles.getChildren().addAll(tituloDetalles,detalles);
        tablaArchivos.setGridLinesVisible(true);

        txtUsosSolucion.setText("Usos: "+usosSol);
        txtFechaSolucion.setText(txtFechaSolucion.getText()+new SimpleDateFormat("dd-MM-yyyy").format(this.fechaSol));


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
        String[] extensiones={"jpg","png","jpeg"};
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
            }catch (Exception ex) {
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
    
    private void showPage(GridPane gridPane) {
        gridPane.getChildren().clear();

        int startIndex = this.paginaActual * this.imagenesPorPagina;
        int endIndex = Math.min(startIndex + this.imagenesPorPagina , this.listaImagenes.size());

        int col = 0;
        int row = 0;
        for (int i = startIndex; i < endIndex; i++) {
            Image image = this.listaImagenes.get(i);
            ImageView imageView = new ImageView(image);
            //imageView.setFitWidth(100);
            imageView.setFitWidth(gridPane.getWidth() / 3);
            imageView.setFitHeight(gridPane.getHeight() / 3);
            //imageView.setFitHeight(100);
            imageView.setCursor(Cursor.HAND);
            imageView.setPreserveRatio(true);
            gridPane.add(imageView, col, row);
            
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }
        gridPane.setGridLinesVisible(true);
        //Creo el popup para expandir imagenes
        for (Node node : gridPane.getChildren()) {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                imageView.setOnMouseClicked(event -> {
                    Popup popup = new Popup();
                    ImageView popupImageView = new ImageView(imageView.getImage());
                    popupImageView.setFitWidth(700);
                    popupImageView.setPreserveRatio(true);
                    popup.getContent().add(popupImageView);

                    //Obtengo la escena para deshabilitar el click del raton
                    Scene scene = gridPane.getScene();
                    scene.getRoot().setDisable(true);

                    popup.show(gridPane.getScene().getWindow());
                    popup.setOnHidden(hiddenEvent -> {
                        popup.hide();
                        scene.getRoot().setDisable(false);
                    });
                });              
            }
        }  
    }

    private int getNumPages() {
        return (int) Math.ceil((double) this.listaImagenes.size() / this.imagenesPorPagina);
    }

    @FXML
    private void sumarPunto(MouseEvent event) {
        this.usosSol++;
        this.solucion.setPuntos(this.usosSol);
        Conexion.getInstance().merge(this.solucion);
        this.txtUsosSolucion.setText("Usos: "+usosSol);
        this.btnValorar.setDisable(true);
    }
}
