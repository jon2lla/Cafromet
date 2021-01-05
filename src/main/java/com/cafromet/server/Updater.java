package com.cafromet.server;

import java.io.File;

import com.cafromet.util.GestorFicheros;

public class Updater {
	private static String DATOS_ESPACIOS_NAT = "src" + File.separator 
			+ "resource" + File.separator 
			+ "com" + File.separator
			+ "cafromet" + File.separator
			+ "files" + File.separator
			+ "json" + File.separator 
			+ "espacios-naturales.json";
	private static String DATOS_ESTACIONES = "src" + File.separator 
			+ "resource" + File.separator 
			+ "com" + File.separator
			+ "cafromet" + File.separator
			+ "files" + File.separator
			+ "json" + File.separator 
			+ "estaciones.json";
	private static String DATOS_INDEX = "src" + File.separator 
			+ "resource" + File.separator 
			+ "com" + File.separator
			+ "cafromet" + File.separator
			+ "files" + File.separator
			+ "json" + File.separator 
			+ "index.json";
	private static String DATOS_PUEBLOS = "src" + File.separator 
			+ "resource" + File.separator 
			+ "com" + File.separator
			+ "cafromet" + File.separator
			+ "files" + File.separator
			+ "json" + File.separator 
			+ "pueblos.json";
	
	
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
//		GestorFicheros gfIndex = new GestorFicheros(new File(DATOS_INDEX), 1);
//		GestorFicheros gfPueblos = new GestorFicheros(new File(DATOS_PUEBLOS), 2);
		GestorFicheros gfEspNat = new GestorFicheros(new File(DATOS_ESPACIOS_NAT), 3);
//		GestorFicheros gfEstaciones = new GestorFicheros(new File(DATOS_ESTACIONES), 4);
		gfEspNat.start();
//		gfEstaciones.start();
//		gfIndex.start();
//		gfPueblos.start();
		return true;	
	}
	
}
