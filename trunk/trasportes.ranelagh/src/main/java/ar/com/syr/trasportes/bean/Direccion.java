package ar.com.syr.trasportes.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

import ar.com.syr.trasportes.utils.Observable;


@Embeddable
public class Direccion  implements Serializable{

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
	public static String[] atributos() {
		return new String[] {CODPOSTAL, LOCALIDAD, TELEFONO, DIRECCION};
	}
	

}
