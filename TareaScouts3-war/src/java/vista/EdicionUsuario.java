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
import javax.annotation.PostConstruct;
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
    
    int gr;
    
    //METHODS
    
    public String edit(){
         try {

            this.usuario=neg.refrescarUsuario(ctr.getUsuario());
            gr = usuario.getGrupo().getId();

         } catch (NegocioException ex) {
            Logger.getLogger(EdicionUsuario.class.getName()).log(Level.SEVERE, null, ex);
         }

        return "datosUsuario.xhtml";
    }
        
    
    public String commit(Usuario usr) throws NegocioException{
        usuario.setGrupo(neg.getGrupo(gr));
        neg.modificarUsuario(usuario);
        this.usuario=neg.refrescarUsuario(ctr.getUsuario());
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
    public int getGr() {
        return gr ;
    }

    public void setGr(int gr) {
        this.gr = gr;
    }
    
    
    /**
     * Creates a new instance of EdicionUsuario
     */
    public EdicionUsuario()  {
              
    }
    
    @PostConstruct
    public void init(){
        try {
            this.usuario=neg.refrescarUsuario(ctr.getUsuario());
        } catch (NegocioException ex) {
            Logger.getLogger(EdicionUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
