package Persistencia;



import Logica.Clases.Etiqueta;
import Logica.Clases.Tecnologia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class Conexion {
    
    private Conexion() {
    }
    
    public static Conexion getInstance() {
        return ConexionHolder.INSTANCE;
    }
    
    private static class ConexionHolder {

        private static final Conexion INSTANCE = new Conexion();
        private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadDePersistencia");
        private static final EntityManager em = emf.createEntityManager();
    }
    
    public EntityManager getEntity() {
        return ConexionHolder.em;
    }
    
    public void persist(Object object) { //crea y hace update
        EntityManager em = getEntity();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void merge(Object object) {
        EntityManager em = getEntity();
        em.getTransaction().begin();
        try {
            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void eliminar(Object object) {
        EntityManager em = getEntity();
        em.getTransaction().begin();
        try {
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void refresh(Object object) {
        EntityManager em = getEntity();
        em.getTransaction().begin();
        try {
            em.refresh(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
    
    public <T> List<T> select(String sql, Class <T> clase){
        EntityManager em = getEntity();
        List<T> resultado = null;
        em.getTransaction().begin();
        try {
            resultado = em.createQuery(sql,clase).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return resultado;
    
    }
    
    
    
    
    
    public void cerrarConexion() {
        getEntity().close();
    }
}
