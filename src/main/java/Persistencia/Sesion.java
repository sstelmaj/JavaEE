/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Logica.Clases.AjustesUsuario;
import Logica.Clases.Perfil;
import Logica.Clases.Usuario;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JInternalFrame;

/**
 *
 * @author Usuario
 */
public class Sesion {
    private static Sesion instancia;
    private String user;
    
    private Usuario usuario;
    
    
    
    private boolean isFullHD ;
   
    private AnchorPane panelPrincipal;
    
    private Stage stagePrincipal;

    public Stage getStagePrincipal() {
        return stagePrincipal;
    }

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }
    
   private AjustesUsuario ajustes;

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
    
    //panel del contenido

    public AnchorPane getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(AnchorPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public boolean isIsFullHD() {
        return isFullHD;
    }

    public void setIsFullHD(boolean isFullHD) {
        this.isFullHD = isFullHD;
        if(isFullHD){
            this.stagePrincipal.setWidth(1936.0);
            this.stagePrincipal.setHeight(1056.0);
            System.out.println("llega aca");
        }else{
            this.stagePrincipal.setWidth(200.200);
            System.out.println("llega aca sino");
        }
    }

    

    
    
    
    
    
    
    
    
}
