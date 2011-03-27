package ar.com.syr.transportes.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.nny.base.common.Observable;

@Embeddable
public class Licencia extends Observable implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String REGISTRO = "registro";

    public static final String CNRT = "cnrt";

    public static final String LIBRETA_SANITARIA = "libretaSanitaria";

    public static final String FECHA_DE_NACIMIENTO = "fechaDeNacimiento";

    @Temporal(TemporalType.DATE)
    private Date registro;

    @Temporal(TemporalType.DATE)
    private Date cnrt;

    @Temporal(TemporalType.DATE)
    private Date libretaSanitaria;

    @Temporal(TemporalType.DATE)
    private Date fechaDeNacimiento;

    public Date getRegistro() {
        return registro;
    }

    public void setRegistro(final Date registro) {
        this.registro = registro;
    }

    public Date getCnrt() {
        return cnrt;
    }

    public void setCnrt(final Date cnrt) {
        this.cnrt = cnrt;
    }

    public Date getLibretaSanitaria() {
        return libretaSanitaria;
    }

    public void setLibretaSanitaria(final Date libretaSanitaria) {
        this.libretaSanitaria = libretaSanitaria;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(final Date categoria) {
        this.fechaDeNacimiento = categoria;
    }

    @Override
    public String[] atributos() {
        return new String[] { FECHA_DE_NACIMIENTO, CNRT, REGISTRO, LIBRETA_SANITARIA };
    }

}