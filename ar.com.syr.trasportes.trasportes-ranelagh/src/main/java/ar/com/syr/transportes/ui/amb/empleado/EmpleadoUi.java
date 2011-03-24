package ar.com.syr.transportes.ui.amb.empleado;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;

import ar.com.nny.base.common.Observable;
import ar.com.nny.base.ui.swing.components.ActionMethodListener;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.Generator;
import ar.com.nny.base.ui.swing.components.abms.PanelEdicion;
import ar.com.nny.base.ui.swing.components.search.SearchPanel;
import ar.com.syr.transportes.bean.Direccion;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.bean.Licencia;
import ar.com.syr.transportes.serach.HomeEmpleado;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.beans.BeanAdapter;


public class EmpleadoUi extends GeneralFrame<Empleado> {
	private static final long serialVersionUID = 7725064134440510100L;

	protected PanelEdicion<Observable> direccion;
	protected PanelEdicion<Observable> licencia;


	public EmpleadoUi() {
		super("Empleado", Empleado.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void createForm(PanelEdicion<Empleado> edicion) {
		direccion = new PanelEdicion<Observable>("Direccion", new Direccion());
		licencia = new PanelEdicion<Observable>("Licencia", new Licencia());
		BeanAdapter beanDireccion = new BeanAdapter(edicion.getModel());
		Bindings.bind(direccion, "model", beanDireccion.getValueModel("direccion"));
		Bindings.bind(licencia, "model", beanDireccion.getValueModel("licencia"));
		edicion.addComponent("Seleccione El Legajo", comboBox);
		edicion.addBindingTextField(Empleado.NOMBRE, "Nombre");
		edicion.addBindingTextField(Empleado.APELLIDO, "Apellido");
		edicion.addBindingIntegerField(Empleado.DNI, "Dni");
		edicion.addBindingTextField(Empleado.LEGAJO, "Legajo");
		edicion.addBindingTextField(Empleado.CUIL,"Cuil");
		edicion.addBindingCheckBox(Empleado.PROPIO, "Propio");
		edicion.addBindingIntegerField(Empleado.REGISTRO, "Registro");
		edicion.addBindingDateField(Licencia.CATEGORIA, "Categoria");
		edicion.addBindingDateField(Licencia.CNRT, "Cnrt");
		edicion.addBindingDateField(Licencia.LIBRETA_SANITARIA, "LibretaSanitaria");
		edicion.addBindingDateField(Licencia.REGISTRO, "Registro");
		edicion.addBindingTextField(Direccion.CALLE, "Direccion");
		edicion.addBindingTextField(Direccion.LOCALIDAD, "Localidad");
//		direccion.addBindingIntegerField(Direccion.TELEFONO, "Telefono");
//		direccion.addBindingIntegerField(Direccion.CODPOSTAL, "CodPostal");

	}
	
	@Override
	protected Empleado getDefaultModel() {
		return new Empleado();
	}
	
	
    public static void main(String[] args) {
    	new EmpleadoUi();
    }

    @Override
    protected void createHome() {
        home = new HomeEmpleado();
    }
    

    @Override
    protected void createSearchForm(SearchPanel<Empleado> search) {
        search.addBindingTextField(Empleado.LEGAJO, "Legajo");
        search.addBindingTextField(Empleado.NOMBRE, "Nombre");
        search.addBindingTextField(Empleado.APELLIDO, "Apellido");
    }



}