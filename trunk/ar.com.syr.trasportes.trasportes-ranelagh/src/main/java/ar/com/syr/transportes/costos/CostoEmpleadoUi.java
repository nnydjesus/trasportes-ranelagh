package ar.com.syr.transportes.costos;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.abms.PanelEdicion;
import ar.com.syr.transportes.bean.CostoEmpleado;
import ar.com.syr.transportes.serach.HomeCostoempleado;

@SuppressWarnings("serial")
public class CostoEmpleadoUi extends GeneralFrame<CostoEmpleado> implements Item{

	public CostoEmpleadoUi() {
		super("CostoEmpleado", CostoEmpleado.class);
		super.addActions();
		panel.setSize(200,600);
	}	

	@Override
	protected void createForm(PanelEdicion<CostoEmpleado> edicion) {
		edicion.addComponent("Seleccione El Empleado", comboBox);
		edicion.addBindingDoubleField(CostoEmpleado.COSTO_TOTAL, "Costo");
		
	}
	
   protected void addPanels(){
        panel.addTab("General",null, edicion, "Edicion");
        panel.addTab("Tabla",null, table, "tabla");
    }
	

	@Override
	protected void createHome() {
		home = new HomeCostoempleado();		
	}

	@Override
	protected CostoEmpleado getDefaultModel() {
		return new CostoEmpleado();
	}
	
}
