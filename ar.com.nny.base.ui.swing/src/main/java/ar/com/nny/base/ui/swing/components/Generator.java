package ar.com.nny.base.ui.swing.components;

import java.util.List;

import javax.swing.JTable;

import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.SelectionInList;

public class Generator {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static GeneralTable GENERATE_TABLE(List<?> tablaList, String[] fields) {
		SelectionInList<?> selectionInList = new SelectionInList(tablaList);		
		ModelBinding tablaModel = new ModelBinding(selectionInList, fields);		
		TableAdapter table = new TableAdapter();
		table.setAutoCreateRowSorter(true);
		table.setSelectionModel(new SingleListSelectionAdapter(
				selectionInList.getSelectionIndexHolder()));
		return new GeneralTable(tablaModel, table);
	}

}
