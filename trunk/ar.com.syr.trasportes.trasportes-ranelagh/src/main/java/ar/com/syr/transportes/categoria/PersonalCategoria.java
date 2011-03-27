package ar.com.syr.transportes.categoria;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.common.ItemComposite;
import ar.com.syr.transportes.ui.amb.AdelantosUI;

public class PersonalCategoria extends ItemComposite {
    private static final long serialVersionUID = -6130962545676299492L;

    public PersonalCategoria() {
        this.add(new Nomina());
        this.add(new AdelantosUI());
    }

    @Override
    public String toString() {
        return "Personal";
    }

    
    @Override
    public void mostrar(Object item) {
        ((Item)item).mostrar();     
    }
    
    
    public static void main(String[] args) {
        new Operaciones();
    }

}
