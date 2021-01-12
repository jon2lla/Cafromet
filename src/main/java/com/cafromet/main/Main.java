package com.cafromet.main;

import com.cafromet.cliente.VentanaCliente;
import com.cafromet.modeloDAO.PruebasCRUD;
import com.cafromet.server.Updater;
import com.cafromet.server.VentanaServidor;

public class Main {
	
	public static void main(String[] args) {
//		PruebasCRUD.pruebas();
		Updater.getInstance().comprobarActualizaciones();
//		VentanaServidor server = new VentanaServidor();
//		VentanaCliente ventanaCliente = new VentanaCliente();
//		
//		ventanaCliente.inicioVentaCliente();
//		
	}
}
