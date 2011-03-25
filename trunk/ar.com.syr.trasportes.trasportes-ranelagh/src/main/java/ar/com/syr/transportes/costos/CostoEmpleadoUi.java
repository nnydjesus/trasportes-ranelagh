package ar.com.syr.transportes.costos;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.search.SearchPanel;
import ar.com.syr.transportes.bean.CostoEmpleado;
import ar.com.syr.transportes.search.HomeCostoempleado;

public class CostoEmpleadoUi extends GeneralFrame<CostoEmpleado> implements Item {

    private static final long serialVersionUID = 1L;

    public CostoEmpleadoUi() {
        super("CostoEmpleado", CostoEmpleado.class);
        super.addActions();
        panel.setSize(200, 600);
    }

    @Override
    protected void createForm(final AbstractBindingPanel<CostoEmpleado> edicion) {
        edicion.addComponent("Seleccione El Empleado", comboBox);
        edicion.addBindingDoubleField(CostoEmpleado.COSTO_TOTAL, "Costo");
    }

    @Override
    protected void createSearchForm(final SearchPanel<CostoEmpleado> panel) {
    }

    @Override
    protected void addPanels() {
        panel.addTab("General", null, edicion, "Edicion");
        panel.addTab("Tabla", null, table, "tabla");
    }

    @Override
    protected void createHome() {
        home = HomeCostoempleado.getInstance();
    }

}
