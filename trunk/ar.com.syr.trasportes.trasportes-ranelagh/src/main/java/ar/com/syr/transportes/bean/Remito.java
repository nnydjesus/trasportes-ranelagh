package ar.com.syr.transportes.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ar.com.nny.base.generator.annotations.DataGenerator;
import ar.com.nny.base.utils.IdentificablePersistentObject;
import ar.com.syr.transportes.initialData.DataGeneratorRemito;

@Entity
@Table(name = "remito")
@DataGenerator(DataGeneratorRemito.class )
public class Remito extends IdentificablePersistentObject implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FECHA = "fecha";

    public static final String FECHA_S = "fechaS";

    public static final String ORIGEN = "origen";

    public static final String DESTINO = "destino";

    public static final String NUMERO_REMITO = "numero";

    public static final String COSTO = "costo";

    public static final String COSTO_CHOFER = "costoChofer";

    public static final String PORCENTAGE = "porcentage";

    public static final String COMBUSTIBLE = "combustible";

    public static final String LITROS = "litros";

    public static final String KM = "km";

    public static final String PEAJE = "peaje";

    public static final String PATENTE = "patente";

    public static final String PAGO = "pago";
    
    public static final String EMPLEADO = "empleado";

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Basic
    private String origen = "";

    @Basic
    private String destino = "";

    @Basic
    private Double costo;

    @Basic
    private Integer porcentage = 0;

    @Basic
    private Double combustible;

    @Basic
    private Double litros;

    @Basic
    private Integer km;

    @Basic
    private Double peaje;

    @ManyToOne(cascade=CascadeType.ALL)
    private Unidad patente;

    @Basic
    private Boolean pago = false;

    @Transient
    private Double costoChofer;
    
    @ManyToOne(cascade=CascadeType.ALL)
    private Empleado empleado;

    public Date getFecha() {
        return fecha;
    }

    public String getFechaS() {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    public void setFecha(final Date fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(final String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(final String destino) {
        this.destino = destino;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(final Double costo) {
        this.costo = costo;
        if(this.costo != null)
            this.setCostoChofer(porcentage * this.getCosto() / 100);
    }

    public void setCosto(final Long costo) {
        this.setCosto((double) costo);
    }

    public Double getCombustible() {
        return combustible;
    }

    public void setCombustible(final Double combustible) {
        this.combustible = combustible;
    }

    public Double getLitros() {
        return litros;
    }

    public void setLitros(final Double litors) {
        litros = litors;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(final Integer km) {
        this.km = km;
    }

    public Double getPeaje() {
        return peaje;
    }

    public void setPeaje(final Double peaje) {
        this.peaje = peaje;
    }

    public void setNumero(final String numero) {
        this.setId(numero);
    }
    public String getNumero() {
        return getId();
    }

    public void setPago(final Boolean pago) {
        this.pago = pago;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPorcentage(final Object porcentage) {
        this.porcentage = (Integer) porcentage;
        if (costo != null) {
            this.setCostoChofer(this.porcentage * this.getCosto() / 100);
        }
    }

    public Object getPorcentage() {
        return porcentage;
    }

    public void setCostoChofer(final Double costo) {
        costoChofer = costo;
        porcentage = (int) (costoChofer * 100 / this.getCosto());
    }

    public Double getCostoChofer() {
        if (costoChofer == null && porcentage != null && costo != null) {
            costoChofer = porcentage * this.getCosto() / 100;
        }
        return costoChofer;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }
    
//    public void setPatente(Unidad patente) {
//        this.patente = patente;
//    }
//
//    public Unidad getPatente() {
//        return patente;
//    }

    @Override
    public String[] atributos() {
        return new String[] { NUMERO_REMITO, FECHA, ORIGEN, DESTINO, COSTO, COSTO_CHOFER, PAGO, PORCENTAGE, COMBUSTIBLE, LITROS,
                KM, PEAJE, PATENTE, EMPLEADO };
    }

    public static void main(final String[] args) {
    }

    @Override
    public String getName() {
        return "Remito";
    }

    public void setPatente(Unidad patente) {
        this.patente = patente;
    }

    public Unidad getPatente() {
        return patente;
    }


}
