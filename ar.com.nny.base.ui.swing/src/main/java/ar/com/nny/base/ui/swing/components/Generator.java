package ar.com.nny.base.ui.swing.components;

import java.util.List;

import javax.swing.JTable;

import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.SelectionInList;

public class Generator {
	
	@SuppressWarnings("unchecked")
	public static GeneralTable GENERATE_TABLE(List<?> tablaList, String[] fields) {
		SelectionInList<?> selectionInList = new SelectionInList(tablaList);		
		ModelBinding tablaModel = new ModelBinding(selectionInList, fields);		
		JTable tableTeachers = new JTable();
		tableTeachers.setSelectionModel(new SingleListSelectionAdapter(
				selectionInList.getSelectionIndexHolder()));
		return new GeneralTable(tablaModel, null, tableTeachers);
	}

}