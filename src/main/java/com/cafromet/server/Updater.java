package com.cafromet.server;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Fuente;
import com.cafromet.modelo.MedicionId;
import com.cafromet.modelodao.CentroMeteorologicoDAO;
import com.cafromet.modelodao.FuenteDAO;
import com.cafromet.modelodao.MedicionDAO;
import com.cafromet.util.Encriptacion;
import com.cafromet.util.GestorFicheros;
import com.cafromet.util.JsonToXml;

public class Updater extends Thread{
	protected static String RUTA_RES =  StringsUpdater.getString("Updater.0") + File.separator  //$NON-NLS-1$
			+ StringsUpdater.getString("Updater.1") + File.separator  //$NON-NLS-1$
			+ StringsUpdater.getString("Updater.2") + File.separator //$NON-NLS-1$
			+ StringsUpdater.getString("Updater.3") + File.separator //$NON-NLS-1$
			+ StringsUpdater.getString("Updater.4") + File.separator; //$NON-NLS-1$
	protected static String JSON = StringsUpdater.getString("Updater.5"); //$NON-NLS-1$
	protected static String RUTA_JSON = RUTA_RES + StringsUpdater.getString("Updater.6") + File.separator; //$NON-NLS-1$
	public static String RUTA_TEMP =  RUTA_RES + StringsUpdater.getString("Updater.7") + File.separator; //$NON-NLS-1$
	public static String RUTA_XML =  RUTA_RES + StringsUpdater.getString("Updater.8") + File.separator; //$NON-NLS-1$
	protected static String DATOS_PUEBLOS = RUTA_JSON + StringsUpdater.getString("Updater.9") + JSON; //$NON-NLS-1$
	protected static String DATOS_ESPACIOS_NAT = RUTA_JSON + StringsUpdater.getString("Updater.10") + JSON; //$NON-NLS-1$
	protected static String DATOS_ESTACIONES = RUTA_JSON + StringsUpdater.getString("Updater.11") + JSON;  //$NON-NLS-1$
	protected static String DATOS_INDEX = RUTA_TEMP + StringsUpdater.getString("Updater.12") + JSON; //$NON-NLS-1$
	protected static String URL_PUEBLOS = StringsUpdater.getString("Updater.13") + JSON; //$NON-NLS-1$
	protected static String URL_ESPACIOS = StringsUpdater.getString("Updater.14") + JSON; //$NON-NLS-1$
	protected static String URL_ESTACIONES = StringsUpdater.getString("Updater.15") + JSON; //$NON-NLS-1$
	protected static String URL_INDEX = StringsUpdater.getString("Updater.16") + JSON; //$NON-NLS-1$
	
	
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
		
	@Override
	public void run() {
		comprobarActualizaciones();
	}

	public boolean comprobarActualizaciones() {
		GestorFicheros.crearDirectorio(RUTA_TEMP);
		GestorFicheros.crearDirectorio(RUTA_JSON);
		GestorFicheros.crearDirectorio(RUTA_XML);
		FuenteDAO.iniciarSesion();
		System.out.println(StringsUpdater.getString("Updater.17")); //$NON-NLS-1$

		//ACTUALIZACION MUNICIPIOS
		comprobarActuMunicipios();
				
		//ACTUALIZACION ESPACIOS NATURALES
		comprobarActuEspaciosNat();
		
		//ACTUALIZACION CENTRO METEOROLOGICOS
		comprobarActuCentrosMet();
		
		//ACTUALIZACION INDEX
		comprobarActuIndex();
		
		//ACTUALIZACION MEDICIONES	
		comprobarActuMediciones();
				
		return true;
	}

	public boolean mostrarHash(String hash, String hash2) {
		if(hash != null) {
			System.out.println(StringsUpdater.getString("Updater.18") + hash); //$NON-NLS-1$
		}
		if(hash2 != null) {
			System.out.println(StringsUpdater.getString("Updater.19") + hash2); //$NON-NLS-1$
		}
		return true;
	}
	
	public boolean comprobarActuMunicipios() {
		new PeticionHttp(URL_PUEBLOS, RUTA_TEMP + StringsUpdater.getString("Updater.20") + JSON); //$NON-NLS-1$
		Fuente fuenteMunicipios = new Fuente();
		fuenteMunicipios.setId(1);
		fuenteMunicipios.setFormato(JSON);
		fuenteMunicipios.setNombre(StringsUpdater.getString("Updater.21")); //$NON-NLS-1$
		fuenteMunicipios.setUrl(URL_PUEBLOS);
		fuenteMunicipios.setHash(Encriptacion.generateHash(GestorFicheros.readFileAsString(new File(RUTA_TEMP + StringsUpdater.getString("Updater.22") + JSON)), StringsUpdater.getString("Updater.23"))); //$NON-NLS-1$ //$NON-NLS-2$
		if(!FuenteDAO.insertarRegistro(fuenteMunicipios)) {
			Fuente fuenteMunicipios2 = FuenteDAO.consultarRegistroPorNombre(StringsUpdater.getString("Updater.24")); //$NON-NLS-1$
			mostrarHash(fuenteMunicipios.getHash(), fuenteMunicipios2.getHash());
			if(!fuenteMunicipios.equals(fuenteMunicipios2)) {
				actualizarMunicipios();
			}
		}
		else {
			actualizarMunicipios();
		}
		return true;

	}

	public boolean comprobarActuEspaciosNat() {
		new PeticionHttp(URL_ESPACIOS, RUTA_TEMP + StringsUpdater.getString("Updater.25") + JSON); //$NON-NLS-1$
		Fuente fuenteEspaciosNat = new Fuente();
		fuenteEspaciosNat.setId(2);
		fuenteEspaciosNat.setFormato(JSON);
		fuenteEspaciosNat.setNombre(StringsUpdater.getString("Updater.26")); //$NON-NLS-1$
		fuenteEspaciosNat.setUrl(URL_ESPACIOS);
		fuenteEspaciosNat.setHash(Encriptacion.generateHash(GestorFicheros.readFileAsString(new File(RUTA_TEMP + StringsUpdater.getString("Updater.27") + JSON)), StringsUpdater.getString("Updater.28"))); //$NON-NLS-1$ //$NON-NLS-2$
		if(!FuenteDAO.insertarRegistro(fuenteEspaciosNat)) {
			Fuente fuenteEspaciosNat2 = FuenteDAO.consultarRegistroPorNombre(StringsUpdater.getString("Updater.29")); //$NON-NLS-1$
			mostrarHash(fuenteEspaciosNat.getHash(), fuenteEspaciosNat2.getHash());
			if(!fuenteEspaciosNat.equals(fuenteEspaciosNat2)) {
				actualizarEspaciosNat();
			}
		}
		else {
			actualizarEspaciosNat();
		}
		return true;
	}
 
	public boolean comprobarActuCentrosMet() {
		new PeticionHttp(URL_ESTACIONES, RUTA_TEMP + StringsUpdater.getString("Updater.30") + JSON); //$NON-NLS-1$
		Fuente fuenteCentrosMet = new Fuente();
		fuenteCentrosMet.setId(3);
		fuenteCentrosMet.setFormato(JSON);
		fuenteCentrosMet.setNombre(StringsUpdater.getString("Updater.31")); //$NON-NLS-1$
		fuenteCentrosMet.setUrl(URL_ESTACIONES);
		fuenteCentrosMet.setHash(Encriptacion.generateHash(GestorFicheros.readFileAsString(new File(RUTA_TEMP + StringsUpdater.getString("Updater.32") + JSON)), StringsUpdater.getString("Updater.33"))); //$NON-NLS-1$ //$NON-NLS-2$
		if(!FuenteDAO.insertarRegistro(fuenteCentrosMet)) {
			Fuente fuenteCentrosMet2 = FuenteDAO.consultarRegistroPorNombre(StringsUpdater.getString("Updater.34")); //$NON-NLS-1$
			mostrarHash(fuenteCentrosMet.getHash(), fuenteCentrosMet2.getHash());
			if(!fuenteCentrosMet.equals(fuenteCentrosMet2)) {
				actualizarCentrosMet();
			}
		}
		else {
			actualizarCentrosMet();
		}
		return true;

	}

	public boolean comprobarActuIndex() {
		new PeticionHttp(URL_INDEX, RUTA_TEMP + StringsUpdater.getString("Updater.35") + JSON); //$NON-NLS-1$
		Fuente fuenteIndex = new Fuente();
		fuenteIndex.setId(4);
		fuenteIndex.setFormato(JSON);
		fuenteIndex.setNombre(StringsUpdater.getString("Updater.36")); //$NON-NLS-1$
		fuenteIndex.setUrl(URL_INDEX);
		fuenteIndex.setHash(Encriptacion.generateHash(GestorFicheros.readFileAsString(new File(RUTA_TEMP + StringsUpdater.getString("Updater.37") + JSON)), StringsUpdater.getString("Updater.38"))); //$NON-NLS-1$ //$NON-NLS-2$
		FuenteDAO.iniciarSesion();
		if(!FuenteDAO.insertarRegistro(fuenteIndex)) {
			Fuente fuenteIndex2 = FuenteDAO.consultarRegistroPorNombre(StringsUpdater.getString("Updater.39")); //$NON-NLS-1$
			mostrarHash(fuenteIndex.getHash(), fuenteIndex.getHash());
			if(!fuenteIndex.equals(fuenteIndex2)) {
				actualizarIndex();
			}
		}
		else {
			actualizarIndex();
		}
		
		FuenteDAO.cerrarSesion();
		return true;

	}

	public boolean comprobarActuMediciones() {
		File fichero = null;
		CentroMeteorologicoDAO.iniciarSesion();
		List<CentroMeteorologico> centros = CentroMeteorologicoDAO.consultarRegistros(); 
		for (CentroMeteorologico centroMeteorologico : centros) {
		
			String hash = null;
	
			if(centroMeteorologico.getUrl() != null) {
				String nombreFormateado = centroMeteorologico.getNombre().replace(StringsUpdater.getString("Updater.40"), StringsUpdater.getString("Updater.41")).toLowerCase(); //$NON-NLS-1$ //$NON-NLS-2$

				new PeticionHttp(centroMeteorologico.getUrl(), RUTA_TEMP + nombreFormateado + StringsUpdater.getString("Updater.42") + JSON); //$NON-NLS-1$
				fichero = new File(RUTA_TEMP + nombreFormateado + StringsUpdater.getString("Updater.43") + JSON); //$NON-NLS-1$
			
				//ACTUALIZACION POR HASH
//				try {
//					byte[] c = Files.readAllBytes(Paths.get(RUTA_TEMP + nombreFormateado + "_temp" + JSON));
//					hash = Encriptacion.generateHash(c.toString(), "SHA1");
//				} catch (IOException e1) {
//					System.out.println("\n ERROR AL ENCRIPTAR");
//				}
				
				//ACTUALIZACION POR TAMAÑO
				hash = String.valueOf(fichero.length());
				
				if(centroMeteorologico.getHash() == null) {
					centroMeteorologico.setHash(hash);
					CentroMeteorologicoDAO.iniciarSesion();
					CentroMeteorologicoDAO.actualizarRegistro(centroMeteorologico);
					CentroMeteorologicoDAO.cerrarSesion();
					try {
						actualizarMediciones(centroMeteorologico);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(!centroMeteorologico.getHash().equals(hash)) {	
					try {
						centroMeteorologico.setHash(hash);
						CentroMeteorologicoDAO.iniciarSesion();
						CentroMeteorologicoDAO.actualizarRegistro(centroMeteorologico);
						CentroMeteorologicoDAO.cerrarSesion();
						actualizarMediciones(centroMeteorologico);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}	
			mostrarHash(centroMeteorologico.getHash(), hash);	
		}
		
		return true;

	}
	public boolean actualizarMunicipios() {
		try {
			GestorFicheros gfMunicipios = new GestorFicheros(new File(RUTA_TEMP + StringsUpdater.getString("Updater.44") + JSON), new File(DATOS_PUEBLOS), 1); //$NON-NLS-1$
			gfMunicipios.start();
			System.out.println(StringsUpdater.getString("Updater.45") + DATOS_PUEBLOS); //$NON-NLS-1$
			gfMunicipios.join();
			System.out.println(StringsUpdater.getString("Updater.46")); //$NON-NLS-1$
		} catch (InterruptedException e) {
			System.out.println(StringsUpdater.getString("Updater.47") + DATOS_PUEBLOS); //$NON-NLS-1$
		}
		return true;
	}

	public boolean actualizarEspaciosNat() {	
		try {
			GestorFicheros gfEspNat = new GestorFicheros(new File(RUTA_TEMP + StringsUpdater.getString("Updater.48") + JSON), new File(DATOS_ESPACIOS_NAT), 2); //$NON-NLS-1$
			gfEspNat.start();
			System.out.println(StringsUpdater.getString("Updater.49") + DATOS_ESPACIOS_NAT); //$NON-NLS-1$
			gfEspNat.join();
			System.out.println(StringsUpdater.getString("Updater.50")); //$NON-NLS-1$
		} catch (InterruptedException e) {
			System.out.println(StringsUpdater.getString("Updater.51") + DATOS_ESPACIOS_NAT); //$NON-NLS-1$
		}
		return true;
	}
	
	public boolean actualizarCentrosMet() {
		try {
			GestorFicheros gfEstaciones = new GestorFicheros(new File(RUTA_TEMP + StringsUpdater.getString("Updater.52") + JSON), new File(DATOS_ESTACIONES), 3); //$NON-NLS-1$
			gfEstaciones.start();
			System.out.println(StringsUpdater.getString("Updater.53") + DATOS_ESTACIONES); //$NON-NLS-1$
			gfEstaciones.join();
			System.out.println(StringsUpdater.getString("Updater.54")); //$NON-NLS-1$
		} catch (InterruptedException e) {
			System.out.println(StringsUpdater.getString("Updater.55") + DATOS_ESTACIONES); //$NON-NLS-1$
		}
		return true;
	}
	
	public boolean actualizarIndex() {
		try {
			GestorFicheros gfIndex = new GestorFicheros(new File(RUTA_TEMP + StringsUpdater.getString("Updater.56") + JSON), 4); //$NON-NLS-1$
			gfIndex.start();
			System.out.println(StringsUpdater.getString("Updater.57") + RUTA_TEMP + StringsUpdater.getString("Updater.58") + JSON); //$NON-NLS-1$ //$NON-NLS-2$
			gfIndex.join();
			System.out.println(StringsUpdater.getString("Updater.59")); //$NON-NLS-1$
		} catch (InterruptedException e) {
			System.out.println(StringsUpdater.getString("Updater.60") + RUTA_TEMP + StringsUpdater.getString("Updater.61") + JSON); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return true;
	}

	public boolean actualizarMediciones(CentroMeteorologico centroMeteorologico) throws InterruptedException {
		String nombreFormateado = centroMeteorologico.getNombre().replace(StringsUpdater.getString("Updater.62"), StringsUpdater.getString("Updater.63")).toLowerCase(); //$NON-NLS-1$ //$NON-NLS-2$
		
		GestorFicheros gfMediciones = new GestorFicheros(new File (RUTA_TEMP + nombreFormateado + StringsUpdater.getString("Updater.64") + JSON), new File(RUTA_TEMP + nombreFormateado + StringsUpdater.getString("Updater.65") + JSON), 5, centroMeteorologico); //$NON-NLS-1$ //$NON-NLS-2$
		gfMediciones.start();		
		
		//DESCOMENTAR PARA ACTUALIZACION DE MEDICIONES SECUENCIAL
//		gfMediciones.join();

		System.out.println(StringsUpdater.getString("Updater.66") + RUTA_TEMP + nombreFormateado + StringsUpdater.getString("Updater.67") + JSON); //$NON-NLS-1$ //$NON-NLS-2$
		System.out.println(StringsUpdater.getString("Updater.68")); //$NON-NLS-1$
		return true;
	
	}
}
