package negocio;

import entidades.Documento;
import entidades.Evento;
import entidades.Grupo;
import entidades.Usuario;
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
        if (u.getContraseña().equals(user.getContraseña())) {
            throw new ContrasenyaInvalidaException();
        }
    }

    @Override
    public Usuario refrescarUsuario(Usuario user) throws NegocioException {
        compruebaLogin(user);
        Usuario u = em.find(Usuario.class, user.getNombre());
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
        em.persist(e);
    }

    @Override
    public List<Evento> getEventos(Grupo g) {

        List<Evento> res;

        Query q1 = em.createQuery("SELECT c FROM Evento c WHERE c.pertenece_a = ?1 ORDER BY C.fecha ASC");
        q1.setParameter(1, g.getId());

        res = q1.getResultList();

        return res;
    }

     @Override
    public List<Documento> documentosUser(Usuario usr) throws NegocioException {

        Usuario user = refrescarUsuario(usr);
        List<Documento> listDomUser = user.getLista_documentos();

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
            em.persist(e);
        }
        
    }
   

    @Override
    public void quitarse(Usuario user, Evento e) throws NegocioException {
        Usuario u= refrescarUsuario(user);
        
        List<Evento> res= u.getParticipa_eventos();
        
        if (res.contains(e)){
            em.remove(e);
            
        }else{
            throw new UsuarioApuntadoException("Usuario apuntado");
           
        }
    }


    @Override
    public List<Usuario> listaUsuarios() {
       
        List<Usuario> res;
        Query u1 = em.createQuery("SELECT c FROM Evento c ORDER BY C.fecha ASC");
        
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
        Query q = em.createQuery("selcect d from Documento d");
        List<Documento> docs = q.getResultList();
        
        if (docs.contains(doc)) {
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
}
