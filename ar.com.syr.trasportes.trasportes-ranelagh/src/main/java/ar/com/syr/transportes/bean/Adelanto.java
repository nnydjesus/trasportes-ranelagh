package ar.com.syr.transportes.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.nny.base.bean.IdGenerator;
import ar.com.nny.base.generator.annotations.GeneratedId;
import ar.com.nny.base.persistence.SerializationStrategy;
import ar.com.nny.base.persistence.Through;
import ar.com.nny.base.utils.IdentificablePersistentObject;

@Entity
@Table(name = "adelanto")
@GeneratedId
public class Adelanto extends IdentificablePersistentObject  implements Serializable{
    
    public static final String FECHA = "fecha";
    public static final String EMPLEADO = "empleado";
    public static final String COMENTARIO = "comentario";
    public static final String NUMERO_DE_ORDEN = "id";
    public static final String FORMA_DE_PAGO = "fornaDePago";

    @Basic
    private Date fecha;
    
    @ManyToOne()
    @JoinColumn(name = "adelanto_id")
    private Empleado empleado;
    
    @Basic
    private String comentario;
    
    @OneToOne(fetch = FetchType.EAGER)
    @SerializationStrategy(access = Through.ACCESSOR)
    private FormaDePago fornaDePago;


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


    public FormaDePago getFornaDePago() {
        return fornaDePago;
    }

    public void setFornaDePago(FormaDePago fornaDePago) {
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
        return new String[] {NUMERO_DE_ORDEN,FECHA, EMPLEADO, COMENTARIO};
    }

    @Override
    public String getName() {
        return "Adelanto";
    }
}
