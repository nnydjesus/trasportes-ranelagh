package ar.com.nny.java.base.ui;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Preloader extends JFrame implements Runnable {
	private static final int ANCHO = 625, ALTO = 413;

	private Image fondo = null;
	private Image icono = null;
	private int progreso = 0;
	private Container jframe;

	public void paint(Graphics fGraphics) {
		fGraphics.drawImage(fondo, 0, 0, this);
		fGraphics.setColor(Color.GREEN);
		fGraphics.setFont(new Font("Arial", Font.BOLD, 19));
		fGraphics
				.drawString("Cargando ...", (ANCHO / 2) - 95, ALTO / 2);
		fGraphics.fillRect((ANCHO / 2) - 100, ALTO / 2 + 30, progreso, 30);
		fGraphics.setColor(Color.BLACK);
		fGraphics.drawRect((ANCHO / 2) - 101, ALTO / 2 + 29, 201, 32);

	}
	public Preloader() {
		this.loadImages();
		Dimension screenSize = super.getToolkit().getScreenSize();
		super.setResizable(false);
		super.setUndecorated(true);
		this.setIconImage(new ImageIcon(getLogo()).getImage());
		this.setLocation((screenSize.width - ANCHO) / 2,
				(screenSize.height - ALTO) / 2);
		this.setSize(ANCHO, ALTO);
		super.setIconImage(icono);
		this.setVisible(true);
		this.start();
	}
	
	public Preloader(String title) {
		this();
		this.setTitle(title);
	}

	protected String getLogo() {
		return "./Images/logo.jpg";
	}

	public Preloader(Container jframe,String title) {
		this(title);
		this.setJframe(jframe);

	}

	public void run() {
//		AudioClip inicio = null;
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
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		getJframe().setVisible(true);
		this.setVisible(false);
		this.dispose();
	}

	protected void loadImages() {
		fondo = new ImageIcon(getImageLoader()).getImage();
		icono = new ImageIcon(iconImage()).getImage();
	}

	protected String getImageLoader() {
		return "./Images/trasporte.jpg";
	}
	
	public void start() {
		new Thread(this).start();
	}

	public String iconImage() {
		return getLogo();
	}

	public void setJframe(Container jframe) {
		this.jframe = jframe;
	}

	public Container getJframe() {
		return jframe;
	}
	
	public static void main(String[] args) {
		new Preloader(null, "Sample").start();
	}
}
