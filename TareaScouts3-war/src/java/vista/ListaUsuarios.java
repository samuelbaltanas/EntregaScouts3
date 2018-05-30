/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import negocio.Negocio;
/**
 *
 * @author kenza.hibbi
 */
@Named(value = "listaUsuarios")
@RequestScoped
public class ListaUsuarios implements Serializable {
    
    @EJB
    Negocio neg;
       
     public ListaUsuarios(){   
     }
    
     
     public List<Usuario> getUsuarios() {
        return  neg.listaUsuarios();
    }
    
    
}
