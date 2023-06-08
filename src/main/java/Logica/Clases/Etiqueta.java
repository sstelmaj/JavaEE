package Logica.Clases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    @ManyToMany(mappedBy = "etiquetas")
    private List<Solucion> solucions;

    @ManyToMany(mappedBy = "etiquetas")
    private List<Error> errors;

    private String padre;

    private Boolean active = Boolean.TRUE;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    

    
    
    
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Etiqueta> sub_etiqueta;

    

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

  

     public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public List<Solucion> getSolucions() {
        return solucions;
    }

    public void setSolucions(List<Solucion> solucions) {
        this.solucions = solucions;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
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
