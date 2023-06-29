/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Logica.Clases.AjustesUsuario;
import Logica.Clases.Perfil;
import Logica.Clases.Usuario;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class Sesion {
    private static Sesion instancia;
    private String user;
    
    private Usuario usuario;
    
    
    
    private boolean isFullHD ;
    
    private boolean isDarkMode;
    
   
    private AnchorPane panelPrincipal;
    
    private Stage stagePrincipal;

    public Stage getStagePrincipal() {
        return stagePrincipal;
    }

    public void setStagePrincipal(Stage stagePrincipal) {
        if(stagePrincipal !=null){
            System.out.println("llega el stage");
          
        
        }
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
            stagePrincipal.setMinHeight(1056.0);
            stagePrincipal.setMinWidth(1936.0);
            stagePrincipal.centerOnScreen();
            System.out.println("llega aca");
        }else{
            stagePrincipal.setMinHeight(800.0);
            stagePrincipal.setMinWidth(1595.0);
            stagePrincipal.setHeight(800.0);
            stagePrincipal.setWidth(1595.0);
            stagePrincipal.centerOnScreen();
            System.out.println("llega aca sino");
        }
    }

    public boolean isIsDarkMode() {
        return isDarkMode;
    }

    public void setIsDarkMode(boolean isDarkMode) {
        this.isDarkMode = isDarkMode;
    }

    

    
    
    
    
    
    
    
    
}
