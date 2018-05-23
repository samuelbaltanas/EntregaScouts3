/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Grupo;
import entidades.Usuario;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author sam
 */
@Named(value = "edicionUsuario")
@RequestScoped
public class EdicionUsuario {

    
    @Inject
    ListaUsuarios lst;
    
    @Inject
    listaGrupos lgr;
    
    Usuario usuario;
    Grupo gr;
    
    //METHODS
    
    public String edit(String alias){
        
        int idx = lst.getUsuarios().indexOf(new Usuario(alias));
    
        this.usuario = lst.getUsuarios().get(idx);
        return "datosUsuario.xhtml";
    }
        
    
    public String commit(){
        
        int idx = lst.getUsuarios().indexOf(usuario);
        Usuario u2 = lst.getUsuarios().get(idx);
        u2.setNombre(this.usuario.getNombre());
        u2.setApellidos(this.usuario.getApellidos());
        u2.setDni(this.usuario.getDni());
        u2.setFecha_nacimiento(this.usuario.getFecha_nacimiento());
        u2.setEmail(this.usuario.getEmail());
        u2.setDireccion(this.usuario.getDireccion());
        u2.setGrupo(this.usuario.getGrupo());
        u2.setTelefono(this.usuario.getTelefono());
        lst.getUsuarios().set(idx, u2);
        return null;
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

    public void setGr(String gr) {
        this.gr = lgr.getGrupos().get(new Integer(gr));
    }
    
    
    /**
     * Creates a new instance of EdicionUsuario
     */
    public EdicionUsuario() {
        this.usuario = new Usuario();
        this.gr = new Grupo();
    }
    
}
