package ar.com.syr.transportes.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.com.nny.base.bean.IdGenerator;
import ar.com.nny.base.utils.IdentificablePersistentObject;
import ar.com.syr.transportes.bean.enums.FormaDePagoType;

@Entity
@Table(name = "adelanto")
public class Adelanto extends IdentificablePersistentObject  implements Serializable{
    
    public static final String FECHA = "fecha";
    public static final String EMPLEADO = "empleado";
    public static final String COMENTARIO = "comentario";
    public static final String MONTO = "monto";
    public static final String NUMERO_DE_ORDEN = "id";
    public static final String FORMA_DE_PAGO = "fornaDePago";

    @Basic
    private Date fecha;
    
    @ManyToOne()
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @JoinColumn(name = "adelanto_id")
    private Empleado empleado;
    @Basic
    private String comentario;
    @Basic
    private Double monto;
    @Id
    private String id = IdGenerator.getInstance().nextId();
    @Enumerated
    private FormaDePagoType fornaDePago;


    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.firePropertyChange(EMPLEADO, this.empleado, empleado);
        this.empleado = empleado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.firePropertyChange(COMENTARIO, this.comentario, comentario);
        this.comentario = comentario;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.firePropertyChange(MONTO, this.monto, monto);
        this.monto = monto;
    }

    public String getId() {
        return id;
    }

    public void setId(String numeroDeOrden) {
        this.firePropertyChange(NUMERO_DE_ORDEN, this.id, id);
        this.id = numeroDeOrden;
    }

    public FormaDePagoType getFornaDePago() {
        return fornaDePago;
    }

    public void setFornaDePago(FormaDePagoType fornaDePago) {
        this.firePropertyChange(FORMA_DE_PAGO, this.fornaDePago, fornaDePago);
        this.fornaDePago = fornaDePago;
    }


    public void setFecha(Date fecha) {
        this.firePropertyChange(FECHA, this.fecha, fecha);
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    @Override
    public String[] atributos() {
        return new String[] {NUMERO_DE_ORDEN,FECHA, EMPLEADO, COMENTARIO, MONTO,FORMA_DE_PAGO};
    }
}
