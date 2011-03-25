package ar.com.nny.base.ui.swing.components;

import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

@SuppressWarnings({ "unused", "unchecked" })
public class MyJComboBox extends JComboBox {

    private static final long serialVersionUID = 1L;

    private String method;

    private Vector vector = new Vector();

    public MyJComboBox() {
    }

    public MyJComboBox(final List<?> list) {
        super();
        this.update(list);
        // this.setRenderer(new MyRenderer());
        this.setModel(new DefaultComboBoxModel(vector));
    }

    public void update(final List<?> list) {
        vector.removeAll(vector);
        vector.addAll(list);
    }

    @Override
    public void setSelectedItem(final Object object) {
        super.setSelectedItem(object);
    }

    @Override
    public Object getSelectedItem() {
        return super.getSelectedItem();
    }

    public void addDefaultValue(final Object value) {
        vector.add(0, value);
        this.setSelectedIndex(0);
    }

    public void selectedDefault() {
        this.setSelectedIndex(0);
    }

    // class MyRenderer extends DefaultListCellRenderer{
    // @Override
    // public Component getListCellRendererComponent(JList list, Object value,
    // int index, boolean isSelected, boolean cellHasFocus) {
    // JLabel jLabel = new JLabel();
    // if(value == null)
    // return jLabel;
    // jLabel.setText(((Observable) value).mostrar());
    // // if(value instanceof Observable)
    // // jLabel = new JLabel(((Observable) value).getId());
    // // else
    // // jLabel = new JLabel(value.toString());
    // if (isSelected) {
    // jLabel.setBackground(Color.BLACK);
    // jLabel.setForeground(Color.RED);
    // }
    // return jLabel;
    // }
    // }

}
