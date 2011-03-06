package ar.com.nny.java.base.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.nny.java.base.utils.HashUtils;
import ar.com.nny.java.base.utils.IdentificablePersistentObject;

@Entity
@Table(name = "usuario")
public class Usuario extends IdentificablePersistentObject implements Serializable {

	@Id
	private String nombre;
	
	@Basic
	private String pass;
	
	@Basic
	private boolean conectado;
	
	public Usuario() {
	}
	
	public Usuario(String nombre, String pass){
		this.nombre = nombre;
		this.pass = pass;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}

	public boolean isConectado() {
		return conectado;
	}
	
	public boolean checkPassword(String pass){
		return this.pass.equals(HashUtils.hash(pass));
	}

	@Override
	public String[] atributos() {
		return null;
	}

	
	
}
