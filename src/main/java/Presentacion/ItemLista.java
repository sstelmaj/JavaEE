/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Presentacion;

import Logica.Clases.Error;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ItemLista extends Control {

    private Pane anchorPane;
    private Text txtTitulo;
    private Text txtDescripcion;
    private Button btnDetalle;
    private Logica.Clases.Error error;
    public ItemLista() {
        // Crear el AnchorPane
        anchorPane = new AnchorPane();
        anchorPane.setPrefSize(400.0, 90.0);
        
         // Crear los componentes
        txtTitulo = new Text("Titulo del error");
        txtTitulo.setLayoutX(14.0);
        txtTitulo.setLayoutY(30.0);
        
        txtDescripcion = new Text("Descripcion");
        txtDescripcion.setLayoutX(14.0);
        txtDescripcion.setLayoutY(51.0);
        txtDescripcion.setWrappingWidth(306.16064453125);
        txtDescripcion.setFont(new Font(13.0));
        
        btnDetalle = new Button(">");
        btnDetalle.setLayoutX(339.0);
        btnDetalle.setLayoutY(30.0);
        
        anchorPane.setVisible(true);
        // Establecer el AnchorPane como contenido del control
        anchorPane.getChildren().addAll(txtTitulo, txtDescripcion, btnDetalle);
        getChildren().add(anchorPane);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ItemListaSkin(this);
    }

    public Pane getAnchorPane() {
        return anchorPane;
    }

    public Text getTitulo() {
        return txtTitulo;
    }

    public void setTitulo(String txtTitulo) {
        this.txtTitulo.setText(txtTitulo);
    }

    public Text getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(Text txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
    
    
}