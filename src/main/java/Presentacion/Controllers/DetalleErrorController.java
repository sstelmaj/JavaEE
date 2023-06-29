/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.Archivo;
import Logica.Clases.Solucion;
import Logica.Clases.Error;
import Logica.Clases.Etiqueta;
import Logica.Clases.Nota;
import Logica.Controladores.ErrorController;
import Logica.Controladores.EtiquetaController;
import Persistencia.Conexion;
import Persistencia.Sesion;
import Presentacion.Componentes.ItemNota;
import Presentacion.PanelCodigoSolucion;
import java.awt.Desktop;
import javafx.application.HostServices;
import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
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
    private GridPane tablaImagenes;
    
    private Error error;
    private String descripcion,codigo,consola,creador;
    private Date fechaModif;
    private List<Archivo> archivos;

    private List<Etiqueta> etiquetasError;
    private List<Etiqueta>allEtiquetas;
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
    @FXML
    private AnchorPane anchPaneDescrip1;
    @FXML
    private ListView<ItemNota> listaNotas;
    @FXML
    private TextArea txtAreaNotas;
    private List<Nota> notasError;
    @FXML
    private AnchorPane anchorFile;
    @FXML
    private TableView<Archivo> tablaArchivos;
    @FXML
    private TableColumn<Archivo, String> columnArchivo;
    @FXML
    private TableColumn<Archivo, String> columnExt;
    private ObservableList<Archivo> listaArchivos;
    private List<Image> listaImagenes=new ArrayList();
    private int imagenesPorPagina = 9; // Número de imágenes por pagina del GridPane
    private int paginaActual = 0; // Página actual del GridPane imagenes
    @FXML
    private Button botonVer;
    @FXML
    private Button prevButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button btnAgregarSolucion;
    @FXML
    private Button btnModificarError;

    public Error getErrorDetalle() {
        return errorDetalle;
    }

    public void setErrorDetalle(Error errorDetalle) {
        this.error = errorDetalle;
        this.initializeConDatos(); 
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
       this.initializeConDatos();
    }
    public void initializeConDatos(){
        if(this.anchPaneGeneral!=null && this.error!=null){
            this.errorDetalle=error;
            soluciones=error.getSoluciones();
            notasError=error.getNotas();
            soluciones.sort(Comparator.comparingInt(Solucion::getPuntos));
            allEtiquetas=EtiquetaController.getInstance().listaEtiquetas();
                this.descripcion=error.getDescripcion();
                this.codigo=error.getCodigo();
                this.creador=error.getUsuario().getNombre();
                this.consola=error.getConsola();
                this.archivos=error.getArchivos();
                this.fechaModif=error.getFechaSubida();
                this.etiquetasError=error.getEtiquetas();

                this.dashboard = DashboardController.getInstance();

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

            listaArchivos=FXCollections.observableArrayList();
            this.columnArchivo.setCellValueFactory(new PropertyValueFactory("nombre"));
            this.columnExt.setCellValueFactory(new PropertyValueFactory("extension"));

            //Divido los archivos en imagenes y archivos
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
            if(error.getLink()!=null && !error.getLink().isEmpty()){
                Archivo linkTemp=new Archivo();
                linkTemp.setUrl(error.getLink());
                linkTemp.setNombre("Link asociado");
                linkTemp.setExtension("web");
                this.listaArchivos.add(linkTemp);
            }
            if(error.getRepositorio()!=null && !error.getRepositorio().isEmpty()){
                Archivo linkTemp=new Archivo();
                linkTemp.setUrl(error.getRepositorio());
                linkTemp.setNombre("Repositorio");
                linkTemp.setExtension("web");
                this.listaArchivos.add(linkTemp);
            }
            this.tablaArchivos.setItems(listaArchivos);
            
            //Panel de imagenes
            showPage(tablaImagenes);
            //Button prevButton = new Button("Anterior");
            prevButton.setOnAction(e -> {
                if (this.paginaActual > 0) {
                    paginaActual--;
                    showPage(tablaImagenes);
                }
            });

            //Button nextButton = new Button("Siguiente");
            nextButton.setOnAction(e -> {
                if (this.paginaActual < getNumPages() - 1) {
                    this.paginaActual++;
                    showPage(tablaImagenes);
                }
            });

            // Agregar un ChangeListener a la propiedad widthProperty y heightProperty del GridPane
            tablaImagenes.widthProperty().addListener((obs, oldVal, newVal) -> {
                for (Node node : tablaImagenes.getChildren()) {
                    if(node instanceof ImageView){
                        ImageView imageView = (ImageView) node;
                        imageView.setFitWidth(newVal.doubleValue() / 3);
                    }
                }
            });

            tablaImagenes.heightProperty().addListener((var obs, var oldVal, var newVal) -> {
                for (Node node : tablaImagenes.getChildren()) {
                    if(node instanceof ImageView){
                        ImageView imageView = (ImageView) node;
                        imageView.setFitHeight(newVal.doubleValue() / 3);
                    }
                }
            });

                //Cargar datos en panel de descripcion y consola
                Text tituloDescripcion=new Text("Descripcion del error \n");
                tituloDescripcion.getStyleClass().add("titulos");
                Text txtDescripcion=new Text("Creado por: "+creador+ "\nDescripcion: "+descripcion);
                //tituloDescripcion.setFill(Color.BLACK);
                //tituloDescripcion.setFont(Font.font("Helvetica", FontPosture.ITALIC, 19));
                txtDescripcion.setFill(Color.GRAY);
                txtDescripcion.setFont(Font.font("Calibri", FontPosture.ITALIC, 15));
                txtFlowDescripcion.getChildren().addAll(tituloDescripcion,txtDescripcion);
                hBoxEtiquetas.setSpacing(10);
                for(Etiqueta e:etiquetasError){
                    Label etiqueta=new Label(e.getNombre());
                    etiqueta.getStyleClass().add("etiquetas");
                    hBoxEtiquetas.getChildren().add(etiqueta);
                }


                Text txtConsola=new Text(this.consola);
                txtConsola.setFill(Color.GRAY);
                txtConsola.setFont(Font.font("Calibri", FontPosture.ITALIC, 15));
                this.txtFlowConsola.getChildren().addAll(txtConsola);


                this.txtFechaModif.setText(txtFechaModif.getText()+new SimpleDateFormat("dd-MM-yyyy").format(this.fechaModif)/*this.fechaModif.toString()*/);

            for(Solucion sol:error.getSoluciones()){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/busquedaSolucion.fxml"));
                    Parent subfileRoot = loader.load();
                    // Obtén el controlador del archivo subfile.fxml
                    BusquedaSolucionController subfileController = loader.getController();
                    subfileController.setDatos("Solucion de ejemplo", sol.getDescripcion(), sol.getId(), this.dashboard, sol.getEtiquetas());
                    subfileController.initialize();


                    lista.getChildren().add(subfileRoot);
                }catch (IOException e2) {
                    System.out.println(e2);
                }  
            }

            //BOTON MODIFICAR ERROR
            if(!Objects.equals(Sesion.getInstance().getUsuario().getId(), error.getUsuario().getId())){
                this.btnModificarError.setDisable(true);
            }

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

            //PANEL NOTAS
            cargarNotas();
        }
    }
    
    private void setError(Error error){
        this.error=error;
        this.initializeConDatos();
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
            Label etiqueta=new Label(seleccionado);
            etiqueta.getStyleClass().add("etiquetas");
            //Text etiqueta=new Text(seleccionado);
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
            List<Etiqueta>etiquetasSolucion=sol.getEtiquetas();
            boolean contieneTodos = etiquetasSolucion.stream()
            .map(Etiqueta::getNombre)
            .collect(Collectors.toList())
            .containsAll(etiquetasSeleccionadas);
            
            if(contieneTodos){
                try {
                    String displayedText=this.cortarDescripcion(sol.getDescripcion());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/busquedaSolucion.fxml"));
                    Parent subfileRoot = loader.load();
                    // Obtén el controlador del archivo subfile.fxml
                    BusquedaSolucionController subfileController = loader.getController();
                    subfileController.setDatos("Solucion", displayedText, sol.getId(), this.dashboard, sol.getEtiquetas());
                    subfileController.initialize();

                    lista.getChildren().add(subfileRoot);
                }catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
    
    private void eliminarEtiquetaSeleccionada (MouseEvent event) {
        Label etiqueta = (Label) event.getSource();
        this.comboBoxSubEtiq.getItems().add(etiqueta.getText());
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
    
    private void cargarNotas(){
        listaNotas.getItems().clear(); 
        for(Nota nota:notasError){
          
            ItemNota itemNota=new ItemNota();
            itemNota.setContenidoNota(nota.getContenido());
            itemNota.setTxtFecha(new SimpleDateFormat("dd-MM-yyyy").format(nota.getFechaSubida()));
            listaNotas.getItems().add(itemNota);
           
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
    private void agregarNota(MouseEvent event) {
        Nota nota= new Nota();
        Date fecha=Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if(!this.txtAreaNotas.getText().isEmpty()){
            List <Nota>notas= this.errorDetalle.getNotas();
            nota.setContenido(this.txtAreaNotas.getText());
            nota.setFechaSubida(fecha);
            notas.add(nota);
            Conexion.getInstance().persist(nota);
            this.errorDetalle.setNotas(notas);
            Conexion.getInstance().merge(errorDetalle);
        }
        this.notasError=this.errorDetalle.getNotas();
        cargarNotas();
    }
    
    @FXML
    private void mostrarArchivos(){
        if(this.tablaArchivos.getSelectionModel().getSelectedItem()!=null){
            Archivo archivoTemp=this.tablaArchivos.getSelectionModel().getSelectedItem();
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
    }

    @FXML
    private void agregarSolucion(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        try {
                 loader.setLocation(getClass().getResource("/fxml/subirSolucion.fxml"));
                 Parent nuevaVista = loader.load();
                 SubirSolucionController subirSolucionController=(SubirSolucionController)loader.getController();
                 
                 subirSolucionController.setErrorAsociado(this.error);
                 
                 DashboardController dashboardController = DashboardController.getInstance();
                 dashboardController.setControladorAnterior(this);
                // dashboardController.setControladorSiguiente();
                 
                 dashboardController.getAnchorPane().getChildren().setAll(nuevaVista);
             } catch (IOException ex) {
                 System.out.println(ex);
             }
    }

    @FXML
    private void modificarError(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        try {
                 loader.setLocation(getClass().getResource("/fxml/subirError.fxml"));
                 Parent nuevaVista = loader.load();
                 SubirErrorController subirErrorController=(SubirErrorController)loader.getController();
                 
                 subirErrorController.setErrorModificar(this.error);
                 subirErrorController.setTipoPantalla("Modificar Error");
                 subirErrorController.setPanelContent(this.getAnchPaneGeneral());
                 
                 DashboardController dashboardController = DashboardController.getInstance();
                 dashboardController.setControladorAnterior(this);
                // dashboardController.setControladorSiguiente();
                 
                 dashboardController.getAnchorPane().getChildren().setAll(nuevaVista);
             } catch (IOException ex) {
                 System.out.println(ex);
        }
    }
    
    private String cortarDescripcion(String descripcion){
    // Obtener la posición de la cadena ingresada en el texto original
        int startIndex = 0;

        // Calcular los índices de inicio y fin para mostrar una porción del texto original
        int maxDisplayedLength = 40; // Cantidad máxima de caracteres a mostrar
        int textLength = descripcion.length();

        int endIndex = Math.min(startIndex + maxDisplayedLength, textLength);
        startIndex = Math.max(endIndex - maxDisplayedLength, 0);

        String displayedText = descripcion.substring(startIndex, endIndex);
        displayedText= displayedText.replaceAll("\\r?\\n", " ");

        // Actualizar el texto del Label con la porción correspondiente
        return displayedText;
    }
}
