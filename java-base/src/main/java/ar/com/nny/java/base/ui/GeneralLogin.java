package ar.com.nny.java.base.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ar.com.nny.java.base.bean.Usuario;
import ar.com.nny.java.base.dao.GenericDao;
import ar.com.nny.java.base.exception.NonBusinessException;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class GeneralLogin extends JFrame{

	private JLabel nombre = new JLabel("Nombre de Usuario :"); 
	private JLabel password =new JLabel("Password :");
	private JTextField nombret = new JTextField();;
	private JPasswordField pass = new JPasswordField();
	private JButton aceptar = new JButton(new ImageIcon("./Images/check.jpg"));
	private JButton cancelar = new JButton(new ImageIcon("./Images/cancel.jpg"));
	private DefaultFormBuilder panel;
//	private List<Usuario> listUsuarios;
	private JLabel notifyError = new JLabel();
	private JLabel confirmaContrasenia = new JLabel("Confirmacion de contrasenia"); 
	private JPasswordField confirmacon= new JPasswordField();
	protected GenericDao<Usuario> dao = new GenericDao<Usuario>(Usuario.class, "Usuario");
	 
	public GeneralLogin(){
//		 this.listUsuarios = new ArrayList<Usuario>(); 
//		 listUsuarios.add(new Usuario("susy","1234"));
//		 listUsuarios.add(new Usuario("ronny","1234"));
		 this.init();
		 this.setVisible(true);
		 this.pack();
		 this.setLocation(350, 330);
	 }
	public void agregarComponentes(){
		panel.append(nombre,nombret);
		panel.append(password, pass);
		
	}
	
	public void addActions(){
		cancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}

		});
		
	}
	
	protected void onCancel() {
		System.exit(0);
	}

	public void init(){
		this.panel= new DefaultFormBuilder(new FormLayout("p, 2dlu, p:g"));
		aceptar.setText("Aceptar");
		cancelar.setText("Cancelar");
		notifyError.setForeground(Color.RED);
	}
	public void limpiar(){
		nombret.setText("");
		pass.setText("");
	}
	public Usuario buscarUsuario(String nombre){
//		 boolean buscando = false;
//			for (Usuario usuario : this.listUsuarios) {
//				if (usuario.getNombre().equals(nombre)){
//					buscando = true;
//					return buscando;
//				}
//				
//			}
//			return buscando;
		Usuario byId=null;
		try {
			byId = dao.getById(nombre);			
		} catch (NonBusinessException e) {
		}
		return byId;
		}
//	public String buscarcontrasenia(String nombre){
//		 String buscando = "";				
//		 for (Usuario usuario : this.listUsuarios) {
//			 if (usuario.getNombre().equals(nombre)){
//					return usuario.getPass();
//					}
//					
//				}
//				return buscando;
//	}
	public boolean verificarCamposVacios(){
		return nombret.getText().isEmpty() | pass.getText().isEmpty();
	}
	
	public JLabel getNombre() {
		return nombre;
	}

	public void setNombre(JLabel nombre) {
		this.nombre = nombre;
	}

	public JLabel getPassword() {
		return password;
	}

	public void setPassword(JLabel password) {
		this.password = password;
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

//	public List<Usuario> getListUsuarios() {
//		return listUsuarios;
//	}
//
//	public void setListUsuarios(List<Usuario> listUsuarios) {
//		this.listUsuarios = listUsuarios;
//	}
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
