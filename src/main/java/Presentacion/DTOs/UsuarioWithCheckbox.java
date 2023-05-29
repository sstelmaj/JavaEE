/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.DTOs;

import Logica.Clases.Perfil;
import Logica.Clases.Usuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;

/**
 *
 * @author UsuarioWithCheckbox
 */
public class UsuarioWithCheckbox {

    private Long id;
    private String nombre;
    private String apellido;
    private String mail;
    private Perfil perfil;
    private CheckBox checkbox;
    
    public UsuarioWithCheckbox(Usuario user) {
        this.id = user.getId();
        this.nombre = user.getNombre();
        this.apellido = user.getApellido();
        this.mail = user.getMail();
        this.perfil = user.getPerfil();
        this.checkbox = new CheckBox();
        this.checkbox.setSelected(user.isActive());
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
    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }
    
    public void setCheckboxAction(EventHandler<ActionEvent> eventHandler) {
        this.checkbox.setOnAction(eventHandler);
    }

    public void setCheckboxIsSelected(Boolean selected) {
        this.checkbox.setSelected(selected);
    }
}
