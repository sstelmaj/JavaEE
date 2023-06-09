/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package Logica.Controladores;

import Logica.Clases.Solucion;
import Persistencia.Conexion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SolucionController {
    
    private SolucionController() {
    }
    
    public static SolucionController getInstance() {
        return SolucionControllerHolder.INSTANCE;
    }
    
    private static class SolucionControllerHolder {
        private static final SolucionController INSTANCE = new SolucionController();
        private static final Conexion con=Conexion.getInstance();
    }
    
     public List<Solucion> obtenerSolucion(long id){
        List<Solucion> resultado=null;
        EntityManager em= SolucionControllerHolder.con.getEntity();
        em.getTransaction().begin();
        try{
            Query q = em.createNativeQuery("SELECT * from Solucion where id="+id, Solucion.class);
            resultado=q.getResultList();
            em.getTransaction().commit();  
    }catch(Exception e){
        em.getTransaction().rollback();
    }
      return resultado;  
    }
     
     public List<Solucion> obtenerSoluciones(){
        EntityManager em = Conexion.getInstance().getEntity();
        List<Solucion> resultado = null;
        em.getTransaction().begin();
        try {
            resultado = em.createNativeQuery("SELECT * FROM solucion", Solucion.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        }
        return resultado;
     }
}
