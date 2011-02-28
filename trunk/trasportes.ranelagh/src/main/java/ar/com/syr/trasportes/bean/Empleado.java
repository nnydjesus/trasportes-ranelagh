package ar.com.syr.trasportes.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.syr.trasportes.utils.Observable;

@Entity
@Table(name = "empleado")
public class Empleado extends Observable implements Serializable{
	
	public static final String LEGAJO = "legajo"; 
	public static final String APELLIDO = "apellido";
	public static final String NOMBRE = "nombre";
	public static final String DNI ="dni";
	public static final String REGISTRO  ="registroc";
	public static final String CUIL = "cuil";
	public static final String DIRECCION ="direccionc";
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
	private Integer registroc;
	
	@Basic
	private String cuil;
	
	@Embedded
	private static Licencia licencia;
	
	@Embedded
	private static Direccion direccionc;

	
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
	public Integer getRegistroc() {
		return registroc;
	}
	public void setRegistroc(Integer registro) {
		this.registroc = registro;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public static Direccion getDireccionc() {
		return direccionc;
	}
	public static void setDireccion(Direccion direccion) {
		direccionc = direccion;
	}
	public static Licencia getLicencia() {
		return licencia;
	}
	public static void setLicencia(Licencia licenciac) {
		licencia = licenciac;
	}
	public void setLibretaSanitaria(Date libretaSanitaria) {
		getLicencia().setLibretaSanitaria(libretaSanitaria);
	}
	public Date getLibretaSanitaria() {
		return getLicencia().getLibretaSanitaria();
	}
	public void setRegistro(Date registro) {
		getLicencia().setRegistro(registro);
	}
	public Date getRegistro() {
		return getLicencia().getRegistro();
	}
	public void setCnrt(Date cnrt) {
		getLicencia().setCnrt(cnrt);
	}
	public Date getCnrt() {
		return getLicencia().getCnrt();
	}
	public void setCategoria(Date categoria) {
		getLicencia().setCategoria(categoria);
	}
	public Date getCategoria() {
		return getLicencia().getCategoria();
	}
	public void setLocalidad(String localidad) {
		getDireccionc().setLocalidad(localidad);
	}
	public String getLocalidad() {
		return getDireccionc().getLocalidad();
	}
	public void setTelefono(Integer telefono) {
		getDireccionc().setTelefono(telefono);
	}
	public Integer getTelefono() {
		return getDireccionc().getTelefono();
	}
	public void setCodPostal(Integer codPostal) {
		getDireccionc().setCodPostal(codPostal);
	}
	public Integer getCodPostal() {
		return getDireccionc().getCodPostal();
	}
	public void setDireccion(String direccion) {
		getDireccionc().setDireccion(direccion);
	}
	public String getDireccion() {
		return getDireccionc().getDireccion();
	}
	
	public String[] atributos() {
	return new String[] {LEGAJO,Licencia.REGISTRO,Licencia.LIBRETA_SANITARIA,Licencia.CNRT,Licencia.CATEGORIA, REGISTRO, APELLIDO, CUIL, NOMBRE, DNI, Direccion.DIRECCION,Direccion.LOCALIDAD,Direccion.TELEFONO,Direccion.CODPOSTAL, PROPIO};
	}
	
	@Override
	public String getId() {
		return legajo;
	}
	

}
