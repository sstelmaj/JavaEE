/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.DTOs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class CantidadPorFecha {
    private Long cantidad;
    private Date fechaSubida;
    private SimpleDateFormat formato;

    public CantidadPorFecha() {
        this.formato = new SimpleDateFormat("dd/MM");
    }
    
    public CantidadPorFecha(Long cantidad, Date fechaSubida) {
        this.cantidad = cantidad;
        this.fechaSubida = fechaSubida;
        this.formato = new SimpleDateFormat("dd/MM");
    }
    
    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public int getIntCantidad() {
        return cantidad.intValue();
    }
    public String getStringFechaSubida() {
        return formato.format(fechaSubida);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.fechaSubida);
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
        final CantidadPorFecha other = (CantidadPorFecha) obj;
        return Objects.equals(this.fechaSubida, other.fechaSubida);
    }
    
    

    @Override
    public String toString() {
        return "CantidadPorFecha{" + "cantidad=" + cantidad + ", fechaSubida=" + fechaSubida + '}';
    }
    
}
