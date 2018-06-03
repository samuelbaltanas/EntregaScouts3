/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
/**
 *
 * @author Adrián
 */



@Entity
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   private String nombre;
   @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
   @Column(name="descripcion" , nullable= false , length=500)
    private String descripcion;
  
  //Relaciones
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "jnd_evn_grp",
    joinColumns = @JoinColumn(name = "evento_fk"),
    inverseJoinColumns = @JoinColumn(name = "grupo_fk"))
    private List<Grupo> pertenece_a;
    
    @JoinTable(name = "jnd_usr_tut")
    @ManyToMany(mappedBy = "participa_eventos", cascade = CascadeType.MERGE)
    private List<Usuario> participantes;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public List<Usuario> getParticipantes() {
        return participantes;
    } 
   
    public String getNombre() {
        return nombre;
    }

    public Date getFecha() {
        return fecha;
    }
   
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Evento() {
    }
    
    

    public Evento(Long id) {
        this.id = id;
    }
  
    
    
    @Override
    public int hashCode() {
        int hash = 3;
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
        final Evento other = (Evento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }

        return true;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }

   public List<Grupo> getPertenece_a() {
        return pertenece_a;
    }

    public void setPertenece_a(List<Grupo> pertenece_a) {
        this.pertenece_a = pertenece_a;
    }
   
}
    

