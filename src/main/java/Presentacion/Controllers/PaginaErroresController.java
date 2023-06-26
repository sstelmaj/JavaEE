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
        // Verificar si se han seleccionado valores en ambos ComboBoxes
        /*if (seleccionSo != null || seleccionFramework != null) {
            if(seleccionSo!=null && !seleccionSo.equals("Ninguno")){
                this.valores.add(seleccionSo.trim());
            }
            
            if(seleccionFramework!=null && !seleccionFramework.equals("Ninguno")){
               // valores.add(seleccionFramework);
               this.valores.add(seleccionFramework.trim());
            }
           */
       //     valores.add("Angular");
       if(this.valores.isEmpty()){
           if(erroresBusqueda !=null){
                listaErrores.getItems().clear(); 
                for (Logica.Clases.Error error : erroresBusqueda) {
                    ItemLista item = new ItemLista("Error",this);
                    item.setError(error);
                    item.setTitulo(error.getTitulo());
                    item.setTxtDescripcion(error.getDescripcion());
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
                    if(erroresBusqueda!= null && erroresBusqueda.contains(error)){
                        ItemLista item = new ItemLista("Error",this);
                        item.setError(error);
                        item.setTitulo(error.getTitulo());
                        item.setTxtDescripcion(error.getDescripcion());
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
    
}
