/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Usuario;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sam
 */
@Named(value = "login")
@RequestScoped
public class Login {

    
    private String usuario;
    private String contrasenia;
    
    @Inject
    ControlAutorizacion ctrl;
    
    @Inject
    ListaUsuarios lst;
    
    @PersistenceContext(unitName = "TareaScouts2PU")
    private EntityManager em;
    
    /**
     * Creates a new instance of Login
     */
    public Login() {
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
     public String autenticar(){
        
         if(this.usuario == null){
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Rellena los campos para hacer login", "Rellena los campos para hacer login"));
            return null;
        }
        Usuario user = new Usuario();
        user.setAlias(usuario);
        int i = lst.getUsuarios().indexOf(user);
        user = lst.getUsuarios().get(i);
        
            
        if (user != null) {
            
            if(user.getContraseña().equals(this.contrasenia)){
                ctrl.setUsuario(user);
                return "listaEventos.xhtml";
            }else{
                FacesContext ctx = FacesContext.getCurrentInstance();
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciales erroneas", "Credenciales erroneas"));
                return null;
            }
        } else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no registrado", "Usuario no registrado"));
            return null;
        }
         
     }
    
}
