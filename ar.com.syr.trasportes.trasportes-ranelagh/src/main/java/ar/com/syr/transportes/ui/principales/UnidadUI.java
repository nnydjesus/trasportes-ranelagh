package ar.com.syr.transportes.ui.principales;

import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.search.SearchPanel;
import ar.com.syr.transportes.bean.Unidad;
import ar.com.syr.transportes.bean.enums.TipoDeUnidad;
import ar.com.syr.transportes.search.HomeUnidad;
import ar.com.syr.transportes.ui.amb.ABMUnidad;

public class UnidadUI extends GeneralFrame<Unidad> {
    private static final long serialVersionUID = 7725064134440510100L;

    public UnidadUI() {
        super("Unidad", Unidad.class);
    }

    @Override
    protected void createHome() {
        home = HomeUnidad.getInstance();
    }

    @Override
    protected SearchPanel<Unidad> createSearchPanel(Unidad newInstance) {
        return super.createSearchPanel(newInstance);
    }
    @Override
    protected void createSearchForm(final SearchPanel<Unidad> search) {
        search.addAutocompletetextField(Unidad.PATENTE, "Patente");
        search.addAutocompletetextField(Unidad.MODELO, "Modelo");
        search.addAutocompletetextField(Unidad.MARCA, "Marca");
        search.addBindingComboBox(Unidad.TIPO, TipoDeUnidad.values());
    }

    public static void main(String[] args) {
        new UnidadUI().setVisible(true);
    }


    @Override
    public Class<ABMUnidad> abmClass() {
        return ABMUnidad.class;
    }


}