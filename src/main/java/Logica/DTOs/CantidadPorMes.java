/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.DTOs;

import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class CantidadPorMes {
    private Long cantidad;
    private String mes;

    public CantidadPorMes() {
    }
    
    public CantidadPorMes(Long cantidad, String mes) {
        this.cantidad = cantidad;
        this.mes = mes;
    }
    
    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getMes() {
        return mes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.mes);
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
        final CantidadPorMes other = (CantidadPorMes) obj;
        return Objects.equals(this.mes, other.mes);
    }
    
    

    @Override
    public String toString() {
        return "CantidadPorMes{" + "cantidad=" + cantidad + ", mes=" + mes + '}';
    }
    
}
