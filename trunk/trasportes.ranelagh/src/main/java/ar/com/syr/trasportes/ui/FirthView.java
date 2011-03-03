package ar.com.syr.trasportes.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Tree;

import com.jgoodies.forms.builder.ButtonStackBuilder;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class FirthView extends JFrame {
	
	private ButtonStackBuilder panelBotones;
	private JPanel panelTree;
	private JButton tablas;
	private JButton administracion;
	private JButton operaciones;
	private JButton controDeUnidades;
	private JButton viajes;
	private JButton bultos;
	private Tree tr;
	
	public FirthView() {
		
		this.setTitle("Transportes Ranelagh");
		this.setLayout(new BorderLayout());
		this.panelBotones = new ButtonStackBuilder();
		this.panelTree = new JPanel();
		this.tablas = new JButton(new ImageIcon("./Images/resources.jpg"));
		this.tablas.setText("Tablas");
		this.administracion = new JButton("Administracion");
		this.operaciones = new JButton("Operaciones");
		this.controDeUnidades = new JButton("ControlDeUnidades");
		panelBotones.addGridded(tablas);
		panelBotones.addGridded(administracion);
		panelBotones.addGridded(operaciones);
		panelBotones.addGridded(controDeUnidades);	
		this.add(BorderLayout.WEST,panelBotones.getPanel());
		this.addActions();
		this.pack();
		this.setSize(600,400);
		this.setVisible(true);

	}
	public void addActions(){
		
	tablas.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new Tree();
			

		}
	});
	
	administracion.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
		}
	});
	
	}
	public static void main(String[] args) {
	    new FirthView();
	}


}