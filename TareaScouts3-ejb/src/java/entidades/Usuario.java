/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sam
 */
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long credencial;
    
    //Variables
    @Id
    @Column(name="alias" , nullable= false , length=20)
    private String alias;
    @Column(name="dni" , nullable= false , length=10)
    private String dni;
    @Column(name="nombre" , nullable= false , length=50)
    private String nombre;
    @Column(name="apellidos" , nullable= false , length=50)
    private String apellidos;
    @Temporal(TemporalType.DATE)
     @Column(name="fecha_nacimiento" , nullable= false)
    private Date fecha_nacimiento;
    @Column(name="contraseña" , nullable= false , length=16)
    private String contraseña;
    @Column(name="email" , nullable= false , length=50)
    private String email;
    @Column(name="direccion", length=50)
    private String direccion; 
    @Column(name="telefono", length=50)
    private String telefono;
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_ingreso" , nullable= false)
    private Date fecha_ingreso;
    @Temporal(TemporalType.DATE)
    private Date fecha_baja;
    
    //Relaciones
    @ManyToOne
    private Grupo grupo;

    private Rol rol;
    
    @OneToMany(mappedBy = "dueño")
    private List<Documento> lista_documentos;
    
    @ManyToOne
    private Localidad reside_en;
    
    @ManyToMany
    @JoinTable(name = "jnd_usr_tut",
    joinColumns = @JoinColumn(name = "usuario_fk"),
    inverseJoinColumns = @JoinColumn(name = "tutor_legal_fk"))
    private List<Tutor_legal> es_tutelado;
    
    @ManyToMany
    @JoinTable(name = "jnd_usr_evt",
    joinColumns = @JoinColumn(name = "usuario_fk"),
    inverseJoinColumns = @JoinColumn(name = "evento_fk"))
    private List<Evento> participa_eventos;

    //Methods

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
   
    public List<Tutor_legal> getEs_tutelado() {
        return es_tutelado;
    }

    public List<Evento> getParticipa_eventos() {
        return participa_eventos;
    }
    
    public Localidad getReside_en() {
        return reside_en;
    }

    public void setReside_en(Localidad reside_en) {
        this.reside_en = reside_en;
    }
    

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEs_tutelado(List<Tutor_legal> es_tutelado) {
        this.es_tutelado = es_tutelado;
    }

    public void setParticipa_eventos(List<Evento> participa_eventos) {
        this.participa_eventos = participa_eventos;
    }
    
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public void setFecha_baja(Date fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCredencial() {
        return credencial;
    }

    public String getAlias() {
        return alias;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public Date getFecha_baja() {
        return fecha_baja;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.credencial);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.alias, other.alias)) {
            return false;
        }
        return true;
    }

    public List<Documento> getLista_documentos() {
        return lista_documentos;
    }

    public void setLista_documentos(List<Documento> lista_documentos) {
        this.lista_documentos = lista_documentos;
    }

    @Override
    public String toString() {
        return "Usuario{" + "credencial=" + credencial + '}';
    }

    public Usuario() {
    }

    
    public Usuario(Long id) {
        this.credencial = id;
    }  

    public Usuario(String alias) {
        this.alias = alias;
    }
    
    
}

