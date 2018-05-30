/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Grupo;
import entidades.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import negocio.Negocio;

 import javax.inject.Inject;
import negocio.NegocioException;

/**
 *
 * @author sam
 */
@Named(value = "edicionUsuario")
@RequestScoped
public class EdicionUsuario {

    @EJB
    Negocio neg; 
    @Inject
    ControlAutorizacion ctr;
    Usuario usuario;
    Grupo gr;
    
    //METHODS
    
    public String edit(){
         try {

            this.usuario=neg.refrescarUsuario(ctr.getUsuario());

         } catch (NegocioException ex) {
            Logger.getLogger(EdicionUsuario.class.getName()).log(Level.SEVERE, null, ex);
         }

        return "datosUsuario.xhtml";
    }
        
    
    public String commit(){
        
        neg.modificarUsuario(usuario);
        return null;
    }

    public List<Grupo> listaGrup(){
        return neg.listaGrupos();
    }
    
    //GETTERS & SETTERS
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getGr() {
        return Integer.toString(gr.getId()) ;
    }

    public void setGr(int gr) {
        this.gr = neg.getGrupo(gr);
    }
    
    
    /**
     * Creates a new instance of EdicionUsuario
     */
    public EdicionUsuario() {
        this.usuario = new Usuario();
        this.gr = new Grupo();
    }
    
}
