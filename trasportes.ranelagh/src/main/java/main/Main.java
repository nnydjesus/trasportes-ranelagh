package main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;

import ar.com.syr.transportes.ui.LoginUser;


	
public class Main implements Runnable {


	@Override
	public void run() {
		try {
			UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
			new LoginUser();
		} catch (Exception e) {
		}

	}

	public static void main(String[] args) {
//		JFrame.setDefaultLookAndFeelDecorated(true);
//		TransportesRanelagh reproductor = new TransportesRanelagh(new PreInicio());
		Main main = new Main();
		EventQueue.invokeLater(main);
//		new Login();
//		HibernateUtil.getSession();
	}

}
