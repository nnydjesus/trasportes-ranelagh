package ar.com.nny.base.ui;

import javax.swing.JOptionPane;

import ar.com.nny.base.bean.Usuario;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

@SuppressWarnings("serial")
public class Login extends GeneralLogin {
	private Usuario user;

	public Login() {
		super();
		this.setSize(250, 150);	
		this.setVisible(true);
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
		super.addActions();

	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onClickAceptar() {
		if (verificarCamposVacios()) 
			JOptionPane.showMessageDialog(getAceptar(),"Debe completar todos los campos.");
		else {
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
					} else 
						getNotifyError().setText("Contraseña incorrecta.");
				}

			} else 
				getNotifyError().setText("Usuario Incorrecto.");
		}
		
	}
	
	@Override
	protected void onCancel() {
		super.onCancel();
		dispose();
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
