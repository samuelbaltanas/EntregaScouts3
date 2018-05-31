/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Evento;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import entidades.Grupo;
import java.util.List;
import javax.inject.Inject;
import negocio.Negocio;

/**
 *
 * @author Adrián
 */
@Named(value = "creacionEdicionEvento")
@RequestScoped
public class CreacionEdicionEvento implements Serializable{
    @EJB 
    private Negocio negocio;
    
    private Grupo g;
    private Evento evento;
    private String nombre;
    private String descripcion;
    private Date fecha;
   
    
    boolean creacion;

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String editar(Long id){
        List <Evento> res;
        Evento event= negocio.getEvento(id);
        this.evento=event;
        creacion = false;
        return "evento.xhtml";
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String crear(){
      
        Evento event =new Evento();
        event.setNombre(nombre);
        event.setFecha(fecha);
        event.setDescripcion(descripcion);
        negocio.addEvento(event);
        
         return "evento.xhtml";
    }

    public String commit(Evento ev){
        if(creacion){
            negocio.addEvento(ev);
           
        }else{
          negocio.setEvento(ev);
        }
        
        return "listaEventos.xhtml";
    }

    public boolean isCreacion() {
        return creacion;
    }

    public void setCreacion(boolean creacion) {
        this.creacion = creacion;
    }
   
    
    /**
     * Creates a new instance of CreacionEdicionEvento
     */
    public CreacionEdicionEvento() {
        
        this.evento = new Evento();
    }  
    
    
     
  }

