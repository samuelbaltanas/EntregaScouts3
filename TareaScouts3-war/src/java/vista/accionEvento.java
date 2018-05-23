/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Evento;
import entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;



import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Alberto
 */
@Named(value = "accionEvento")
@RequestScoped
public class accionEvento implements Serializable{
    @Inject 
    ControlAutorizacion crt;
    @Inject 
    listaEventos lista;
    
    public accionEvento() {
        
    }
    
    public String apuntarse(Long e){
        
        int i = lista.getEventos().indexOf(new Evento(e));
        Evento ev = lista.getEventos().get(i);

        crt.getUsuario().getParticipa_eventos().add(ev);
        
        return null;
    }
    public String quitarse(Long e){
        
        crt.getUsuario().getParticipa_eventos().remove(new Evento(e));
        
        return null;
    }
    public boolean estaApuntado(Long e){
       
        Evento ev = new Evento(e);
        List<Evento> ls = crt.getUsuario().getParticipa_eventos();
        
        boolean res = ls.contains(ev);
        
        return res;
    }
    
    
    
}
