package ar.com.nny.base.ui.swing.components;

import java.io.Serializable;
import java.util.List;

import ar.com.nny.base.common.Observable;



/**Representa la grilla de horarios semanal 
 * Contiene los metodos necesarios para crear una matriz
 * en base a una lista de franjas de la misma semana.  */
@SuppressWarnings({ "unused", "serial","unchecked" })
public class TablaModelo<T> extends Modelo<T> {
	
	private ModelBinding modelBinding;
	private String[] columnNames;
	
	
	public TablaModelo() {
	}
	
	
	public TablaModelo(List<T> listModel, String[] columnNames) {
		this.getDatos().addAll(listModel);
		this.columnNames = columnNames;
	}
	
	public int getColumnCount(){
		return columnNames.length;
	}
	public int getRowCount(){
		return this.getDatos().size();
	}
	
	 public Object getValueAt(int rowIndex, int columnIndex) {
	    Observable observable = (Observable)this.getDatos().get(rowIndex);
		String colum = this.columnNames[columnIndex];
		Object property = observable.getProperty(colum);
	       System.out.println(property);
		return property;          
	         
	}


	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
		
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		this.getDatos().add(rowIndex, aValue);
	}
}
