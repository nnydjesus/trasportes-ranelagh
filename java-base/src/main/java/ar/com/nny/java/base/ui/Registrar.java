package ar.com.nny.java.base.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ar.com.nny.java.base.bean.Usuario;
import ar.com.nny.java.base.utils.HashUtils;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

@SuppressWarnings("deprecation")
public class Registrar extends GeneralLogin {
	private static final long serialVersionUID = -2516849884023358109L;

	public Registrar() {
		super();
	this.setSize(350, 160);	
	}

	public void init() {
		this.setTitle("Registrarse");
		super.init();
		this.agregarComponentes();
		this.getPanel().append(this.getConfirmaContrasenia(),
				this.getConfirmacon());
		this.addActions();
		this.getPanel().append(this.getAceptar(), this.getCancelar());
		DefaultFormBuilder defaultFormBuilder = new DefaultFormBuilder(new FormLayout("pref, pref"));
		defaultFormBuilder.append(this.getPanel().getPanel());
		defaultFormBuilder.append(this.getNotifyError());
		this.add(defaultFormBuilder.getPanel());
	}

	public void agregarComponentes() {
		super.agregarComponentes();

	}

	@Override
	public void addActions() {
		this.getAceptar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nombrer = getNombret().getText();
				String passwordr = getPass().getText();
				String passwordv = getConfirmacon().getText();
				if (verificarCamposVacios()
						| getConfirmacon().getText().isEmpty()) {
					//Registrar.this.limpiar();
					Registrar.this.getNotifyError().setText("");
					JOptionPane.showMessageDialog(Registrar.this.getAceptar(),
							"Debe de completar todos los campos.");
				} else {
					if (buscarUsuario(nombrer) == null) {
						if (passwordr.equals(passwordv)) {
							Usuario usuarionew = new Usuario(nombrer,HashUtils.hash(passwordr));
							usuarionew.setConectado(false);
							onSubmit(usuarionew);
							Registrar.this.limpiar();
							Registrar.this.getNotifyError().setText("");
							JOptionPane.showMessageDialog(Registrar.this
									.getConfirmacon(),
									"se ha registrado con exito.");

						} else {
							Registrar.this.limpiar();
							Registrar.this.getNotifyError().setText(
									"Contraseñas no coinciden.");
						}
					} else {
						Registrar.this.limpiar();
						Registrar.this.getNotifyError().setText(
								"El usuario ya existe.");
					}

				}
			}
		});
		super.addActions();
	
	}

	
	protected void onSubmit(Usuario usuarionew) {
		dao.save(usuarionew);
	}
	
	public void limpiar() {
		super.limpiar();
		this.getConfirmacon().setText("");

	}

	public static void main(String[] args) {
		new Registrar();
	}
}
