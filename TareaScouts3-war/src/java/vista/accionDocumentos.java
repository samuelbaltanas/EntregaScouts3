/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidades.Documento;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import negocio.Negocio;
import negocio.NegocioException;

/**
 *
 * @author sam
 */
@Named(value = "accionDocumentos")
@RequestScoped
public class accionDocumentos implements Serializable {

    @EJB
    private Negocio negocio;

    @Inject
    ControlAutorizacion ctr;

    private Part file;

    public accionDocumentos() {
    }

    public List<Documento> getDocumentos() {
        return negocio.listaDocumentos();
    }

    public List<Documento> getDocsUsuario() {
        List<Documento> lst = null;
        try {
            lst = negocio.documentosUser(ctr.getUsuario());
        } catch (NegocioException ex) {
            Logger.getLogger(accionDocumentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {

        this.file = file;
    }

    public String verificar(Long id) {

        Documento d = negocio.getDocumento(id);

        d.setEstado_documento(Documento.Estado.CORRECTO);
        try {
            negocio.modificarDocumento(d);
        } catch (NegocioException ex) {
            Logger.getLogger(accionDocumentos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String commit(Long id) {

        InputStream str = null;
        Documento doc = negocio.getDocumento(id);

        try {
            str = file.getInputStream();
            
            byte[] cont = new Scanner(str).useDelimiter("\\A").next().getBytes();
            doc.setFilename(file.getSubmittedFileName());
            doc.setFile(cont);
            
            str.close();
        } catch (IOException ex) {
            Logger.getLogger(accionDocumentos.class.getName()).log(Level.SEVERE, null, ex);
        }

        doc.setEstado_documento(Documento.Estado.PENDIENTE_ENTREGA);
        doc.setFecha_subida(new Date());

        try {
            negocio.modificarDocumento(doc);
        } catch (NegocioException ex) {
            Logger.getLogger(accionDocumentos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String documentos() {
        if (!ctr.esEducando()) {
            return "documentacion.xhtml";
        } else {
            return "user_docs.xhtml";
        }
    }

    public void downloadDoc(Long id) {
        Documento dc = negocio.getDocumento(id);

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        
        ec.responseReset();
        ec.setResponseContentType(ec.getMimeType(dc.getFilename()));
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + dc.getFilename() + "\"");
        try {
            OutputStream out = ec.getResponseOutputStream();
            out.write(dc.getFile());
        } catch (IOException ex) {
            Logger.getLogger(accionDocumentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fc.responseComplete();

    }
}
