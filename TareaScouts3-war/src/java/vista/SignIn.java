/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Grupo;
import entidades.Localidad;
import entidades.Usuario;

import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import entidades.Grupo;
import entidades.Rol;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import negocio.Negocio;
import negocio.NegocioException;
import negocio.UsuarioExistenteException;

/**
 *
 * @author sam
 */
@Named(value = "signIn")
@RequestScoped
public class SignIn {

    private Usuario user;
    private String verify;
    private int gr;

  

    @Inject
    ControlAutorizacion ctrl;

    @EJB
    Negocio neg;

    /*   @PersistenceContext(unitName = "TareaScouts3-PU")
    private EntityManager em;
     */
    public List<Grupo> groupList() {

        return neg.listaGrupos();
    }

    public void setVerify(String pass) {
        this.verify = pass;
    }

    public String getVerify() {
        return this.verify;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String autenticar() {

        if (!this.user.getContraseña().equals(this.verify)) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ambas contraseñas no coinciden.",
                            "Ambas contraseñas no coinciden.")
            );
            return null;
        }

        this.user.setFecha_ingreso(new Date());
        this.user.setGrupo(neg.getGrupo(gr));

        try {
            neg.nuevoUsuario(this.user);
        } catch (UsuarioExistenteException ex) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Nombre usuario ya en uso.",
                            "Nombre usuario ya en uso.")
            );
            return null;
        } catch (NegocioException ex) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.ctrl.setUsuario(this.user);

        return "listaEventos.xhtml";
    }

    

    /**
     * Creates a new instance of SignIn
     */
    public SignIn() {
        this.user = new Usuario();
        this.user.setReside_en(new Localidad());
        this.user.setRol(new Rol(Rol.Rol1.EDUCANDO));
        this.user.setParticipa_eventos(new LinkedList<>());
        this.user.setLista_documentos(new LinkedList<>());
        this.verify = "";

    }
    
      public int getGr() {
        return gr;
    }

    public void setGr(int gr) {
        this.gr = gr;
    }
}
