package ar.com.nny.base.ui.swing.components;

import javax.swing.ListModel;

import ar.com.nny.base.common.Observable;

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
	 
	 @Override
	public Class<?> getColumnClass(int columnIndex) {
		 if(getRowCount()>0)
			 return getValueAt(0, columnIndex).getClass();
		 else
			 return Object.class;
	}

	
	

}


