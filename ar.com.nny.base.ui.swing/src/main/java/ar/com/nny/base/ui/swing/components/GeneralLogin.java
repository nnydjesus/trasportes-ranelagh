package ar.com.nny.base.ui.swing.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ar.com.nny.base.bean.Usuario;
import ar.com.nny.base.dao.GenericDao;
import ar.com.nny.base.exception.NonBusinessException;
import ar.com.nny.base.utils.Path;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

@SuppressWarnings("serial")
public abstract class GeneralLogin extends JFrame{

//	private JLabel nombre = new JLabel("Nombre de Usuario :"); 
//	private JLabel password =new JLabel("Password :");
	private JTextField nombret = new JTextField();;
	private JPasswordField pass = new JPasswordField();
	private JButton aceptar = new JButton(new ImageIcon(Path.path()+"Images/check.jpg"));
	private JButton cancelar = new JButton(new ImageIcon(Path.path()+"Images/cancel.jpg"));
	private DefaultFormBuilder panel;
	private JLabel notifyError = new JLabel();
	private JLabel confirmaContrasenia = new JLabel("Confirmacion de contrasenia"); 
	private JPasswordField confirmacon= new JPasswordField();
	protected GenericDao<Usuario> dao = new GenericDao<Usuario>(Usuario.class, "Usuario");
	 
	public GeneralLogin(){
		 this.init();
		 this.pack();
		 this.setLocation(350, 330);
	 }
	public void agregarComponentes(){
		panel.append("Nombre de Usuario :",nombret);
		panel.append("Password :", pass);
		
	}
	
	public void addActions(){
		cancelar.addActionListener(new ActionMethodListener(this, "onCancel"));
		
		aceptar.addActionListener(new ActionMethodListener(this, "onClickAceptar"));
		pass.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					onClickAceptar();
			}
		});
		
	}
	
	public abstract void onClickAceptar();
	
	public void onCancel() {
		this.setVisible(false);
	}

	public void init(){
		this.panel= new DefaultFormBuilder(new FormLayout("pref, 4dlu, pref"));
		aceptar.setText("Aceptar");
		cancelar.setText("Cancelar");
		notifyError.setForeground(Color.RED);
	}
	public void limpiar(){
		nombret.setText("");
		pass.setText("");
	}
	public Usuario buscarUsuario(String nombre){
		Usuario byId=null;
		try {
			byId = dao.getById(nombre);			
		} catch (NonBusinessException e) {
		}
		return byId;
		}

	@SuppressWarnings("deprecation")
	public boolean verificarCamposVacios(){
		return nombret.getText().isEmpty() | pass.getText().isEmpty();
	}
	
	public JTextField getNombret() {
		return nombret;
	}

	public void setNombret(JTextField nombret) {
		this.nombret = nombret;
	}

	public JPasswordField getPass() {
		return pass;
	}

	public void setPass(JPasswordField pass) {
		this.pass = pass;
	}

	public JButton getAceptar() {
		return aceptar;
	}

	public void setAceptar(JButton aceptar) {
		this.aceptar = aceptar;
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public void setCancelar(JButton cancelar) {
		this.cancelar = cancelar;
	}

	public DefaultFormBuilder getPanel() {
		return panel;
	}

	public void setPanel(DefaultFormBuilder panel) {
		this.panel = panel;
	}

	public void setNotifyError(JLabel notifyError) {
		this.notifyError = notifyError;
	}
	public JLabel getNotifyError() {
		return notifyError;
	}
	public void setConfirmaContrasenia(JLabel confirmaContrasenia) {
		this.confirmaContrasenia = confirmaContrasenia;
	}
	public JLabel getConfirmaContrasenia() {
		return confirmaContrasenia;
	}
	public void setConfirmacon(JPasswordField confirmacon) {
		this.confirmacon = confirmacon;
	}
	public JPasswordField getConfirmacon() {
		return confirmacon;
	}

}