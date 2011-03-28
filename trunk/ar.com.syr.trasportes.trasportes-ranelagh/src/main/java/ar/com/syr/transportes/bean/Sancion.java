package ar.com.syr.transportes.bean;

import java.util.Date;

import javax.persistence.Entity;

import ar.com.nny.base.utils.IdentificablePersistentObject;

@Entity
public class Sancion extends IdentificablePersistentObject {
	private Date fecha;
	@Override
	public String[] atributos() {
		return null;
	}

}
