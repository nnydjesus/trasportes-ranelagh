package ar.com.syr.transportes.bean;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.nny.base.utils.IdentificablePersistentObject;

@Entity
public class Vacacion extends IdentificablePersistentObject {
	
	public static final String DESDE = "desde";
	public static final String HASTA = "hasta";
	public static final String LEGAJO = "legajo";
	public static final String NOMBRE = "nombre";
	public static final String APELLIDO = "apellido";
	
	@GeneratedValue
	@Id
	private String id;
	
	@Temporal(TemporalType.DATE)
	private Date desde;
	@Temporal(TemporalType.DATE)
	private Date hasta;
	@Basic
	private String legajo;
	@Basic
	private String nombre;
	@Basic
	private String apellido;
	
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getApellido() {
		return apellido;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	public Date getHasta() {
		return hasta;
	}
	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}
	public String getLegajo() {
		return legajo;
	}
	public void setDesde(Date desde) {
		this.desde = desde;
	}
	public Date getDesde() {
		return desde;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String[] atributos() {
		return new String[] {APELLIDO, NOMBRE, LEGAJO,DESDE,HASTA};
	}
}
