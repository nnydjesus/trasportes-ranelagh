package ar.com.nny.java.base.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ar.com.nny.java.base.bean.Usuario;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class Login extends GeneralLogin {
	private Usuario user;

	public Login() {
		super();
	this.setSize(300, 130);
	}

	public void init() {
		this.setTitle("Login");
		super.init();
		super.agregarComponentes();
		this.getPanel().append(this.getAceptar(), this.getCancelar());
		this.addActions();
		DefaultFormBuilder defaultFormBuilder = new DefaultFormBuilder(new FormLayout("pref, pref"));
		defaultFormBuilder.append(this.getPanel().getPanel());
		defaultFormBuilder.append(this.getNotifyError());
		this.add(defaultFormBuilder.getPanel());

	}

	public void addActions() {

		this.getAceptar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (verificarCamposVacios()) {
					JOptionPane.showMessageDialog(Login.this.getAceptar(),"Debe completar todos los campos.");
				} else {
					String nombreIngresado = getNombret().getText();
					String passwordIngresado = getPass().getText();
					user = buscarUsuario(nombreIngresado);
					if (user != null) {
						if (inOn()) {
							getNotifyError().setText("Ya estas logeado.");
						} else {
							if (user.checkPassword(passwordIngresado)) {
								onSubmit(nombreIngresado);
								limpiar();
								// tengo que modificar el campo conectado del
								// usuario
								// llamar a la pantalla principal
							} else {
								getNotifyError().setText("Contraseña incorrecta.");
						
							}

						}

					} else {
						getNotifyError().setText("Usuario Incorrecto.");
					}

				}
			}

		});
		super.addActions();

	}

	protected void onSubmit(String nombreIngresado) {
		Login.this.getNotifyError().setText("Bienvenido  "+ nombreIngresado);
	}

	public boolean inOn() {
		return user.isConectado();
	}

	public static void main(String[] args) {
		new Login();
	}
}
