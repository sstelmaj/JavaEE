/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion;

import Logica.Clases.Solucion;
import Logica.Controladores.ErrorController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    private TextField buscador;
    @FXML
    private Text txtTitulo;
    @FXML
    private Text txtFechaModif;
    
    private AnchorPane apPrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
    }
    public void initialize(AnchorPane ap){
        List<Solucion>soluciones=ErrorController.getInstance().obtenerSolucionesDelError(151);
        for(Solucion sol:soluciones){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/busquedaSolucion.fxml"));
                Parent subfileRoot = loader.load();
                // Obtén el controlador del archivo subfile.fxml
                BusquedaSolucionController subfileController = loader.getController();
                System.out.println("Antes de setear datos");
                subfileController.setDatos("Solucion de ejemplo", sol.getDescripcion(),sol.getId());
                subfileController.initialize(ap);

                // Realiza cualquier configuración adicional en el subfileController si es necesario
                lista.getChildren().add(subfileRoot);
            }catch (IOException e) {
                e.printStackTrace();
            }  
        }
    
    }
}
