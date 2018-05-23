/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author kenza.hibbi
 */
@Entity
public class Tutor_legal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="dni" , nullable= false , length=50)
    private String dni;
    
    @Column(name="Nombre" , nullable= false , length=50)
    private String nombre;
    @Column(name="Apellido" , nullable= false , length=50)
    private String apellidos;
    @Column(name="Email", length=50)
    private String email;
     

    
    //Relaciones
    
    @ManyToMany(mappedBy = "es_tutelado")
    private List<Usuario> tutela_a;

    public List<Usuario> getTutela_a() {
        return tutela_a;
    }

    public void setTutela_a(List<Usuario> tutela_a) {
        this.tutela_a = tutela_a;
    }

     public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.dni);
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
        final Tutor_legal other = (Tutor_legal) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Tutor_legal{" + "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + '}';
    }
    
    
    

   
   
    
}
