package ui;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Ronny
 *
 */
@SuppressWarnings("serial")
public class GeneralPanel extends JPanel {
	
	public GeneralPanel(LayoutManager layout) {
		super(layout);
	}
	public GeneralPanel() {
		super();
	}

	public void addBoton( String nombre, ActionListener listener) {
		JButton button = new JButton(nombre);
		button.addActionListener(listener);
		this.add(button);
	}

	public void addLabel(String labelText, int center) {
		this.add(new JLabel(labelText, center));
	}
	protected void addTextField(String name, String style){
		this.add(new JTextField(name), style);
	}
	
	protected void addComponent(Component component, String style){
		this.add(component, style);
	}
}