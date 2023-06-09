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
import Logica.Clases.Solucion_Etiqueta;
import Logica.Controladores.ErrorController;
import Logica.Controladores.EtiquetaController;
import Presentacion.PanelCodigoSolucion;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private HBox buscador;
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

    private List<Error_Etiqueta> etiquetasError;
    List<Etiqueta>allEtiquetas;
    private List<Solucion>soluciones;
    private ArrayList<String>etiquetasSeleccionadas=new ArrayList();
    DashboardController dashboard;
    @FXML
    private AnchorPane apEtiquetas;
    @FXML
    private HBox hBoxEtiquetas;
    @FXML
    private ComboBox<String> comboBoxEtiquetas;
    @FXML
    private Button btnAgregarEtiqueta;
    @FXML
    private ComboBox<String> comboBoxSubEtiq;

    private Logica.Clases.Error errorDetalle;

    public Error getErrorDetalle() {
        return errorDetalle;
    }

    public void setErrorDetalle(Error errorDetalle) {
        this.errorDetalle = errorDetalle;
    }

    public AnchorPane getAnchPaneGeneral() {
        return anchPaneGeneral;
    }

    public void setAnchPaneGeneral(AnchorPane anchPaneGeneral) {
        this.anchPaneGeneral = anchPaneGeneral;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
    }
    public void initialize(AnchorPane ap, Logica.Clases.Error error){
        //List<Solucion>soluciones=ErrorController.getInstance().obtenerSolucionesDelError(151 /*idError*/);
        //Error error=ErrorController.getInstance().obtenerError(151 /*idError*/);
        soluciones=error.getSoluciones();
        soluciones.sort(Comparator.comparingInt(Solucion::getPuntos));
        allEtiquetas=EtiquetaController.getInstance().listaEtiquetas();
            this.descripcion=error.getDescripcion();
            this.codigo=error.getCodigo();
            this.consola=error.getConsola();
            this.archivos=error.getArchivos();
            this.fechaModif=error.getFechaSubida();
            this.etiquetasError=error.getError_Etiquetas();
        
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
                    Hyperlink link= new Hyperlink(arch.getUrl());
                    this.anchPaneRefExt.getChildren().add(link);
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
            for(Error_Etiqueta e:etiquetasError){
                Text etiqueta=new Text(e.getEtiqueta().getNombre());
                etiqueta.setFill(Color.BLACK);
                hBoxEtiquetas.getChildren().add(etiqueta);
            }
            tablaArchivos.setGridLinesVisible(true);

            Text txtConsola=new Text(this.consola);
            txtConsola.setFill(Color.GRAY);
            txtConsola.setFont(Font.font("Calibri", FontPosture.ITALIC, 15));
            this.txtFlowConsola.getChildren().addAll(txtConsola);
            
            
            this.txtFechaModif.setText(txtFechaModif.getText()+new SimpleDateFormat("dd-MM-yyyy").format(this.fechaModif)/*this.fechaModif.toString()*/);


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

    
        for(Solucion sol:error.getSoluciones()){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/busquedaSolucion.fxml"));
                Parent subfileRoot = loader.load();
                // Obtén el controlador del archivo subfile.fxml
                BusquedaSolucionController subfileController = loader.getController();
                subfileController.setDatos("Solucion de ejemplo", sol.getDescripcion(), sol.getId());
                subfileController.initialize(ap);

                
                lista.getChildren().add(subfileRoot);
            }catch (IOException e2) {
                System.out.println(e2);
            }  
        }
        
        //PANEL DE ENLACES EXTERNOS
        
        
        
        //FILTRADO DE SOLUCIONES
        filtrarSoluciones();
        
        for(Etiqueta e: allEtiquetas){
            if(e.getPadre()==null){
                this.comboBoxEtiquetas.getItems().add(e.getNombre());
            }
        }
        comboBoxEtiquetas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            cargarSubetiquetas();
        });
        //TODO: AGREGAR NOTA Y VER LAS NOTAS DEL ERROR
    
    }
    
    private void setError(long id){
        this.idError=id;
    }
    public void setDashboard(DashboardController dash){
        this.dashboard=dash;
    }
    
    private boolean checkIfFileHasExtension(String s) {
        String[] extensionesImagenes={"jpg","png","jpeg","webx"};
        return Arrays.stream(extensionesImagenes).anyMatch(entry -> s.endsWith(entry));
    }

    @FXML
    private void agregarEtiquetaAFiltro(MouseEvent event) {
        String seleccionado=this.comboBoxSubEtiq.getSelectionModel().getSelectedItem();
        if(seleccionado!=null){
            this.etiquetasSeleccionadas.add(seleccionado);
            Text etiqueta=new Text(seleccionado);
            etiqueta.setOnMouseClicked(this::eliminarEtiquetaSeleccionada);
            this.buscador.getChildren().add(etiqueta);
            this.comboBoxSubEtiq.getItems().remove(seleccionado); 
            filtrarSoluciones();
        }
    }
    
private void filtrarSoluciones(){
        boolean tieneEtiqueta=false;
        this.lista.getChildren().clear();
        for(Solucion sol:this.soluciones){
            List<Solucion_Etiqueta>etiquetasSolucion=sol.getSolucion_Etiquetas();
            boolean contieneTodos = etiquetasSolucion.stream()
            .map(Solucion_Etiqueta::getEtiqueta)
            .map(Etiqueta::getNombre)
            .collect(Collectors.toList())
            .containsAll(etiquetasSeleccionadas);
            
            if(contieneTodos){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/busquedaSolucion.fxml"));
                    Parent subfileRoot = loader.load();
                    // Obtén el controlador del archivo subfile.fxml
                    BusquedaSolucionController subfileController = loader.getController();
                    subfileController.setDatos("Solucion de ejemplo", sol.getDescripcion(), sol.getId(), this.dashboard, sol.getSolucion_Etiquetas());
                    subfileController.initialize();

                    lista.getChildren().add(subfileRoot);
                }catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
    
    private void eliminarEtiquetaSeleccionada (MouseEvent event) {
        Text etiqueta = (Text) event.getSource();
        //this.comboBoxEtiquetas.getItems().add(etiqueta.getText());
        this.buscador.getChildren().remove(etiqueta);
        this.etiquetasSeleccionadas.remove(etiqueta.getText());
        filtrarSoluciones();
    }
    
    private void cargarSubetiquetas(){
        this.comboBoxSubEtiq.getItems().clear();
        for(Etiqueta e:this.allEtiquetas){
            if(e.getPadre()!=null){
                if(e.getPadre().equals(comboBoxEtiquetas.getSelectionModel().getSelectedItem()) && !this.etiquetasSeleccionadas.contains(e.getPadre())){
                    this.comboBoxSubEtiq.getItems().add(e.getNombre());
                }
            }else{
                if(e.getNombre().equals(comboBoxEtiquetas.getSelectionModel().getSelectedItem()) && !this.etiquetasSeleccionadas.contains(e.getNombre())){
                    this.comboBoxSubEtiq.getItems().add(e.getNombre());
                }
            }
        }
    }
}
