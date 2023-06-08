/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion.Controllers;

import Logica.Controladores.ErrorController;
import Presentacion.ItemLista;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author joaco
 */
public class PruebaFiltradoController implements Initializable {

    @FXML
    private ComboBox<String> comboSo;
    @FXML
    private ComboBox<String> comboFramework;
    @FXML
    private ListView<Logica.Clases.Error> listaErrores;
    @FXML
    private AnchorPane idItemError;
    @FXML
    private Text txtTitulo;
    @FXML
    private Text txtDescripcion;
    @FXML
    private Button btnDetalle;
    @FXML
    private Button botonIngresar;
    @FXML
    private ListView<ItemLista> lista;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> itemsS = comboSo.getItems();
         ObservableList<String> itemsF = comboFramework.getItems();
        itemsS.add("Ninguno"); 
        itemsS.add("Windows");
        itemsS.add("Linux");
        itemsS.add("SO");
        
        itemsF.add("Ninguno"); 
        itemsF.add("Framework");
        itemsF.add("Angular");
        itemsF.add("React");
        itemsF.add("Yii");
       // ArrayList<String> valores = new ArrayList<>();
        //List<Logica.Clases.Error> errores = ErrorController.getInstance().filtradoErroresPorEtiquetas(valores);
        comboSo.setOnAction(event -> filtrarErrores());
        comboFramework.setOnAction(event -> filtrarErrores());
        
    }    
    
    private void filtrarErrores() {
        String seleccionSo = comboSo.getValue();
        String seleccionFramework = comboFramework.getValue();

        // Verificar si se han seleccionado valores en ambos ComboBoxes
        if (seleccionSo != null || seleccionFramework != null) {
            ArrayList<String> valores = new ArrayList<>();
            if(seleccionSo!=null && !seleccionSo.equals("Ninguno")){
                valores.add(seleccionSo.trim());
            }
            
            if(seleccionFramework!=null && !seleccionFramework.equals("Ninguno")){
               // valores.add(seleccionFramework);
               valores.add(seleccionFramework.trim());
            }
            
       //     valores.add("Angular");
            List<Logica.Clases.Error> errores = ErrorController.getInstance().filtradoErroresPorEtiquetas(valores);
            System.out.println(errores.size());
            valores.clear();
            // Actualizar la lista de errores en el ListView
            if(errores !=null){
                lista.getItems().clear(); 
                for (Logica.Clases.Error error : errores) {
                    ItemLista item = new ItemLista();
                    item.setError(error);
                    item.setTitulo(error.getTitulo());
                    lista.getItems().add(item);
                }
            }
            listaErrores.getItems().setAll(errores);
        }
    }

    @FXML
    private void DetalleSolucion(MouseEvent event) {
        
    }

    @FXML
    private void ingresarPanel(ActionEvent event) {

        ItemLista item = new ItemLista();
       lista.getItems().add(item);
    }
}
