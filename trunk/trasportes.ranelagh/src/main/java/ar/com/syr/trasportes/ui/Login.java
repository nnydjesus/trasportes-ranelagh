package ar.com.syr.trasportes.ui;

import main.TransportesRanelagh;


public class Login extends ar.com.nny.java.base.ui.Login{
	
	@Override
	protected void onSubmit(String nombreIngresado) {
		setVisible(false);
		dispose();
		new TransportesRanelagh(new PreInicio());
	}
}
