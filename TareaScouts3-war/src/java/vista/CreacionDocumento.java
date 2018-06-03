package vista;

import entidades.Documento;
import entidades.Grupo;
import entidades.Rol;
import entidades.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import negocio.Negocio;
import negocio.NegocioException;

/**
 *
 * @author Alberto
 */
@Named(value = "creacionDocumento")
@RequestScoped
public class CreacionDocumento implements Serializable {

    @EJB
    private Negocio neg;

    private Documento doc;

    int gr;

    public String commit() throws NegocioException {

        this.doc.setEstado_documento(Documento.Estado.PENDIENTE_ENTREGA);

        for (Usuario usr : neg.getGrupo(gr).getLista_usuarios()) {
            if (usr.getRol().getId() != Rol.Rol1.EDUCANDO) {
                continue;
            }

            this.doc.setDueño(usr);
            usr.getLista_documentos().add(doc);
            neg.modificarUsuario(usr);

        }

        return "documentacion.xhtml";
    }

    public int getGr() {
        return gr;
    }

    public void setGr(int gr) {
        this.gr = gr;
    }

    public List<Grupo> listaGrup() {
        return neg.listaGrupos();
    }

    public Documento getDoc() {
        return doc;
    }

    public void setDoc(Documento doc) {
        this.doc = doc;
    }

    public String crear() {
        return "documento.xhtml";
    }

    public CreacionDocumento() {
        doc = new Documento();
    }
}
