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
import java.util.ArrayList;
import java.util.List;
import negocio.Negocio;

/**
 *
 * @author Adrián
 */
@Named(value = "creacionEdicionEvento")
@RequestScoped
public class CreacionEdicionEvento implements Serializable {

    @EJB
    private Negocio negocio;
    private Evento evento;
    boolean creacion;

    ArrayList<Integer> list;

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String editar(Long id) {
        Evento event = negocio.getEvento(id);
        this.evento = event;
        creacion = false;
       
        return "evento.xhtml";
    }

    public String crear() {

        this.creacion = true;
        return "evento.xhtml";
    }

    public String commit() {

        if (creacion) {
            List<Grupo> lst = new ArrayList<>();
            
            for(Integer i : list) {
                lst.add(negocio.getGrupo(i));
            }
            
            evento.setPertenece_a(lst);
            
            negocio.addEvento(evento);
        } else {
            negocio.setEvento(evento);
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

    public List<Grupo> listaGrupos() {

        return negocio.listaGrupos();
    }

}
