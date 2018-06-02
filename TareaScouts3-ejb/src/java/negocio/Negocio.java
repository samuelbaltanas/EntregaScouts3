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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author samu
 */
@Local
public interface Negocio {
    
        public void compruebaLogin(Usuario user) throws NegocioException;
        
        //Grupo
        public List<Grupo> listaGrupos();
        public Grupo getGrupo(int id);
        
        //Eventos
        public void addEvento(Evento e);
        public void setEvento(Evento e);
        public Evento getEvento(Long id);
        public boolean estaApuntado(Usuario usr, Long e)  throws NegocioException;
        public List<Evento> getEventos(Usuario u) throws NegocioException;
        public List<Evento> allEventos();
        
        public void apuntarse(Usuario user, Evento e) throws NegocioException;
        public void quitarse(Usuario user, Evento e) throws NegocioException;
        
        //Usuarios
        public List<Usuario> listaUsuarios();
        public Usuario refrescarUsuario(Usuario user) throws NegocioException;
        public void nuevoUsuario(Usuario user) throws NegocioException;
        public void modificarUsuario(Usuario user);
        
        //Documentos
        public List<Documento> listaDocumentos();
        public List<Documento> documentosUser(Usuario usr) throws NegocioException;
        public void modificarDocumento(Documento doc) throws NegocioException;
        public void crearDocumento(Documento doc);
        public Documento getDocumento(Long id);
        
}
