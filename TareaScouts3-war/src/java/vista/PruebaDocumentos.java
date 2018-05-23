/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Documento;
import entidades.Rol;
import entidades.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import javax.inject.Named;

@Named(value = "pruebaDocumentos")
@SessionScoped
public class PruebaDocumentos implements Serializable{
    
    private List<Documento> documentos;
    
    @Inject
    private ListaUsuarios lst;
    
    @Inject
    private ControlAutorizacion crt;
    
    public PruebaDocumentos(){
       
    }

    @PostConstruct
    private void init(){
        
        documentos = new ArrayList<>();
        
        //creo unos documentos de prueba
        Documento d1 = new Documento(new Long(1), new Date(), new Date(), Documento.Estado.PENDIENTE_REVISION);
        d1.setNombre("Entrega1");
        Documento d2 = new Documento(new Long(2), new Date(), new Date(), Documento.Estado.PENDIENTE_REVISION);
        d2.setNombre("Entrega2");
        Documento d3 = new Documento(new Long(3), new Date(), null, Documento.Estado.PENDIENTE_ENTREGA);
        d3.setNombre("Entrega3");
        documentos.add(d1);
        documentos.add(d2);
        documentos.add(d3);
        d1.setDueño(lst.getUsuarios().get(1));
        lst.getUsuarios().get(1).getLista_documentos().add(d1);
        d2.setDueño(lst.getUsuarios().get(1));
        lst.getUsuarios().get(1).getLista_documentos().add(d2);
        d3.setDueño(lst.getUsuarios().get(1));
        lst.getUsuarios().get(1).getLista_documentos().add(d3);
    }
    
    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }
    
    public List<Documento> usrDocumentos() {
        return this.crt.getUsuario().getLista_documentos();
    }

    
}
