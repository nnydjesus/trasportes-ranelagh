package ar.com.syr.transportes.ui;

import main.TransportesRanelagh;
import ar.com.nny.base.ui.swing.components.Login;


public class LoginUser extends Login{
	
	@Override
	protected void onSubmit(String nombreIngresado) {
		setVisible(false);
		dispose();
//		new TransportesRanelagh(new PreInicio());
		new TransportesRanelagh().setVisible(true);
	}
	
	@Override
	public void onCancel() {
		super.onCancel();
		System.exit(DO_NOTHING_ON_CLOSE);
	}
}
