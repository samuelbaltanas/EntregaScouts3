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

/**
 *
 * @author sam
 */
@Named(value = "signIn")
@RequestScoped
public class SignIn {
        
    private Usuario user;
    private String verify; 
    private Grupo gr;
    
    @Inject
    ControlAutorizacion ctrl;
    
    @Inject 
    ListaUsuarios lst;
    
    @Inject
    listaGrupos lgr;
    
 /*   @PersistenceContext(unitName = "TareaScouts2PU")
    private EntityManager em;
  */
    public List<Grupo> groupList(){
        /*Query q;
        q = em.createQuery("SELECT g FROM Grupo g");
        List<Grupo> res;
        res = q.getResultList();
        return res;*/
        
        return null;
    }
    
 
    public void setVerify(String pass){
        this.verify = pass;
    }
    
    public String getVerify(){
        return this.verify;
    }
   
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    public String autenticar(){
    
     //   if(em.find(Usuario.class, this.user.getAlias()) != null){
          if(this.lst.getUsuarios().contains(this.user)){
           FacesContext ctx = FacesContext.getCurrentInstance();
           ctx.addMessage(null, 
                   new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                           "El alias ya esta en uso. Elija otro para continuar.", 
                           "El alias ya esta en uso. Elija otro para continuar.")
           );
           return null; 
        }
        
        if(!this.user.getContraseña().equals(this.verify)){
             FacesContext ctx = FacesContext.getCurrentInstance();
           ctx.addMessage(null, 
                   new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                           "Ambas contraseñas no coinciden.", 
                           "mbas contraseñas no coinciden.")
           );
           return null; 
        }
        
        this.user.setFecha_ingreso(new Date());
        this.lst.getUsuarios().add(user);
        this.ctrl.setUsuario(this.user);
        
        return "listaEventos.xhtml";
    }

    public String getGr() {
        return Integer.toString(gr.getId()) ;
    }

    public void setGr(String gr) {
        this.gr = lgr.getGrupos().get(new Integer(gr));
    }
    
    
    
    /**
     * Creates a new instance of SignIn
     */
    public SignIn(){
        this.user = new Usuario();
        this.user.setReside_en(new Localidad());
        this.gr = new Grupo(0);
        this.user.setRol(new Rol(Rol.Rol1.EDUCANDO));
         this.user.setParticipa_eventos(new LinkedList<>());
            this.user.setLista_documentos(new LinkedList<>());
        this.verify = "";
        
    }
}
