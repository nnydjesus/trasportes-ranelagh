package ar.com.nny.base.ui.swing.components;

import javax.swing.ListModel;

import ar.com.nny.base.common.Observable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;

@SuppressWarnings({ "rawtypes" })
public class ModelBinding extends AbstractTableAdapter implements Model {

    private static final long serialVersionUID = 1L;

    public ModelBinding(final ListModel listModel, final String[] columnNames) {
        super(listModel, columnNames);
    }

    public Object getSelected(final int i) {
        return this.getRow(i);
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        Observable observable = (Observable) this.getRow(rowIndex);
        String colum = this.getColumnName(columnIndex);
        Object property = observable.getProperty(colum);
        return property;

    }

    @Override
    public Class<?> getColumnClass(final int columnIndex) {
        if (this.getRowCount() > 0) {
            Object valueAt = this.getValueAt(0, columnIndex);
            return valueAt == null ? Object.class : valueAt.getClass();
        } else
            return Object.class;
    }
    
}
