package ar.com.nny.base.ui.swing.components.search;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import ar.com.nny.base.search.Home;
import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.ActionMethodListener;
import ar.com.nny.base.ui.swing.components.GeneralTable;
import ar.com.nny.base.ui.swing.components.Generator;
import ar.com.nny.base.ui.swing.components.autocomplete.AutoCompleteTextField;
import ar.com.nny.base.utils.IdentificablePersistentObject;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.value.ValueModel;

public class SearchPanel<T extends IdentificablePersistentObject> extends AbstractBindingPanel<T> {

    private JButton search;

    private JButton clear;

    protected Home<T> home;

    private GeneralTable table;

    protected List<T> result = new ArrayList<T>();

    public SearchPanel(final T model, final Home<T> home) {
        super(model);
        this.home = home;
        setTable(this.createTable(home.createExample()));
        this.addActions();
        this.add(this.getTable());
    }

    private static final long serialVersionUID = 1L;

    protected GeneralTable createTable(final T newInstance) {
        return Generator.GENERATE_TABLE(result, newInstance.atributos());
    }

    @Override
    protected void addButtons() {
        this.search = new JButton();
        this.search.setText("Buscar");
        this.clear = new JButton();
        this.clear.setText("Limpiar");

        this.getPanelDeBotones().addGridded(search);
        this.getPanelDeBotones().addGridded(clear);

    }

    private void addActions() {
        this.search.addActionListener(new ActionMethodListener(this, "search"));
        this.clear.addActionListener(new ActionMethodListener(this, "clear"));
    }

    public void clear() {
        result.removeAll(result);
        this.setModel(home.createExample());
        table.getModelo().fireTableDataChanged();
    }

    public void search() {
        result.removeAll(result);
        result.addAll(home.searchByExample(this.getModel()));
        table.getModelo().fireTableDataChanged();
    }

    public AutoCompleteTextField addAutocompletetextField(final String property, final String label) {
        ValueModel valueModel = beanAdapter.getValueModel(property);
        AutoCompleteTextField createTextField = new AutoCompleteTextField();
        Bindings.bind(createTextField, valueModel);
        this.addListDataAutoComplete(createTextField, property);
        this.getPanelDeAtributos().append(label, createTextField);
        return createTextField;
    }

    protected void addListDataAutoComplete(final AutoCompleteTextField text, final String property) {
        for (T object : home.getAll()) {
            text.addToDictionary(property, object);
        }
    }

    public GeneralTable getTable() {
        return table;
    }

    protected void setTable(GeneralTable table) {
        this.table = table;
    }

}
