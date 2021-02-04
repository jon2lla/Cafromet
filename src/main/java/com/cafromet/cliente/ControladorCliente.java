package com.cafromet.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;
import com.cafromet.util.Encriptacion;

public class ControladorCliente implements ActionListener {

	private Datos datos;
	public VentanaRegistrar ventanaRegistrar = new VentanaRegistrar();
	public VentanaCliente ventanaCliente = new VentanaCliente();

	public ControladorCliente(VentanaCliente pVentanaCliente) {

		ventanaCliente = pVentanaCliente;

		inicializarControlador();
	}

	public void inicializarControlador() {

		ventanaCliente.getbtnEnviar().addActionListener(this);
		ventanaCliente.getbtnEnviar().setActionCommand("Enviar");
		ventanaCliente.getBtnRegistrar().addActionListener(this);
		ventanaCliente.getBtnRegistrar().setActionCommand("Registrar");
	}

	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {

		case "Enviar":
			
			String usu = ventanaCliente.getTextFieldUsuario().getText();
			String pass = Encriptacion.generateHash(ventanaCliente.getTextFieldPassw().getText(), "SHA1");
			
			if (!usu.isEmpty() && !pass.isEmpty()) {
				enviarPeticion(usu + "," + pass, Peticiones.p101a);
			}else {
				JOptionPane.showMessageDialog(null, "CAMPO(S) VACIO(S)", "ERROR", JOptionPane.ERROR_MESSAGE);
			}	
			break;
			
		case "Registrar":

			ventanaRegistrar.VentRegistrar();
			ventanaCliente.dispose();
			break;
		}
	}

	public boolean enviarPeticion(String contenido, Peticiones peticion) {
		try {
			datos = new Datos();
			datos.setContenido(contenido);
			datos.setPeticion(peticion);
			IOListenerClt IOListenerClt = new IOListenerClt(datos);
			Thread hiloSender = new Thread(IOListenerClt);
			hiloSender.start();
			hiloSender.join();
			datos = IOListenerClt.getDatos();
			procesarRecepcion();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return true;
	}

	public boolean procesarRecepcion() {
		
		switch (datos.getPeticion().getCodigo()) {

		case 1:
			
			boolean existe = (boolean) datos.getObjeto();
			
			if (existe) {
				VentanaMenuPrincipal ventanaMenuPrincipal = new VentanaMenuPrincipal();
				ventanaMenuPrincipal.InicioMenuPrincipal();
				ventanaCliente.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "NO EXISTE EL USUARIO O CONTRASEÑA INCORRECTA", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			}		
			break;
		}
		return true;
	}
}
