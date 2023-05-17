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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JInternalFrame;
import jdk.nashorn.api.scripting.JSObject;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

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
    private ComboBox<String> comboTecnologia;
    @FXML
    private TextField textFieldTitulo;
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
    private ListView<String> listaEtiquetas;
    
    private SwingNode swingNode;
    
    private SwingNode swingNodeConsole;
    @FXML
    private TextArea textDescripcion;
    @FXML
    private TextField textFieldRepositorio;
    private TextField filtroEtiquetas;
    
    private  ObservableList<String> items;
    private ListView<String> etiquetasSeleccionadas;
   
    @FXML
    private ListView<String> listaCompletado;
    @FXML
    private AnchorPane anchorPrueba;
    private AnchorPane anchorPrueba2;
    
    private Stage popupStage;
    
    private ListView<String> listView;
    
    private List<Tecnologia> tecnologias;
    
    private ObservableList<String> originalItems;
    
    private String ignoredText = "";
    @FXML
    private String tipoPantalla ="-";
    @FXML
    private Button btn_prueba;
    
    @FXML
    private Label textTitulo;
    
    private Logica.Clases.Error errorModificar;
    
    private Logica.Clases.Error error;
    
    private StringProperty tipoPantallaProperty = new SimpleStringProperty("");

    public StringProperty tipoPantallaProperty() {
        return tipoPantallaProperty;
    }

    public final String getTipoPantalla() {
        return tipoPantallaProperty.get();
    }

    public final void setTipoPantalla(String tipoPantalla) {
        tipoPantallaProperty.set(tipoPantalla);
    }
    
    public final void setErrorModificar(Logica.Clases.Error error) {
        this.errorModificar = error;
    }
   
    /**
      
   * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //para el primer renderizado visual
         tipoPantallaProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue=="Modificar Error") {
                
               
                System.out.println("Modificar Error");
                tipoPantalla =newValue;
                textTitulo.setText(newValue);
                botonIngresar.setText("Guardar Cambios");
               
                if(errorModificar != null){
                    textDescripcion.setText(errorModificar.getDescripcion());
                    textFieldTitulo.setText(errorModificar.getTitulo());
                     linkTextFieldUrl.setText(errorModificar.getLink());
                     textFieldRepositorio.setText(errorModificar.getRepositorio());
                    
                    String fechaSubida = errorModificar.getFechaSubida().toString();
                    if(fechaSubida != null){
                       DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                        String fechaString;
                        Date fechaDate;
                        try {
                            fechaDate = dateFormat.parse(fechaSubida);
                            Instant instant = fechaDate.toInstant();
                            ZoneId zoneId = ZoneId.systemDefault();
                            LocalDate fechaLocalDate = instant.atZone(zoneId).toLocalDate();
                            inputFecha.setValue(fechaLocalDate);
                            System.out.println(fechaLocalDate); 
                                
                        } catch (ParseException ex) {
                            Logger.getLogger(SubirErrorController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    JInternalFrame internalFrameConsola = (JInternalFrame) swingNodeConsole.getContent();
       
                    //obtenemos el contenido de el internal frame
                    if (internalFrameConsola instanceof RSTA) {
                        RSTA rsta = (RSTA) internalFrameConsola;
                        rsta.setTextAreaContenido(errorModificar.getConsola());
                    }   
                    JInternalFrame internalFrameCodigo = (JInternalFrame) swingNode.getContent();
       
                    //obtenemos el contenido de el internal frame
                    if (internalFrameCodigo instanceof RSTA) {
                        RSTA rsta = (RSTA) internalFrameCodigo;
                        rsta.setTextAreaContenido(errorModificar.getCodigo());
                              
                    } 

                }

            }
            else if(newValue=="Subir Error"){
                System.out.println("Subir Error");
                tipoPantalla =newValue;
                textTitulo.setText(newValue);
            }
              
        });
         
         
        //para poder filtrar luego aca obtengo el observable list para el filtrado
        try {
            List<Etiqueta> etiquetas = Conexion.getInstance().listaEtiquetas();

            // Crear un ObservableList a partir de la lista existente
             items = FXCollections.observableArrayList();

            // Agregar los elementos a la lista ObservableList
            for (Etiqueta etiqueta : etiquetas) {
                items.add(etiqueta.getNombre());
            }

            //asigno a la lista en la descripcion
            listaCompletado.setItems(items);
           
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace();
        }
        
      
          
        textDescripcion.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains("#")) {
                filterListViewD(listaCompletado, newValue, items);
            }
             else {
                // Restaurar la lista original cuando se borra el carácter "@"
                listaCompletado.setItems(items);
            }
        });
        

        listaCompletado.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String selectedItem = listaCompletado.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int atIndex = textDescripcion.getText().lastIndexOf("#");
                    if (atIndex != -1) {
                        String prefix = textDescripcion.getText().substring(atIndex, atIndex + 1);
                        String updatedSelectedItem = prefix + selectedItem + " "; // Agregar un espacio después del item
                        textDescripcion.replaceText(atIndex, textDescripcion.getLength(), updatedSelectedItem);
                        textDescripcion.requestFocus();
                    }
                }
            }
        });
        
        textDescripcion.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.UP || keyCode == KeyCode.DOWN) {
                event.consume(); // Evita que el evento se propague a otros componentes

                if (listaCompletado.getItems().isEmpty()) {
                    return; // Sale del método si la lista está vacía
                }

                listaCompletado.requestFocus(); // Establece el foco en el ListView
                listaCompletado.getSelectionModel().selectFirst(); // Selecciona el primer elemento del ListView
            }
        });
        
        //para actualizar los datos luego de instanciar
        
        rstaCode();
        
        rstaConsola();
        
         
     
      
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
      
       
       
       //buscamos el internal frame del codigo
       JInternalFrame internalFrame = (JInternalFrame) swingNode.getContent();
       
       if(tipoPantalla.equals("Subir Error")){
             this.error = new Logica.Clases.Error();
       }else if(tipoPantalla.equals("Modificar Error")){
           this.error = this.errorModificar;
           System.out.println("Quiere Modificar");
       }
        //obtenemos el contenido de el internal frame
        if (internalFrame instanceof RSTA) {
            RSTA rsta = (RSTA) internalFrame;
            RSyntaxTextArea textArea = rsta.getTextArea();

            // Accedemos al text area
            String contenido = textArea.getText();

            System.out.print(contenido);
            //seteamos el contenido
            error.setCodigo(contenido);
        } else {
            
        }
        
        error.setTitulo(textFieldTitulo.getText());
        
        JInternalFrame internalFrameConsola = (JInternalFrame) swingNodeConsole.getContent();
       
        //obtenemos el contenido de el internal frame
        if (internalFrameConsola instanceof RSTA) {
            RSTA rsta = (RSTA) internalFrameConsola;
            RSyntaxTextArea textArea = rsta.getTextArea();

            // Accedemos al text area
            String contenido = textArea.getText();

            System.out.print(contenido);
            //seteamos el contenido
            error.setConsola(contenido);
        } else {
            
        }
        
        error.setDescripcion(textDescripcion.getText());
        
    //    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("es")); (util para despues o capaz el input fecha lo modifica)

        // Formatear LocalDate a String en español
    //    String fechaFormateada = localDate.format(formatter);
        Date date = Date.from(inputFecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(date);
        error.setFechaSubida(date);
        error.setLink(linkTextFieldUrl.getText());
        error.setRepositorio(textFieldRepositorio.getText());
        
        Conexion.getInstance().persist(error);
        
    }

    
    private void llenarTecnologias() {
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
    }

    //filtrado de las etiquetas para ingresar en la descripcion
    private void filterListViewD(ListView<String> listView, String filterText, ObservableList<String> items) {
    // Verificar si el texto de búsqueda contiene al menos un carácter "@"
    if (filterText.contains("#")) {
        
        // Obtener la última ocurrencia del carácter "@"
        int lastIndex = filterText.lastIndexOf("#");

        // Obtener el texto después del último carácter "@" y hasta el próximo espacio (o hasta el final del texto)
        String searchText = filterText.substring(lastIndex + 1);

        // Verificar si se ingresa un espacio después del texto filtrado
        if (searchText.endsWith(" ")) {
            // Restaurar la lista original
            listView.setItems(items);
        } else {
            // Crear un nuevo filtro utilizando el texto ingresado
            Predicate<String> filter = item ->
                    item.toLowerCase().contains(searchText.toLowerCase());

            // Filtrar la lista de etiquetas utilizando el filtro
            List<String> filteredList = items.filtered(filter);

            // Actualizar la lista del ListView con los elementos filtrados
            listView.setItems(FXCollections.observableArrayList(filteredList));
        }
        
        
    } else {
        // Restaurar la lista original cuando no se encuentra el carácter "@"
        listView.setItems(items);
    }
    
    
}
    
    public void rstaCode(){
        try {
        
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
         
        }catch (Exception e) {
              // Manejo de la excepción
              e.printStackTrace();
        }
    
    
    }
    
    public void rstaConsola(){
        try {
                JInternalFrame iFrameConsole = new RSTA();
                iFrameConsole.setPreferredSize(new Dimension(550,400));
                iFrameConsole.setSize(20, 20);
                iFrameConsole.setVisible(true);
              //  iFrameConsole.setAlwaysOnTop(true);
                iFrameConsole.setBorder(null);
                ((javax.swing.plaf.basic.BasicInternalFrameUI) iFrameConsole.getUI()).setNorthPane(null);
                 
                swingNodeConsole = new SwingNode();

                swingNodeConsole.setContent(iFrameConsole);
                scrollConsole.setContent(swingNodeConsole);
            } catch (Exception e) {
                // Manejo de la excepción
                e.printStackTrace();
            }
    }
    @FXML
     public void setPantalla(String pantalla) {
            this.tipoPantalla=pantalla;
            
      }
    @FXML
    private void clickPrueba(ActionEvent event) {
           System.out.println(tipoPantalla);

        }      
    
}
