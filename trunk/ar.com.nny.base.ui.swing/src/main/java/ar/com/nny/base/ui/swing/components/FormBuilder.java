package ar.com.nny.base.ui.swing.components;
import java.awt.Component;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import ar.com.nny.base.common.Observable;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

public class FormBuilder<T> extends DefaultFormBuilder{
    
    private T model;
    protected BeanAdapter beanAdapter;
    
    
    public FormBuilder(final T model, FormLayout layout) {
        super(layout);
        this.model = model;
        this.setDefaultDialogBorder();
        this.construirPanelEdicion();
    }

    public FormBuilder(final T model) {
        this(model,new FormLayout("p, 2dlu, p:g"));
    }

    private void construirPanelEdicion() {
        this.beanAdapter = new BeanAdapter(model);
    }

    public JPanel build() {
        return this.getPanel();
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
        JTextField createTextField = ComponentFactory.createTextField(nameModel);
        this.append(label, createTextField);
        return createTextField;
    }

    public JFormattedTextField addBindingIntegerField(final String property, final String label) {
        ValueModel nameModel = beanAdapter.getValueModel(property);
        JFormattedTextField createIntegerField = ComponentFactory.createIntegerField(nameModel,
                NumberFormat.getNumberInstance());
        this.append(label, createIntegerField);
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
        this.append(label, textField);
        return textField;
    }

    public JDateChooser addBindingDateField(final String property, final String label) {
        JDateChooser date = new JDateChooser();
        ValueModel nameModel = beanAdapter.getValueModel(property);
        Bindings.bind(date, "date", nameModel);
        this.append(label, date);
        return date;
    }

    public JCheckBox addBindingCheckBox(final String property, final String label) {
        ValueModel nameModel = beanAdapter.getValueModel(property);
        JCheckBox createCheckBox = ComponentFactory.createCheckBox(nameModel, label);
        this.append(label, createCheckBox);
        return createCheckBox;
    }

    public void addComponent(final String label, final Component component) {
        this.append(label, component);
    }

    public JComboBox addBindingComboBox(final String property, final List<?> list) {
        return addBindingComboBox(property,  new SelectionInList(list));
    }

    public JComboBox addBindingComboBox(String property, Object[] values) {
        return addBindingComboBox(property,  new SelectionInList(values));
    }
    public JComboBox addBindingComboBox(String property, SelectionInList<?> selectionInList) {
        ValueModel valueModel = beanAdapter.getValueModel(property);
        JComboBox createCombobox = ComponentFactory.createComboBox(selectionInList);
//        JComboBox createCombobox = new JComboBox(selectionInList.getList().toArray());
        this.bind(createCombobox, "selectedItem", valueModel);
        this.append(property, createCombobox);
        createCombobox.addActionListener(new ActionMethodListener(this, "setComboBoxModel", createCombobox, property));
        return createCombobox;
    }
    
    public void bind(final JComponent component, final String propertyName, final String property) {
        ValueModel valueModel = beanAdapter.getValueModel(property);
        bind(component, propertyName, valueModel);
    }
    public void bind(final JComponent component, final String propertyName, ValueModel valueModel) {
        Bindings.bind(component, propertyName, valueModel);
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

    public void setComboBoxModel(MyJComboBox combo,String property){
        this.setComboBoxModel((JComboBox)combo, property);
    }
    public void setComboBoxModel(JComboBox combo,String property){
        ((Observable)this.getModel()).setProperty(property, combo.getSelectedItem());
    }

    @Override
    public String toString() {
        return model.toString();
    }
    
}
