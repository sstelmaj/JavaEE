/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Usuario
 */
@Entity
public class Error implements Serializable {

    @OneToMany(mappedBy = "error")
    private List<Nota> notas;

    @ManyToOne
    private Usuario usuario;
    
    @OneToMany(mappedBy = "error")
    private List<Error_Etiqueta> error_Etiquetas;

    @OneToMany(mappedBy = "error")
    private List<Error_Tecnologia> error_Tecnologias;
    
    @OneToMany
    private List<Archivo> archivos;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String titulo;
    private String descripcion;
    private String link;
    private String repositorio;
    
    @Lob
    @Column(name = "codigo", columnDefinition = "TEXT")
    private String codigo;
    
    @Lob
    @Column(name = "consola", columnDefinition = "TEXT")
    private String consola;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaSubida;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Error)) {
            return false;
        }
        Error other = (Error) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Error[ id=" + id + " ]";
    }
    
}
