package com.cafromet.cliente;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class VentanaCliente extends JFrame {
	private VentanaCliente ventanaCliente;
	private JPanel contentPane;
	private JButton btnEnviar;
	private JTextField textFieldUsuario;
	private JTextField textFieldPassw;
	private JButton btnRegistrar;
	
	//get-set	
	public JButton getbtnEnviar() {
		return btnEnviar;
	}

	public JTextField getTextFieldUsuario() {
		return textFieldUsuario;
	}

	public JTextField getTextFieldPassw() {
		return textFieldPassw;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public void setBtnRegistrar(JButton btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
	}

	//controlador
	@SuppressWarnings("unused")
	public void inicioVentaCliente() {
		
		ventanaCliente = new VentanaCliente();
		ventanaCliente.setVisible(true);
		
		ControladorCliente controladorCliente = new ControladorCliente(ventanaCliente);
		
	}

	public VentanaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(850, 400, 341, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(52, 176, 89, 23);
		contentPane.add(btnEnviar);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(91, 60, 143, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		textFieldPassw = new JTextField();
		textFieldPassw.setColumns(10);
		textFieldPassw.setBounds(91, 119, 143, 20);
		contentPane.add(textFieldPassw);
		
		JLabel lblLogin = new JLabel("NOMBRE");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLogin.setBounds(91, 32, 94, 17);
		contentPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("CONTRASEÑA");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(91, 91, 143, 17);
		contentPane.add(lblPassword);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(191, 176, 89, 23);
		contentPane.add(btnRegistrar);
	}

}
