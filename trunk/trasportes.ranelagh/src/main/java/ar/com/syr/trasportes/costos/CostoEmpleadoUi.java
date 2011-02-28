package ar.com.syr.trasportes.costos;

import ar.com.syr.trasportes.bean.CostoEmpleado;
import ar.com.syr.trasportes.common.Item;
import ar.com.syr.trasportes.ui.GeneralFrame;

public class CostoEmpleadoUi extends GeneralFrame<CostoEmpleado> implements Item{

	public CostoEmpleadoUi() {
		super("CostoEmpleado", CostoEmpleado.class);
		super.addActions();
	}

	@Override
	protected void createForm() {
		edicion.addBindingTextField(CostoEmpleado.LEGAJO, "Legajo");
		edicion.addBindingDoubleField(CostoEmpleado.COSTO_TOTAL, "Costo");
		
	}
	
	
}
