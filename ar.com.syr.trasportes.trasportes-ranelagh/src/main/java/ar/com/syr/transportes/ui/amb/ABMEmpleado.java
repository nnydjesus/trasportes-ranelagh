package ar.com.syr.transportes.ui.amb;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import main.Main;
import main.TransportesRanelagh;

import ar.com.nny.base.search.Home;
import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.FormBuilder;
import ar.com.nny.base.ui.swing.components.GeneralTable;
import ar.com.nny.base.ui.swing.components.Generator;
import ar.com.nny.base.ui.swing.components.abms.ABMFrame;
import ar.com.syr.transportes.bean.Direccion;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.bean.Licencia;
import ar.com.syr.transportes.bean.Remito;
import ar.com.syr.transportes.bean.enums.Categoria;
import ar.com.syr.transportes.search.HomeEmpleado;
import ar.com.syr.transportes.search.HomeRemito;

import com.jgoodies.forms.layout.FormLayout;

public class ABMEmpleado extends ABMFrame<Empleado>{
    private static final long serialVersionUID = 1L;

    private GeneralTable viajesRealizados;

    private List<Remito> viajes;
    
    private AbstractBindingPanel<Licencia> vencimientos;

    private FormBuilder<Direccion> direccionEditor;

    public ABMEmpleado(Empleado model) {
        this(model, Main.getApp());
    }
    public ABMEmpleado(JFrame parent) {
        this(new Empleado(), parent);
    }
    public ABMEmpleado(Empleado model, JFrame parent) {
        super(model, parent);
        this.addActions();
    }

    @Override
    protected void createForm(final AbstractBindingPanel<Empleado> edicion) {
        viajes = new ArrayList<Remito>();
        vencimientos = new AbstractBindingPanel<Licencia>(edicion.getModel().getLicencia());
//        edicion.addComponent("Seleccione El Legajo", comboBox);
        edicion.getBuilder().appendSeparator();
        edicion.addBindingTextField(Empleado.NOMBRE, "Nombre");
        edicion.addBindingTextField(Empleado.APELLIDO, "Apellido");
        edicion.addBindingIntegerField(Empleado.DNI, "Dni");
        edicion.addBindingDateField(Empleado.FECHA_DE_NACIMIENTO, "Fecha de nacimiento");
        edicion.addBindingTextField(Empleado.LEGAJO, "Legajo");
        edicion.addBindingTextField(Empleado.CUIL, "Cuil");
        edicion.addBindingComboBox(Empleado.CATEGORIA, Categoria.values());
        edicion.addBindingCheckBox(Empleado.PROPIO, "Propio");
        edicion.addBindingTextField(Empleado.REGISTRO, "Registro Municipal");
        edicion.addBindingTextField(Empleado.NUMBER_CNRT, "Numero de Cnrt");
        direccionEditor = createDireccionEditor();
        edicion.addComponent("Direccion", direccionEditor.build());
        edicion.addBindingTextField(Direccion.LOCALIDAD, "Localidad");
//        edicion.addBindingIntegerField(Direccion.TELEFONO, "Telefono");

        vencimientos.addBindingDateField(Licencia.REGISTRO, "Registro Municipal");
        vencimientos.addBindingDateField(Licencia.CNRT, "Cnrt");
        vencimientos.addBindingDateField(Licencia.LIBRETA_SANITARIA, "LibretaSanitaria");
        vencimientos.addBindingDateField(Licencia.REGISTRO, "Registro");
        
        viajesRealizados = Generator.GENERATE_TABLE(viajes, new Remito().atributos());
    }
    

    @Override
    protected Home getHome() {
        return HomeEmpleado.getInstance();
    }

    @Override
    protected void addPanels(final JPanel panel) {
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("General", panel);
        jTabbedPane.addTab("vencimientos", vencimientos);
        jTabbedPane.addTab("viajes Realizados", viajesRealizados);
        this.add(jTabbedPane);
    }

    protected void addActions() {
        viajesRealizados.getTabla().addMouseListener(new MouseAdapter() {
            @Override
         public void mousePressed(MouseEvent e) {
            if(e.getClickCount() == 2){
                Remito selected = (Remito)  viajesRealizados.getSelected();
                selected.setPago(!selected.getPago());
                HomeRemito.getInstance().update(selected);
                SwingUtilities.updateComponentTreeUI(viajesRealizados);
            }
         }
     });
    }
    
    @Override
    protected void setEditionModel(Empleado observable) {
        vencimientos.setModel(observable.getLicencia());
        direccionEditor.setModel(observable.getDireccion());
        viajes.removeAll(viajes);
        viajes.addAll(observable.getRemitos());
        super.setEditionModel(observable);
    }
    
    @Override
    public void edicionAceptar(Object object) {
        ((TransportesRanelagh)this.getParent()).getPersonalTree().getNomina().addItem(object);
        ((TransportesRanelagh)this.getParent()).getPersonalTree().getEmpleadoUI().getSearch().addObjectToSearch((Empleado) object);
        ((TransportesRanelagh)this.getParent()).getTree().updateTree();
        super.edicionAceptar(object);
    }

    protected FormBuilder<Direccion> createDireccionEditor(){
        // Right-aligned label, gap, component, gap, component
        FormBuilder<Direccion> form = new FormBuilder<Direccion>(getEdicion().getModel().getDireccion(),
                new FormLayout("pref, 4dlu, 50dlu,4dlu, pref,4dlu, pref:g,4dlu, pref,4dlu, pref:g,4dlu, pref,4dlu, pref:g "));
        form.addBindingTextField(Direccion.CALLE, "calle");
        form.addBindingIntegerField(Direccion.NUMERO, "numero");
        form.addBindingTextField(Direccion.ENTRE_X, "entre");
        form.addBindingTextField(Direccion.ENTRE_Y, "y");
        return form;
        
    }
    public static void main(String[] args) {
        new ABMEmpleado(new Empleado()).setVisible(true);
    }
    
}