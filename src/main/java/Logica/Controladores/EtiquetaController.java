/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Controladores;

import Logica.Clases.Error;
import Logica.Clases.Etiqueta;
import Logica.Clases.Solucion;
import Persistencia.Conexion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EtiquetaController {
    
    private static EtiquetaController instance = null;

    private EtiquetaController() {
    }
    
    public static EtiquetaController getInstance() {
        if (instance == null) {
            instance = new EtiquetaController();
        }
        return instance;
    }
    
    public List<Etiqueta> listaEtiquetas() {
    EntityManager em = Conexion.getInstance().getEntity();
    List<Etiqueta> resultado = null;
    em.getTransaction().begin();
        try {
            resultado = em.createNativeQuery("SELECT * FROM etiqueta", Etiqueta.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    return resultado;
    }
    
    public List<Etiqueta> listaEtiquetasActivas() {
    EntityManager em = Conexion.getInstance().getEntity();
    List<Etiqueta> resultado = null;
    em.getTransaction().begin();
        try {
            resultado = em.createNativeQuery("SELECT * FROM etiqueta WHERE active =1", Etiqueta.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    return resultado;
    }
    
    public boolean existeEtiqueta(String nombre) {
    EntityManager em = Conexion.getInstance().getEntity();
    Object etiqueta = null;
    boolean resultado = false;
    em.getTransaction().begin();
        try {
            etiqueta=em.createNativeQuery("SELECT * FROM etiqueta WHERE nombre ='"+nombre+"'").getSingleResult();
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
    
    public Etiqueta obtenerEtiqueta(String nombre_etiqueta){
        Etiqueta etiqueta=null;
        Object resultado = null;
        EntityManager em= Conexion.getInstance().getEntity(); ;
        em.getTransaction().begin();
        try{
            Query q = em.createNativeQuery("SELECT * from etiqueta where nombre='"+nombre_etiqueta+"'", Etiqueta.class);
            resultado=q.getSingleResult();
            em.getTransaction().commit();  
        }catch(Exception e){
            em.getTransaction().rollback();
        }
        if(resultado!=null){
            etiqueta = (Etiqueta) resultado;
            System.out.println("funciona");
        }
      return etiqueta;  
    }
    
    public List<Integer> filtradoSolucionesPorEtiquetas(ArrayList<String> valores) {
             EntityManager em = Conexion.getInstance().getEntity();
             List<Integer> resultado = null;
             em.getTransaction().begin();
             try {
                 // Construir la consulta dinámica con los parámetros
                 StringBuilder queryBuilder = new StringBuilder();
                 queryBuilder.append("SELECT ID FROM solucion WHERE ID IN (SELECT SOLUCION_ID FROM solucion_etiqueta WHERE etiqueta_NOMBRE IN (");

                 // Agregar los parámetros en la consulta
                 for (int i = 0; i < valores.size(); i++) {
                     queryBuilder.append("?");

                     // Agregar coma para todos los parámetros excepto el último
                     if (i < valores.size() - 1) {
                         queryBuilder.append(",");
                     }
                 }

                 queryBuilder.append(") GROUP BY solucion_ID HAVING COUNT(DISTINCT etiqueta_NOMBRE) >= ?)");

                 // Crear la consulta con la cadena SQL construida
                 Query q = em.createNativeQuery(queryBuilder.toString(), int.class );

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
