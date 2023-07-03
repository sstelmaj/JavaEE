/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Controladores;

import Logica.Clases.Error;
import Logica.Clases.Solucion;
import Logica.DTOs.CantidadPorMes;
import Persistencia.Conexion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
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
    
    public Error obtenerError(long id){
        EntityManager em = Conexion.getInstance().getEntity();
        Object resultado = null;
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("SELECT * FROM error where id="+id, Error.class);
            resultado=q.getSingleResult();    
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        }
        return (Error)resultado;
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
    public List<Solucion> obtenerSolucionesDelError(long id){
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
    
     public List<Logica.Clases.Error> listaErrores(ArrayList valores,String unico){
        EntityManager em = Conexion.getInstance().getEntity();
        List<Logica.Clases.Error> resultado = null;
        em.getTransaction().begin();
        try{
            Query q = em.createNativeQuery("SELECT * From error WHERE id = ?", Logica.Clases.Error.class);
            q.setParameter(1, unico);
             System.out.println("la consulta para obtener el error es:"+q.toString());
            resultado = q.getResultList();
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
        return resultado;
    }
     
        public List<Logica.Clases.Error> filtradoErroresPorEtiquetas(ArrayList<String> valores) {
             EntityManager em = Conexion.getInstance().getEntity();
             List<Logica.Clases.Error> resultado = null;
             em.getTransaction().begin();
             try {
                 // Construir la consulta dinámica con los parámetros
                 StringBuilder queryBuilder = new StringBuilder();
                 queryBuilder.append("SELECT * FROM error WHERE ID IN (SELECT errors_ID FROM error_etiqueta WHERE etiquetas_NOMBRE IN (");

                 // Agregar los parámetros en la consulta
                 for (int i = 0; i < valores.size(); i++) {
                     queryBuilder.append("?");

                     // Agregar coma para todos los parámetros excepto el último
                     if (i < valores.size() - 1) {
                         queryBuilder.append(",");
                     }
                 }

                 queryBuilder.append(") GROUP BY errors_ID HAVING COUNT(DISTINCT etiquetas_NOMBRE) >= ?)");

                 // Crear la consulta con la cadena SQL construida
                 Query q = em.createNativeQuery(queryBuilder.toString(), Logica.Clases.Error.class);
                 
                 // Establecer los parámetros en la consulta
                 for (int i = 0; i < valores.size(); i++) {
                     q.setParameter(i + 1, valores.get(i));
                 }

                 // Establecer el parámetro para la cantidad de valores
                 q.setParameter(valores.size() + 1, valores.size());
                 
                 resultado = q.getResultList();
                 em.getTransaction().commit();
             } catch (Exception e) {
                 em.getTransaction().rollback();
             }
             return resultado;
         }
        
        public List<Error> busquedaDeErrores(String busqueda){
            EntityManager em = Conexion.getInstance().getEntity();
            List<Logica.Clases.Error> resultado = null;
            em.getTransaction().begin();
            try {
            Query q=em.createNativeQuery("SELECT * FROM error where descripcion LIKE(\"%"+busqueda+"%\") OR consola LIKE(\"%"+busqueda+"%\");", Logica.Clases.Error.class);
            resultado = q.getResultList();
            em.getTransaction().commit();
            } catch (Exception e){
                    em.getTransaction().rollback();
            }
            return resultado;
        }     
        
    public List<CantidadPorMes> obtenerCantidadErroresMensuales() {
        EntityManager em = Conexion.getInstance().getEntity();
        List<CantidadPorMes> resultado = new ArrayList<>();
        em.getTransaction().begin();
        try{
            Query q = em.createNativeQuery("SELECT COUNT(*) AS CANTIDAD, DATE_FORMAT(FECHASUBIDA, '%Y/%m') AS MES FROM error WHERE FECHASUBIDA <= CURDATE() AND FECHASUBIDA > DATE_SUB(CURDATE(), INTERVAL 8 MONTH) GROUP BY DATE_FORMAT(FECHASUBIDA, '%Y/%m')", "CantidadPorMes");
            resultado = q.getResultList();
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
        return resultado;
    }
    
    public int obtenerCantidadErrores(){
        EntityManager em = Conexion.getInstance().getEntity();
        int cantidad = 0;
        
        Object resultado = em.createNativeQuery("SELECT COUNT(*) FROM error").getSingleResult();
        cantidad = ((Number) resultado).intValue();
        return cantidad;
    }
}
