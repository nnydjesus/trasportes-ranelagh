package utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Ronny
 *
 */
public abstract class  MouseClicked implements MouseListener  {
	
	public MouseClicked() {
		// TODO Auto-generated constructor stub
	}
	
    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     */
    public abstract void mouseClicked(MouseEvent e);

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {	
	}

}
