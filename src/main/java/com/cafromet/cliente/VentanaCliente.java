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



	//controlador
	@SuppressWarnings("unused")
	public void inicioVentaCliente() {
		
		ventanaCliente = new VentanaCliente();
		ventanaCliente.setVisible(true);
		
		ControladorCliente controladorCliente = new ControladorCliente(ventanaCliente);
		
	}

	public VentanaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(207, 175, 89, 23);
		contentPane.add(btnEnviar);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(207, 56, 143, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		textFieldPassw = new JTextField();
		textFieldPassw.setColumns(10);
		textFieldPassw.setBounds(207, 113, 143, 20);
		contentPane.add(textFieldPassw);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setBounds(91, 59, 94, 17);
		contentPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(74, 116, 123, 17);
		contentPane.add(lblPassword);
	}

}
