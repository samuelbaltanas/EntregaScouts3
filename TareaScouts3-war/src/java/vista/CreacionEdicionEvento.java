/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Evento;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import javax.inject.Inject;

/**
 *
 * @author Adrián
 */
@Named(value = "creacionEdicionEvento")
@RequestScoped
public class CreacionEdicionEvento implements Serializable{
    
    private Evento evento;
    
    @Inject 
    listaEventos lista;
    
    boolean creacion;

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String editar(Long id){
    
        Evento tmp = new Evento(id);
        int idx = this.lista.getEventos().indexOf(tmp);
        this.evento = this.lista.getEventos().get(idx);
        creacion = false;
        
        return "evento.xhtml";
    }
    
    public String crear(){
        creacion = true;
        this.evento = new Evento(new Long(this.lista.getEventos().size()));
        return "evento.xhtml";
    }

    public String commit(Evento ev){
        if(creacion){
            lista.getEventos().add(ev);
        }else{
            int pos = this.lista.getEventos().indexOf(ev);
            lista.getEventos().set(pos, ev);
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

