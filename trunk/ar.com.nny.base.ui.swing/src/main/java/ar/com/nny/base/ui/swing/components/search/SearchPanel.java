package ar.com.nny.base.ui.swing.components.search;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ar.com.nny.base.common.Observable;
import ar.com.nny.base.search.Home;
import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.ActionMethodListener;
import ar.com.nny.base.ui.swing.components.GeneralTable;
import ar.com.nny.base.ui.swing.components.Generator;
import ar.com.nny.base.ui.swing.components.JFormattedDecimalTextField;
import ar.com.nny.base.ui.swing.components.ModelBinding;
import ar.com.nny.base.ui.swing.components.WindowsSearch;
import ar.com.nny.base.ui.swing.components.autocomplete.AutoCompleteTextField;
import ar.com.nny.base.ui.swing.components.autocomplete.LimeTextField;
import ar.com.nny.base.utils.IdentificablePersistentObject;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class SearchPanel<T extends IdentificablePersistentObject> extends AbstractBindingPanel<T> {
    
    private static final long serialVersionUID = 1L;

    public static final String UPDATE_TOTAL = "updateTotals";

    private JButton search;

    private JButton clear;

    protected Home<T> home;

    private GeneralTable table;

    protected List<T> result = new ArrayList<T>();
    
    private Map<String, AutoCompleteTextField> autocompletable = new HashMap<String, AutoCompleteTextField>();  
    private Map<String, LimeTextField> totals = new HashMap<String, LimeTextField>();  
    
    private WindowsSearch parent;
    
    private DefaultFormBuilder panelTotals= new DefaultFormBuilder(new FormLayout("p, 2dlu, p:g")); 

    private JButton delete;

    public SearchPanel(final T model, final Home<T> home, WindowsSearch parent) {
        super(model);
        this.parent = parent;
        this.home = home;
        setTable(this.createTable(home.createExample()));
        this.addActions();
        this.add(this.getTable());
        this.add(panelTotals.getPanel());
    }

    public void delete() {
        try {
            Object selected = table.getSelected();
            home.delete((T) selected);
            search();
            parent.deleteObject(selected);
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Seleccionar un elemento de la tabla para que sea eliminado",
                    "Error de seleccion",  JOptionPane.ERROR_MESSAGE );
        }
    }

    protected GeneralTable createTable(final T newInstance) {
        return Generator.GENERATE_TABLE(result, newInstance.atributos());
    }

    @Override
    protected void addButtons() {
        this.search = new JButton();
        this.search.setText("Buscar");
        this.clear = new JButton();
        this.clear.setText("Limpiar");
        this.delete = new JButton();
        this.delete.setText("Borrar");
        delete.addActionListener(new ActionMethodListener(this, "delete"));
        

        this.getPanelDeBotones().addButton(search);
        this.getPanelDeBotones().addRelatedGap();
        this.getPanelDeBotones().addButton(clear);
        this.getPanelDeBotones().addRelatedGap();
        this.getPanelDeBotones().addButton(delete);

    }

    protected void addActions() {
        this.search.addActionListener(new ActionMethodListener(this, "search"));
        this.clear.addActionListener(new ActionMethodListener(this, "clear"));
        this.table.getTabla().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() >=2){
                    editSelected((T) table.getSelected());
                }
            }
        });
        
        table.getTabla().addVetoableChangeListener(new VetoableChangeListener() {
            
            @Override
            public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                JOptionPane.showConfirmDialog(table, "afsdfasdf");
            }
        });
    }

    public void clear() {
        result.removeAll(result);
        this.setModel(home.createExample());
        updateTotals();
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void search() {
        result.removeAll(result);
        result.addAll(home.searchByExample(this.getModel()));
        updateTotals();
        SwingUtilities.updateComponentTreeUI(this);
    }

    public AutoCompleteTextField addAutocompletetextField(final String property, final String label) {
        ValueModel valueModel = getBuilder().getBeandAdapter().getValueModel(property);
        AutoCompleteTextField createTextField = new AutoCompleteTextField();
        Bindings.bind(createTextField, valueModel);
        this.addListDataAutoComplete(createTextField, property);
        this.getBuilder().append(label, createTextField);
        autocompletable.put(property,createTextField);
        return createTextField;
    }
    
    protected void addListDataAutoComplete(final AutoCompleteTextField text, final String property) {
        for (T object : home.getAll()) {
            text.addToDictionary(property, object);
        }
    }
    
    public void addObjectToSearch(T object){
        for (String  property : autocompletable.keySet()) {
            autocompletable.get(property).addToDictionary(property, object);
        }
    }
    
    public void addTextTotal(String property, String label){
        JFormattedDecimalTextField text = new JFormattedDecimalTextField();
        text.setEditable(false);
        panelTotals.append(label,text);
        totals.put(property, text);
    }
    
    public void updateTotals(){
        Double value;
        for (String property : totals.keySet()) {
            double total =0.0;
            for (Observable objeject : ((ModelBinding) table.getTabla().getModel()).getDatos()){
                 value = (Double)objeject.getProperty(property);
                if(value != null)
                    total += value;
            }
            totals.get(property).setText(total+"");
        }
    }
    
    protected void editSelected(T selected){
        parent.editSelected(selected);
    }

    public GeneralTable getTable() {
        return table;
    }

    protected void setTable(GeneralTable table) {
        this.table = table;
    }

    public void setSearch(JButton search) {
        this.search = search;
    }

    public JButton getSearch() {
        return search;
    }

    public void setClear(JButton clear) {
        this.clear = clear;
    }

    public JButton getClear() {
        return clear;
    }
}
