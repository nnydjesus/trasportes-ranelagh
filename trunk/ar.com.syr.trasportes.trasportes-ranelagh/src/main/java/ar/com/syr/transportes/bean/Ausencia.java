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
public class Ausencia extends IdentificablePersistentObject {
	
	public static final String FECHA = "fecha";
	public static final String LEGAJO = "legajo";
	public static final String APELLIDO = "apellido";
	public static final String NOMBRE = "nombre";
	public static final String AVISO = "aviso";
	public static final String MOTIVO = "motivo";
	public static final String FECHAREINICIO = "fechaReinicio";
	public static final String COSTO = "costo";
	
	@GeneratedValue
	@Id
	private String id;

	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Basic
	private String legajo;
	@Basic
	private String apellido;
	@Basic
	private String nombre;
	@Basic
	private String motivo;
	@Basic
	private boolean aviso;
	@Basic
	private Double costo;
	@Temporal(TemporalType.DATE)
	private Date fechaReinicio;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public boolean isAviso() {
		return aviso;
	}

	public void setAviso(boolean aviso) {
		this.aviso = aviso;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Date getFechaReinicio() {
		return fechaReinicio;
	}

	public void setFechaReinicio(Date fechaReinicio) {
		this.fechaReinicio = fechaReinicio;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public String[] atributos() {
		return new String[] {LEGAJO, NOMBRE,APELLIDO,AVISO,COSTO,FECHA,FECHAREINICIO,MOTIVO};
	}

}
