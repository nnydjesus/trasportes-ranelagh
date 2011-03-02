package ar.com.syr.trasportes.costos;

import ar.com.syr.trasportes.bean.CostoEmpleado;
import ar.com.syr.trasportes.common.Item;
import ar.com.syr.trasportes.dao.CostoEmpleadoDao;
import ar.com.syr.trasportes.ui.GeneralFrame;
import ar.com.syr.trasportes.ui.MyJComboBox;

public class CostoEmpleadoUi extends GeneralFrame<CostoEmpleado> implements Item{

	public CostoEmpleadoUi() {
		super("CostoEmpleado", CostoEmpleado.class);
		super.addActions();
	}	

	@Override
	protected void createForm() {
		comboBox = new MyJComboBox(((CostoEmpleadoDao) dao).getAllIdsEmpleados());
		edicion.addComponent("Seleccione El Remito", comboBox);
//		edicion.addBindingTextField(CostoEmpleado.EMPLEADO, "Legajo");
		edicion.addBindingDoubleField(CostoEmpleado.COSTO_TOTAL, "Costo");
		
	}

	@Override
	protected void createDao() {
		dao = new CostoEmpleadoDao();		
	}
	
	
}
