package ar.com.syr.trasportes.ui;

import javax.swing.ListModel;


import ar.com.syr.trasportes.utils.Observable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;

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


