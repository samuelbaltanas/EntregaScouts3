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
public class NegocioImpl implements Negocio{
    
    @PersistenceContext(unitName = "TareaScouts3-PU")
    private EntityManager em;
    
      @Override
    public void compruebaLogin(Usuario user) throws NegocioException{
        Usuario u=em.find(Usuario.class,user.getNombre());
        if(user==null){
           throw new NombreInvalidoException();
        }
       if(u.getContraseña().equals(user.getContraseña())){
          throw new ContrasenyaInvalidaException();
    }
  }
    @Override
    public Usuario refrescarUsuario(Usuario user) throws NegocioException{
        compruebaLogin(user);
        Usuario u=em.find(Usuario.class, user.getNombre());
        em.refresh(u);
        return u;
    }

    @Override
    public void nuevoUsuario(Usuario user) throws NegocioException {
        Usuario u=em.find(Usuario.class, user.getNombre());
        if(u!=null){
            throw new UsuarioInexistenteException();
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
        //return g.getLista_eventos();
    }

    @Override
    public void apuntarse(Usuario user, Evento e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void quitarse(Usuario user, Evento e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> listaUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public List<Documento> listaDocumentos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Documento> documentosUser(Usuario usr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarDocumento(Documento doc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearDocumento(Documento doc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    private void ContrasenyaInvalidaException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
