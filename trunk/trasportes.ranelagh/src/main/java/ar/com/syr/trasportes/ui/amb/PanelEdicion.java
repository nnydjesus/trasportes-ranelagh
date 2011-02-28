package ar.com.syr.trasportes.ui.amb;

import java.awt.Component;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.builder.ButtonStackBuilder;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;


public class PanelEdicion<T > extends JPanel {

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
		this.setBotonAgregar(new JButton(new ImageIcon("./Images/add.jpg")));
		this.getBotonAgregar().setText("Agregar");
		this.setBotonModificar(new JButton(new ImageIcon("./Images/check.jpg")));
		this.getBotonModificar().setText("Modificar");
		this.getBotonModificar().setEnabled(false);
		this.setBotonCancelar(new JButton(new ImageIcon("./Images/cancel.jpg")));
		this.getBotonCancelar().setText("Limpiar");
		this.construirPanelEdicion();
		
	}
	
	private void construirPanelEdicion() {
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
//		this.getPanelDeBotones().setLayout(new FlowLayout());
		panelDeBotones.addGridded(this.getBotonAgregar());
//		panelDeBotones.addUnrelatedGap();
		panelDeBotones.addGridded(this.getBotonModificar());
//		panelDeBotones.addRelatedGap();
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
	public JLabel addBindingTextField(String property, String label){		
		ValueModel nameModel = beanAdapter.getValueModel(property);
		return panelDeAtributos.append(label , BasicComponentFactory.createTextField(nameModel));
	}
	
	public JLabel addBindingIntegerField(String property, String label){		
		ValueModel nameModel = beanAdapter.getValueModel(property);
		return panelDeAtributos.append(label , BasicComponentFactory.createIntegerField(nameModel, NumberFormat.getNumberInstance()));
	}
	public JLabel addBindingDoubleField(String property, String label){		
		ValueModel nameModel = beanAdapter.getValueModel(property);
		return panelDeAtributos.append(label ,BasicComponentFactory.createFormattedTextField(nameModel, new DecimalFormat("0.#####")));
	}
	public JLabel addBindingDateField(String property, String label){		
		JDateChooser date = new JDateChooser();
		ValueModel nameModel = beanAdapter.getValueModel(property);
		Bindings.bind(date, "date", nameModel);
		return panelDeAtributos.append(label,date);
	}
	public JLabel addBindingCombobox(String label, SelectionInList<?> selectionInList, ListCellRenderer cellRenderer){		
		return panelDeAtributos.append(label , BasicComponentFactory.createComboBox(selectionInList, cellRenderer));
	}
	
	public JLabel addBindingCheckBox(String property, String label) {
		ValueModel nameModel = beanAdapter.getValueModel(property);
		return panelDeAtributos.append(label , BasicComponentFactory.createCheckBox(nameModel, label));
	}
	
	public void addComponent(String label,Component component){
		panelDeAtributos.append(label, component);
	}
	
	
	
	

	public JLabel getTituloEdicion() {
		return tituloEdicion;
	}
	public void setTituloEdicion(JLabel tituloEdicion) {
		this.tituloEdicion = tituloEdicion;
	}
	public JPanel getPanelDeAtributos() {
		return panelDeAtributos.getPanel();
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

	
}
