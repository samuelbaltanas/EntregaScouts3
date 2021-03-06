package negocio;

import entidades.Documento;
import entidades.Evento;
import entidades.Grupo;
import entidades.Localidad;
import entidades.Rol;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author sam
 */
@Stateless
@LocalBean
public class NegocioImpl implements Negocio {

    @PersistenceContext(unitName = "TareaScouts3-PU")
    private EntityManager em;

    @Override
    public void compruebaLogin(Usuario user) throws NegocioException {
        Usuario u = em.find(Usuario.class, user.getAlias());
        if (u == null) {
            throw new UsuarioInexistenteException();
        }
        if (!u.getContraseña().equals(user.getContraseña())) {
            throw new ContrasenyaInvalidaException();
        }
    }

    @Override
    public Usuario refrescarUsuario(Usuario user) throws NegocioException {
        compruebaLogin(user);
        Usuario u = em.find(Usuario.class, user.getAlias());
        em.refresh(u);
        return u;
    }
    
    @Override
    public void nuevoUsuario(Usuario user) throws NegocioException {
        Usuario u = em.find(Usuario.class, user.getNombre());
        if (u != null) {
            throw new UsuarioExistenteException();
        }
        em.persist(user);
    }

    @Override
    public void modificarUsuario(Usuario user) {
        em.merge(user);

    }

    @Override
    public void addEvento(Evento e) {
        
        em.persist(e);
    }

    @Override
    public void setEvento(Evento e) {
        em.merge(e);
    }

    @Override
    public List<Evento> getEventos(Usuario u) throws NegocioException {

        Usuario us = refrescarUsuario(u);
        
        List<Evento> res;

        Query q = em.createQuery("SELECT ev from Evento ev WHERE ev.pertenece_a = ?1");
        q.setParameter(1, us.getGrupo());
        res = q.getResultList();
        
        return res;
    }

     @Override
    public List<Documento> documentosUser(Usuario usr) throws NegocioException {

        Usuario user = refrescarUsuario(usr);
        List<Documento> listDomUser;
        
        Query q = em.createQuery("SELECT d FROM Documento d WHERE d.dueño = ?1");
        q.setParameter(1, usr);
        
        listDomUser = q.getResultList();
            

        return listDomUser;
    }
 
     @Override
    public void crearDocumento(Documento doc) {
        
        em.persist(doc);
           
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Grupo> listaGrupos() {
        
        Query q = em.createQuery("select g from Grupo g");
        List<Grupo> res = q.getResultList();
        
        return res;
    }

    @Override
    public Grupo getGrupo(int id) {
        
        Grupo gr = em.find(Grupo.class, id);
        
        return gr;
    }
    
    @Override
    public void apuntarse(Usuario user, Evento e) throws NegocioException {
     
        Usuario u= refrescarUsuario(user);
        
        List<Evento> res= u.getParticipa_eventos();
        
        if (res.contains(e)){
            throw new UsuarioApuntadoException("Usuario apuntado");
        }else{
            res.add(e);
            em.merge(u);
        }
        
    }
   

    @Override
    public void quitarse(Usuario user, Evento e) throws NegocioException {
        Usuario u= refrescarUsuario(user);
        
        List<Evento> res= u.getParticipa_eventos();
        
        if (res.contains(e)){
            res.remove(e);
            em.merge(u);
        }else{
            throw new UsuarioApuntadoException("Usuario apuntado");
           
        }
    }


    @Override
    public List<Usuario> listaUsuarios() {
       
        List<Usuario> res;
        Query u1 = em.createQuery("SELECT c FROM Usuario c ORDER BY C.nombre ASC");
        
        res = u1.getResultList();
        
        return res;
    }
 
    @Override
    public List<Documento> listaDocumentos() {
        List <Documento> res;
        Query q1 = em.createQuery("SELECT I FROM Documento I");
        res=q1.getResultList();
        return res;
    }
    
    @Override
    public void modificarDocumento(Documento doc) throws NegocioException{
        Documento dc = em.find(Documento.class, doc.getId());
        
        if(dc == null){
            throw new InexistentDocException();
        }
        
        em.merge(doc);
    }

    @Override
    public Evento getEvento(Long id) {
      return em.find(Evento.class,id);
    }
    
    @Override
    public Documento getDocumento(Long id){
        
        return em.find(Documento.class, id);
    }

    @Override
    public boolean estaApuntado(Usuario usr, Long e) throws NegocioException{
        
            usr = refrescarUsuario(usr);
               
            return usr.getParticipa_eventos().contains(new Evento(e));
      
    }

    @Override
    public List<Evento> allEventos() {
        Query q = em.createQuery("select g from Evento g ORDER BY G.fecha ASC");
        List<Evento> res = q.getResultList();
        
        return res;
        
    }
    
    @Override
    public void borrarEvento(Long e){
          Evento ev = em.find(Evento.class, e);
          em.remove(ev);
           
    }
    
    @Override
    public void addLocalidad(Localidad loc){
        boolean create = !em.contains(loc);
        
        if (create) {
            em.persist(loc);
        }
    
    }

    @Override
    public Rol getRol(Rol.Rol1 role) {
       return em.find(Rol.class, role);
    }
    

    
}
