package ar.com.nny.base.ui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import ar.com.nny.base.utils.Path;

public class TopPanel extends GeneralPanel{
	private static final long serialVersionUID = 1L;

	private JButton btImprimir;
	private JButton btCerrar;
	
	public TopPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		btImprimir = new JButton(new ImageIcon(Path.path()+"Images/assignments.jpg"));
		btCerrar = new JButton(new ImageIcon(Path.path()+"Images/cancel.jpg"));
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
