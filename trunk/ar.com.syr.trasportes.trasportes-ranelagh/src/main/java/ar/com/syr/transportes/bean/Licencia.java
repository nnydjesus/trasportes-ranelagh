package ar.com.syr.transportes.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.nny.base.common.Observable;


@Embeddable
public class Licencia extends Observable implements Serializable{
	private static final long serialVersionUID = 1L;

	public static final String REGISTRO = "registro"; 
	public static final String CNRT = "cnrt";
	public static final String LIBRETA_SANITARIA = "libretaSanitaria";
	public static final String CATEGORIA ="categoria";
	
	@Temporal(TemporalType.DATE) 
	private Date registro;
	
	@Temporal(TemporalType.DATE) 
	private Date cnrt;
	
	@Temporal(TemporalType.DATE) 
	private Date libretaSanitaria;
	
	@Temporal(TemporalType.DATE) 
	private Date categoria;
	
	public Date getRegistro() {
		return registro;
	}
	public void setRegistro(Date registro) {
		this.registro = registro;
	}
	public Date getCnrt() {
		return cnrt;
	}
	public void setCnrt(Date cnrt) {
		this.cnrt = cnrt;
	}
	public Date getLibretaSanitaria() {
		return libretaSanitaria;
	}
	public void setLibretaSanitaria(Date libretaSanitaria) {
		this.libretaSanitaria = libretaSanitaria;
	}
	public Date getCategoria() {
		return categoria;
	}
	public void setCategoria(Date categoria) {
		this.categoria = categoria;
	}
	public  String[] atributos() {
		return new String[] {CATEGORIA, CNRT, REGISTRO, LIBRETA_SANITARIA };
	}
	
	
}