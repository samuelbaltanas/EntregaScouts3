/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
 
/**
 *
 * @author Jose Antonio
 */
@Entity
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Rol1 rol;
    
    

    public Rol1 getId() {
        return rol;
    }

    public void setId(Rol1 rol) {
        this.rol = rol;
    }
    
    public enum Rol1{
        EDUCANDO,
        SCOUTER,
        COORDINADOR,
        RESPONSABLE_DOCUMENTACION
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rol != null ? rol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.rol == null && other.rol != null) || (this.rol != null && !this.rol.equals(other.rol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db_scouts.Rol[ id=" + rol + " ]";
    }

    public Rol() {
    }

    public Rol(Rol1 rol) {
        this.rol = rol;
    }
    
    
    
    
}
