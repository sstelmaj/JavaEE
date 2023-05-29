/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Controladores;

import Logica.Clases.Usuario;
import Persistencia.Conexion;
import java.util.List;
import javax.persistence.EntityManager;

public class UsuarioController {
    
    private static UsuarioController instance = null;

    private UsuarioController() {
    }
    
    public static UsuarioController getInstance() {
        if (instance == null) {
            instance = new UsuarioController();
        }
        return instance;
    }
    
    public List<Usuario> obtenerUsuarios(){
        EntityManager em = Conexion.getInstance().getEntity();
        List<Usuario> resultado = null;
        em.getTransaction().begin();
        try {
            resultado = em.createNativeQuery("SELECT * FROM usuario", Usuario.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        }
        return resultado;
    }
    
    public void actualizarUsuario(Usuario usuario) {
        EntityManager em = Conexion.getInstance().getEntity();
        em.merge(usuario);
    }
    public boolean iniciarSesion(String mail, String password){
        List<Usuario> usuarios = UsuarioController.getInstance().obtenerUsuarios();
        for (Usuario u : usuarios) {
            if (u.getMail().equals(mail)){
                if (u.getPassword().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }
    
    

}
