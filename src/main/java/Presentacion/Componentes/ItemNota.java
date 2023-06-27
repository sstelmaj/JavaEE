/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Componentes;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author paulo
 */
public class ItemNota extends Control {
    private Pane anchorPane;
    private Text txtNota;
    private Text txtFecha;
    
    public ItemNota(){
        anchorPane = new AnchorPane();
        anchorPane.setPrefSize(400.0, 108.0);
        
         // Crear los componentes
        txtFecha = new Text("Contenido de la nota");
        txtFecha.setLayoutX(266.0);
        txtFecha.setLayoutY(89.0);
        txtFecha.setWrappingWidth(144.16064453125);
        txtFecha.setFont(new Font(13.0));
        
        txtNota = new Text("Descripcion");
        txtNota.setLayoutX(18.0);
        txtNota.setLayoutY(30.0);
        txtNota.setWrappingWidth(367.33740234375);
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
    

    public Text getTxtNota() {
        return txtNota;
    }

    public void setTxtNota(Text txtNota) {
        this.txtNota = txtNota;
    }

    public Text getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(String txtFecha) {
        this.txtFecha.setText(txtFecha);
    }
    
    public void setContenidoNota(String contenido){
        this.txtNota.setText(contenido);
    }
    
}
