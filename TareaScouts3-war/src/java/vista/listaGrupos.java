package vista;

import entidades.Grupo;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Alberto
 */
@Named(value = "listaGrupos")
@SessionScoped
public class listaGrupos implements Serializable{
    private LinkedList<Grupo> grupos;
     
    public listaGrupos(){
        grupos = new LinkedList<Grupo>();
        String lst[] = {"Castores", "Lobatos", "Scouts", "Escultas", "Rovers", "Scouters de Apoyo"};
        for(int i=0;i<6;i++){
            Grupo g = new Grupo(i);
            g.setNombre(lst[i]);
            g.setLista_eventos(new LinkedList<>());
            grupos.add(g);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.grupos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final listaGrupos other = (listaGrupos) obj;
        if (!Objects.equals(this.grupos, other.grupos)) {
            return false;
        }
        return true;
    }

    public LinkedList<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(LinkedList<Grupo> grupos) {
        this.grupos = grupos;
    }

  
    
}

