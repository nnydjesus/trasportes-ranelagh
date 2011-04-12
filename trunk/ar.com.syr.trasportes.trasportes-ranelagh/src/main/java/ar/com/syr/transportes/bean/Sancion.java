package ar.com.syr.transportes.bean;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.nny.base.generator.annotations.GeneratedId;
import ar.com.nny.base.utils.IdentificablePersistentObject;

@Entity
@GeneratedId
public class Sancion extends IdentificablePersistentObject {
	
	public static final String FECHA = "fecha";
	public static final String CANT_DE_DIAS = "cantDeDias";
    public static final String EMPLEADO = "empleado";
	
	@OneToOne
	private Empleado empleado;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Basic
	private int cantDeDias;
	
	@Override
	public String[] atributos() {
		return new String[] {FECHA, CANT_DE_DIAS} ;
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

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    @Override
    public String getName() {
        return "Sancion";
    }

}
