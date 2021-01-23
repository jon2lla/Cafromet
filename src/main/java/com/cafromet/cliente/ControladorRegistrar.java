package com.cafromet.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.cafromet.modelo.Cliente;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorRegistrar implements ActionListener {
	
	private Datos datos;
	VentanaRegistrar ventanaRegistrar = new VentanaRegistrar();
	VentanaCliente ventanaCliente  = new VentanaCliente();
	
	public ControladorRegistrar(VentanaRegistrar ventanaRegistrar) {

		this.ventanaRegistrar = ventanaRegistrar;
		inicializarControlador();

	}

	public void inicializarControlador() {

		ventanaRegistrar.getBtnRegistrar().addActionListener(this);
		ventanaRegistrar.getBtnRegistrar().setActionCommand("Registrar");
		ventanaRegistrar.getBtnCancelar().addActionListener(this);
		ventanaRegistrar.getBtnCancelar().setActionCommand("Cancelar");

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {

		case "Registrar":

			String usu = ventanaRegistrar.getTextFieldNombre().getText();
			String pass = ventanaRegistrar.getTextFieldPassword().getText();
			String pass2 = ventanaRegistrar.getTextFieldPassword2().getText();
		
			Cliente cliente = new Cliente();
			cliente.setUsuario(usu);
			cliente.setPasswd(pass);
			if (!usu.isEmpty()) {
				if (pass.equals(pass2) && !pass2.isEmpty()) {
					enviarPeticion(cliente, Peticiones.p102a);
				}else{
					JOptionPane.showMessageDialog(null, "LAS CONTRASEÑAS NO COINCIDEN", "ERROR", JOptionPane.ERROR_MESSAGE);
				}	
			}else {
				JOptionPane.showMessageDialog(null, "CAMPO(S) VACIO(S)", "ERROR", JOptionPane.ERROR_MESSAGE);
			}	
			break;
		case "Cancelar":
			ventanaCliente.inicioVentaCliente();
			ventanaRegistrar.dispose();
		break;
		}
	}
	
	public boolean enviarPeticion(Cliente cliente, Peticiones peticion) {
		try {
			datos = new Datos();
	        datos.setObjeto(cliente);
	        datos.setPeticion(peticion);
	        IOListenerClt IOListenerClt = new IOListenerClt(datos);
	        Thread hiloSender = new Thread(IOListenerClt);
	        hiloSender.start();
			hiloSender.join();
			datos = IOListenerClt.getDatos();			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return procesarRecepcion();
	}
	
	public boolean procesarRecepcion() {
		
		 switch (datos.getPeticion().getPlataforma()) {

         case 1:
             boolean insertado = (boolean) datos.getObjeto();
             if (insertado) {
                 ventanaCliente.inicioVentaCliente();
                 ventanaRegistrar.dispose();
             } else {
                 JOptionPane.showMessageDialog(null, "USUARIO YA REGISTRADO","REGISTRADO",JOptionPane.INFORMATION_MESSAGE);
                 return false;
             }
             break;
     }
		return true;
	}
}
