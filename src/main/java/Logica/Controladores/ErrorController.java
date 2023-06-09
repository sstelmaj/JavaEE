/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Controladores;

import Logica.Clases.Error;
import Logica.Clases.Solucion;
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
    
    public List<Error> obtenerError(long id){
        EntityManager em = Conexion.getInstance().getEntity();
        List<Error> resultado = null;
        em.getTransaction().begin();
        try {
            resultado = em.createNativeQuery("SELECT * FROM error where id="+id, Error.class).getResultList();
                em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        }
        return resultado;
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
        
        
}
