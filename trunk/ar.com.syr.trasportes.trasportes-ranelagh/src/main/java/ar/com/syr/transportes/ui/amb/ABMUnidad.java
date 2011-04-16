package ar.com.syr.transportes.ui.amb;

import javax.swing.JFrame;

import main.Main;
import ar.com.nny.base.search.Home;
import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.abms.ABMFrame;
import ar.com.syr.transportes.bean.Unidad;
import ar.com.syr.transportes.bean.enums.ClaseDeUnidad;
import ar.com.syr.transportes.bean.enums.TipoDeUnidad;
import ar.com.syr.transportes.search.HomeUnidad;

public class ABMUnidad extends ABMFrame<Unidad>{
    private static final long serialVersionUID = 1L;
    

    public ABMUnidad(Unidad model) {
        this(model, Main.getApp());
    }
    public ABMUnidad(JFrame parent) {
        this(new Unidad(), parent);
    }
    public ABMUnidad(Unidad model, JFrame parent) {
        super(model, parent);
    }

    @Override
    protected void createForm(final AbstractBindingPanel<Unidad> edicion) {
        edicion.addBindingTextField(Unidad.PATENTE, "Patente");
        edicion.addBindingTextField(Unidad.MODELO, "modelo");
        edicion.addBindingTextField(Unidad.MARCA, "marca");
        edicion.addBindingComboBox(Unidad.TIPO, TipoDeUnidad.values());
        edicion.addBindingComboBox(Unidad.CLASE, ClaseDeUnidad.values());
        edicion.addBindingTextField(Unidad.NUMERO_CHAZIS, "numero de chazis");
        edicion.addBindingDoubleField(Unidad.COMBUSTIBLE, "combustible");
    }
    

    @Override
    protected Home getHome() {
        return HomeUnidad.getInstance();
    }
    
    public static void main(String[] args) {
        new ABMUnidad(new JFrame()).setVisible(true);
    }

    
}