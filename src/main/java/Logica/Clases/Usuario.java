package Logica.Clases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import Logica.DTOs.CantidadPorMes;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Temporal;

/**
 *
 * @author Usuario
 */
@SqlResultSetMapping(
    name = "CantidadPorMes",
    classes = @ConstructorResult(
        targetClass = CantidadPorMes.class,
        columns = {
            @ColumnResult(name = "CANTIDAD"),
            @ColumnResult(name = "MES"),
        }
    )
)
@Entity
public class Usuario implements Serializable {

    @OneToMany(mappedBy = "usuario")
    private List<Error> errors;

    @OneToMany(mappedBy = "usuario")
    private List<Solucion> solucions;

    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nombre;
    private String apellido;
    @Id
    private String mail;
    private String password;
    private Boolean active = Boolean.TRUE;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Perfil perfil;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AjustesUsuario ajustes;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRegistro;
    
    @PrePersist
    protected void onCreate() {
        if(fechaRegistro == null) {
            fechaRegistro = new Date();
        }
    }
    
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public AjustesUsuario getAjustes() {
        return ajustes;
    }

    public void setAjustes(AjustesUsuario ajustes) {
        this.ajustes = ajustes;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public List<Solucion> getSolucions() {
        return solucions;
    }

    public void setSolucions(List<Solucion> solucions) {
        this.solucions = solucions;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Boolean isActive() {
        return active;
    }

    public void setIsActive(Boolean active) {
        this.active = active;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "errors=" + errors + ", solucions=" + solucions + ", id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + ", password=" + password + ", active=" + active + ", perfil=" + perfil + ", ajustes=" + ajustes + '}';
    }

    
    
}