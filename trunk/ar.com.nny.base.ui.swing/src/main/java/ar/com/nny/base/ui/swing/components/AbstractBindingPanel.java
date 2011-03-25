package ar.com.nny.base.ui.swing.components;

import java.awt.Component;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.text.NumberFormatter;

import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.builder.ButtonStackBuilder;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

/**
 * TODO: description
 */
public class AbstractBindingPanel<T> extends JPanel {
    private static final long serialVersionUID = -4916057975139862174L;

    private JLabel titulo;

    private ButtonStackBuilder panelDeBotones;

    private DefaultFormBuilder panelDeAtributos;

    private T model;

    protected BeanAdapter beanAdapter;

    public AbstractBindingPanel(final String claseAEditar, final T model) {
        this.model = model;
        panelDeAtributos = new DefaultFormBuilder(new FormLayout("p, 2dlu, p:g"));
        panelDeAtributos.setDefaultDialogBorder();
        this.titulo = new JLabel();
        panelDeBotones = new ButtonStackBuilder();
        this.construirPanelEdicion();

    }

    private void construirPanelEdicion() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addButtons();
        this.beanAdapter = new BeanAdapter(model);
        this.add(this.titulo);
        this.add(panelDeAtributos.getPanel());
        this.add(this.getPanelDeBotones().getPanel());

    }

    protected void addButtons() {
    }

    /***
     * este medoto "baindea" un campo de texto "TextField" a un modelo. El
     * modelo debe respetar el Contrato de Java beans, esoa debe tener getters y
     * setters para de la propiedad "property" esto permite que el campo le
     * pague al modelo automaticamente.
     * 
     * @param field
     * @return
     */
    public JTextField addBindingTextField(final String property, final String label) {
        ValueModel nameModel = beanAdapter.getValueModel(property);
        JTextField createTextField = BasicComponentFactory.createTextField(nameModel);
        panelDeAtributos.append(label, createTextField);
        return createTextField;
    }

    public JFormattedTextField addBindingIntegerField(final String property, final String label) {
        ValueModel nameModel = beanAdapter.getValueModel(property);
        JFormattedTextField createIntegerField = BasicComponentFactory.createIntegerField(nameModel,
                NumberFormat.getNumberInstance());
        panelDeAtributos.append(label, createIntegerField);
        return createIntegerField;
    }

    public JFormattedTextField addBindingDoubleField(final String property, final String label) {
        ValueModel valueModel = beanAdapter.getValueModel(property);
        // DecimalFormat decimalFormat = new DecimalFormat("#.##");
        NumberFormatter decimalFormat = new NumberFormatter();
        decimalFormat.setValueClass(Double.class);
        // decimalFormat.setDecimalSeparatorAlwaysShown(true);
        JFormattedTextField textField = new JFormattedTextField(decimalFormat);
        Bindings.bind(textField, valueModel);
        panelDeAtributos.append(label, textField);
        return textField;
    }

    public JDateChooser addBindingDateField(final String property, final String label) {
        JDateChooser date = new JDateChooser();
        ValueModel nameModel = beanAdapter.getValueModel(property);
        Bindings.bind(date, "date", nameModel);
        panelDeAtributos.append(label, date);
        return date;
    }

    public JComboBox addBindingCombobox(final String label, final SelectionInList<?> selectionInList,
            final ListCellRenderer cellRenderer) {
        JComboBox createComboBox = BasicComponentFactory.createComboBox(selectionInList, cellRenderer);
        panelDeAtributos.append(label, createComboBox);
        return createComboBox;
    }

    public JCheckBox addBindingCheckBox(final String property, final String label) {
        ValueModel nameModel = beanAdapter.getValueModel(property);
        JCheckBox createCheckBox = BasicComponentFactory.createCheckBox(nameModel, label);
        panelDeAtributos.append(label, createCheckBox);
        return createCheckBox;
    }

    public void addComponent(final String label, final Component component) {
        panelDeAtributos.append(label, component);
    }

    public JComboBox addBindingComboBox(final String property, final List<?> list) {
        ValueModel nameModel = beanAdapter.getValueModel(property);
        SelectionInList<?> selectionInList = new SelectionInList(list);
        JComboBox createCheckBox = BasicComponentFactory.createComboBox(selectionInList);
        panelDeAtributos.append(property, createCheckBox);
        return createCheckBox;
    }

    public void bind(final JComponent component, final String propertyName, final String property) {
        ValueModel valueModel = beanAdapter.getValueModel(property);
        Bindings.bind(component, propertyName, valueModel);
    }

    public void setTitulo(final String title) {
        this.titulo.setText(title);
    }

    public DefaultFormBuilder getPanelDeAtributos() {
        return panelDeAtributos;
    }

    public ButtonStackBuilder getPanelDeBotones() {
        return panelDeBotones;
    }

    public void setPanelDeBotones(final ButtonStackBuilder buttonStackBuilder) {
        this.panelDeBotones = buttonStackBuilder;
    }

    public T getModel() {
        return model;
    }

    public void setModel(final Object object) {
        this.model = (T) object;
        beanAdapter.setBean(object);

    }

    public BeanAdapter getBeandAdapter() {
        return beanAdapter;
    }

}
