package com.cafromet.main;

import com.cafromet.modeloDAO.PruebasCRUD;
import com.cafromet.server.Updater;
import com.cafromet.server.LoggerServidor;

public class MainServer {
	
	public static void main(String[] args) {
//		PruebasCRUD.pruebas();
		Updater.getInstance().comprobarActualizaciones();

//		VentanaServidor server = new VentanaServidor();		
	}
}
