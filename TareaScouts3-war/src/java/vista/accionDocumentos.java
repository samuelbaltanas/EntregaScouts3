/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Documento;
import java.io.File;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;


/**
 *
 * @author sam
 */
@Named(value = "accionDocumentos")
@RequestScoped
public class accionDocumentos implements Serializable {
    
    @Inject
    ControlAutorizacion ctr;
    
    @Inject
    PruebaDocumentos docs;
    
 
    private Part file;
   
    
    /**
     * Creates a new instance of accionDocumentos
     */
    public accionDocumentos() {
        
    }

   

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        
        this.file = file;
    }
    
    public String save(Long id){
        
        int index = this.docs.getDocumentos().indexOf(new Documento(id, null, null, null));
        
        Documento tmp = this.docs.getDocumentos().get(index);
        
        tmp.setEstado_documento(Documento.Estado.PENDIENTE_REVISION);
        
        tmp.setFile(file);
        
        this.docs.getDocumentos().set(index, tmp);
        
        index =  this.ctr.getUsuario().getLista_documentos().indexOf(tmp);
        
        this.ctr.getUsuario().getLista_documentos().set(index, tmp);
        
        return null;
    }
    
    
    public String documentos(){
        if (!ctr.esEducando()) {
            return "documentacion.xhtml";
        } else {
            return "user_docs.xhtml";
        }
    }
    
}
