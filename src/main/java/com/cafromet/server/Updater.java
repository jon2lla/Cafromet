package com.cafromet.server;

import java.io.File;

import com.cafromet.util.GestorFicheros;

public class Updater {
	
	private Updater() {}
	
	public static Updater getInstance() {
		return Holder.INSTANCE;
	}

	private static class Holder {
		private static final Updater INSTANCE = new Updater();
	}

	private Object readResolve() {
		return Holder.INSTANCE;
	}
	
	public boolean actualizarFuentes() {
		GestorFicheros gfEspNat = new GestorFicheros(new File("espacios-naturales.json"), 1);
		GestorFicheros gfEstaciones = new GestorFicheros(new File("estaciones.json"), 1);
		GestorFicheros gfIndex = new GestorFicheros(new File("index.json"), 1);
		GestorFicheros gfPueblos = new GestorFicheros(new File("pueblos.json"), 1);
		gfEspNat.start();
//		gfEstaciones.start();
//		gfIndex.start();
//		gfPueblos.start();
		return true;	
	}
	
}
