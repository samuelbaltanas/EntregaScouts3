/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/**
 *
 * @author Alberto
 */

@Entity
@IdClass(HistorialID.class)
public class Historial implements Serializable {
 
    private static final long serialVersionUID = 1L;
    @Id @ManyToOne private Grupo id;
    @Id @ManyToOne private Usuario cuenta;

    public Grupo getId() {
        return id;
    }

    public void setId(Grupo id) {
        this.id = id;
    }

    public Usuario getCuenta() {
        return cuenta;
    }

    public void setCuenta(Usuario cuenta) {
        this.cuenta = cuenta;
    }    
 
    
    
    @Override
    public String toString() {
        return "sii.Historial[ id=" + id + " ]";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.cuenta);
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
        final Historial other = (Historial) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.cuenta, other.cuenta)) {
            return false;
        }
        return true;
    }

    
    
}
