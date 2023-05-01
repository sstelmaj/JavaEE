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
import javax.persistence.OneToMany;

/**
 *
 * @author Usuario
 */
@Entity
public class Tecnologia implements Serializable {

    @OneToMany(mappedBy = "tecnologia")
    private List<Error_Tecnologia> error_Tecnologias;

    private static final long serialVersionUID = 1L;
    @Id
    private String nombre;
    
    @Id
    private String version;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Error_Tecnologia> getError_Tecnologias() {
        return error_Tecnologias;
    }

    public void setError_Tecnologias(List<Error_Tecnologia> error_Tecnologias) {
        this.error_Tecnologias = error_Tecnologias;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.nombre);
        hash = 73 * hash + Objects.hashCode(this.version);
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
        final Tecnologia other = (Tecnologia) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.version, other.version);
    }
    
    

    

    @Override
    public String toString() {
        return "Clases.Tecnologia[ id=" + nombre + " ]";
    }
    
}
