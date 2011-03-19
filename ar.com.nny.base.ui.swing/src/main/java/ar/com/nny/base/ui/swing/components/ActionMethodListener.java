package ar.com.nny.base.ui.swing.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.com.nny.base.utils.ReflectionUtils;

public class ActionMethodListener implements ActionListener {
	
	private String method;
	private Object object;

	public ActionMethodListener(Object object, String method) {
		this.object = object;
		this.method = method;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ReflectionUtils.invokeMethod(object, method);
	}

}
