package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import utils.Observable;

@Entity
@Table(name = "remito")
public class Remito extends Observable implements Serializable{
	
	public static final String FECHA = "fecha";
	public static final String FECHA_S = "fechaS";
	public static final String ORIGEN ="origen";
	public static final String DESTINO  ="destino";
	public static final String NRO_REMITO_1 = "nroRemito1";
	public static final String NRO_REMITO_2 ="nroRemito2";
	public static final String COSTO ="costo";
	public static final String CHOFER = "chofer";
	public static final String COMBUSTIBLE ="combustible";
	public static final String LITROS= "litros";
	public static final String LUGAR =  "lugar";
	public static final String KM = "km";
	public static final String PEAJE = "peaje";
	public static final String PATENTE = "patente";
	
    @Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
    @Basic
	private String origen;
    
    @Basic
	private String destino;
    
	@Id
	private String nroRemito1;
	
    @Basic
	private String nroRemito2;
    
    @Basic
	private Double costo;
    
    @Basic
	private Double chofer;
    
    @Basic
	private Double combustible;
    
    @Basic
	private Double litros;
    
    @Basic
	private String lugar;
    
    @Basic
	private Integer km;
    
    @Basic
	private Double peaje;
    
    @Basic
	private String patente;
	
	
	
	
	public Date getFecha() {
		return fecha;
	}
	public String getFechaS() {
		return new SimpleDateFormat("dd/MM/yyyy").format(fecha);  
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getNroRemito1() {
		return nroRemito1;
	}
	public void setNroRemito1(String nroRemito1) {
		this.nroRemito1 = nroRemito1;
	}
	public String getNroRemito2() {
		return nroRemito2;
	}
	public void setNroRemito2(String nroRemito2) {
		this.nroRemito2 = nroRemito2;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public Double getChofer() {
		return chofer;
	}
	public void setChofer(Double chofer) {
		this.chofer = chofer;
	}
	public Double getCombustible() {
		return combustible;
	}
	public void setCombustible(Double combustible) {
		this.combustible = combustible;
	}
	public Double getLitros() {
		return litros;
	}
	public void setLitros(Double litors) {
		this.litros = litors;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public Integer getKm() {
		return km;
	}
	public void setKm(Integer km) {
		this.km = km;
	}
	public Double getPeaje() {
		return peaje;
	}
	public void setPeaje(Double peaje) {
		this.peaje = peaje;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public static String[] atributos() {
		return new String[] {FECHA, ORIGEN, DESTINO, NRO_REMITO_1, COSTO, CHOFER, COMBUSTIBLE, LITROS, NRO_REMITO_2, LUGAR, KM, PEAJE, PATENTE};
	}
	
	@Override
	public String getId() {
		return this.nroRemito1;
	}
	public static void main(String[] args) {
		System.out.println(Remito.atributos());
	}
	

}
