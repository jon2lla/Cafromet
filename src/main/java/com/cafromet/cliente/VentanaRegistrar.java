package com.cafromet.cliente;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaRegistrar extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldPassword;
	private JTextField textFieldPassword2;
	private JButton btnRegistrar;
	private JButton btnCancelar;

	
	//get-set
	public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}

	public void setTextFieldNombre(JTextField textFieldNombre) {
		this.textFieldNombre = textFieldNombre;
	}

	public JTextField getTextFieldPassword() {
		return textFieldPassword;
	}

	public void setTextFieldPassword(JTextField textFieldPassword) {
		this.textFieldPassword = textFieldPassword;
	}

	public JTextField getTextFieldPassword2() {
		return textFieldPassword2;
	}

	public void setTextFieldPassword2(JTextField textFieldPassword2) {
		this.textFieldPassword2 = textFieldPassword2;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	//constructor
	public void VentRegistrar() {

		VentanaRegistrar ventanaRegistrar = new VentanaRegistrar();
		ventanaRegistrar.setVisible(true);
		
		ControladorRegistrar controladorRegistrar = new ControladorRegistrar(ventanaRegistrar);
		
	}

	public VentanaRegistrar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(85, 26, 83, 14);
		contentPane.add(lblNewLabel);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(85, 54, 177, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblIntroduceContrasea = new JLabel("Introduce Contraseña:");
		lblIntroduceContrasea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIntroduceContrasea.setBounds(85, 94, 177, 14);
		contentPane.add(lblIntroduceContrasea);

		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(85, 114, 177, 20);
		contentPane.add(textFieldPassword);

		JLabel lblRepiteContrasea = new JLabel("Repite Contraseña:");
		lblRepiteContrasea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRepiteContrasea.setBounds(85, 154, 177, 14);
		contentPane.add(lblRepiteContrasea);

		textFieldPassword2 = new JTextField();
		textFieldPassword2.setColumns(10);
		textFieldPassword2.setBounds(85, 176, 177, 20);
		contentPane.add(textFieldPassword2);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(53, 230, 89, 23);
		contentPane.add(btnRegistrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(222, 230, 89, 23);
		contentPane.add(btnCancelar);
	}

}
