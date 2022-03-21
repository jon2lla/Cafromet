package com.cafromet.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Cliente;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Fotos;
import com.cafromet.modelo.Fuente;
import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.MedicionId;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Municipio_EspacioNatural;
import com.cafromet.modelo.Municipio_EspacioNaturalId;
import com.cafromet.modelo.Provincia;
import com.cafromet.modelodao.CentroMeteorologicoDAO;
import com.cafromet.modelodao.ClienteDAO;
import com.cafromet.modelodao.EspacioNaturalDAO;
import com.cafromet.modelodao.FotoDAO;
import com.cafromet.modelodao.MedicionDAO;
import com.cafromet.modelodao.MunicipioDAO;
import com.cafromet.modelodao.Municipio_EspacioDAO;
import com.cafromet.modelodao.ProvinciaDAO;
import com.cafromet.modelodto.FotoDTO;
import com.cafromet.server.Updater;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GestorFicheros extends Thread {

	private File ficheroEntrada;
	private File ficheroSalida;
	private int tipo;
	private Provincia provincia;
	private Municipio municipio;
	private EspacioNatural espacio;
	private String[] codMunicipios;
	private String[] idProvincias;
	private ArrayList<Municipio_EspacioNatural> mun_esps = new ArrayList<>();
	private CentroMeteorologico centroMet;
//	private Fuente fuente;
	private CentroMeteorologico centroMeteorologico;
	private String str;

	
	public GestorFicheros(File ficheroEntrada, File ficheroSalida, int tipo) {
		this.ficheroEntrada = ficheroEntrada;
		this.ficheroSalida = ficheroSalida;
		this.tipo = tipo;
	}
	public GestorFicheros(File ficheroEntrada, int tipo) {
		this.ficheroEntrada = ficheroEntrada;
		this.tipo = tipo;
	}
	public GestorFicheros(File ficheroEntrada, File ficheroSalida, int tipo, CentroMeteorologico centroMeteorologico) {
		this.ficheroEntrada = ficheroEntrada;
		this.ficheroSalida = ficheroSalida;
		this.centroMeteorologico = centroMeteorologico;
		this.tipo = tipo;
	}

	@Override
	public void run() {
//		filtrarJson();
		switch (tipo) {
		case 1:
			filtrarJson();
			procesarElementoJsonMunicipio(procesarJson());
			break;
		case 2:
			filtrarJson();
			procesarElementoJsonEspacio(procesarJson());
			break;
		case 3:
			filtrarJson();
			procesarElementoJsonCentrosMet(procesarJson());
			break;
		case 4:
			procesarElementoJsonIndex(procesarJson());
			break;
		case 5:
			filtrarJson();
			procesarElementoJsonMedicion(procesarJson());
			String nombreFormateado = centroMeteorologico.getNombre().replace(StringsGestorFicheros.getString("GestorFicheros.0"), StringsGestorFicheros.getString("GestorFicheros.1")).toLowerCase();			 //$NON-NLS-1$ //$NON-NLS-2$
			JsonToXml jtx = new JsonToXml();
			jtx.convertJsonToXml(nombreFormateado + StringsGestorFicheros.getString("GestorFicheros.2"), nombreFormateado, StringsGestorFicheros.getString("GestorFicheros.3"), StringsGestorFicheros.getString("GestorFicheros.4") + nombreFormateado + StringsGestorFicheros.getString("GestorFicheros.5"), Updater.RUTA_XML); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			jtx.start();
			break;
		}

	}

	/**
	 * Metodo que recibe un fichero Json como parametro y llama a las funciones para
	 * filtrarlo y procesar sus elementos
	 * 
	 * @param fichero Json a procesar
	 * @return true
	 * @throws FileNotFoundException
	 */
	public JsonElement procesarJson() {

		JsonParser parser = new JsonParser();

		FileReader fr = null;

		try {
			if(tipo == 4) {
				fr = new FileReader(ficheroEntrada);
			}
			else {
				fr = new FileReader(ficheroSalida);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		JsonElement datos = parser.parse(fr);
		try {
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return datos;
	}

	/**
	 * Metodo que lee un fichero Json y filtra los errores que pueda contener
	 * 
	 * @param fichero Json a filtrar
	 * @return true
	 */
	public String filtrarJson() {
		String linea;
		String contenido = StringsGestorFicheros.getString("GestorFicheros.6"); //$NON-NLS-1$
		FileReader frFichero = null;
		FileWriter fwFichero = null;
		BufferedReader brFichero = null;

		try {
			linea = StringsGestorFicheros.getString("GestorFicheros.7"); //$NON-NLS-1$
//			int contador = 0;
			brFichero = new BufferedReader(new FileReader(ficheroEntrada));
			while ((linea = brFichero.readLine()) != null) {
				switch (tipo) {
				case 1:
					if (comprobarCamposMunicipios(linea)) {
						linea = remplazoMunicipios(linea);
						contenido = contenido + linea + StringsGestorFicheros.getString("GestorFicheros.8"); //$NON-NLS-1$

					} else if (comprobarEstructuraJson(linea)) {
						linea = remplazoHT(linea);
						contenido = contenido + linea + StringsGestorFicheros.getString("GestorFicheros.9"); //$NON-NLS-1$

					}
					break;
				case 2:
					if (comprobarCamposEspacioNat(linea)) {
						linea = remplazoEspacioNat(linea);
						contenido = contenido + linea + StringsGestorFicheros.getString("GestorFicheros.10"); //$NON-NLS-1$

					} else if (comprobarEstructuraJson(linea)) {
						linea = remplazoHT(linea);
						contenido = contenido + linea + StringsGestorFicheros.getString("GestorFicheros.11"); //$NON-NLS-1$

					}
					break;
				case 3:
					if (comprobarCamposCentrosMet(linea)) {
						linea = remplazoCentrosMet(linea);
						contenido = contenido + linea + StringsGestorFicheros.getString("GestorFicheros.12"); //$NON-NLS-1$

					} else if (comprobarEstructuraJson(linea)) {
						linea = remplazoHT(linea);
						contenido = contenido + linea + StringsGestorFicheros.getString("GestorFicheros.13"); //$NON-NLS-1$

					}
					break;
//				case 4:
//					 if (comprobarCamposIndex(linea)) {
//						linea = remplazoIndex(linea);	
//						contenido = contenido + "\n" + linea;						
//					} else if (comprobarEstructuraJsonIndex(linea)) {
//						linea = remplazoHTIndex(linea);
//						if(contador==0) {
//							contenido = contenido + linea;
//						}else {
//							contenido = contenido + "\n" + linea;
//						}
//					}
//					contador=1;
//					break;
				case 5:
					if (comprobarEstructuraJson(linea)) {
						
						linea = remplazoHT(linea);						
					}	
					contenido = contenido + linea + StringsGestorFicheros.getString("GestorFicheros.14"); //$NON-NLS-1$
					break;
				}
			}

			fwFichero = new FileWriter(ficheroSalida);
			fwFichero.write(contenido);
		} catch (FileNotFoundException fn) {
			System.out.println(StringsGestorFicheros.getString("GestorFicheros.15")); //$NON-NLS-1$
		} catch (IOException io) {
			System.out.println(StringsGestorFicheros.getString("GestorFicheros.16")); //$NON-NLS-1$
		} finally {
			try {
				brFichero.close();
				fwFichero.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return contenido;
	}

	public String remplazoHT(String linea) {
		if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.17"))) { //$NON-NLS-1$

			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.18"), StringsGestorFicheros.getString("GestorFicheros.19")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.20"))) { //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.21"), StringsGestorFicheros.getString("GestorFicheros.22")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return linea;
	}

	public boolean comprobarEstructuraJson(String linea) {
		if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.23")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.24")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.25"))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			return true;
		}
		return false;
	}

	public String eliminarRepetidos(String linea) {
		String lineaNueva = StringsGestorFicheros.getString("GestorFicheros.26"); //$NON-NLS-1$
		String[] arrayLinea = linea.split(StringsGestorFicheros.getString("GestorFicheros.27")); //$NON-NLS-1$
		String str1 = StringsGestorFicheros.getString("GestorFicheros.28"); //$NON-NLS-1$

		for (int i = 0; i < arrayLinea.length; i++) {
			String str2 = arrayLinea[i];
			str2 = str2.replace(StringsGestorFicheros.getString("GestorFicheros.29"), StringsGestorFicheros.getString("GestorFicheros.30")); //$NON-NLS-1$ //$NON-NLS-2$
			str2 = str2.replace(StringsGestorFicheros.getString("GestorFicheros.31"), StringsGestorFicheros.getString("GestorFicheros.32")); //$NON-NLS-1$ //$NON-NLS-2$
			if (!str2.equals(str1)) {
				str1 = str2;
				switch (i) {
				case 0:
					str2 = StringsGestorFicheros.getString("GestorFicheros.33") + str2 + StringsGestorFicheros.getString("GestorFicheros.34"); //$NON-NLS-1$ //$NON-NLS-2$
					break;
				case 2:
					str2 = StringsGestorFicheros.getString("GestorFicheros.35") + str2; //$NON-NLS-1$
					break;
				}
				lineaNueva = lineaNueva + str2 + StringsGestorFicheros.getString("GestorFicheros.36"); //$NON-NLS-1$
			}
		}
		lineaNueva = lineaNueva.trim();
		lineaNueva = lineaNueva + StringsGestorFicheros.getString("GestorFicheros.37"); //$NON-NLS-1$
		return lineaNueva;
	}

	public static String removerUltimoCaracter(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			result = str.substring(0, str.length() - 1);
		}
		return result;
	}

	public static String readFileAsString(String file) throws IOException {
		return new String(Files.readAllBytes(Paths.get(file)));
	}

	public static String readFileAsString(File fichero) {
		String linea;
		String contenido = StringsGestorFicheros.getString("GestorFicheros.38"); //$NON-NLS-1$
		BufferedReader brFichero = null;

		try {
			brFichero = new BufferedReader(new FileReader(fichero));
			while ((linea = brFichero.readLine()) != null) {

				contenido += linea;

			}
		} catch (FileNotFoundException fn) {
			System.out.println(StringsGestorFicheros.getString("GestorFicheros.39")); //$NON-NLS-1$
		} catch (IOException io) {
			System.out.println(StringsGestorFicheros.getString("GestorFicheros.40")); //$NON-NLS-1$
		} finally {
			try {
				brFichero.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return contenido;
	}
	
	
	public static boolean crearDirectorio(String ruta) {
		File directorio = new File(ruta);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println(StringsGestorFicheros.getString("GestorFicheros.41") + ruta); //$NON-NLS-1$
            } else {
                System.out.println(StringsGestorFicheros.getString("GestorFicheros.42") + ruta); //$NON-NLS-1$
            }
        }
		return true;
	}
	
	public static boolean eliminarDirectorio(File fichero) {

		File[] ficheros = fichero.listFiles();

		for (int x = 0; x < ficheros.length; x++) {
			if (ficheros[x].isDirectory()) {
				eliminarDirectorio(ficheros[x]);
			}
			ficheros[x].delete();
		}
		return true;
	}
	
	public static boolean eliminarFichero(File fichero) {
		if (fichero.delete()) {
			System.out.println(StringsGestorFicheros.getString("GestorFicheros.43") + fichero.getPath()); //$NON-NLS-1$
		} else {
			System.out.println(StringsGestorFicheros.getString("GestorFicheros.44") + fichero.getPath()); //$NON-NLS-1$
		}
		return true;
	}
	
	public synchronized static boolean guardarFoto(FotoDTO fotoDTO) {
		FotoDAO.iniciarSesion();
		ClienteDAO.iniciarSesion();
		EspacioNaturalDAO.iniciarSesion();
		Fotos foto = new Fotos();
		Cliente cliente4 = ClienteDAO.consultarRegistro(fotoDTO.getIdCliente());
		EspacioNatural espacio3 = EspacioNaturalDAO.consultarRegistroPorId(fotoDTO.getIdEspacio());
		foto.setCliente(cliente4);
		foto.setEspacioNatural(espacio3);
		foto.setIdFoto(fotoDTO.getIdFoto());
		if(!FotoDAO.insertarRegistro(foto)) {
			return false;
		}
		String rutaFoto = Updater.RUTA_FOTOS + fotoDTO.getIdCliente() + File.separator + fotoDTO.getIdEspacio() + File.separator;
		GestorFicheros.crearDirectorio(rutaFoto);
	    try {
			FileOutputStream fos = new FileOutputStream(rutaFoto + fotoDTO.getIdFoto() + ".jpeg");
			fos.write(fotoDTO.getbArray());
			fos.close();

		} catch (IOException e) {
			System.out.println("\n !ERROR AL CREAR EL FICHERO .JPEG");
			return false;

		}
	    FotoDAO.cerrarSesion();
		ClienteDAO.cerrarSesion();
		EspacioNaturalDAO.cerrarSesion();
		return true;
	}
	
	public synchronized static ArrayList<FotoDTO> cargarFotos(int idCliente, int idEspacio) {
		FotoDAO.iniciarSesion();

		List<Fotos> listaFotos = FotoDAO.consultarRegistros(idCliente, idEspacio);
		ArrayList<FotoDTO> listaFotosDTO = new ArrayList<>();
		String rutaFoto = Updater.RUTA_FOTOS + idCliente + File.separator + idEspacio + File.separator;
		GestorFicheros.crearDirectorio(rutaFoto);
		for (Fotos foto : listaFotos) {
			try {
				FotoDTO fotoDTO = new FotoDTO();
				BufferedImage bImage = ImageIO.read(new File(rutaFoto + foto.getIdFoto() + ".jpeg"));
			    ByteArrayOutputStream bos = new ByteArrayOutputStream();
			    ImageIO.write(bImage, "jpeg", bos );
			    byte [] data = bos.toByteArray();
			    		    
				fotoDTO.setIdFoto(foto.getIdFoto());
				fotoDTO.setbArray(data);
				fotoDTO.setIdCliente(idCliente);
				fotoDTO.setIdEspacio(idEspacio);
				listaFotosDTO.add(fotoDTO);

			} catch (IOException e) {
				System.out.println("\n !ERROR AL CREAR EL FICHERO .JPEG");
				return null;
			}
		}
  
	    FotoDAO.cerrarSesion();
		return listaFotosDTO;
	}
	
	
	
	// PUEBLOS.JSON *

	private String remplazoMunicipios(String linea) {

		linea = linea.trim();
		if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.45"))) //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.46"), StringsGestorFicheros.getString("GestorFicheros.47")); //$NON-NLS-1$ //$NON-NLS-2$
		else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.48"))) { //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.49"), StringsGestorFicheros.getString("GestorFicheros.50")); //$NON-NLS-1$ //$NON-NLS-2$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.216"), StringsGestorFicheros.getString("GestorFicheros.217"));
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.218"), StringsGestorFicheros.getString("GestorFicheros.219"));
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.220"), StringsGestorFicheros.getString("GestorFicheros.221"));
		}
		else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.51"))) { //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.52"), StringsGestorFicheros.getString("GestorFicheros.53")); //$NON-NLS-1$ //$NON-NLS-2$
			linea = eliminarRepetidos(linea);
		} else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.54"))) { //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.55"), StringsGestorFicheros.getString("GestorFicheros.56")); //$NON-NLS-1$ //$NON-NLS-2$
			linea = eliminarRepetidos(linea);
		} else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.57")) || (linea.contains(StringsGestorFicheros.getString("GestorFicheros.58")))) { //$NON-NLS-1$ //$NON-NLS-2$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.59"), StringsGestorFicheros.getString("GestorFicheros.60")); //$NON-NLS-1$ //$NON-NLS-2$
			linea = eliminarRepetidos(linea);
			linea = removerUltimoCaracter(linea);
		}
		linea = StringsGestorFicheros.getString("GestorFicheros.61") + linea; //$NON-NLS-1$
		return linea;
	}

	public boolean comprobarCamposMunicipios(String linea) {
		if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.62")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.63")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.64")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.65")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.66")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.67")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.68")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.69")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.70")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.71"))) { //$NON-NLS-1$ //$NON-NLS-2$
			return true;
		}
		return false;
	}

	/**
	 * Metodo que recibe un elemento Json y procesa los elemementos hijo de manera
	 * recursiva
	 * 
	 * @param Elemento Json a procesar
	 * @return true
	 */
	private boolean procesarElementoJsonMunicipio(JsonElement elemento) {
		if (elemento.isJsonObject()) {

			JsonObject obj = elemento.getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			Iterator<Map.Entry<String, JsonElement>> iter = entradas.iterator();

			while (iter.hasNext()) {
				Map.Entry<String, JsonElement> entrada = iter.next();

				if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.72"))) { //$NON-NLS-1$
					municipio = new Municipio();
					municipio.setNombre(entrada.getValue().getAsString());
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.73"))) { //$NON-NLS-1$
					municipio.setDescripcion(entrada.getValue().getAsString());
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.74"))) { //$NON-NLS-1$
					municipio.setCodMunicipio(Integer.parseInt(entrada.getValue().getAsString()));
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.75"))) { //$NON-NLS-1$
					provincia = new Provincia();
					provincia.setNombre(entrada.getValue().getAsString());
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.76"))) { //$NON-NLS-1$
					provincia.setIdProvincia(entrada.getValue().getAsInt());
					ProvinciaDAO.iniciarSesion();
					ProvinciaDAO.insertarRegistro(provincia);
					ProvinciaDAO.cerrarSesion();
					municipio.setProvincia(provincia);
					MunicipioDAO.iniciarSesion();
					MunicipioDAO.insertarRegistro(municipio);
					MunicipioDAO.cerrarSesion();
				}
				procesarElementoJsonMunicipio(entrada.getValue());
			}
		} else if (elemento.isJsonArray()) {
			JsonArray array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();

			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				procesarElementoJsonMunicipio(entrada);
			}
		}
		return true;
	}

	// ESPACIOS-NATURALES.JSON *

	public String remplazoEspacioNat(String linea) {
		linea = linea.trim();

		if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.77"))) //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.78"), StringsGestorFicheros.getString("GestorFicheros.79")); //$NON-NLS-1$ //$NON-NLS-2$
		else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.80"))) { //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.81"), StringsGestorFicheros.getString("GestorFicheros.82")); //$NON-NLS-1$ //$NON-NLS-2$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.216"), StringsGestorFicheros.getString("GestorFicheros.217"));
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.218"), StringsGestorFicheros.getString("GestorFicheros.219"));
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.220"), StringsGestorFicheros.getString("GestorFicheros.221"));
		}
		else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.83"))) //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.84"), StringsGestorFicheros.getString("GestorFicheros.85")); //$NON-NLS-1$ //$NON-NLS-2$
		else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.86"))) //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.87"), StringsGestorFicheros.getString("GestorFicheros.88")); //$NON-NLS-1$ //$NON-NLS-2$
		else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.89"))) { //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.90"), StringsGestorFicheros.getString("GestorFicheros.91")); //$NON-NLS-1$ //$NON-NLS-2$
			if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.92"))) //$NON-NLS-1$
				linea = StringsGestorFicheros.getString("GestorFicheros.93"); //$NON-NLS-1$
		} else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.94"))) { //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.95"), StringsGestorFicheros.getString("GestorFicheros.96")); //$NON-NLS-1$ //$NON-NLS-2$
			if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.97"))) //$NON-NLS-1$
				linea = StringsGestorFicheros.getString("GestorFicheros.98"); //$NON-NLS-1$
		} else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.99"))) { //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.100"), StringsGestorFicheros.getString("GestorFicheros.101")); //$NON-NLS-1$ //$NON-NLS-2$
		} else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.102"))) { //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.103"), StringsGestorFicheros.getString("GestorFicheros.104")); //$NON-NLS-1$ //$NON-NLS-2$
		} else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.105"))) { //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.106"), StringsGestorFicheros.getString("GestorFicheros.107")); //$NON-NLS-1$ //$NON-NLS-2$
		} else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.108"))) { //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.109"), StringsGestorFicheros.getString("GestorFicheros.110")); //$NON-NLS-1$ //$NON-NLS-2$
			linea = removerUltimoCaracter(linea);
		}
		linea = StringsGestorFicheros.getString("GestorFicheros.111") + linea; //$NON-NLS-1$
		return linea;
	}

	public boolean comprobarCamposEspacioNat(String linea) {
		if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.112")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.113")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.114")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.115")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.116")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.117")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.118")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.119")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.120")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.121")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.122")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.123")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.124")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.125")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.126")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.127")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.128")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.129")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.130")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.131"))) { //$NON-NLS-1$ //$NON-NLS-2$
			return true;
		}
		return false;
	}

	/**
	 * Metodo que recibe un elemento Json y procesa los elemementos hijo de manera
	 * recursiva
	 * 
	 * @param Elemento Json a procesar
	 * @return true
	 */
	public boolean procesarElementoJsonEspacio(JsonElement elemento) {
		if (elemento.isJsonObject()) {

			JsonObject obj = elemento.getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			Iterator<Map.Entry<String, JsonElement>> iter = entradas.iterator();

			while (iter.hasNext()) {
				Map.Entry<String, JsonElement> entrada = iter.next();

				if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.132"))) { //$NON-NLS-1$
					espacio = new EspacioNatural();
					espacio.setNombre(entrada.getValue().getAsString());
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.133"))) { //$NON-NLS-1$
					espacio.setDescripcion(entrada.getValue().getAsString());
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.134"))) { //$NON-NLS-1$
					espacio.setTipo(entrada.getValue().getAsString());
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.135"))) { //$NON-NLS-1$
					espacio.setCategoria(entrada.getValue().getAsString());
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.136"))) { //$NON-NLS-1$
					espacio.setLatitud(entrada.getValue().getAsDouble());
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.137"))) { //$NON-NLS-1$
					espacio.setLongitud(entrada.getValue().getAsDouble());
					EspacioNaturalDAO.iniciarSesion();
					EspacioNaturalDAO.insertarRegistro(espacio);
					EspacioNaturalDAO.cerrarSesion();
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.138"))) { //$NON-NLS-1$
					codMunicipios = entrada.getValue().getAsString().split(StringsGestorFicheros.getString("GestorFicheros.139")); //$NON-NLS-1$
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.140"))) { //$NON-NLS-1$
					idProvincias = entrada.getValue().getAsString().split(StringsGestorFicheros.getString("GestorFicheros.141")); //$NON-NLS-1$
					for (int i = 0; i < idProvincias.length; i++) {
						int str = Integer.parseInt(codMunicipios[i]);
						int str2 = Integer.parseInt(idProvincias[i]);
						EspacioNaturalDAO.iniciarSesion();
						espacio = EspacioNaturalDAO.consultarRegistroPorNombre(espacio.getNombre());
						EspacioNaturalDAO.cerrarSesion();
						MunicipioDAO.iniciarSesion();
						municipio = MunicipioDAO.consultarRegistro(str, str2);
						MunicipioDAO.cerrarSesion();
						Municipio_EspacioNaturalId mun_espId = new Municipio_EspacioNaturalId(espacio.getIdEspacio(),
								municipio.getIdMunicipio());
						Municipio_EspacioNatural mun_esp = new Municipio_EspacioNatural(mun_espId, espacio, municipio);
						mun_esps.add(mun_esp);
					}
					for (Municipio_EspacioNatural mun_esp2 : mun_esps) {
						Municipio_EspacioDAO.iniciarSesion();
						Municipio_EspacioDAO.insertarRegistro(mun_esp2);
						Municipio_EspacioDAO.cerrarSesion();
					}
				}
				procesarElementoJsonEspacio(entrada.getValue());
			}
		} else if (elemento.isJsonArray()) {
			JsonArray array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();

			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				procesarElementoJsonEspacio(entrada);
			}
		}
		return true;
	}

	// ESTACIONES.JSON *

	private String remplazoCentrosMet(String linea) {

		linea = linea.trim();
		if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.142"))) //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.143"), StringsGestorFicheros.getString("GestorFicheros.144")); //$NON-NLS-1$ //$NON-NLS-2$
		else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.145"))) //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.146"), StringsGestorFicheros.getString("GestorFicheros.147")); //$NON-NLS-1$ //$NON-NLS-2$
		else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.148"))) //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.149"), StringsGestorFicheros.getString("GestorFicheros.150")); //$NON-NLS-1$ //$NON-NLS-2$
		else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.151"))) //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.152"), StringsGestorFicheros.getString("GestorFicheros.153")); //$NON-NLS-1$ //$NON-NLS-2$
		else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.154"))) { //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.155"), StringsGestorFicheros.getString("GestorFicheros.156")); //$NON-NLS-1$ //$NON-NLS-2$
			if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.157"))) //$NON-NLS-1$
				linea = StringsGestorFicheros.getString("GestorFicheros.158"); //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.159"), StringsGestorFicheros.getString("GestorFicheros.160")); //$NON-NLS-1$ //$NON-NLS-2$
			linea = removerUltimoCaracter(linea);
			linea = linea + StringsGestorFicheros.getString("GestorFicheros.161"); //$NON-NLS-1$
		} else if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.162"))) { //$NON-NLS-1$
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.163"), StringsGestorFicheros.getString("GestorFicheros.164")); //$NON-NLS-1$ //$NON-NLS-2$
			if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.165"))) { //$NON-NLS-1$
				linea = StringsGestorFicheros.getString("GestorFicheros.166"); //$NON-NLS-1$
			}
			linea = linea.replace(StringsGestorFicheros.getString("GestorFicheros.167"), StringsGestorFicheros.getString("GestorFicheros.168")); //$NON-NLS-1$ //$NON-NLS-2$

		}

		linea = StringsGestorFicheros.getString("GestorFicheros.169") + linea; //$NON-NLS-1$
		return linea;
	}

	public boolean comprobarCamposCentrosMet(String linea) {
		if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.170")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.171")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.172")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.173")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.174")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.175")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.176")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.177")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.178")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.179")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.180")) //$NON-NLS-1$ //$NON-NLS-2$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.181"))) { //$NON-NLS-1$

			return true;
		}
		return false;
	}

	/**
	 * Metodo que recibe un elemento Json y procesa los elemementos hijo de manera
	 * recursiva
	 * 
	 * @param Elemento Json a procesar
	 * @return true
	 */
	private boolean procesarElementoJsonCentrosMet(JsonElement elemento) {
		if (elemento.isJsonObject()) {

			JsonObject obj = elemento.getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			Iterator<Map.Entry<String, JsonElement>> iter = entradas.iterator();

			while (iter.hasNext()) {
				Map.Entry<String, JsonElement> entrada = iter.next();

				if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.182"))) { //$NON-NLS-1$
					centroMet = new CentroMeteorologico();
					centroMet.setNombre(entrada.getValue().getAsString());
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.183"))) { //$NON-NLS-1$
					ProvinciaDAO.iniciarSesion();
					provincia = ProvinciaDAO.consultarRegistro(entrada.getValue().getAsString());
					ProvinciaDAO.cerrarSesion();
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.184"))) { //$NON-NLS-1$
					MunicipioDAO.iniciarSesion();
					municipio = MunicipioDAO.consultarRegistro(entrada.getValue().getAsString(), provincia.getIdProvincia());
					MunicipioDAO.cerrarSesion();
					if(municipio != null) {
						centroMet.setMunicipio(municipio);
					}
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.185"))) { //$NON-NLS-1$
					centroMet.setDireccion(entrada.getValue().getAsString());
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.186"))) { //$NON-NLS-1$
					centroMet.setLatitud(entrada.getValue().getAsDouble());
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.187"))) { //$NON-NLS-1$
					centroMet.setLongitud(entrada.getValue().getAsDouble());
					CentroMeteorologicoDAO.iniciarSesion();
					CentroMeteorologicoDAO.insertarRegistro(centroMet);
					CentroMeteorologicoDAO.cerrarSesion();
				}
				procesarElementoJsonCentrosMet(entrada.getValue());
			}
		} else if (elemento.isJsonArray()) {
			JsonArray array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();

			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				procesarElementoJsonCentrosMet(entrada);
			}
		}
		return true;
	}

//	// INDEX.JSON
//	private String remplazoIndex(String linea) {
//		linea = linea.trim();
//		if (linea.contains("\"name\": ")) {
//			linea = linea.replace("name", "nombre");
//			linea = linea.replace("_", " ");
//		}
//		linea = "  " + linea;
//		return linea;
//	}

	public boolean comprobarCamposIndex(String linea) {
		if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.188")) || linea.contains(StringsGestorFicheros.getString("GestorFicheros.189"))) { //$NON-NLS-1$ //$NON-NLS-2$
			return true;
		}
		return false;
	}

	public String remplazoHTIndex(String linea) {
//		linea = linea.trim();
		if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.190"))) //$NON-NLS-1$
			linea = StringsGestorFicheros.getString("GestorFicheros.191"); //$NON-NLS-1$
		if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.192"))) { //$NON-NLS-1$
			linea = StringsGestorFicheros.getString("GestorFicheros.193"); //$NON-NLS-1$
		}
		return linea;
	}

	public boolean comprobarEstructuraJsonIndex(String linea) {
		if (linea.contains(StringsGestorFicheros.getString("GestorFicheros.194"))  //$NON-NLS-1$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.195"))  //$NON-NLS-1$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.196"))  //$NON-NLS-1$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.197")) //$NON-NLS-1$
				|| linea.contains(StringsGestorFicheros.getString("GestorFicheros.198"))) { //$NON-NLS-1$
			return true;
		}
		return false;
	}

	/**
	 * Metodo que recibe un elemento Json y procesa los elemementos hijo de manera
	 * recursiva
	 * 
	 * @param Elemento Json a procesar
	 * @return true
	 */
	private boolean procesarElementoJsonIndex(JsonElement elemento) {

		if (elemento.isJsonObject()) {

			JsonObject obj = elemento.getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			Iterator<Map.Entry<String, JsonElement>> iter = entradas.iterator();

			while (iter.hasNext()) {
				Map.Entry<String, JsonElement> entrada = iter.next();
				if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.199"))) { //$NON-NLS-1$
					str = entrada.getValue().getAsString();
					str = str.replace(StringsGestorFicheros.getString("GestorFicheros.200"), StringsGestorFicheros.getString("GestorFicheros.201")); //$NON-NLS-1$ //$NON-NLS-2$
					centroMeteorologico = new CentroMeteorologico();
					centroMeteorologico.setNombre(str);
				} else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.202")) && entrada.getValue().getAsString().contains(StringsGestorFicheros.getString("GestorFicheros.203"))) { //$NON-NLS-1$ //$NON-NLS-2$
					centroMeteorologico.setUrl(entrada.getValue().getAsString());
					CentroMeteorologicoDAO.iniciarSesion();
					CentroMeteorologicoDAO.actualizarRegistro(centroMeteorologico);
					CentroMeteorologicoDAO.cerrarSesion();
				}
				procesarElementoJsonIndex(entrada.getValue());
			}
		} else if (elemento.isJsonArray()) {
			JsonArray array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();

			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				procesarElementoJsonIndex(entrada);
			}
		}
		return true;
	}

	
	//MEDICION.JSON
	
	
	
	private synchronized boolean procesarElementoJsonMedicion(JsonElement elemento) {
		MedicionId medicionId = null;
		Medicion medicion = null;
		if (elemento.isJsonObject()) {
			JsonObject obj = elemento.getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			Iterator<Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				Map.Entry<String, JsonElement> entrada = iter.next();
				try {
					if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.204"))) {	 //$NON-NLS-1$
					    Date date = new SimpleDateFormat(StringsGestorFicheros.getString("GestorFicheros.205")).parse(entrada.getValue().getAsString());           //$NON-NLS-1$
						medicion = new Medicion();
						medicionId = new MedicionId();
						medicionId.setFecha(date);
					}else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.206"))) { //$NON-NLS-1$
					    Date date = new SimpleDateFormat(StringsGestorFicheros.getString("GestorFicheros.207")).parse(entrada.getValue().getAsString());     //$NON-NLS-1$
					    medicionId.setHora(date);
					    medicionId.setIdCentroMet(centroMeteorologico.getIdCentroMet());
					    medicion.setId(medicionId);
					}else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.208"))) { //$NON-NLS-1$
						medicion.setDirViento(Integer.parseInt(entrada.getValue().getAsString().replace(",", ".")));
					}else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.209"))) { //$NON-NLS-1$
						medicion.setHRelativa(Integer.parseInt(entrada.getValue().getAsString().replace(",", ".")));
					}else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.210"))) { //$NON-NLS-1$
						medicion.setPAtmosferica(Float.parseFloat(entrada.getValue().getAsString().replace(",", ".")));
					}else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.211"))) { //$NON-NLS-1$
						medicion.setPrecip(Float.parseFloat(entrada.getValue().getAsString().replace(",", ".")));
					}else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.212"))) { //$NON-NLS-1$
						medicion.setRadSolar(Float.parseFloat(entrada.getValue().getAsString().replace(",", ".")));
					}else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.213"))) { //$NON-NLS-1$
						medicion.setTempAmbiente(Float.parseFloat(entrada.getValue().getAsString().replace(",", ".")));
					}else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.214"))) { //$NON-NLS-1$
						medicion.setVViento(Float.parseFloat(entrada.getValue().getAsString().replace(",", ".")));
					}else if (entrada.getKey().equals(StringsGestorFicheros.getString("GestorFicheros.215"))) { //$NON-NLS-1$
						medicion.setIca(entrada.getValue().getAsString());
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				procesarElementoJsonMedicion(entrada.getValue());
			}
			MedicionDAO.insertarRegistro(medicion);

		} else if (elemento.isJsonArray()) {
			JsonArray array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				procesarElementoJsonMedicion(entrada);
			}
		}
		return true;
	}
}