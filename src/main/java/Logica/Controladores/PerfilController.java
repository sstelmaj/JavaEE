/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Controladores;

import Logica.Clases.Error;
import Logica.Clases.Etiqueta;
import Logica.Clases.Perfil;
import Logica.Clases.Solucion;
import Persistencia.Conexion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
    
    public List<Etiqueta> listaPerfiles() {
    EntityManager em = Conexion.getInstance().getEntity();
    List<Etiqueta> resultado = null;
    em.getTransaction().begin();
        try {
            resultado = em.createNativeQuery("SELECT * FROM perfil", Perfil.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    return resultado;
    }
    
    public boolean existePerfil(String nombre) {
    EntityManager em = Conexion.getInstance().getEntity();
    Object etiqueta = null;
    boolean resultado = false;
    em.getTransaction().begin();
        try {
            etiqueta=em.createNativeQuery("SELECT * FROM perfil WHERE nombre ='"+nombre+"'").getSingleResult();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        if(etiqueta != null){
            System.out.println("existe");
            resultado = true;
        }else{
            System.out.println("no existe");
        }   
    return resultado;
    }
    
    public Perfil obtenerPerfil(String nombre_perfil){
        Perfil perfil=null;
        Object resultado = null;
        EntityManager em= Conexion.getInstance().getEntity(); ;
        em.getTransaction().begin();
        try{
            Query q = em.createNativeQuery("SELECT * from perfil where nombre='"+nombre_perfil+"'", Perfil.class);
            resultado=q.getSingleResult();
            em.getTransaction().commit();  
        }catch(Exception e){
            em.getTransaction().rollback();
        }
        if(resultado!=null){
            perfil = (Perfil) resultado;
            System.out.println("funciona");
        }
      return perfil;  
    }
    
    
    

}
