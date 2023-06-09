/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Logica.Clases.Perfil;
import Logica.Clases.Usuario;

/**
 *
 * @author Usuario
 */
public class Sesion {
    private static Sesion instancia;
    private String user;
    
    private Usuario usuario;
    
    private Perfil perfil;

    private Sesion() {
        // Constructor privado para evitar instanciación directa
    }

    public static Sesion getInstance() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    

    // Agrega más métodos si es necesario

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return usuario.getPerfil();
    }

    
    
    
    
}
