package ar.com.nny.base.ui.styles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.LookAndFeel;

import org.jvnet.substance.SubstanceLegacyDefaultLookAndFeel;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.skin.SubstanceMagellanLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceEmeraldDuskLookAndFeel;
import org.jvnet.substance.skin.SubstanceMagmaLookAndFeel;
import org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel;
import org.jvnet.substance.skin.SubstanceSaharaLookAndFeel;
import org.jvnet.substance.skin.SubstanceTwilightLookAndFeel;

import ar.com.nny.base.ui.FrameLook;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {

	private JMenu menuArchivo;
	private JMenu menuconfiguracion;
	private JMenu aparienciaItem;
	private JMenuItem lookTwilight;
	private JMenuItem lookLegacy;
	private JMenuItem lookBusinessBlackSteel;
	private JMenuItem lookEmeraldDus;
	private JMenuItem lookRavenGraphiteGlass;
	private FrameLook parent;
	
	private SubstanceLookAndFeel substanceTwilightLookAndFeel;
	private SubstanceLookAndFeel substanceLegacyDefaultLookAndFeel;
	private SubstanceLookAndFeel substanceRavenGraphiteGlassLookAndFeel;
	private SubstanceLookAndFeel substanceBusinessBlackSteelLookAndFeel;
	private SubstanceLookAndFeel substanceEmeraldDuskLookAndFeel;
	private JMenuItem lookMagellan;
	private SubstanceLookAndFeel substanceSaharaLokAndFeel;
	private SubstanceMagellanLookAndFeel substanceMagellanLokAndFeel;
	private SubstanceMagmaLookAndFeel substanceMagmaLokAndFeel;
	private JMenuItem lookMagma;
	private JMenuItem lookSahara;

	public Menu(FrameLook reproductor) {
		this.parent = reproductor;
		this.menuArchivo = new JMenu("Archivo");
		this.menuconfiguracion = new JMenu("Configuracion");
		this.aparienciaItem = new JMenu("Apariencia");
		this.lookBusinessBlackSteel= new JMenuItem("BusinessBlackSteel");
		this.lookEmeraldDus = new JMenuItem("EsmeraldDus");
		this.lookLegacy = new JMenuItem("Legacy");
		this.lookRavenGraphiteGlass = new JMenuItem("RavenGraphiteGlass");
		this.lookTwilight = new JMenuItem("Twilight");
		this.lookMagellan = new JMenuItem("Magellan");
		this.lookMagma = new JMenuItem("Magma");
		this.lookSahara = new JMenuItem("Sahara");
		this.substanceBusinessBlackSteelLookAndFeel = new SubstanceBusinessBlackSteelLookAndFeel();
		this.substanceEmeraldDuskLookAndFeel = new SubstanceEmeraldDuskLookAndFeel();
		this.substanceLegacyDefaultLookAndFeel = new SubstanceLegacyDefaultLookAndFeel();
		this.substanceRavenGraphiteGlassLookAndFeel = new SubstanceRavenGraphiteGlassLookAndFeel();
		this.substanceTwilightLookAndFeel = new SubstanceTwilightLookAndFeel();
		this.substanceMagmaLokAndFeel = new SubstanceMagmaLookAndFeel();
		this.substanceMagellanLokAndFeel = new SubstanceMagellanLookAndFeel();
		this.substanceSaharaLokAndFeel = new SubstanceSaharaLookAndFeel();
		
		this.init();
		this.addActions();
		//lookBusinessBlackSteel.doClick();

	}

	private void init() {
		this.menuconfiguracion.add(this.aparienciaItem);
		this.aparienciaItem.add(lookBusinessBlackSteel);
		this.aparienciaItem.add(lookEmeraldDus);
		this.aparienciaItem.add(lookLegacy);
		this.aparienciaItem.add(lookRavenGraphiteGlass);
		this.aparienciaItem.add(lookTwilight);
		this.aparienciaItem.add(lookMagellan);
		this.aparienciaItem.add(lookMagma);
		this.aparienciaItem.add(lookSahara);
		this.add(this.menuArchivo);
		this.add(this.menuconfiguracion);		
	}
	
	public void addActions(){
		this.lookBusinessBlackSteel.addActionListener(new AparienciaListener(substanceBusinessBlackSteelLookAndFeel));
		this.lookEmeraldDus.addActionListener(new AparienciaListener(substanceEmeraldDuskLookAndFeel));
		this.lookLegacy.addActionListener(new AparienciaListener(substanceLegacyDefaultLookAndFeel));
		this.lookRavenGraphiteGlass.addActionListener(new AparienciaListener(substanceRavenGraphiteGlassLookAndFeel));
		this.lookTwilight.addActionListener(new AparienciaListener(substanceTwilightLookAndFeel));
		this.lookSahara.addActionListener(new AparienciaListener(substanceSaharaLokAndFeel));
		this.lookMagellan.addActionListener(new AparienciaListener(substanceMagellanLokAndFeel));
		this.lookMagma.addActionListener(new AparienciaListener(substanceMagmaLokAndFeel));

	}
	
	


	
	class AparienciaListener implements ActionListener{
		
		private LookAndFeel look;

		public AparienciaListener(LookAndFeel look) {
			this.look = look;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			parent.setLook(look);
		}
	}


}
