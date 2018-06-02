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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;
import negocio.Negocio;
import negocio.NegocioException;

/**
 *
 * @author Alberto
 */
@Named(value = "accionEvento")
@RequestScoped
public class accionEvento implements Serializable {

    @Inject
    ControlAutorizacion crt;

    @EJB
    Negocio neg;

    public accionEvento() {

    }

    public String apuntarse(Long e) {

        try {
            neg.apuntarse(crt.getUsuario(), neg.getEvento(e));
        } catch (NegocioException ex) {
            Logger.getLogger(accionEvento.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String quitarse(Long e) {

        try {
            neg.quitarse(crt.getUsuario(), neg.getEvento(e));
        } catch (NegocioException ex) {
            Logger.getLogger(accionEvento.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public boolean estaApuntado(Long e) {

        boolean b = false;

        try {
           
            b = neg.estaApuntado(crt.getUsuario(), e);

        } catch (NegocioException ex) {
            Logger.getLogger(accionEvento.class.getName()).log(Level.SEVERE, null, ex);
        }

        return b;
    }

    public List<Evento> getEventos() {
        List<Evento> list = null;
        
        if(crt.esScouter()){
            list = neg.allEventos();
        }else if(crt.esEducando()){
            list = neg.getEventos(crt.getUsuario().getGrupo());
        }
        
        return list;
    }

}
