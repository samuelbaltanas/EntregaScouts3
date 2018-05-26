/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;


import entidades.Documento;
import entidades.Evento;
import entidades.Grupo;
import entidades.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author samu
 */
@Local
public interface Negocio {
    
        public void compruebaLogin(Usuario user) throws NegocioException;;
        
        //Eventos
        public void addEvento(Evento e);
        public void setEvento(Evento e);
        public List<Evento> getEventos(Grupo g);
        
        public void apuntarse(Usuario user, Evento e);
        public void quitarse(Usuario user, Evento e);
        
        //Usuarios
        public List<Usuario> listaUsuarios();
        public Usuario refrescarUsuario(Usuario user) throws NegocioException;
        public void nuevoUsuario(Usuario user) throws NegocioException;
        public void modificarUsuario(Usuario user);
        
        //Documentos
        public List<Documento> listaDocumentos();
        public List<Documento> documentosUser(Usuario usr);
        public void modificarDocumento(Documento doc);
        public void crearDocumento(Documento doc);
        
        
}
