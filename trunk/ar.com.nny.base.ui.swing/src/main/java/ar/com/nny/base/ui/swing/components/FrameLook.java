package ar.com.nny.base.ui.swing.components;

import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ar.com.nny.base.ui.swing.components.menu.styles.Menu;


public class FrameLook extends JFrame{
	
	protected LookAndFeel look;
	
	public FrameLook() {
		this.setJMenuBar(new Menu(this));
	}
	
	protected void setLoader(Preloader loader){
		loader.start();
	}

	public void setLook(LookAndFeel look2) {
		this.look = look2;
		Runnable runnable = new Runnable() {
			public void run() {
				try {						
						updateUI();
					} catch (UnsupportedLookAndFeelException ex) {
						new RuntimeException(ex);
				}
			}

		};
		SwingUtilities.invokeLater(runnable);
	}
	protected void updateUI() throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(FrameLook.this.look);
		SwingUtilities.updateComponentTreeUI(FrameLook.this.getParent());
	}

}
