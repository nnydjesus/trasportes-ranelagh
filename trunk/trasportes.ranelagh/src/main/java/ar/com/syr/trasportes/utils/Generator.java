package ar.com.syr.trasportes.utils;

import java.util.List;

import javax.swing.JTable;



import ar.com.syr.trasportes.ui.ModelBinding;

import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.SelectionInList;

public class Generator {
	
	public static ar.com.syr.trasportes.ui.GeneralTable GENERATE_TABLE(List<Observable> tablaList, String[] fields) {
		SelectionInList<Observable> selectionInList = new SelectionInList<Observable>(tablaList);		
		ModelBinding tablaModel = new ModelBinding(selectionInList, fields);		
		JTable tableTeachers = new JTable();
		tableTeachers.setSelectionModel(new SingleListSelectionAdapter(
				selectionInList.getSelectionIndexHolder()));
		return new ar.com.syr.trasportes.ui.GeneralTable(tablaModel, null, tableTeachers);
	}

}
