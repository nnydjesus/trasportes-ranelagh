package ar.com.syr.transportes.costos;

import javax.swing.JFrame;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.abms.ABMFrame;
import ar.com.syr.transportes.bean.CostoEmpleado;

public class ABMCostoEmpleado extends ABMFrame<CostoEmpleado> implements Item {

    private static final long serialVersionUID = 1L;

    public ABMCostoEmpleado(JFrame parent) {
        super(new CostoEmpleado(), parent);
    }

    @Override
    protected void createForm(final AbstractBindingPanel<CostoEmpleado> edicion) {
//        edicion.addComponent("Seleccione El Empleado", comboBox);
        edicion.addBindingDoubleField(CostoEmpleado.COSTO_TOTAL, "Costo");
    }

}
