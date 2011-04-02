package ar.com.syr.transportes.ui.amb;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTabbedPane;

import ar.com.nny.base.common.Observable;
import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.GeneralTable;
import ar.com.nny.base.ui.swing.components.Generator;
import ar.com.nny.base.ui.swing.components.abms.PanelEdicion;
import ar.com.nny.base.ui.swing.components.search.SearchPanel;
import ar.com.syr.transportes.bean.Direccion;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.bean.Licencia;
import ar.com.syr.transportes.bean.Remito;
import ar.com.syr.transportes.bean.enums.Categoria;
import ar.com.syr.transportes.search.HomeEmpleado;
import ar.com.syr.transportes.search.HomeRemito;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.beans.BeanAdapter;

public class EmpleadoUi extends GeneralFrame<Empleado> {
    private static final long serialVersionUID = 7725064134440510100L;

    private GeneralTable viajesRealizados;

    private List<Remito> viajes;

    private JComboBox categoria;

    public EmpleadoUi() {
        super("Empleado", Empleado.class);
    }

    @Override
    protected void createForm(final AbstractBindingPanel<Empleado> edicion) {
        viajes = new ArrayList<Remito>();
        edicion.addComponent("Seleccione El Legajo", comboBox);
        edicion.addBindingTextField(Empleado.NOMBRE, "Nombre");
        edicion.addBindingTextField(Empleado.APELLIDO, "Apellido");
        edicion.addBindingIntegerField(Empleado.DNI, "Dni");
        edicion.addBindingTextField(Empleado.LEGAJO, "Legajo");
        edicion.addBindingTextField(Empleado.CUIL, "Cuil");
        categoria = edicion.addBindingComboBox(Empleado.CATEGORIA, Categoria.values());
        edicion.addBindingCheckBox(Empleado.PROPIO, "Propio");
        edicion.addBindingIntegerField(Empleado.REGISTRO, "Registro");
        edicion.addBindingDateField(Licencia.FECHA_DE_NACIMIENTO, "Categoria");
        edicion.addBindingDateField(Licencia.CNRT, "Cnrt");
        edicion.addBindingDateField(Licencia.LIBRETA_SANITARIA, "LibretaSanitaria");
        edicion.addBindingDateField(Licencia.REGISTRO, "Registro");
        edicion.addBindingTextField(Direccion.CALLE, "Direccion");
        edicion.addBindingTextField(Direccion.LOCALIDAD, "Localidad");

        viajesRealizados = Generator.GENERATE_TABLE(viajes, new Remito().atributos());
        // direccion.addBindingIntegerField(Direccion.TELEFONO, "Telefono");
        // direccion.addBindingIntegerField(Direccion.CODPOSTAL, "CodPostal");

    }
    
    @Override
    protected void addActions() {
        super.addActions();
        viajesRealizados.getTabla().addMouseListener(new MouseAdapter() {
            @Override
         public void mousePressed(MouseEvent e) {
            if(e.getClickCount() == 3){
                Remito selected = (Remito)  viajesRealizados.getSelected();
                selected.setPago(!selected.getPago());
                HomeRemito.getInstance().update(selected);
            }
         }
     });
    }


    @Override
    protected void createHome() {
        home = HomeEmpleado.getInstance();
    }

    @Override
    protected void addPanels(final JTabbedPane panel) {
        super.addPanels(panel);
        panel.addTab("viajes Realizados", viajesRealizados);
    }

    @Override
    public void setModel(final Empleado observable) {
        viajes.removeAll(viajes);
        viajes.addAll(observable.getRemitos());
        super.setModel(observable);
    }
    
    @Override
    protected void edicionModificar() {
        edicion.getModel().setCategoria((Categoria) categoria.getSelectedItem());
        super.edicionModificar();
    }

    @Override
    protected void createSearchForm(final SearchPanel<Empleado> search) {
        search.addAutocompletetextField(Empleado.LEGAJO, "Legajo");
        search.addAutocompletetextField(Empleado.NOMBRE, "Nombre");
        search.addAutocompletetextField(Empleado.APELLIDO, "Apellido");
    }

}