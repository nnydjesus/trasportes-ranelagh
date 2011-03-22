package ar.com.nny.base.ui.swing.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.w3c.dom.events.MouseEvent;

import ar.com.nny.base.utils.ReflectionUtils;

public class ActionMethodListener implements ActionListener, ChangeListener, MouseListener,
                                              KeyListener, FocusListener {
    
	
	private String method;
	private Object object;
    private Object[] args;

	public ActionMethodListener(Object object, String method, Object...args) {
		this.object = object;
		this.method = method;
		this.args = args;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ReflectionUtils.invokeMethod(object, method);
	}

    @Override
    public void stateChanged(ChangeEvent e) {
        ReflectionUtils.invokeMethod(object, method);        
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
//        ReflectionUtils.invokeMethod(object, method);        
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
//        ReflectionUtils.invokeMethod(object, method);        
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
//        ReflectionUtils.invokeMethod(object, method);        
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
//        ReflectionUtils.invokeMethod(object, method);        
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
//        ReflectionUtils.invokeMethod(object, method);        
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        ReflectionUtils.invokeMethod(object, method);        
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        ReflectionUtils.invokeMethod(object, method);        
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        ReflectionUtils.invokeMethod(object, method);        
    }

    @Override
    public void focusGained(FocusEvent e) {
        ReflectionUtils.invokeMethod(object, method);        
    }

    @Override
    public void focusLost(FocusEvent e) {
        ReflectionUtils.invokeMethod(object, method);        
    }

}
