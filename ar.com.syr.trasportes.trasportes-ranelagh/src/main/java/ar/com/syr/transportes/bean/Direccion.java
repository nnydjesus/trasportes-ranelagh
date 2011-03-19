package ar.com.syr.transportes.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

import ar.com.nny.base.common.Observable;


@Embeddable
public class Direccion extends Observable implements Serializable{
	private static final long serialVersionUID = -6168575220905354635L;

	public static final String LOCALIDAD = "localidad";
	public static final String DIRECCION = "direccion";
	public static final String TELEFONO = "telefono";
	public static final String CODPOSTAL = "codPostal";
	
	@Basic
	private String localidad;
	@Basic
	private String direccion;
	@Basic
	private Integer telefono;
	@Basic
	private Integer codPostal;

	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}
	
	public  String[] atributos() {
		return new String[] {CODPOSTAL, LOCALIDAD, TELEFONO, DIRECCION};
	}
	

}
