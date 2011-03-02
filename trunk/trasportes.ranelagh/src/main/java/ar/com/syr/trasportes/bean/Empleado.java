package ar.com.syr.trasportes.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import ar.com.mindset.flexy.annotations.flex.serializer.SerializationStrategy;
import ar.com.mindset.flexy.annotations.flex.serializer.Through;
import ar.com.syr.trasportes.utils.IdentificablePersistentObject;

@Entity
@Table(name = "empleado")
public class Empleado extends IdentificablePersistentObject implements Serializable{
	
	public static final String LEGAJO = "legajo"; 
	public static final String APELLIDO = "apellido";
	public static final String NOMBRE = "nombre";
	public static final String DNI ="dni";
	public static final String REGISTRO  ="registro";
	public static final String CUIL = "cuil";
	public static final String DIRECCION ="direccion";
	public static final String PROPIO = "propio";
	public static final String LICENCIA ="licencia";
	
	@Id
	private String legajo;
	@Basic
	private String apellido;
	@Basic
	private String nombre;
	
	@Basic
	private boolean propio;
	
	@Basic
	private Integer dni;
	
	@Basic
	@Column(name="registro_conducir")
	private Integer registro;
	
	@Basic
	private String cuil;
	
	@Embedded
	private  Licencia licencia = new Licencia();
	
	@Embedded
	private  Direccion direccion = new Direccion();
	
	@OneToOne
    @SerializationStrategy(access = Through.TRANSIENT)
	private CostoEmpleado costoEmpleado;
	
	public String getLegajo() {
		return legajo;
	}
	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean getPropio() {
		return propio;
	}
	public void setPropio(boolean propio) {
		this.propio = propio;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public Integer getRegistro() {
		return registro;
	}
	public void setRegistro(Integer registro) {
		this.registro = registro;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public  Direccion getDireccion() {
		return direccion;
	}
	public  void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public  Licencia getLicencia() {
		return licencia;
	}
	public  void setLicencia(Licencia licenciac) {
		licencia = licenciac;
	}
	
	public Date getRegistroL() {
		return licencia.getRegistro();
	}
	public void setRegistroL(Date registro) {
		licencia.setRegistro(registro);
	}
	public Date getCnrt() {
		return licencia.getCnrt();
	}
	public void setCnrt(Date cnrt) {
		licencia.setCnrt(cnrt);
	}
	public Date getLibretaSanitaria() {
		return licencia.getLibretaSanitaria();
	}
	public void setLibretaSanitaria(Date libretaSanitaria) {
		licencia.setLibretaSanitaria(libretaSanitaria);
	}
	public Date getCategoria() {
		return licencia.getCategoria();
	}
	public void setCategoria(Date categoria) {
		licencia.setCategoria(categoria);
	}
	
	
	public String getLocalidad() {
		return direccion.getLocalidad();
	}
	public void setLocalidad(String localidad) {
		direccion.setLocalidad(localidad);
	}
	public String getDireccionD() {
		return direccion.getDireccion();
	}
	public void setDireccionD(String direccion) {
		this.direccion.setDireccion(direccion);
	}
	public int getTelefono() {
		return direccion.getTelefono();
	}
	public void setTelefono(int telefono) {
		direccion.setTelefono(telefono);
	}
	public int getCodPostal() {
		return direccion.getCodPostal();
	}
	public void setCodPostal(int codPostal) {
		direccion.setCodPostal(codPostal);
	}
	
	public void setCostoEmpleado(CostoEmpleado costoEmpleado) {
		this.costoEmpleado = costoEmpleado;
	}
	public CostoEmpleado getCostoEmpleado() {
		return costoEmpleado;
	}
	public String[] atributos() {
	return new String[] {LEGAJO, REGISTRO, APELLIDO, CUIL, NOMBRE, DNI, PROPIO};
	}
	
	@Override
	public String getId() {
		return legajo;
	}
	

}
