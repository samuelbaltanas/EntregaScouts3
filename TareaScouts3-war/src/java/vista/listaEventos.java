/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Evento;
import entidades.Usuario;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Alberto
 */
@Named(value = "listaEventos")
@SessionScoped
public class listaEventos implements Serializable{
    private LinkedList<Evento> eventos;
     
    public listaEventos(){
        eventos = new LinkedList<>();
        for(int i=1;i<9;i++){
            Evento event = new Evento(new Long(i));
            event.setNombre("nombre" +i);
            event.setFecha(Date.from(Instant.now()));
            event.setDescripcion("Evento " + i);
            //event.setParticipantes(new ArrayList<Usuario>());
            eventos.add(event);
            
        }
    }

    public LinkedList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(LinkedList<Evento> eventos) {
        this.eventos = eventos;
    }
     
    
}
