package ar.com.syr.transportes.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.nny.base.common.Observable;
import ar.com.nny.base.generator.annotations.GeneratedId;

@Embeddable
public class Licencia extends Observable implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String REGISTRO = "registro";

    public static final String CNRT = "cnrt";

    public static final String LIBRETA_SANITARIA = "libretaSanitaria";

    @Temporal(TemporalType.DATE)
    private Date registro; 

    @Temporal(TemporalType.DATE)
    private Date art;
    @Temporal(TemporalType.DATE)
    private Date cnrt;

    @Temporal(TemporalType.DATE)
    private Date libretaSanitaria;


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


    @Override
    public String[] atributos() {
        return new String[] { CNRT, REGISTRO, LIBRETA_SANITARIA };
    }

    public void setArt(Date art) {
        this.art = art;
    }

    public Date getArt() {
        return art;
    }

}