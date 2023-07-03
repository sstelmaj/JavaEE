/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Componentes;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author paulo
 */
public class ItemNota extends Control {
    private Pane anchorPane;
    private TextArea txtNota;
    private Label txtFecha;
    
    public ItemNota(){
        anchorPane = new AnchorPane();
        anchorPane.setPrefSize(520.0, 108.0);
        
        // Crear los componentes
        txtFecha = new Label("Contenido de la nota");
        txtFecha.setLayoutX(400.0);
        txtFecha.setLayoutY(89.0);
        //txtFecha.setWrappingWidth(144.16064453125);
        txtFecha.setFont(new Font(13.0));
        
        txtNota = new TextArea("Descripcion");
        txtNota.setWrapText(true);
        txtNota.setStyle("-fx-text-box-border: transparent;");
        txtNota.setPrefSize(500, 78);
        txtNota.setEditable(false);
        txtNota.setLayoutX(8.0);
        txtNota.setLayoutY(3.0);
        //txtNota.setWrappingWidth(367.33740234375);
        txtNota.setFont(new Font(13.0));
        
        anchorPane.setVisible(true);
        // Establecer el AnchorPane como contenido del control
        anchorPane.getChildren().addAll(txtNota, txtFecha);
        getChildren().add(anchorPane);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ItemNotaSkin(this); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public Pane getAnchorPane() {
        return anchorPane;
    }
    

    public TextArea getTxtNota() {
        return txtNota;
    }

    public void setTxtNota(TextArea txtNota) {
        this.txtNota = txtNota;
    }

    public Label getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(String txtFecha) {
        this.txtFecha.setText(txtFecha);
    }
    
    public void setContenidoNota(String contenido){
        this.txtNota.setText(contenido);
    }
    
}
