/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion;

import Logica.Clases.Etiqueta;
import Logica.Controladores.EtiquetaController;
import Persistencia.Conexion;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.skin.TreeViewSkin;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
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
    
    @FXML
    private Button ingresarEtiqueta;
    @FXML
    private Button agregarSubEtiqueta;
    @FXML
    private Button verificarEtiqueta;
    @FXML
    private TextField textEtiquetaPadre;
    
    private List<Etiqueta> etiquetas_eliminar = new ArrayList<>() ;
    @FXML
    private ListView<String> listaSubEtiquetas;
    @FXML
    private TextField textSubEtiqueta;
    @FXML
    private Button botonQuitar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        TreeItem<String> rootItem = new TreeItem("Etiquetas");
        List<Etiqueta> etiquetas_padre = EtiquetaController.getInstance().listaEtiquetas();
        
        for (Etiqueta etiqueta : etiquetas_padre) {
            if(etiqueta.getPadre() == null){
                TreeItem<String> item_padre = new TreeItem(etiqueta.getNombre());
                
                rootItem.getChildren().add(item_padre);
                for(Etiqueta sub_etiqueta : etiqueta.getSub_etiqueta()){
                    TreeItem<String> item_sub = new TreeItem(sub_etiqueta.getNombre());
                    item_padre.getChildren().add(item_sub);
                }
            }        
        }
            
        
    //    rootItem.getChildren().addAll(rootItemSO,rootItemFW);
        arbolEtiquetas.setRoot(rootItem);
        arbolEtiquetas.setShowRoot(false);
        //arbolEtiquetas.setCellFactory(new DraggableCellFactory());  
        
        Etiqueta etiq = EtiquetaController.getInstance().obtenerEtiqueta("xbox");
        
    }
    
    @FXML
    private void clickIngresarEtiqueta(ActionEvent event) {
        EtiquetaController etiquetacontroller=null;
        Etiqueta padre = new Etiqueta();
        padre.setNombre(textEtiquetaPadre.getText());
        
        List<Etiqueta> sub_etiquetas = new ArrayList<>();
        
        
        if(!EtiquetaController.getInstance().existeEtiqueta(textEtiquetaPadre.getText())){
            
            ObservableList<String> items = listaSubEtiquetas.getItems();
            
            if(items != null){
                for (String item : items) {
                    System.out.println(item);
                    if(item.equals(textEtiquetaPadre.getText())){
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Información");
                        alert.setHeaderText(null);
                        alert.setContentText("la subetiqueta: "+item+"no puede ser igual a la etiqueta padre!");
                        alert.showAndWait();
                    }
                    else{
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
            System.out.println("sube la etiqueta con las sub etiquetas");
            } catch (Exception e) {
                // Manejo de la excepción
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("¡Ya existe esa etiqueta padre!");

            alert.showAndWait();
       

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
        
        if(textSubEtiqueta.equals("")){
             Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("El campo de la sub etiqueta no puede estar vacio!");
                alert.showAndWait();
        }else{
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
   

}