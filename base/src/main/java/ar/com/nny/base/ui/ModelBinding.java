package ar.com.nny.base.ui;

import javax.swing.ListModel;

import ar.com.nny.base.utils.Observable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;

@SuppressWarnings({ "unchecked", "serial" })
public class ModelBinding extends AbstractTableAdapter implements Model{

	public ModelBinding(ListModel listModel, String[] columnNames) {
		super(listModel, columnNames);		
	}
	
	
	 public Object getSelected(int i){
	   	return this.getRow(i);
	}
	 
	 @Override
	 public Object getValueAt(int rowIndex, int columnIndex) {
		    Observable observable = (Observable)getRow(rowIndex);
			String colum = this.getColumnName(columnIndex);
			Object property = observable.getProperty(colum);
			return property;          
		         
		}

	
	

}


