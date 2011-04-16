package ar.com.syr.transportes.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.nny.base.generator.annotations.DataGenerator;
import ar.com.nny.base.persistence.SerializationStrategy;
import ar.com.nny.base.persistence.Through;
import ar.com.nny.base.utils.IdentificablePersistentObject;
import ar.com.syr.transportes.bean.enums.Categoria;
import ar.com.syr.transportes.initialData.DataGeneratorEmpleado;

@Entity
@Table(name = "empleado")
@DataGenerator(value=DataGeneratorEmpleado.class, order=1)
public class Empleado extends IdentificablePersistentObject implements Serializable {
    private static final long serialVersionUID = 15580157969060161L;

    public static final String LEGAJO = "legajo";

    public static final String APELLIDO = "apellido";

    public static final String NOMBRE = "nombre";

    public static final String DNI = "dni";

    public static final String REGISTRO = "registroConducir";

    public static final String CUIL = "cuil";

    public static final String DIRECCION = "direccion";

    public static final String PROPIO = "propio";

    public static final String LICENCIA = "licencia";
    
    public static final String CATEGORIA = "categoria";

    public static final String FECHA_DE_NACIMIENTO = "fechaDeNacimiento";
    
    public static final String NUMBER_CNRT = "numberCnrt";
//    @Id
//    private String id = "";

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
    private String registroConducir;
    
    @Basic
    private String numberCnrt;

    @Basic
    private String cuil;
    
    @Temporal(TemporalType.DATE)
    private Date fechaDeNacimiento;

    @Embedded
    private Licencia licencia = new Licencia();

    @Embedded
    private Direccion direccion = new Direccion();
    
    @Enumerated
    private Categoria categoria;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "empleado_id")
    private List<Remito> remitos = new ArrayList<Remito>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true,fetch = FetchType.EAGER)
    @SerializationStrategy(access = Through.ACCESSOR)
    private CostoEmpleado costoEmpleado = new CostoEmpleado();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Adelanto> adelantos = new ArrayList<Adelanto>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Ausencia> ausencias = new ArrayList<Ausencia>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Vacacion> vacaciones = new ArrayList<Vacacion>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Sancion> sanciones = new ArrayList<Sancion>();
    
    
    public Empleado() {
        this.setId("");
    }
    
    public String getLegajo() {
        return super.getId();
    }
    
    public void setLegajo(final String legajo) {
        firePropertyChange("id", this.getId(), legajo);
        super.setId(legajo);
       
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

    public String getRegistroConducir() {
        return registroConducir;
    }

    public void setRegistroConducir(final String registro) {
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
        return  licencia != null ?licencia.getRegistro():null;
    }

    public void setRegistro(final Date registro) {
        licencia.setRegistro(registro);
    }

    public Date getCnrt() {
        return licencia != null ?licencia.getCnrt():null;
    }

    public void setCnrt(final Date cnrt) {
        licencia.setCnrt(cnrt);
    }
    public Date getArt() {
        return licencia != null ? licencia.getArt():null;
    }
    
    public void setArt(final Date cnrt) {
        licencia.setArt(cnrt);
    }

    public Date getLibretaSanitaria() {
        return licencia != null ?licencia.getLibretaSanitaria():null;
    }

    public void setLibretaSanitaria(final Date libretaSanitaria) {
        licencia.setLibretaSanitaria(libretaSanitaria);
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(final Date categoria) {
        fechaDeNacimiento = categoria;
    }

    public String getLocalidad() {
        return licencia != null ?direccion.getLocalidad():null;
    }

    public void setLocalidad(final String localidad) {
        direccion.setLocalidad(localidad);
    }

    public String getCalle() {
        return direccion != null ?direccion.getCalle():null;
    }

    public void setCalle(final String direccion) {
        this.direccion.setCalle(direccion);
    }
    

    public int getTelefono() {
        return direccion != null ?direccion.getTelefono():null;
    }

    public void setTelefono(final int telefono) {
        direccion.setTelefono(telefono);
    }

    public int getCodPostal() {
        return  direccion != null ?direccion.getCodPostal():null;
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
                Direccion.LOCALIDAD, FECHA_DE_NACIMIENTO, Licencia.CNRT,
                Licencia.LIBRETA_SANITARIA, Licencia.REGISTRO };
    }

	public void setVacaciones(List<Vacacion> vacaciones) {
		this.vacaciones = vacaciones;
	}

	public List<Vacacion> getVacaciones() {
		return vacaciones;
	}

	public void setSanciones(List<Sancion> sanciones) {
		this.sanciones = sanciones;
	}

	public List<Sancion> getSanciones() {
		return sanciones;
	}

    public void setNumberCnrt(String numbreCnrt) {
        this.numberCnrt = numbreCnrt;
    }

    public String getNumberCnrt() {
        return numberCnrt;
    }

    @Override
    public String getName() {
        return "Empleado";
    }
    
    
    

}
