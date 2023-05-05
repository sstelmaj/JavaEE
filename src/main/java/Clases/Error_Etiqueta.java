/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Usuario
 */
@Entity
public class Error_Etiqueta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    private Error error;
    
    @Id
    @ManyToOne
    private Etiqueta etiqueta;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (error != null ? error.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the error fields are not set
        if (!(object instanceof Error_Etiqueta)) {
            return false;
        }
        Error_Etiqueta other = (Error_Etiqueta) object;
        if ((this.error == null && other.error != null) || (this.error != null && !this.error.equals(other.error))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Error_Etiqueta[ id=" + error + " ]";
    }
    
}
