package ar.com.nny.base.ui.swing.components;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;

import ar.com.nny.base.common.Observable;


@SuppressWarnings({ "unused", "serial","unchecked" })
public class MyJComboBox extends JComboBox {
	
	private String method;
	private Vector vector = new Vector();


	public MyJComboBox() {
	}

	public MyJComboBox(List<?> list) {
        super();
		update(list);
//		this.setRenderer(new MyRenderer());
        setModel(new DefaultComboBoxModel(vector));
	}

	public void update(List<?> list) {
		vector.removeAll(vector);
		vector.addAll(list);		
	}

	@Override
	public void setSelectedItem(Object object){
		super.setSelectedItem(object);
	}
	
	@Override
	public Object getSelectedItem() {
		return super.getSelectedItem();
	}
	
	public void addDefaultValue(Object value) {
		vector.add(0, value);
		setSelectedIndex(0);
	}
	public void selectedDefault() {
		setSelectedIndex(0);
	}
	
	
 
//	class MyRenderer extends DefaultListCellRenderer{
//		@Override
//		public Component getListCellRendererComponent(JList list, Object value,
//				int index, boolean isSelected, boolean cellHasFocus) {
//			JLabel jLabel = new JLabel();
//			if(value == null)
//				return jLabel;
//			jLabel.setText(((Observable) value).mostrar());
////			if(value instanceof Observable)
////				jLabel = new JLabel(((Observable) value).getId());
////			else 
////				jLabel = new JLabel(value.toString());
//			if (isSelected) {
//				jLabel.setBackground(Color.BLACK);
//				jLabel.setForeground(Color.RED);
//			}
//			return jLabel;
//		}
//	}
	


}
