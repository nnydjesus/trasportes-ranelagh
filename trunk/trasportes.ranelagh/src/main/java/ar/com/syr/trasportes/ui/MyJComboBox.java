package ar.com.syr.trasportes.ui;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;

import ar.com.syr.trasportes.utils.Observable;


public class MyJComboBox extends JComboBox {
	
	
	public MyJComboBox() {
	}

	public MyJComboBox(List<?> list) {
        super();
		Vector vector = new Vector();
		for (Object object : list) {
			vector.add(object);			
		}
		this.setRenderer(new MyRenderer());
        setModel(new DefaultComboBoxModel(vector));
	}

	@Override
	public void setSelectedItem(Object object){
		super.setSelectedItem(object);
	}
	
	@Override
	public Object getSelectedItem() {
		return super.getSelectedItem();
	}
	
	static class MyRenderer extends DefaultListCellRenderer{
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			JLabel jLabel;
			if(value instanceof Observable)
				jLabel = new JLabel(((Observable) value).getId());
			else 
				jLabel = new JLabel(value.toString());
			if (isSelected) {
				jLabel.setBackground(Color.BLACK);
				jLabel.setForeground(Color.RED);
			}
			return jLabel;
		}
	}
	


}
