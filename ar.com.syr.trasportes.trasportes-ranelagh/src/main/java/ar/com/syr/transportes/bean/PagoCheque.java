package ar.com.syr.transportes.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Cheque")
public class PagoCheque extends FormaDePago {
    
    public static final String BANCO = "banco";

    public static final String NUM_CHEQUE = "numeroCheque";

    
    @Override
    public String[] atributos() {
        return new String[]{MONTO, BANCO, NUM_CHEQUE};
    }    
    
    @Override
    public String toString() {
        return "Cheque";
    }

}
