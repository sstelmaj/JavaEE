/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Controladores;

import Logica.Clases.Perfil;
import Persistencia.Conexion;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Perfil
 */
public class PerfilController {
    
    
    private static PerfilController instance = null;

    private PerfilController() {
    }
    
    public static PerfilController getInstance() {
        if (instance == null) {
            instance = new PerfilController();
        }
        return instance;
    }
    
    public List<String> obtenerNombresDePerfiles(){
        EntityManager em = Conexion.getInstance().getEntity();
        List<String> resultado = null;
        em.getTransaction().begin();
        try {
            resultado = em.createNativeQuery("SELECT nombre FROM perfil").getResultList();
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        }
        return resultado;
    }
    
    
    public List<Perfil> obtenerPerfiles(){
        EntityManager em = Conexion.getInstance().getEntity();
        List<Perfil> resultado = null;
        em.getTransaction().begin();
        try {
            resultado = em.createNativeQuery("SELECT * FROM perfil", Perfil.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        }
        return resultado;
    }
    
    public List<Perfil> obtenerPerfilPorId(long id){
        EntityManager em = Conexion.getInstance().getEntity();
        List<Perfil> resultado = null;
        em.getTransaction().begin();
        try {
            resultado = em.createNativeQuery("SELECT * FROM perfil WHERE id="+id, Perfil.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        }
        return resultado;
    }
    
    public List<Perfil> obtenerPerfilPorNombre(String nombre){
        EntityManager em = Conexion.getInstance().getEntity();
        List<Perfil> resultado = null;
        em.getTransaction().begin();
        try {
            resultado = em.createNativeQuery("SELECT * FROM perfil WHERE nombre="+nombre, Perfil.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        }
        return resultado;
    }
    
    public void actualizarPerfil(Perfil perfil) {
        EntityManager em = Conexion.getInstance().getEntity();
        em.merge(perfil);
    }
    
}
