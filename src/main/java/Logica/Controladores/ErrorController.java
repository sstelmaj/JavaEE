/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Controladores;

import Logica.Clases.Error;
import Logica.Clases.Solucion;
import Persistencia.Conexion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ErrorController {
    
    private static ErrorController instance = null;

    private ErrorController() {
    }
    
    public static ErrorController getInstance() {
        if (instance == null) {
            instance = new ErrorController();
        }
        return instance;
    }
    
    public List<Logica.Clases.Error> obtenerErrores(){
        EntityManager em = Conexion.getInstance().getEntity();
        List<Logica.Clases.Error> resultado = null;
        em.getTransaction().begin();
        try {
            resultado = em.createNativeQuery("SELECT * FROM error", Logica.Clases.Error.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        }
        return resultado;
    }
    
     public List<Logica.Clases.Error> listaErrores(ArrayList valores,String unico){
        EntityManager em = Conexion.getInstance().getEntity();
        List<Logica.Clases.Error> resultado = null;
        em.getTransaction().begin();
        try{
            Query q = em.createNativeQuery("SELECT * From error WHERE id = ?", Logica.Clases.Error.class);
            q.setParameter(1, unico);
            resultado = q.getResultList();
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
        return resultado;
    }
    
    

}
