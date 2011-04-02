package ar.com.syr.transportes.bean;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

import ar.com.syr.transportes.bean.enums.Banco;

@Entity
@DiscriminatorValue("Cheque")
public class PagoCheque extends FormaDePago {
    
    public static final String BANCO = "banco";

    public static final String NUM_CHEQUE = "numeroCheque";

    @Enumerated
    private Banco banco;
    
    @Basic
    private String numeroCheque;

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }
    
    @Override
    public String[] atributos() {
        return new String[]{MONTO, BANCO, NUM_CHEQUE};
    }    
    
    @Override
    public String toString() {
        return "Cheque";
    }

}
