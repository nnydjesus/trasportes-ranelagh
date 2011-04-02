package ar.com.syr.transportes.bean;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.nny.base.utils.IdentificablePersistentObject;

public class Sancion extends IdentificablePersistentObject {
	
	public static final String FECHA = "fecha";
	public static final String CANTDEDIAS = "cantDeDias";
	
	@GeneratedValue
	@Id
	private String id;
	

	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Basic
	private int cantDeDias;
	
	@Override
	public String[] atributos() {
		return new String[] {FECHA, CANTDEDIAS} ;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setCantDeDias(int cantDeDias) {
		this.cantDeDias = cantDeDias;
	}

	public int getCantDeDias() {
		return cantDeDias;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
