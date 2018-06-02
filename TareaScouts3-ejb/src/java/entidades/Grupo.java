/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

/**
 *
 * @author Alberto
 */
@Entity
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public List<Historial> getList_historial() {
        return list_historial;
    }

    public List<Usuario> getLista_usuarios() {
        return lista_usuarios;
    }
    private String nombre;
    
    //Relaciones
    @OneToMany(mappedBy = "id")
    private List<Historial> list_historial; 
         
   @OneToMany(mappedBy = "grupo")
    private List<Usuario> lista_usuarios;
   
    @JoinTable(name = "jnd_evn_grp")
    @ManyToMany(mappedBy = "pertenece_a", cascade = CascadeType.ALL)
    private List<Evento> lista_eventos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Evento> getLista_eventos() {
        return lista_eventos;
    }

    public void setLista_eventos(List<Evento> lista_eventos) {
        this.lista_eventos = lista_eventos;
    }



    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public String toString() {
        return "sii.Grupo[ id=" + nombre + " ]";
    }

    public Grupo() {
    }

    public Grupo(int id) {
        this.id = id;
    }
  
    
}
