/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import entidades.Rol;
import entidades.Usuario;
import java.io.Serializable;
import java.util.LinkedList;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;
/**
 *
 * @author kenza.hibbi
 */
@Named(value = "listaUsuarios")
@SessionScoped
public class ListaUsuarios implements Serializable {
    
    
    private LinkedList<Usuario> usuarios;
   
     public ListaUsuarios(){  
         usuarios= new LinkedList<>();
           for(int i=0;i<7;i++){
            Usuario usuario1 = new Usuario(new Long(i));
            usuario1.setNombre("nombre" +i);
            usuario1.setApellidos("Apellido" +i);
            usuario1.setDni("X123456 " + i);
            usuario1.setAlias("u" +i);
            usuario1.setContraseña("u" +i);
            usuario1.setEmail("email " + i +"@gmail.com");
            usuario1.setTelefono("6666666" +i);
               if (i %2 ==0) {
                   usuario1.setRol(new Rol(Rol.Rol1.SCOUTER));
               }else{
                   usuario1.setRol(new Rol(Rol.Rol1.EDUCANDO));
               }
            usuario1.setParticipa_eventos(new LinkedList<>());
            usuario1.setLista_documentos(new LinkedList<>());
            usuarios.add(usuario1);
        }
         
     }
    
     
     public LinkedList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(LinkedList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    
  /*  public boolean buscar(String dni ){
        boolean res= false;
        for(int i=0; i<usuarios.size();i++){
            if(usuarios.get(i).equals(dni)){
                res=true;
            }
            
        }
        return res;
    }
   */
}
