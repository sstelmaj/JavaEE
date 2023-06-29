/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package Logica.Controladores;

import Logica.Clases.Solucion;
import Logica.DTOs.CantidadPorFecha;
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
    
     public Solucion obtenerSolucion(long id){
        Object resultado=null;
        EntityManager em= SolucionControllerHolder.con.getEntity();
        em.getTransaction().begin();
        try{
            Query q = em.createNativeQuery("SELECT * from Solucion where id="+id, Solucion.class);
            resultado=q.getSingleResult();
            em.getTransaction().commit();  
    }catch(Exception e){
        em.getTransaction().rollback();
    }
      return (Solucion)resultado;  
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
     
     
     public List<CantidadPorFecha> obtenerCantidadSolucionesSemanalesPorFecha(String fecha) {
        EntityManager em = Conexion.getInstance().getEntity();
        List<CantidadPorFecha> resultado = null;
        em.getTransaction().begin();
        try{
            Query q = em.createNativeQuery("SELECT COUNT(*) AS CANTIDAD, FECHASUBIDA From solucion WHERE FECHASUBIDA <= ? AND FECHASUBIDA > DATE_SUB(?, INTERVAL 6 DAY) GROUP BY FECHASUBIDA", "CantidadPorFecha");
            q.setParameter(1, fecha);
            q.setParameter(2, fecha);
            resultado = q.getResultList();
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
        return resultado;
    }
     
     public int obtenerCantidadSoluciones(){
        EntityManager em = Conexion.getInstance().getEntity();
        int cantidad = 0;
        
        Object resultado = em.createNativeQuery("SELECT COUNT(*) FROM solucion").getSingleResult();
        cantidad = ((Number) resultado).intValue();
        return cantidad;
     }
}
