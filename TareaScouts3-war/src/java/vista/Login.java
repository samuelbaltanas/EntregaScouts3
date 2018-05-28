/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import negocio.ContrasenyaInvalidaException;
import negocio.Negocio;
import negocio.NegocioException;
import negocio.UsuarioInexistenteException;

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

    @EJB
    Negocio neg;

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

    public String autenticar() {
        
        Usuario user = new Usuario();
        user.setAlias(usuario);

        try {
            user = neg.refrescarUsuario(user);
        } catch (ContrasenyaInvalidaException ex) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciales erroneas", "Credenciales erroneas"));
            return null;
        } catch (UsuarioInexistenteException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no registrado", "Usuario no registrado"));
            return null;
        } catch (NegocioException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        ctrl.setUsuario(user);
        return "listaEventos.xhtml";
    }

}
