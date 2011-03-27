package ar.com.syr.transportes.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.TransportesRanelagh;
import ar.com.nny.base.generator.DDLGenerator;
import ar.com.nny.base.ui.swing.components.Login;

public class LoginUser extends Login {

    private static final long serialVersionUID = 1L;

    public LoginUser() {
        super();
        JButton comp = new JButton("Generar base");
        comp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                DDLGenerator.main();
            }
        });
        this.add(comp);
    }

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
