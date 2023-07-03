/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Presentacion.Componentes;

import Logica.Clases.Error;
import Logica.Clases.Etiqueta;
import Logica.Clases.Solucion;
import Presentacion.Controllers.DashboardController;
import Presentacion.Controllers.DetalleErrorController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ItemLista extends Control {

    private Pane anchorPane;
    private Label txtTitulo;
    private Label txtDescripcion;
    private Button btnDetalle;
    private HBox hBoxEtiquetas;
    private String tipoItem;
    
    
    private Logica.Clases.Error error;
    
    private Solucion solucion;
    
    private Initializable controlador;
    
    public ItemLista(String tipo, Initializable controlador) {
        // Crear el AnchorPane
        tipoItem = tipo;
        this.controlador = controlador;
        anchorPane = new AnchorPane();
        anchorPane.setPrefSize(580.0, 105.0);
        
         // Crear los componentes
        txtTitulo = new Label("Titulo del error");
        txtTitulo.setLayoutX(23.0);
        txtTitulo.setLayoutY(7.0);
        
        txtDescripcion = new Label("Descripcion");
        txtDescripcion.setLayoutX(23.0);
        txtDescripcion.setLayoutY(34.0);
        //txtDescripcion.setWrappingWidth(306.16064453125);
        txtDescripcion.setFont(new Font(13.0));
        
        hBoxEtiquetas= new HBox();
        hBoxEtiquetas.setLayoutX(23.0);
        hBoxEtiquetas.setLayoutY(72.0);
        hBoxEtiquetas.setSpacing(10);
        
        btnDetalle = new Button(">");
        btnDetalle.setLayoutX(544.0);
        btnDetalle.setLayoutY(32.0);
        btnDetalle.setOnMouseClicked(event -> Detalle());
        
        anchorPane.setVisible(true);
        // Establecer el AnchorPane como contenido del control
        anchorPane.getChildren().addAll(txtTitulo, txtDescripcion, btnDetalle,hBoxEtiquetas);
        getChildren().add(anchorPane);
    }
    
    private void Detalle(){
         FXMLLoader loader = new FXMLLoader();
        if(tipoItem.equals("solucion")){
            
        }
        else{
           
             try {
                 loader.setLocation(getClass().getResource("/fxml/detalleError.fxml"));
                 Parent nuevaVista = loader.load();
                 DetalleErrorController detalleErrorController=(DetalleErrorController)loader.getController();
                 DashboardController dashboardController = DashboardController.getInstance();
                 detalleErrorController.setAnchPaneGeneral(dashboardController.getAnchorPane());
                 detalleErrorController.setErrorDetalle(this.error);
                 //detalleErrorController.initialize(dashboardController.getAnchorPane(),this.error);
                 
                 dashboardController.setControladorAnterior(this.controlador);
                 dashboardController.setControladorSiguiente(detalleErrorController);
                 dashboardController.setVista(nuevaVista);
                 //dashboardController.getAnchorPane().getChildren().setAll(nuevaVista);
             } catch (IOException ex) {
                 Logger.getLogger(ItemLista.class.getName()).log(Level.SEVERE, null, ex);
             }
        
        }
    
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ItemListaSkin(this);
    }

    public Pane getAnchorPane() {
        return anchorPane;
    }

    public Label getTitulo() {
        return txtTitulo;
    }

    public void setTitulo(String txtTitulo) {
        this.txtTitulo.setText(txtTitulo);
    }

    public Label getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(String txtDescripcion) {
        this.txtDescripcion.setText(txtDescripcion);
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Solucion getSolucion() {
        return solucion;
    }

    public void setSolucion(Solucion solucion) {
        this.solucion = solucion;
    }
    
    public void setEtiquetas(List<Etiqueta> etiquetasError){
        for(Etiqueta e:etiquetasError){
            Label etiqueta=new Label(e.getNombre());
            etiqueta.getStyleClass().add("etiquetas");
            hBoxEtiquetas.getChildren().add(etiqueta);
        }
    }
    
    
}