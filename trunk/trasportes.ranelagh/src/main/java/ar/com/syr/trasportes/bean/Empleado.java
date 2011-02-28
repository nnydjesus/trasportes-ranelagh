package ar.com.syr.trasportes.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private Integer registroc;
	
	@Basic
	private String cuil;
	
	@Embedded
	private Licencia licencia;
	
	@Embedded
	private Direccion direccion;

	
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
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public Licencia getLicencia() {
		return licencia;
	}
	public void setLicencia(Licencia licencia) {
		this.licencia = licencia;
	}
	public void setLibretaSanitaria(Date libretaSanitaria) {
		this.getLicencia().setLibretaSanitaria(libretaSanitaria);
	}
	public Date getLibretaSanitaria() {
		return this.getLicencia().getLibretaSanitaria();
	}
	public void setRegistro(Date registro) {
		this.getLicencia().setRegistro(registro);
	}
	public Date getRegistro() {
		return this.getLicencia().getRegistro();
	}
	public void setCnrt(Date cnrt) {
		this.getLicencia().setCnrt(cnrt);
	}
	public Date getCnrt() {
		return this.getLicencia().getCnrt();
	}
	public void setCategoria(Date categoria) {
		this.getLicencia().setCategoria(categoria);
	}
	public Date getCategoria() {
		return this.getLicencia().getCategoria();
	}
	
	public String[] atributos() {
	return new String[] {LEGAJO,Licencia.REGISTRO,Licencia.LIBRETA_SANITARIA,Licencia.CNRT,Licencia.CATEGORIA, REGISTRO, APELLIDO, CUIL, NOMBRE, DNI, Direccion.DIRECCION,Direccion.LOCALIDAD,Direccion.TELEFONO,Direccion.CODPOSTAL, PROPIO};
	}
	
	@Override
	public String getId() {
		return legajo;
	}
	

}
