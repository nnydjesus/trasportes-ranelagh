package ar.com.nny.java.base.utils;

import java.util.List;

import javax.swing.JTable;



import ar.com.nny.java.base.ui.ModelBinding;

import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.SelectionInList;

public class Generator {
	
	@SuppressWarnings("unchecked")
	public static ar.com.nny.java.base.ui.GeneralTable GENERATE_TABLE(List<?> tablaList, String[] fields) {
		SelectionInList<?> selectionInList = new SelectionInList(tablaList);		
		ModelBinding tablaModel = new ModelBinding(selectionInList, fields);		
		JTable tableTeachers = new JTable();
		tableTeachers.setSelectionModel(new SingleListSelectionAdapter(
				selectionInList.getSelectionIndexHolder()));
		return new ar.com.nny.java.base.ui.GeneralTable(tablaModel, null, tableTeachers);
	}

}
