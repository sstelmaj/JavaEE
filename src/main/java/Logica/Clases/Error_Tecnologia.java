package Logica.Clases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Usuario
 */
@Entity
public class Error_Tecnologia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    private Error error;
    
    @Id
    @ManyToOne
    private Tecnologia tecnologia;
    
    @OneToMany(mappedBy = "error_Tecnologia")
    private List<Solucion> soluciones;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Tecnologia getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(Tecnologia tecnologia) {
        this.tecnologia = tecnologia;
    }

    public List<Solucion> getSoluciones() {
        return soluciones;
    }

    public void setSoluciones(List<Solucion> soluciones) {
        this.soluciones = soluciones;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.error);
        hash = 41 * hash + Objects.hashCode(this.tecnologia);
        hash = 41 * hash + Objects.hashCode(this.soluciones);
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
        final Error_Tecnologia other = (Error_Tecnologia) obj;
        if (!Objects.equals(this.error, other.error)) {
            return false;
        }
        if (!Objects.equals(this.tecnologia, other.tecnologia)) {
            return false;
        }
        return Objects.equals(this.soluciones, other.soluciones);
    }
    
    
    
    
    
    
    @Override
    public String toString() {
        return "Clases.Error_Tecnologia[ id=" + error + " ]";
    }
    
}
