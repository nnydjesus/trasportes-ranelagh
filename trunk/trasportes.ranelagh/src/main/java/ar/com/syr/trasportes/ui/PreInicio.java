package ar.com.syr.trasportes.ui;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class PreInicio extends JFrame implements Runnable {
	private static final int ANCHO = 400, ALTO = 247;

	private Image fondo = null;
	private Image icono = null;
	private int progreso = 0;
	private Container jframe;

	public void paint(Graphics fGraphics) {
		fGraphics.drawImage(fondo, 0, 0, this);
		fGraphics.setColor(Color.yellow.darker());
		fGraphics.setFont(new Font("Arial", Font.BOLD, 19));
		fGraphics
				.drawString("Cargando imagenes...", (ANCHO / 2) - 95, ALTO / 2);
		fGraphics.fillRect((ANCHO / 2) - 100, ALTO / 2 + 30, progreso, 30);
		fGraphics.setColor(Color.BLACK);
		fGraphics.drawRect((ANCHO / 2) - 101, ALTO / 2 + 29, 201, 32);

	}

	public PreInicio(Container jframe) {
		super("Corazones");
		this.jframe = jframe;

		this.loadImages();

		Dimension screenSize = super.getToolkit().getScreenSize();
		super.setResizable(false);
		super.setUndecorated(true);
		this.setIconImage(new ImageIcon("./Images/classroom_assignment_background.jpg").getImage());
		this.setLocation((screenSize.width - ANCHO) / 2,
				(screenSize.height - ALTO) / 2);
		this.setSize(ANCHO, ALTO);
		super.setIconImage(icono);
		this.setVisible(true);
		new Thread(this).start();
	}

	public void run() {
		AudioClip inicio = null;
//		try {
//			inicio = Applet.newAudioClip(new URL("file:"
//					+ UICommonUtils.PATH_MUSIC + "inicio.wav"));
//		} catch (MalformedURLException e1) {
//			throw new RuntimeException(e1);
//		}
//		inicio.play();
		for (int i = 0; i <= 40; i++) {
			progreso = (i * 5);
			repaint();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		jframe.setVisible(true);
		this.setVisible(false);
		this.dispose();
	}

	private void loadImages() {
		fondo = new ImageIcon("./Images/classroom_assignment_background.jpg").getImage();
//		icono = new ImageIcon(UICommonUtils.PATH + "icono.PNG")
//				.getImage();
	}

	public Image getIconImage() {
		return icono;
	}

	public static void main(String[] args) {
		new PreInicio(null);
	}
}
