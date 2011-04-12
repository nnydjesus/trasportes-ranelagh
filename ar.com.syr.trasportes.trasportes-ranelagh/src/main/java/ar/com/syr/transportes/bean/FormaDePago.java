package ar.com.syr.transportes.bean;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import ar.com.nny.base.persistence.SerializationStrategy;
import ar.com.nny.base.persistence.Through;
import ar.com.nny.base.utils.IdentificablePersistentObject;
import ar.com.syr.transportes.bean.enums.Banco;

@Entity
//@MappedSuperclass
@DiscriminatorValue("Efectivo")
public class FormaDePago extends IdentificablePersistentObject {
    
    public static final String MONTO = "monto";
    
    @Id
    @SerializationStrategy(access = Through.ACCESSOR)
    @GeneratedValue
    private String id;
    
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