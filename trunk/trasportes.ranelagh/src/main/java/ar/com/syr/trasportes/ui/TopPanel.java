package ar.com.syr.trasportes.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TopPanel extends GeneralPanel{
	private static final long serialVersionUID = 1L;

	private JButton btImprimir;
	private JButton btCerrar;
	
	public TopPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		btImprimir = new JButton(new ImageIcon("./Images/assignments.jpg"));
		btCerrar = new JButton(new ImageIcon("./Images/cancel.jpg"));
		this.add(btImprimir, BorderLayout.CENTER);
		this.add(btCerrar, BorderLayout.WEST);
	}

	public void setBtCerrar(JButton btCerrar) {
		this.btCerrar = btCerrar;
	}

	public JButton getBtCerrar() {
		return btCerrar;
	}

	public void setBtImprimir(JButton btImprimir) {
		this.btImprimir = btImprimir;
	}

	public JButton getBtImprimir() {
		return btImprimir;
	}
	

}
