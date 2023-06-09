/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.DTOs;

import Logica.Clases.Usuario;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

/**
 *
 * @author UsuarioParaGestionar
 */
public class UsuarioParaGestionar {

    private Long id;
    private String nombre;
    private String apellido;
    private String mail;
    private CheckBox checkbox;
    private ComboBox combobox;
    
    public UsuarioParaGestionar(Usuario user, List<String> perfilesDisponibles) {
        this.id = user.getId();
        this.nombre = user.getNombre();
        this.apellido = user.getApellido();
        this.mail = user.getMail();
        
        this.checkbox = new CheckBox();
        this.checkbox.setSelected(user.isActive());
        
        this.combobox = new ComboBox(FXCollections.observableArrayList(perfilesDisponibles));
        this.combobox.setValue(user.getPerfil().getNombre());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }

    public void setCheckboxIsSelected(Boolean selected) {
        this.checkbox.setSelected(selected);
    }
    
    public void setCheckboxAction(EventHandler<ActionEvent> eventHandler) {
        this.checkbox.setOnAction(eventHandler);
    }

    public ComboBox getCombobox() {
        return combobox;
    }

    public void setComboboxValue(String value) {
        this.combobox.setValue(value);
    }
    
    public void setComboboxAction(EventHandler<ActionEvent> eventHandler) {
        this.combobox.setOnAction(eventHandler);
    }

    @Override
    public String toString() {
        return "UsuarioParaGestionar{" + "nombre=" + nombre + ", apellido=" + apellido;
    }
    
}
