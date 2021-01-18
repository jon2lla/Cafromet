package com.cafromet.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorCliente implements ActionListener {

	private Datos datos;

	public VentanaCliente ventanaCliente = new VentanaCliente();

	public ControladorCliente(VentanaCliente pVentanaCliente) {

		ventanaCliente = pVentanaCliente;

		inicializarControlador();
	}

	public void inicializarControlador() {

		ventanaCliente.getbtnEnviar().addActionListener(this);
		ventanaCliente.getbtnEnviar().setActionCommand("Enviar");

	}

	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {

		case "Enviar":
			
			String usu = ventanaCliente.getTextFieldUsuario().getText();
			String pass = ventanaCliente.getTextFieldPassw().getText();
			
			try {

				enviarPeticion(usu + "," + pass, Peticiones.p101a);

			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}

			break;

		}
	}

	public void enviarPeticion(String contenido, Peticiones peticion) throws InterruptedException {
		datos = new Datos();
		datos.setContenido(contenido);
		datos.setPeticion(peticion);
		IOListenerClt IOListenerClt = new IOListenerClt(datos);
		Thread hiloSender = new Thread(IOListenerClt);
		hiloSender.start();
		hiloSender.join();
		datos = IOListenerClt.getDatos();
		procesarRecepcion();
	}
	@SuppressWarnings("unused")
	public void procesarRecepcion() {
		
		switch (datos.getPeticion().getCodigo()) {

		case 1:
			
			boolean existe = (boolean) datos.getObjeto();
			
			if (existe) {
				System.out.println("\n Existe el usuario");
				VentanaMunicipio VentanaMunicipio = new VentanaMunicipio();
				VentanaMunicipio.setVisible(true);

				ControladorVentanMunicipio controladorVentanMunicipio = new ControladorVentanMunicipio(VentanaMunicipio);
				
				
			} else {
				System.out.println("\n NO EXISTE");
			}
			
			break;
		}
	}

}
