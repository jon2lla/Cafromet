package com.cafromet.main;


import com.cafromet.server.Updater;

import java.util.logging.Level;

import com.cafromet.server.Logger;

public class MainServer {
	
	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		Updater.getInstance().comprobarActualizaciones();

		Logger server = new Logger();	
		server.iniciarSesion();
	}
}
