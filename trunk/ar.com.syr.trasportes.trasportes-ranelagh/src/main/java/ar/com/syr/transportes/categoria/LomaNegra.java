package ar.com.syr.transportes.categoria;


import javax.swing.JFrame;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.common.ItemComposite;
import ar.com.nny.base.ui.swing.components.search.WindowsSearch;
import ar.com.syr.transportes.bean.Remito;
import ar.com.syr.transportes.ui.amb.ABMRemito;
import ar.com.syr.transportes.ui.amb.RemitoUI;

public class LomaNegra extends ItemComposite{
    public LomaNegra(JFrame parent) {
        RemitoUI remitos = new RemitoUI();
        this.add(remitos);
        this.add(new ABMRemito(parent).setNombre("Nuevo Remito"));
        this.add(new WindowsSearch<Remito>( remitos.getSearch()));
    }

    @Override
    public String toString() {
        return "Loma Negra";
    }

    @Override
    public void mostrar(Object item) {
        ((Item)item).mostrar();     
    }


}
