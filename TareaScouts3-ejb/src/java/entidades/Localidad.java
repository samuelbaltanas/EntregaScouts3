/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany; 

/**
 *
 * @author Adrián
 */
@Entity
public class Localidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer codigo_postal;

    //Variables
    @Column(name="provincia",nullable=false,length=30)
    private String provincia;
    @Column(name="nombre",nullable=false,length=50)
    private String nombre;
    
    //Relaciones
    @OneToMany(mappedBy = "reside_en")
    private List<Usuario> esta_formada;
    

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCodigo_postal() {
        return codigo_postal;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo_postal(Integer codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    


    
   

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localidad)) {
            return false;
        }
        Localidad other = (Localidad) object;
        if ((this.codigo_postal == null && other.codigo_postal != null) || (this.codigo_postal != null && !this.codigo_postal.equals(other.codigo_postal))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Localidad{" + "codigo_postal=" + codigo_postal + '}';
    }
}