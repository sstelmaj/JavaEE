/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Controladores;

import Logica.Clases.Solucion;
import Persistencia.Conexion;
import java.util.List;
import javax.persistence.EntityManager;

public class ErrorController {
    
    private static ErrorController instance = null;
    
    private ErrorController(){}
    
    public static ErrorController getInstance() {
        if (instance == null) {
            instance = new ErrorController();
        }
        return instance;
    }
    
    
    public List<Solucion> obtenerSolucionesDelError(int id){
        EntityManager em = Conexion.getInstance().getEntity();
        List<Solucion> resultado = null;
        em.getTransaction().begin();
        try {
            resultado = em.createNativeQuery("SELECT * FROM solucion where error_id="+id, Solucion.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        }
        return resultado;
    }
}
