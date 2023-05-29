/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Clases;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author joaco
 */
@Entity
public class Solucion_Etiqueta implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    @Id
    @ManyToOne
    private Solucion solucion;
    
    @Id
    @ManyToOne
    private Etiqueta etiqueta;
    
    

    public Solucion getSolucion() {
        return solucion;
    }

    public void setSolucion(Solucion solucion) {
        this.solucion = solucion;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.solucion);
        hash = 97 * hash + Objects.hashCode(this.etiqueta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Solucion_Etiqueta other = (Solucion_Etiqueta) obj;
        return Objects.equals(this.etiqueta, other.etiqueta);
    }

    

   

    
    
}
