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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
    private ListView<String> listaEtiquetas;
    
    private SwingNode swingNode;
    
    private SwingNode swingNodeConsole;
    @FXML
    private TextArea textDescripcion;
    @FXML
    private TextField textFieldRepositorio;
    @FXML
    private TextField filtroEtiquetas;
    
    private  ObservableList<String> items;
    @FXML
    private Button botonAgregarEtiqueta;
    @FXML
    private ListView<String> etiquetasSeleccionadas;
    private final String[] palabrasClave = {"auto", "automóvil", "autobús", "autopista"};
    @FXML
    private ListView<String> listaCompletado;
    @FXML
    private AnchorPane anchorPrueba;
    @FXML
    private AnchorPane anchorPrueba2;
    @FXML
    private TextArea textPrueba8;
    @FXML
    private TextArea text50;
    
    private Stage popupStage;
    
    private ListView<String> listView;
    
    private List<Tecnologia> tecnologias;
    
    private ObservableList<String> originalItems;
    
    private String ignoredText = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ///Prueba de 
//        ObservableList<String> items = FXCollections.observableArrayList(
//                "Elemento 1",
//                "Elemento 2",
//                "Elemento 3"
//        );

        // Crear el componente ListView
//        listView = new ListView<>(items);
        
         text50.addEventHandler(KeyEvent.KEY_TYPED, event -> {
            if (event.getCharacter().equals("@")) {
            //    openPopup();
            //    updatePopupPosition();
                System.out.println("hola");
            }
        });

        
        llenarTecnologias();

        
        
        
        RSyntaxTextArea rsyntax = new RSyntaxTextArea(20, 60);;
        RTextScrollPane sp = new RTextScrollPane(rsyntax);
        SwingNode swingNodeConsole2 = new SwingNode();
        CompletionProvider provider = createCompletionProvider();


        AutoCompletion ac = new AutoCompletion(provider);
        ac.setShowDescWindow(true);
        ac.setParameterAssistanceEnabled(true);
   
        ac.setAutoCompleteEnabled(true);
        ac.setAutoActivationEnabled(true);
        ac.setAutoCompleteSingleChoices(true);
        ac.setAutoActivationDelay(800);
         ac.install(rsyntax);
        swingNodeConsole2.setContent(sp);
         anchorPrueba2.getChildren().add(swingNodeConsole2);
         
         
         
         
        //para poder filtrar luego aca obtengo el observable list para el filtrado
        try {
            List<Etiqueta> etiquetas = Conexion.getInstance().listaEtiquetas();

            // Crear un ObservableList a partir de la lista existente
             items = FXCollections.observableArrayList();

            // Agregar los elementos a la lista ObservableList
            for (Etiqueta etiqueta : etiquetas) {
                items.add(etiqueta.getNombre());
            }

            // Asignar el ObservableList al ListView
            listaEtiquetas.setItems(items);
            //asigno a la lista en la descripcion
            listaCompletado.setItems(items);
           
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace();
        }
        
       filtroEtiquetas.textProperty().addListener((observable, oldValue, newValue) -> {
            filterListView(listaEtiquetas, newValue, items);
        });
          
        textDescripcion.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains("@")) {
                filterListViewD(listaCompletado, newValue, items);
            }
             else {
                // Restaurar la lista original cuando se borra el carácter "@"
                listaCompletado.setItems(items);
            }
        });
        
//        listaCompletado.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.ENTER) {
//                String selectedItem = listaCompletado.getSelectionModel().getSelectedItem();
//                if (selectedItem != null) {
//                    int atIndex = textDescripcion.getText().lastIndexOf("@");
//                    if (atIndex != -1) {
//                        textDescripcion.insertText(atIndex + 1, selectedItem);
//                    }
//                }
//            }
//        });
        listaCompletado.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String selectedItem = listaCompletado.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int atIndex = textDescripcion.getText().lastIndexOf("@");
                    if (atIndex != -1) {
                        String prefix = textDescripcion.getText().substring(atIndex, atIndex + 1);
                        String updatedSelectedItem = prefix + selectedItem;
                        textDescripcion.replaceText(atIndex, textDescripcion.getLength(), updatedSelectedItem);
                    }
                }
            }
        });
        
        
        
        
        
        JInternalFrame iFrame = new RSTA_TEXT();
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
                JInternalFrame iFrameConsole = new RSTA_TEXT();
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
       Logica.Clases.Error error = new Logica.Clases.Error();
       
       
       //buscamos el internal frame del codigo
       JInternalFrame internalFrame = (JInternalFrame) swingNode.getContent();
       
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

    @FXML
    private void agregarEtiqueta(ActionEvent event) {
        
        String selectedItem = listaEtiquetas.getSelectionModel().getSelectedItem();

        // Verifica si se seleccionó algún elemento
        if (selectedItem != null) {
            // Agrega el elemento a la ListView de destino
            etiquetasSeleccionadas.getItems().add(selectedItem);
            
            
        }
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

    
    private void filterListViewD(ListView<String> listView, String filterText, ObservableList<String> items) {
    // Verificar si el texto de búsqueda contiene al menos un carácter "@"
    if (filterText.contains("@")) {
        // Obtener la última ocurrencia del carácter "@"
        int lastIndex = filterText.lastIndexOf("@");

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
    
  
   
    
    private void filterListView(ListView<String> listView, String filterText, ObservableList<String> items) {
    // Crear un nuevo filtro utilizando el texto ingresado
    Predicate<String> filter = item ->
            item.toLowerCase().contains(filterText.toLowerCase());

    // Filtrar la lista de etiquetas utilizando el filtro
    List<String> filteredList = items.filtered(filter);

    // Actualizar la lista del ListView con los elementos filtrados
    listView.setItems(FXCollections.observableArrayList(filteredList));
}
    
    private CompletionProvider createCompletionProvider() {

     
      DefaultCompletionProvider provider = new DefaultCompletionProvider();
        provider.setAutoActivationRules(true, "@");

      // Add completions for all Java keywords. A BasicCompletion is just
      // a straightforward word completion.
      provider.addCompletion(new BasicCompletion(provider, "abstract"));
      provider.addCompletion(new BasicCompletion(provider, "assert"));
      provider.addCompletion(new BasicCompletion(provider, "break"));
      provider.addCompletion(new BasicCompletion(provider, "case"));
      // ... etc ...
      provider.addCompletion(new BasicCompletion(provider, "transient"));
      provider.addCompletion(new BasicCompletion(provider, "try"));
      provider.addCompletion(new BasicCompletion(provider, "void"));
      provider.addCompletion(new BasicCompletion(provider, "volatile"));
      provider.addCompletion(new BasicCompletion(provider, "while"));

      // Add a couple of "shorthand" completions. These completions don't
      // require the input text to be the same thing as the replacement text.
      provider.addCompletion(new ShorthandCompletion(provider, "sysout",
            "System.out.println(", "System.out.println("));
      provider.addCompletion(new ShorthandCompletion(provider, "syserr",
            "System.err.println(", "System.err.println("));

      return provider;

   }
//    private void openPopup() {
//        // Crear un nuevo escenario (Stage) para la ventana emergente
//         popupStage = new Stage();
//
//        // Configurar el contenido de la ventana emergente (puedes usar un nuevo archivo FXML y controlador si es necesario)
//        // Aquí se muestra un ejemplo simple de contenido usando un StackPane con una etiqueta
//        popupStage.setAlwaysOnTop(true);
//        StackPane popupRoot = new StackPane(listView);
//        Scene popupScene = new Scene(popupRoot, 200, 100);
//        popupStage.setScene(popupScene);
//
//        // Mostrar la ventana emergente
//        popupStage.show();
//    }
    
//    private void updatePopupPosition() {
//        int caretPosition = text50.getCaretPosition();
//        int lineNumber = text50.getText().substring(0, caretPosition).split("\n").length;
//        int columnIndex = caretPosition - text50.getText().lastIndexOf('\n', caretPosition - 1) - 1;
//
//        // Calcular las coordenadas de la ventana emergente
//        double x = text50.localToScreen(text50.getBoundsInLocal()).getMinX() + columnIndex * 10;
//        double y = text50.localToScreen(text50.getBoundsInLocal()).getMinY() + lineNumber * 20 + 20;
//
//        // Establecer la posición de la ventana emergente
//        popupStage.setX(x);
//        popupStage.setY(y);
//    }

    
}
