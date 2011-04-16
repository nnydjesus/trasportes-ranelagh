package ar.com.syr.transportes.bean;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

import ar.com.nny.base.generator.annotations.GeneratedId;
import ar.com.nny.base.utils.IdentificablePersistentObject;
import ar.com.syr.transportes.bean.enums.Banco;

@Entity
@DiscriminatorValue("Efectivo")
@GeneratedId
public class FormaDePago extends IdentificablePersistentObject {
    
    private static final long serialVersionUID = 4067419003302477676L;

    public static final String MONTO = "monto";
    
    @Enumerated
    private Banco banco;
    
    @Basic
    private String numeroCheque;
    
    @Basic
    private Double monto;

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getMonto() {
        return monto;
    }
    
    @Override
    public String[] atributos() {
        return new String[]{MONTO};
    }
    
    @Override
    public String toString() {
        return "Efectivo";
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Banco getBanco() {
        return banco;
    }

    @Override
    public String getName() {
        return "Efectivo";
    }
}
