package com.cafromet.server;

import java.io.File;

import com.cafromet.util.GestorFicheros;

public class Updater {
	public static String RUTA_RES =  "src" + File.separator 
			+ "resource" + File.separator 
			+ "com" + File.separator
			+ "cafromet" + File.separator
			+ "files" + File.separator
			+ "json" + File.separator;
	private static String DATOS_INDEX = RUTA_RES + "index.json";
	private static String DATOS_PUEBLOS = RUTA_RES + "pueblos.json";
	private static String DATOS_ESPACIOS_NAT = RUTA_RES + "espacios-naturales.json";
	private static String DATOS_ESTACIONES = RUTA_RES + "estaciones.json"; 

	
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
		GestorFicheros gfMunicipios = new GestorFicheros(new File(DATOS_PUEBLOS), 2);
		GestorFicheros gfEspNat = new GestorFicheros(new File(DATOS_ESPACIOS_NAT), 3);
//		GestorFicheros gfEstaciones = new GestorFicheros(new File(DATOS_ESTACIONES), 4);
//		gfIndex.start();
		gfMunicipios.start();
		gfEspNat.start();
//		gfEstaciones.start();

		return true;	
	}
	
}
