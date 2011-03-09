package ar.com.syr.transportes.ui;

import main.TransportesRanelagh;
import ar.com.nny.base.ui.Login;


@SuppressWarnings("serial")
public class LoginUser extends Login{
	
	@Override
	protected void onSubmit(String nombreIngresado) {
		setVisible(false);
		dispose();
		new TransportesRanelagh(new PreInicio());
	}
	
	@Override
	protected void onCancel() {
		super.onCancel();
		System.exit(DO_NOTHING_ON_CLOSE);
	}
}
