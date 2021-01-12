package com.cafromet.server;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Fuente;
import com.cafromet.modeloDAO.CentroMeteorologicoDAO;
import com.cafromet.modeloDAO.FuenteDAO;
import com.cafromet.util.Encriptacion;
import com.cafromet.util.GestorFicheros;

public class Updater {
	protected static String RUTA_RES =  "src" + File.separator 
			+ "resource" + File.separator 
			+ "com" + File.separator
			+ "cafromet" + File.separator
			+ "files" + File.separator
			+ "json" + File.separator;
	protected static String DATOS_PUEBLOS = RUTA_RES + "pueblos.json";
	protected static String DATOS_ESPACIOS_NAT = RUTA_RES + "espacios-naturales.json";
	protected static String DATOS_ESTACIONES = RUTA_RES + "estaciones.json"; 
	protected static String DATOS_INDEX = RUTA_RES + "index.json";
	protected static String URL_PUEBLOS = "https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/pueblos_euskadi_turismo/opendata/pueblos.json";
	protected static String URL_ESPACIOS = "https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/playas_de_euskadi/opendata/espacios-naturales.json";
	protected static String URL_ESTACIONES = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/estaciones.json";
	protected static String URL_INDEX = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/index.json";
	
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
		
	public boolean comprobarActualizaciones() {
		FuenteDAO.iniciarSesion();
		System.out.println("\n >> COMPROBANDO ACTUALIZACIONES...");

		//ACTUALIZACION MUNICIPIOS
		new PeticionHttp(URL_PUEBLOS, RUTA_RES + "pueblosTemp.json");
		Fuente fuenteMunicipios = new Fuente();
		fuenteMunicipios.setId(1);
		fuenteMunicipios.setFormato(".json");
		fuenteMunicipios.setNombre("Municipios");
		fuenteMunicipios.setUrl(URL_PUEBLOS);
		fuenteMunicipios.setHash(Encriptacion.generateHash(GestorFicheros.readFileAsString(new File(RUTA_RES + "pueblosTemp.json")), "SHA-256"));
		if(!FuenteDAO.insertarRegistro(fuenteMunicipios)) {
			Fuente fuenteMunicipios2 = FuenteDAO.consultarRegistroPorNombre("Municipios");
			System.out.println(" HASH LOCAL => " + fuenteMunicipios.getHash());
			System.out.println(" HASH REMOTO => " + fuenteMunicipios2.getHash());
			if(!fuenteMunicipios.equals(fuenteMunicipios2)) {
				actualizarMunicipios();
			}
		}
		else {
			actualizarMunicipios();
		}
				
		//ACTUALIZACION ESPACIOS NATURALES
		new PeticionHttp(URL_ESPACIOS, RUTA_RES + "espacios-naturalesTemp.json");
		Fuente fuenteEspaciosNat = new Fuente();
		fuenteEspaciosNat.setId(2);
		fuenteEspaciosNat.setFormato(".json");
		fuenteEspaciosNat.setNombre("Espacios Naturales");
		fuenteEspaciosNat.setUrl(URL_ESPACIOS);
		fuenteEspaciosNat.setHash(Encriptacion.generateHash(GestorFicheros.readFileAsString(new File(RUTA_RES + "espacios-naturalesTemp.json")), "SHA-256"));
		if(!FuenteDAO.insertarRegistro(fuenteEspaciosNat)) {
			Fuente fuenteEspaciosNat2 = FuenteDAO.consultarRegistroPorNombre("Espacios Naturales");
			System.out.println(" HASH LOCAL => " + fuenteEspaciosNat.getHash());
			System.out.println(" HASH REMOTO => " + fuenteEspaciosNat2.getHash());
			if(!fuenteEspaciosNat.equals(fuenteEspaciosNat2)) {
				actualizarEspaciosNat();
			}
		}
		else {
			actualizarEspaciosNat();
		}
		
		//ACTUALIZACION CENTRO METEOROLOGICOS
		new PeticionHttp(URL_ESTACIONES, RUTA_RES + "estacionesTemp.json");
		Fuente fuenteCentrosMet = new Fuente();
		fuenteCentrosMet.setId(3);
		fuenteCentrosMet.setFormato(".json");
		fuenteCentrosMet.setNombre("Estaciones Meteorologicas");
		fuenteCentrosMet.setUrl(URL_ESTACIONES);
		fuenteCentrosMet.setHash(Encriptacion.generateHash(GestorFicheros.readFileAsString(new File(RUTA_RES + "estacionesTemp.json")), "SHA-256"));
		if(!FuenteDAO.insertarRegistro(fuenteCentrosMet)) {
			Fuente fuenteCentrosMet2 = FuenteDAO.consultarRegistroPorNombre("Estaciones Meteorologicas");
			System.out.println(" HASH LOCAL => " + fuenteCentrosMet.getHash());
			System.out.println(" HASH REMOTO => " + fuenteCentrosMet2.getHash());
			if(!fuenteCentrosMet.equals(fuenteCentrosMet2)) {
				actualizarCentrosMet();
			}
		}
		else {
			actualizarCentrosMet();
		}
		
		//ACTUALIZACION INDEX
		new PeticionHttp(URL_INDEX, RUTA_RES + "indexTemp.json");
		Fuente fuenteIndex = new Fuente();
		fuenteIndex.setId(4);
		fuenteIndex.setFormato(".json");
		fuenteIndex.setNombre("Index");
		fuenteIndex.setUrl(URL_INDEX);
		fuenteIndex.setHash(Encriptacion.generateHash(GestorFicheros.readFileAsString(new File(RUTA_RES + "indexTemp.json")), "SHA-256"));
		FuenteDAO.iniciarSesion();
		if(!FuenteDAO.insertarRegistro(fuenteIndex)) {
			Fuente fuenteIndex2 = FuenteDAO.consultarRegistroPorNombre("Index");
			System.out.println(" HASH LOCAL => " + fuenteIndex.getHash());
			System.out.println(" HASH REMOTO => " + fuenteIndex2.getHash());
			if(!fuenteIndex.equals(fuenteIndex2)) {
				actualizarIndex();
			}
		}
		else {
			actualizarIndex();
		}
		
		FuenteDAO.cerrarSesion();
		
		
		//ACTUALIZACION MEDICIONES	
		CentroMeteorologicoDAO.iniciarSesion();
		List<CentroMeteorologico> centros = CentroMeteorologicoDAO.consultarRegistro(); 
		for (CentroMeteorologico centroMeteorologico : centros) {
			new PeticionHttp(centroMeteorologico.getUrl(), RUTA_RES + centroMeteorologico.getNombre()+".json");
			String hash=Encriptacion.generateHash(GestorFicheros.readFileAsString(new File(RUTA_RES + centroMeteorologico.getNombre()+".json")), "SHA-256");
			if((centroMeteorologico.isNull() && centroMeteorologico.getUrl()!=null)
					||(centroMeteorologico.getHash().equals(hash))) {
				actualizarMediciones(centroMeteorologico.getNombre());
				System.out.println(" HASH LOCAL => " + centroMeteorologico.getHash());
				System.out.println(" HASH REMOTO => " + hash);
			}
		}
		CentroMeteorologicoDAO.cerrarSesion();	
		
		GestorFicheros.eliminarFichero(new File(RUTA_RES + "pueblosTemp.json"));
		GestorFicheros.eliminarFichero(new File(RUTA_RES + "espacios-naturalesTemp.json"));
		GestorFicheros.eliminarFichero(new File(RUTA_RES + "estacionesTemp.json"));
		GestorFicheros.eliminarFichero(new File(RUTA_RES + "indexTemp.json"));
		
		System.out.println("\n >> FICHEROS ACTUALIZADOS");
		return true;
	}
	
	public boolean actualizarMunicipios() {
		try {
			GestorFicheros gfMunicipios = new GestorFicheros(new File(RUTA_RES + "pueblosTemp.json"), new File(DATOS_PUEBLOS), 1);
			gfMunicipios.start();
			System.out.println("\n >> ACTUALIZANDO FICHERO => " + DATOS_PUEBLOS);
			gfMunicipios.join();
			System.out.println("\n >> FICHERO ACTUALIZADO");
		} catch (InterruptedException e) {
			System.out.println("\n ERROR AL ACTUALIZAR. FICHERO => " + DATOS_PUEBLOS);
		}
		return true;
	}
	
	public boolean actualizarEspaciosNat() {	
		try {
			GestorFicheros gfEspNat = new GestorFicheros(new File(RUTA_RES + "espacios-naturalesTemp.json"), new File(DATOS_ESPACIOS_NAT), 2);
			gfEspNat.start();
			System.out.println("\n >> ACTUALIZANDO FICHERO => " + DATOS_ESPACIOS_NAT);
			gfEspNat.join();
			System.out.println("\n >> FICHERO ACTUALIZADO");
		} catch (InterruptedException e) {
			System.out.println("\n ERROR AL ACTUALIZAR. FICHERO => " + DATOS_ESPACIOS_NAT);
		}
		return true;
	}
	
	public boolean actualizarCentrosMet() {
		try {
			GestorFicheros gfEstaciones = new GestorFicheros(new File(RUTA_RES + "estacionesTemp.json"), new File(DATOS_ESTACIONES), 3);
			gfEstaciones.start();
			System.out.println("\n >> ACTUALIZANDO FICHERO => " + DATOS_ESTACIONES);
			gfEstaciones.join();
			System.out.println("\n >> FICHERO ACTUALIZADO");
		} catch (InterruptedException e) {
			System.out.println("\n ERROR AL ACTUALIZAR. FICHERO => " + DATOS_ESTACIONES);
		}
		return true;
	}
	
	public boolean actualizarIndex() {
		try {
			GestorFicheros gfIndex = new GestorFicheros(new File(RUTA_RES + "indexTemp.json"), 4);
			gfIndex.start();
			System.out.println("\n >>ACTUALIZANDO FICHERO => " + RUTA_RES + "indexTemp.json");
			gfIndex.join();
			System.out.println("\n >> FICHERO ACTUALIZADO");
		} catch (InterruptedException e) {
			System.out.println("\n ERROR AL ACTUALIZAR. FICHERO => " + RUTA_RES + "indexTemp.json");
		}
		return true;
	}

	public boolean actualizarMediciones(String nombre) {
		try {
			GestorFicheros gfMediciones = new GestorFicheros(new File(RUTA_RES + nombre + ".json"), 5);
			gfMediciones.start();
			System.out.println("\n >>ACTUALIZANDO FICHERO => " + RUTA_RES + nombre + ".json");
			gfMediciones.join();
			System.out.println("\n >> FICHERO ACTUALIZADO");
		} catch (InterruptedException e) {
			System.out.println("\n ERROR AL ACTUALIZAR. FICHERO => " + RUTA_RES + nombre + ".json");
		}
		return true;
	}
	
}
