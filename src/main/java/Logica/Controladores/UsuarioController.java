/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Controladores;


import Logica.Clases.Perfil;
import Logica.Clases.Etiqueta;
import Logica.Clases.Usuario;
import Persistencia.Conexion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

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
    

    public void actualizarEstadoUsuarioPorId(Long id, Boolean nuevoEstado){
        EntityManager em = Conexion.getInstance().getEntity();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Usuario usuario = em.find(Usuario.class, id);
            usuario.setIsActive(nuevoEstado);
            tx.commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        }
    }
    
    public void actualizarPerfilUsuarioPorId(Long id, String nuevoPerfil){
        EntityManager em = Conexion.getInstance().getEntity();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Usuario usuario = em.find(Usuario.class, id);
            Perfil perfil = em.find(Perfil.class, nuevoPerfil);
            usuario.setPerfil(perfil);
            tx.commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        }

    public Usuario obtenerUsuario(Long id){
        Usuario user=null;
        Object resultado = null;
        EntityManager em= Conexion.getInstance().getEntity(); ;
        em.getTransaction().begin();
        try{
            Query q = em.createNativeQuery("SELECT * from usuario where id='"+id+"'", Usuario.class);
            resultado=q.getSingleResult();
            em.getTransaction().commit();  
        }catch(Exception e){
            em.getTransaction().rollback();
        }
        if(resultado!=null){
            user = (Usuario) resultado;
            System.out.println("funciona");
        }
      return user;  
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
