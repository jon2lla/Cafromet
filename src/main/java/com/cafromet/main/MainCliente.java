package com.cafromet.main;

import com.cafromet.cliente.VentanaCliente;

public class MainCliente {
	public static void main(String[] args) {
		inicioCliente();
	}
	
	public static boolean inicioCliente() {
		
		VentanaCliente ventanaCliente = new VentanaCliente();
		ventanaCliente.inicioVentaCliente();
		return true;
	}
}
