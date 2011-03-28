package ar.com.syr.transportes.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.com.nny.base.generator.annotations.DataGenerator;
import ar.com.nny.base.persistence.SerializationStrategy;
import ar.com.nny.base.persistence.Through;
import ar.com.nny.base.utils.IdentificablePersistentObject;
import ar.com.syr.transportes.initialData.DataGeneratorEmpleado;

@Entity
@Table(name = "empleado")
@DataGenerator(DataGeneratorEmpleado.class)
public class Empleado extends IdentificablePersistentObject implements Serializable {
    private static final long serialVersionUID = 15580157969060161L;

    public static final String LEGAJO = "id";

    public static final String APELLIDO = "apellido";

    public static final String NOMBRE = "nombre";

    public static final String DNI = "dni";

    public static final String REGISTRO = "registroConducir";

    public static final String CUIL = "cuil";

    public static final String DIRECCION = "direccion";

    public static final String PROPIO = "propio";

    public static final String LICENCIA = "licencia";
    public static final String CATEGORIA = "categoria";

    @Id
    private String id = "";

    @Basic
    private String apellido = "";

    @Basic
    private String nombre = "";

    @Basic
    private boolean propio;

    @Basic
    private Integer dni;

    @Basic
    @Column(name = "registro_conducir")
    private Integer registroConducir;

    @Basic
    private String cuil;

    @Embedded
    private Licencia licencia = new Licencia();

    @Embedded
    private Direccion direccion = new Direccion();
    
    @Enumerated
    private Categoria categoria;
    
    // @JoinTable(
    //
    // name="Realizador",
    //
    // joinColumns = @JoinColumn( name="empleado_id"),
    //
    // inverseJoinColumns = @JoinColumn( name="remito_id")
    //
    // )
    @OneToMany()
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @JoinColumn(name = "empleado_id")
    private List<Remito> remitos = new ArrayList<Remito>();

    @OneToOne(fetch = FetchType.LAZY)
    // @org.hibernate.annotations.LazyToOne
    // (org.hibernate.annotations.LazyToOneOption.NO_PROXY))
    @SerializationStrategy(access = Through.TRANSIENT)
    private CostoEmpleado costoEmpleado;
    
    @OneToMany()
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    private List<Adelanto> adelantos = new ArrayList<Adelanto>();
    
    @OneToMany()    
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    private List<Ausencia> ausencias = new ArrayList<Ausencia>();
    
    @OneToMany()    
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    private List<Vacacion> vacaciones = new ArrayList<Vacacion>();
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(final String legajo) {
    	firePropertyChange(LEGAJO, this.id, legajo);
        id = legajo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(final String apellido) {
    	firePropertyChange(APELLIDO, this.apellido, apellido);
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
    	firePropertyChange(NOMBRE, this.nombre, nombre);
        this.nombre = nombre;
    }

    public boolean getPropio() {
        return propio;
    }

    public void setPropio(final boolean propio) {
        this.propio = propio;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(final Integer dni) {
        this.dni = dni;
    }

    public Integer getRegistroConducir() {
        return registroConducir;
    }

    public void setRegistroConducir(final Integer registro) {
        registroConducir = registro;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(final String cuil) {
        this.cuil = cuil;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(final Direccion direccion) {
        this.direccion = direccion;
    }

    public Licencia getLicencia() {
        return licencia;
    }

    public void setLicencia(final Licencia licenciac) {
        licencia = licenciac;
    }

    public Date getRegistro() {
        return licencia.getRegistro();
    }

    public void setRegistro(final Date registro) {
        licencia.setRegistro(registro);
    }

    public Date getCnrt() {
        return licencia.getCnrt();
    }

    public void setCnrt(final Date cnrt) {
        licencia.setCnrt(cnrt);
    }

    public Date getLibretaSanitaria() {
        return licencia.getLibretaSanitaria();
    }

    public void setLibretaSanitaria(final Date libretaSanitaria) {
        licencia.setLibretaSanitaria(libretaSanitaria);
    }

    public Date getFechaDeNacimiento() {
        return licencia.getFechaDeNacimiento();
    }

    public void setFechaDeNacimiento(final Date categoria) {
        licencia.setFechaDeNacimiento(categoria);
    }

    public String getLocalidad() {
        return direccion.getLocalidad();
    }

    public void setLocalidad(final String localidad) {
        direccion.setLocalidad(localidad);
    }

    public String getCalle() {
        return direccion.getCalle();
    }

    public void setCalle(final String direccion) {
        this.direccion.setCalle(direccion);
    }

    public int getTelefono() {
        return direccion.getTelefono();
    }

    public void setTelefono(final int telefono) {
        direccion.setTelefono(telefono);
    }

    public int getCodPostal() {
        return direccion.getCodPostal();
    }

    public void setCodPostal(final int codPostal) {
        direccion.setCodPostal(codPostal);
    }

    public List<Remito> getRemitos() {
        return remitos;
    }

    public void setRemitos(final List<Remito> remitos) {
        this.remitos = remitos;
    }

    public void addRemito(final Remito remito) {
        if(remito.getCostoChofer() != null)
            costoEmpleado.setCostoTotal(costoEmpleado.getCostoTotal() + remito.getCostoChofer());
        remitos.add(remito);
    }

    public void removeRemito(final Remito remito) {
        remitos.remove(remito);
    }

    public void setCostoEmpleado(final CostoEmpleado costoEmpleado) {
        this.costoEmpleado = costoEmpleado;
    }

    public CostoEmpleado getCostoEmpleado() {
        return costoEmpleado;
    }
    
    public Categoria getCategoria() {
        return categoria;    
     }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public void setAdelantos(List<Adelanto> adelantos) {
        this.adelantos = adelantos;
    }

    public List<Adelanto> getAdelantos() {
        return adelantos;
    }

    @Override
    public String mostrar() {
        return this.nombre;
    }
    public void setAusencias(List<Ausencia> ausencias) {
    	this.ausencias = ausencias;
    }
    
    public List<Ausencia> getAusencias() {
    	return ausencias;
    }

    @Override
    public String[] atributos() {
        return new String[] { LEGAJO, REGISTRO, APELLIDO, CUIL, NOMBRE, DNI, PROPIO, CATEGORIA, Direccion.CALLE,
                Direccion.LOCALIDAD, Direccion.TELEFONO, Direccion.TELEFONO, Licencia.FECHA_DE_NACIMIENTO, Licencia.CNRT,
                Licencia.LIBRETA_SANITARIA, Licencia.REGISTRO };
    }

	public void setVacaciones(List<Vacacion> vacaciones) {
		this.vacaciones = vacaciones;
	}

	public List<Vacacion> getVacaciones() {
		return vacaciones;
	}



}
