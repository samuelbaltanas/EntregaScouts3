package vista;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import entidades.Rol;
import entidades.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author francis
 */
@Named(value = "controlAutorizacion")
@SessionScoped

public class ControlAutorizacion implements Serializable {
    
    private Usuario usuario;
    
    public ControlAutorizacion() {
       
    }
    

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String home() {
       
        return "";
    }

    /**
     * Creates a new instance of ControlAutorizacion
     */
    
    public boolean esScouter(){
        return usuario != null && usuario.getRol().getId() == Rol.Rol1.SCOUTER;
    }
    public boolean esEducando(){
         return usuario.getRol().getId() == Rol.Rol1.EDUCANDO;
    }
    
    public boolean esCoordinador(){
         return usuario.getRol().getId() == Rol.Rol1.COORDINADOR || usuario.getRol().getId() == Rol.Rol1.RESPONSABLE_DOCUMENTACION;
    }
    
     public String logout()
    {
        // Destruye la sesión (y con ello, el ámbito de este bean)
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        usuario = null;
        return "index.xhtml";
    }
    
}
