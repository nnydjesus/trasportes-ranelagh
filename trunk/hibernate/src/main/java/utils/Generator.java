package utils;

import java.util.List;

import javax.swing.JTable;


import ui.ModelBinding;

import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.SelectionInList;

public class Generator {
	
	public static ui.GeneralTable GENERATE_TABLE(List<Observable> tablaList, String[] fields) {
		SelectionInList<Observable> selectionInList = new SelectionInList<Observable>(tablaList);		
		ModelBinding tablaModel = new ModelBinding(selectionInList, fields);		
		JTable tableTeachers = new JTable();
		tableTeachers.setSelectionModel(new SingleListSelectionAdapter(
				selectionInList.getSelectionIndexHolder()));
		return new ui.GeneralTable(tablaModel, null, tableTeachers);
	}

}
