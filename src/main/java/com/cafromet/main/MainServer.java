package com.cafromet.main;


import com.cafromet.server.Updater;

import java.util.logging.Level;

import com.cafromet.server.Logger;

public class MainServer {
	
	public static void main(String[] args) {
		inicioServer();		
	}
	
	public static boolean inicioServer() {
		//DESACTIVA LOS LOGS DE HIBERNATE
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		
		Updater.getInstance().start();
		
		try {
			Updater.getInstance().join();
		} catch (InterruptedException e) {
			System.out.println("\n !ERROR => INTERRUPTED EXCEPTION");
		}
		Logger server = new Logger();	
		server.iniciarSesion();
		
		return true;
	}
	
}
