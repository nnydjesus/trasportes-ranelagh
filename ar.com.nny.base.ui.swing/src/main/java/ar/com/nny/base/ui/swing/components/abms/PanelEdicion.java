package ar.com.nny.base.ui.swing.components.abms;

import java.awt.Component;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.text.NumberFormatter;

import ar.com.nny.base.ui.swing.components.JFormattedDecimalTextField;
import ar.com.nny.base.utils.Path;

import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.builder.ButtonStackBuilder;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;


@SuppressWarnings("unchecked")
public class PanelEdicion<T > extends JPanel {
	private static final long serialVersionUID = -4916057975139862174L;
	
	private JLabel tituloEdicion;
	private ButtonStackBuilder  panelDeBotones;
	private JButton botonAgregar;
	private JButton botonModificar;
	private JButton botonCancelar;
    private DefaultFormBuilder panelDeAtributos;
	private T model;
	private BeanAdapter beanAdapter;
	
	public PanelEdicion(String claseAEditar, T model){
		this.model = model;
		panelDeAtributos = new DefaultFormBuilder(new FormLayout("p, 2dlu, p:g"));
		panelDeAtributos.setDefaultDialogBorder();
		this.setTituloEdicion(new JLabel("Editar "+claseAEditar));
		panelDeBotones = new ButtonStackBuilder();
		this.setBotonAgregar(new JButton(new ImageIcon(Path.path()+"Images/add.jpg")));
		this.getBotonAgregar().setText("Agregar");
		this.setBotonModificar(new JButton(new ImageIcon(Path.path()+"Images/check.jpg")));
		this.getBotonModificar().setText("Modificar");
		this.getBotonModificar().setEnabled(false);
		this.setBotonCancelar(new JButton(new ImageIcon(Path.path()+"Images/cancel.jpg")));
		this.getBotonCancelar().setText("Limpiar");
		this.construirPanelEdicion();
		
	}
	
	private void construirPanelEdicion() {
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		panelDeBotones.addGridded(this.getBotonAgregar());
		panelDeBotones.addGridded(this.getBotonModificar());
		panelDeBotones.addGridded(this.getBotonCancelar());
		
		
		this.beanAdapter = new BeanAdapter(model);
		this.add(this.getTituloEdicion());
		this.add(panelDeAtributos.getPanel());
		this.add(this.getPanelDeBotones().getPanel());
		
	}
	/***
	 * este medoto "baindea" un campo de texto "TextField" a un modelo.
	 * El modelo debe respetar el Contrato de Java beans, esoa debe tener
	 * getters y setters para de la propiedad "property"
	 * esto permite que el campo le pague al modelo automaticamente.
	 * 
	 * @author Ronny
	 * @param field
	 * @return 
	 */
	public JTextField addBindingTextField(String property, String label){		
		ValueModel nameModel = beanAdapter.getValueModel(property);
		JTextField createTextField = BasicComponentFactory.createTextField(nameModel);
        panelDeAtributos.append(label , createTextField);
        return createTextField;
	}
	
	public JFormattedTextField addBindingIntegerField(String property, String label){		
		ValueModel nameModel = beanAdapter.getValueModel(property);
		JFormattedTextField createIntegerField = BasicComponentFactory.createIntegerField(nameModel, NumberFormat.getNumberInstance());
        panelDeAtributos.append(label , createIntegerField);
        return createIntegerField;
	}
	public JFormattedTextField addBindingDoubleField(String property, String label){		
		ValueModel valueModel = beanAdapter.getValueModel(property);
//		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		 NumberFormatter decimalFormat = new NumberFormatter();
		 decimalFormat.setValueClass(Double.class);
//		decimalFormat.setDecimalSeparatorAlwaysShown(true);
		JFormattedTextField textField = new JFormattedTextField(decimalFormat);
		 Bindings.bind(textField, valueModel);
		panelDeAtributos.append(label, textField);
		return textField;
	}
	
	public JDateChooser addBindingDateField(String property, String label){		
		JDateChooser date = new JDateChooser();
		ValueModel nameModel = beanAdapter.getValueModel(property);
		Bindings.bind(date, "date", nameModel);
		panelDeAtributos.append(label,date);
		return date;
	}
	public JComboBox addBindingCombobox(String label, SelectionInList<?> selectionInList, ListCellRenderer cellRenderer){		
		JComboBox createComboBox = BasicComponentFactory.createComboBox(selectionInList, cellRenderer);
        panelDeAtributos.append(label , createComboBox);
		return createComboBox;
	}
	
	public JCheckBox addBindingCheckBox(String property, String label) {
		ValueModel nameModel = beanAdapter.getValueModel(property);
		JCheckBox createCheckBox = BasicComponentFactory.createCheckBox(nameModel, label);
        panelDeAtributos.append(label , createCheckBox);
        return createCheckBox;
	}
	
	public void addComponent(String label,Component component){
		panelDeAtributos.append(label, component);
	}
	public JComboBox addBindingComboBox(String property,List<?> list){
	    ValueModel nameModel = beanAdapter.getValueModel(property);
        SelectionInList<?> selectionInList = new SelectionInList(list);
        JComboBox createCheckBox = BasicComponentFactory.createComboBox(selectionInList );
        panelDeAtributos.append(property , createCheckBox);
        return createCheckBox;
	}
	
	public void bind(JComponent component, String propertyName , String property){
		ValueModel valueModel = beanAdapter.getValueModel(property);
		Bindings.bind(component, propertyName , valueModel);
	}
	
	
	
	

	public JLabel getTituloEdicion() {
		return tituloEdicion;
	}
	public void setTituloEdicion(JLabel tituloEdicion) {
		this.tituloEdicion = tituloEdicion;
	}
	public DefaultFormBuilder getPanelDeAtributos() {
		return panelDeAtributos;
	}
	
	public ButtonStackBuilder getPanelDeBotones() {
		return panelDeBotones;
	}
	public void setPanelDeBotones(ButtonStackBuilder buttonStackBuilder) {
		this.panelDeBotones = buttonStackBuilder;
	}
	public JButton getBotonAgregar() {
		return botonAgregar;
	}
	public void setBotonAgregar(JButton botonAgregar) {
		this.botonAgregar = botonAgregar;
	}
	public void setBotonModificar(JButton botonModificar) {
		this.botonModificar = botonModificar;
	}

	public JButton getBotonModificar() {
		return botonModificar;
	}
	public JButton getBotonCancelar() {
		return botonCancelar;
	}
	public void setBotonCancelar(JButton botonCancelar) {
		this.botonCancelar = botonCancelar;
	}
	
	/**
	 * @author Ronny
	 * @return
	 */
	public T getModel() {
		return model;
	}
	public void setModel(Object object) {
		this.model = (T) object;
		beanAdapter.setBean(object);
		
	}

	public BeanAdapter getBeandAdapter() {
		return beanAdapter;
	}

	
}
