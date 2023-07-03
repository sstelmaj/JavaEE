package Logica.Clases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import Logica.DTOs.CantidadPorFecha;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Temporal;
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 *
 * @author Usuario
 */

@SqlResultSetMapping(
    name = "CantidadPorFecha",
    classes = @ConstructorResult(
        targetClass = CantidadPorFecha.class,
        columns = {
            @ColumnResult(name = "cantidad"),
            @ColumnResult(name = "fechaSubida"),
        }
    )
)
@Entity
public class Solucion implements Serializable {
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    
    private Error error;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
   
    private Usuario usuario;
    
    @OneToMany(cascade={CascadeType.ALL})
    private List<Archivo> archivos;

    @CascadeOnDelete
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Etiqueta> etiquetas;
   

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Lob
    @Column(name = "codigo", columnDefinition = "TEXT")
    private String codigo;
    
    private String descripcion;
    private String link;
    private int puntos;
    
    private Boolean active = Boolean.TRUE;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaSubida;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<Archivo> archivos) {
        this.archivos = archivos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
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
        if (!(object instanceof Solucion)) {
            return false;
        }
        Solucion other = (Solucion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Solucion[ id=" + id + " ]";
    }
    
}
