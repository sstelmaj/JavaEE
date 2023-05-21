package Logica.Clases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


//ESTO A LA LARGA PUEDE LLEGAR A SER UNA CLASE SI QUEREMOS AGREGAR MAS CATEGORIAS DE FORMA DINAMICA
//enum CATEGORIA{
//    
//}

/**
 *
 * @author Usuario
 */
@Entity
public class Etiqueta implements Serializable {

    @ManyToOne
    private Etiqueta etiquetaPadre;

    @OneToMany(mappedBy = "etiqueta")
    private List<Solucion_Etiqueta> solucion_Etiquetas;
    
    @OneToMany(mappedBy = "etiqueta")
    private List<Error_Etiqueta> error_Etiquetas;
    
    
    @OneToMany(mappedBy = "etiquetaPadre")
    private List<Etiqueta> sub_etiqueta;

    public List<Solucion_Etiqueta> getSolucion_Etiquetas() {
        return solucion_Etiquetas;
    }

    public void setSolucion_Etiquetas(List<Solucion_Etiqueta> solucion_Etiquetas) {
        this.solucion_Etiquetas = solucion_Etiquetas;
    }

    public List<Etiqueta> getSub_etiqueta() {
        return sub_etiqueta;
    }

    public void setSub_etiqueta(List<Etiqueta> sub_etiqueta) {
        this.sub_etiqueta = sub_etiqueta;
    }
    

    private static final long serialVersionUID = 1L;
    @Id
    private String nombre;
    
    //private CATEGORIA categoria;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Error_Etiqueta> getError_Etiquetas() {
        return error_Etiquetas;
    }

    public void setError_Etiquetas(List<Error_Etiqueta> error_Etiquetas) {
        this.error_Etiquetas = error_Etiquetas;
    }

 
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the nombre fields are not set
        if (!(object instanceof Etiqueta)) {
            return false;
        }
        Etiqueta other = (Etiqueta) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Etiqueta[ id=" + nombre + " ]";
    }
    
}
