package ar.com.syr.transportes.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.nny.base.utils.IdentificablePersistentObject;
import ar.com.syr.transportes.bean.enums.ClaseDeUnidad;
import ar.com.syr.transportes.bean.enums.TipoDeUnidad;

@Entity
public class Unidad  extends IdentificablePersistentObject implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String PATENTE = "patente";

    public static final String MODELO = "modelo";

    public static final String MARCA = "marca";

    public static final String NUMERO_CHAZIS = "numeroChaziz";

    public static final String ANIO = "anio";

    public static final String TIPO = "tipo";

    public static final String CLASE = "clase";

    public static final String FECHA_ALTA = "fechaAlta";

    public static final String FECHA_BAJA = "fechaBaja";

    public static final String NUMERO_MOTOR = "numeroMotor";
    
    public static final String UNIDAD_ASOCIADA = "unidadAsociada";

    @Basic
    private String modelo;

    @Basic
    private String marca;

    @Basic
    private String numeroChaziz;

    @Basic
    private Integer anio;

    @Enumerated
    private TipoDeUnidad tipo;

    @Enumerated
    private ClaseDeUnidad clase;

    @Temporal(TemporalType.DATE)
    private Date fechaAlta;

    @Temporal(TemporalType.DATE)
    private Date fechaBaja;

    @Basic
    private String numeroMotor;
    
    @OneToMany(cascade= CascadeType.ALL)
    private List<Remito> viajes= new ArrayList<Remito>();
    
    @OneToOne
    private Unidad unidadAsociada;

    public String getPatente() {
        return this.getId();
    }

    public void setPatente(String patente) {
        this.setId(patente);
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumeroChaziz() {
        return numeroChaziz;
    }

    public void setNumeroChaziz(String numeroChaziz) {
        this.numeroChaziz = numeroChaziz;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public TipoDeUnidad getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeUnidad tipo) {
        this.tipo = tipo;
    }

    public ClaseDeUnidad getClase() {
        return clase;
    }

    public void setClase(ClaseDeUnidad clase) {
        this.clase = clase;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public void setViajes(List<Remito> viajes) {
        this.viajes = viajes;
    }

    public List<Remito> getViajes() {
        return viajes;
    }

    @Override
    public String getName() {
        return "Unidad";
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    @Override
    public String[] atributos() {
        return new String[] { PATENTE, MODELO, MARCA, TIPO, CLASE, ANIO, NUMERO_CHAZIS, FECHA_ALTA, NUMERO_MOTOR, UNIDAD_ASOCIADA };
    }

	public void setUnidadAsociada(Unidad unidadAsociada) {
		this.unidadAsociada = unidadAsociada;
	}

	public Unidad getUnidadAsociada() {
		return unidadAsociada;
	}

}
