package main;

import javax.swing.UIManager;

import org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel;

import ar.com.syr.trasportes.ui.Login;
import ar.com.syr.trasportes.ui.PreInicio;

	
public class Main implements Runnable {

	@Override
	public void run() {
		try {
			TransportesRanelagh reproductor = new TransportesRanelagh(new PreInicio());
			UIManager.setLookAndFeel(new SubstanceRavenGraphiteGlassLookAndFeel());
		} catch (Exception e) {
		}

	}

	public static void main(String[] args) {
//		JFrame.setDefaultLookAndFeelDecorated(true);
//		TransportesRanelagh reproductor = new TransportesRanelagh(new PreInicio());
//		Main main = new Main();
//		EventQueue.invokeLater(main);
		new Login();
	}

}
