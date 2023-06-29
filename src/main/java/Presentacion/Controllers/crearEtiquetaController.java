/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Clases.Etiqueta;
import Logica.Clases.Solucion;
import Logica.Clases.Usuario;
import Logica.Controladores.EtiquetaController;
import Persistencia.Conexion;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.skin.TreeViewSkin;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import me.davidthaler.draggablejfxtreeview.DraggableCellFactory;



/**
 * FXML Controller class
 *
 * @author joaco
 */
public class crearEtiquetaController implements Initializable {
    
    @FXML
    private TreeView arbolEtiquetas;
    
    private TreeItem<String> clickedItem;
    
    private StringProperty tipoPantallaProperty = new SimpleStringProperty("");
    
    private String tipoPantalla ="-";
    
    private Etiqueta etiquetaModificar;
    
    private TreeItem<String> rootItem = new TreeItem("Etiquetas");
    
    ObservableList<String> items;
    @FXML
    private Label textTitulo;
    
    public StringProperty tipoPantallaProperty() {
        return tipoPantallaProperty;
    }

    public final String getTipoPantalla() {
        return tipoPantallaProperty.get();
    }

    public final void setTipoPantalla(String tipoPantalla) {
        tipoPantallaProperty.set(tipoPantalla);
    }
    
    public final void setEtiquetaModificar(Etiqueta etiqueta) {
        this.etiquetaModificar = etiqueta;
    }
    
    @FXML
    private Button ingresarEtiqueta;
    @FXML
    private Button agregarSubEtiqueta;
    @FXML
    private Button verificarEtiqueta;
    @FXML
    private TextField textEtiquetaPadre;
    
    private List<Etiqueta> etiquetas_eliminar = new ArrayList<>() ;
    
    Map<String, Etiqueta> mapEtiquetas = new HashMap<>();
    @FXML
    private ListView<String> listaSubEtiquetas;
    @FXML
    private TextField textSubEtiqueta;
    @FXML
    private Button botonQuitar;
    
    
    @FXML
    private Button botonEliminarEtiqueta;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        actualizarArbol();
        tipoPantallaProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue == "Modificar Etiqueta") {

                System.out.println("Modificar Usuario");
                tipoPantalla = newValue;
                textTitulo.setText(newValue);
                ingresarEtiqueta.setText("Guardar Cambios");

                if (etiquetaModificar != null) {

                    textEtiquetaPadre.setText(etiquetaModificar.getNombre());
                    textEtiquetaPadre.setEditable(false);

                    if (etiquetaModificar.getSub_etiqueta() != null) {
                        for (Etiqueta sub_etiqueta : etiquetaModificar.getSub_etiqueta()) {
                            if (sub_etiqueta.getActive()) {
                                listaSubEtiquetas.getItems().add(sub_etiqueta.getNombre());
                            }
                        }
                    }
                }
            } else if (newValue == "Crear Usuario") {
                System.out.println("Crear Usuario");
                tipoPantalla = newValue;
                textTitulo.setText(newValue);
            }

        });

        //botonEliminarEtiqueta.setOnAction(event -> eliminarEtiqueta());    
    }
    
    @FXML
    private void clickIngresarEtiqueta(ActionEvent event) {
        if(EtiquetaController.getInstance().existeEtiqueta(textEtiquetaPadre.getText())){
            boolean agregarSubEtiquetas = ConfirmationDialog.show("La etiqueta padre ya existe. ¿Desea agregar las sub-etiquetas?", "Confirmacion");
            if (agregarSubEtiquetas) {
                agregarEtiquetas(true);
            } 
        } else {
            agregarEtiquetas(false);
        }
    }

    @FXML
    private void clickVerificar(ActionEvent event) {
        if(!textEtiquetaPadre.getText().equals("")){
            System.out.println("prueba");
            boolean exist = EtiquetaController.getInstance().existeEtiqueta(textEtiquetaPadre.getText());
            if(exist){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("¡Ya existe esa etiqueta padre!");

                alert.showAndWait();
            
            }else{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("La etiqueta no existe");

                alert.showAndWait();
            }
        }
    }

    @FXML
    private void clickAgregarSubEtiqueta(ActionEvent event) {
        boolean exist = EtiquetaController.getInstance().existeSubEtiqueta(textSubEtiqueta.getText(), textEtiquetaPadre.getText());
        if(textSubEtiqueta.getText().equals("")){
             Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("El campo de la sub-etiqueta no puede estar vacio!");
                alert.showAndWait();
        }
        else if (exist){
             Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Esa etiqueta ya existe!");
                alert.showAndWait();
        }
        else if (listaSubEtiquetas.getItems().contains(textSubEtiqueta.getText())){
            Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Ya has agregado esta sub-etiqueta!");
                alert.showAndWait();
        } else {
            listaSubEtiquetas.getItems().add(textSubEtiqueta.getText());
        }
    }

    @FXML
    private void clickQuitar(ActionEvent event) {
        int selectedIndex = listaSubEtiquetas.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            // Eliminar el elemento seleccionado de la lista
            listaSubEtiquetas.getItems().remove(selectedIndex);
            System.out.println("Elemento eliminado: " + selectedIndex);
        } else {
             Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Ningun elemento seleccionado");
                alert.showAndWait();
        }
    }
    
    private void agregarEtiquetas(boolean existe){
        EtiquetaController etiquetacontroller=null;
        Etiqueta padre = new Etiqueta();
        padre.setNombre(textEtiquetaPadre.getText());
        
        List<Etiqueta> sub_etiquetas = new ArrayList<>();
        sub_etiquetas = EtiquetaController.getInstance().obtenerSubEtiquetas(padre.getNombre());
        
        items = listaSubEtiquetas.getItems();
            
            if (items != null) {
            for (String item : items) {
                System.out.println(item);
                if (item.equals(textEtiquetaPadre.getText())) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Información");
                    alert.setHeaderText(null);
                    alert.setContentText("la subetiqueta: " + item + " no puede ser igual a la etiqueta padre!");
                    alert.showAndWait();
                } else {
                    Etiqueta etiq = new Etiqueta();
                    etiq.setNombre(item);
                    etiq.setPadre(padre.getNombre());
                    sub_etiquetas.add(etiq);
                }
            }
        }
        padre.setSub_etiqueta(sub_etiquetas);
        try {

            Conexion.getInstance().merge(padre);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("Se ha creado la etiqueta con exito!");
            alert.showAndWait();
            actualizarArbol();
            listaSubEtiquetas.getItems().clear();
            textEtiquetaPadre.clear();
            textSubEtiqueta.clear();
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace();
        }
    }
    
    private void actualizarArbol() {
        List<Etiqueta> etiquetas_padre = EtiquetaController.getInstance().listaEtiquetasActivas();
        rootItem.getChildren().clear();
        for (Etiqueta etiqueta : etiquetas_padre) {
            if(etiqueta.getPadre() == null && etiqueta.getActive()){
                TreeItem<String> item_padre = new TreeItem(etiqueta.getNombre());
                
                rootItem.getChildren().add(item_padre);
                for(Etiqueta sub_etiqueta : etiqueta.getSub_etiqueta()){
                    if(sub_etiqueta.getActive()){
                        TreeItem<String> item_sub = new TreeItem(sub_etiqueta.getNombre());
                         item_padre.getChildren().add(item_sub);
                    }
                }
                rootItem.getChildren().sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
            }   
            if(etiqueta.getActive()){
                mapEtiquetas.put(etiqueta.getNombre(), etiqueta);
            }
        }
        
    //    rootItem.getChildren().addAll(rootItemSO,rootItemFW);
        arbolEtiquetas.setRoot(rootItem);
        arbolEtiquetas.setShowRoot(false);
    }

    @FXML
    private void eliminarEtiqueta() {
        TreeView treeView = arbolEtiquetas;

        TreeItem<String> seleccionado = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        String nombre_etiqueta = seleccionado.getValue();
        Etiqueta et = EtiquetaController.getInstance().obtenerEtiqueta(nombre_etiqueta);
        Etiqueta padre = EtiquetaController.getInstance().obtenerEtiqueta(et.getPadre());
        
        
        boolean eliminarEtiqueta = ConfirmationDialog.show("¿Seguro que desea eliminar la etiqueta y sus sub-etiquetas? Se eliminará la/s etiqueta/s en aquellos errores y soluciones que la/s tengan.", "Confirmacion");
        if (eliminarEtiqueta) {
            borrarEtiqueta(seleccionado);
            if (padre!=null){
                Conexion.getInstance().getEntity().refresh(padre);
            }
            actualizarArbol();
        } else {
            System.out.println("etiqueta no eliminada");
        }
        
    }
    
    private void borrarEtiqueta(TreeItem<String> item) {
        item.getChildren().forEach(child -> borrarEtiqueta(child));
        // Aquí implementa tu lógica de eliminación para el item
        System.out.println("Eliminando etiqueta: " + item.getValue());
        Etiqueta etiqueta;
        etiqueta = EtiquetaController.getInstance().obtenerEtiqueta(item.getValue());
        Conexion.getInstance().eliminar(etiqueta);
    }
    
}