package ar.com.syr.transportes.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ar.com.nny.base.generator.annotations.DataGenerator;
import ar.com.nny.base.utils.IdentificablePersistentObject;
import ar.com.syr.transportes.initialData.DataGeneratorRemito;


@Entity
@Table(name = "remito")
@DataGenerator(DataGeneratorRemito.class)
public class Remito extends IdentificablePersistentObject implements Serializable{
	private static final long serialVersionUID = 1L;

	public static final String FECHA = "fecha";
	public static final String FECHA_S = "fechaS";
	public static final String ORIGEN ="origen";
	public static final String DESTINO  ="destino";
	public static final String ID = "id";
	public static final String COSTO ="costo";
	public static final String COSTO_CHOFER ="costoChofer";
	public static final String PORCENTAGE = "porcentage";
	public static final String COMBUSTIBLE ="combustible";
	public static final String LITROS= "litros";
	public static final String KM = "km";
	public static final String PEAJE = "peaje";
	public static final String PATENTE = "patente";
	public static final String PAGO = "pago";
	
    @Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
    @Basic
	private String origen="";
    
    @Basic
	private String destino="";
    
	@Id
	private String id;
	
    
    @Basic
	private Double costo;
    
    @Basic
	private Integer porcentage=0;
    
    @Basic
	private Double combustible;
    
    @Basic
	private Double litros;
    
    
    @Basic
	private Integer km;
    
    @Basic
	private Double peaje;
    
    @Basic
	private String patente="";
	
    @Basic
    private Boolean pago = false;
    @Transient
	private Double costoChofer;
	
	
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
	public String getId() {
		return this.id;
	}
	public void setId(String nroRemito1) {
		this.id = nroRemito1;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
		setCostoChofer(this.porcentage*getCosto()/100);
	}
	public void setCosto(Long costo) {
		this.setCosto((double)costo);
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
	
	public void setPago(Boolean pago) {
		this.pago = pago;
	}
	public Boolean getPago() {
		return pago;
	}
	public void setPorcentage(Object porcentage) {
		this.porcentage = (Integer) porcentage;
		if(costo!= null)
		    setCostoChofer(this.porcentage*getCosto()/100);
	}
	public Object getPorcentage() {
		return porcentage;
	}
	
	public void setCostoChofer(Double costo) {
		costoChofer = costo;
		porcentage = (int) (costoChofer*100/getCosto());
	}
	
	public Double getCostoChofer(){
		if(costoChofer == null && porcentage != null && costo != null)
			costoChofer = porcentage*getCosto()/100;
		return this.costoChofer;
	}
	@Override
	public  String[] atributos() {
		return new String[] {FECHA, ORIGEN, DESTINO, ID, COSTO, COSTO_CHOFER, PAGO, PORCENTAGE, COMBUSTIBLE, LITROS, KM, PEAJE, PATENTE};
	}
	
	
	public static void main(String[] args) {
	}
	

}
