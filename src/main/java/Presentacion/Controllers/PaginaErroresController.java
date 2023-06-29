/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.Etiqueta;
import Logica.Controladores.ErrorController;
import Logica.Controladores.EtiquetaController;
import Presentacion.Componentes.ItemLista;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author joaco
 */
public class PaginaErroresController implements Initializable {

    @FXML
    private ComboBox<String> comboSo;
    @FXML
    private ComboBox<String> comboFramework;
    @FXML
    private ListView<Text> listaEtiquetas;
    @FXML
    private ListView<ItemLista> listaErrores;
    @FXML
    private TextField txtBuscador;
    private List<Etiqueta>allEtiquetas;
    private ArrayList<String> valores = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allEtiquetas=EtiquetaController.getInstance().listaEtiquetas();
        
        filtrarErrores();
        
        for(Etiqueta e: allEtiquetas){
            if(e.getPadre()==null){
                this.comboSo.getItems().add(e.getNombre());
            }
        }
        comboSo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            cargarSubetiquetas();
        });
       // ArrayList<String> valores = new ArrayList<>();
        //List<Logica.Clases.Error> errores = ErrorController.getInstance().filtradoErroresPorEtiquetas(valores);
        //comboSo.setOnAction(event -> filtrarErrores());
        //comboFramework.setOnAction(event -> filtrarErrores());
    }   
    
    @FXML
        private void filtrarErrores() {
        String busqueda=this.txtBuscador.getText();
        List<Logica.Clases.Error>erroresBusqueda=null;
        if(!busqueda.isEmpty()){        //Si existe algo ingresado en el buscador obtiene los errores que coincidan
            erroresBusqueda=ErrorController.getInstance().busquedaDeErrores(busqueda);
        }else{
            erroresBusqueda = ErrorController.getInstance().obtenerErrores();
        }
        
        String seleccionSo = (String) comboSo.getValue();
        String seleccionFramework = (String) comboFramework.getValue();

        if(this.listaEtiquetas.getItems()!=null){
            if(this.valores.isEmpty()){
                if(erroresBusqueda !=null){
                     listaErrores.getItems().clear(); 
                     for (Logica.Clases.Error error : erroresBusqueda) {
                         // Obtener la parte del texto original que coincide con el texto ingresado
                         //String displayedText = error.getDescripcion().substring(0, Math.min(error.getDescripcion().length(), this.txtBuscador.getText().length()));
                         String displayedText= this.cortarDescripcion(this.txtBuscador.getText(),error.getDescripcion());
                         ItemLista item = new ItemLista("Error",this);
                         item.getStylesheets().add(getClass().getResource("/styles/detalles.css").toExternalForm());
                         item.setError(error);
                         item.setTitulo(error.getTitulo());
                         item.setEtiquetas(error.getEtiquetas());
                         item.setTxtDescripcion(displayedText);
                         listaErrores.getItems().add(item);
                     }
                 }
           //listaErrores.getItems().setAll(errores);
           //this.valores.clear();
            }else{
                List<Logica.Clases.Error> errores = ErrorController.getInstance().filtradoErroresPorEtiquetas(valores);
                System.out.println(errores.size());
                //this.valores.clear();
                // Actualizar la lista de errores en el ListView
                if(errores != null){
                    listaErrores.getItems().clear(); 
                    for (Logica.Clases.Error error : errores) {
                        //String displayedText = error.getDescripcion().substring(0, Math.min(error.getDescripcion().length(), this.txtBuscador.getText().length()));
                        String displayedText= this.cortarDescripcion(this.txtBuscador.getText(),error.getDescripcion());
                        if(erroresBusqueda!= null && erroresBusqueda.contains(error)){
                            ItemLista item = new ItemLista("Error",this);
                            item.getStylesheets().add(getClass().getResource("/styles/detalles.css").toExternalForm());
                            item.setError(error);
                            item.setTitulo(error.getTitulo());
                            item.setEtiquetas(error.getEtiquetas());
                            item.setTxtDescripcion(displayedText);
                            listaErrores.getItems().add(item);
                        }
                    }
                //listaErrores.getItems().setAll(errores);
                }
            }   
        }
    }
        
        private void cargarSubetiquetas(){
        this.comboFramework.getItems().clear();
        for(Etiqueta e:this.allEtiquetas){
            if(e.getPadre()!=null){
                if(e.getPadre().equals(comboSo.getSelectionModel().getSelectedItem()) && !this.valores.contains(e.getNombre())){
                    this.comboFramework.getItems().add(e.getNombre());
                }
            }else{
                if(e.getNombre().equals(comboSo.getSelectionModel().getSelectedItem()) && !this.valores.contains(e.getNombre())){
                    this.comboFramework.getItems().add(e.getNombre());
                }
            }
        }
    }

    @FXML
    private void agregarEtiquetaAFiltro(MouseEvent event) {
        String seleccionado=this.comboFramework.getSelectionModel().getSelectedItem();
        if(seleccionado!=null){
            this.valores.add(seleccionado);
            Text etiqueta=new Text(seleccionado);
            etiqueta.setOnMouseClicked(this::eliminarEtiquetaSeleccionada);
            this.listaEtiquetas.getItems().add(etiqueta);
            this.comboFramework.getItems().remove(seleccionado); 
            filtrarErrores();
        }
    }
    
    private void eliminarEtiquetaSeleccionada (MouseEvent event) {
        Text etiqueta = (Text) event.getSource();
        this.comboFramework.getItems().add(etiqueta.getText());
        this.listaEtiquetas.getItems().remove(etiqueta);
        this.valores.remove(etiqueta.getText());
        filtrarErrores();
    }
    
    private String cortarDescripcion(String buscador,String descripcion){
    // Obtener la posición de la cadena ingresada en el texto original
            int startIndex = descripcion.indexOf(buscador);

            if (startIndex != -1) {
                // Calcular los índices de inicio y fin para mostrar una porción del texto original
                int maxDisplayedLength = 50; // Cantidad máxima de caracteres a mostrar
                int textLength = descripcion.length();

                int endIndex = Math.min(startIndex + maxDisplayedLength + 10, textLength);
                startIndex = Math.max(endIndex - maxDisplayedLength - 10, 0);

                // Obtener la porción del texto original que contiene la cadena ingresada en el centro
                String displayedText = descripcion.substring(startIndex, endIndex);
                displayedText= displayedText.replaceAll("\\r?\\n", " ");

                // Actualizar el texto del Label con la porción correspondiente
                return displayedText;
            }else{
                String displayedText = descripcion.substring(0, 20);
                displayedText= displayedText.replaceAll("\\r?\\n", " ");
                return displayedText;
            }
    }
    
}
