package ar.com.syr.transportes.ui;

import main.TransportesRanelagh;
import ar.com.nny.base.ui.swing.components.Login;

public class LoginUser extends Login {

    private static final long serialVersionUID = 1L;

    @Override
    protected void onSubmit(final String nombreIngresado) {
        this.setVisible(false);
        this.dispose();
        // new TransportesRanelagh(new PreInicio());
        new TransportesRanelagh().setVisible(true);
    }

    @Override
    public void onCancel() {
        super.onCancel();
        System.exit(DO_NOTHING_ON_CLOSE);
    }
}
