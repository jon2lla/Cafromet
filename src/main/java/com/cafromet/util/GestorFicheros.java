package com.cafromet.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.checkerframework.checker.units.qual.C;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Fuente;
import com.cafromet.modelo.MedicionId;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Municipio_EspacioNatural;
import com.cafromet.modelo.Municipio_EspacioNaturalId;
import com.cafromet.modelo.Provincia;
import com.cafromet.modeloDAO.CentroMeteorologicoDAO;
import com.cafromet.modeloDAO.EspacioNaturalDAO;
import com.cafromet.modeloDAO.FuenteDAO;
import com.cafromet.modeloDAO.MunicipioDAO;
import com.cafromet.modeloDAO.Municipio_EspacioDAO;
import com.cafromet.modeloDAO.ProvinciaDAO;
import com.cafromet.server.Updater;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.sun.xml.bind.v2.model.core.ID;


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
	private Fuente fuente;
	
	public GestorFicheros(File ficheroEntrada, File ficheroSalida, int tipo) {
		this.ficheroEntrada = ficheroEntrada;
		this.ficheroSalida = ficheroSalida;
		this.tipo = tipo;
	}
	
	@Override
	public void run() {
    	filtrarJson();
		switch(tipo) {		
		case 1:
			procesarElementoJsonMunicipio(procesarJson());
			break;	
		case 2:
			procesarElementoJsonEspacio(procesarJson());
			break;
		case 3:
			procesarElementoJsonCentrosMet(procesarJson());
			break;
		case 4:
			procesarElementoJsonIndex(procesarJson());
			break;
		}
	
	}

    /**
	 * Metodo que recibe un fichero Json como parametro y llama a las funciones para filtrarlo y procesar sus elementos
	 * @param fichero Json a procesar
	 * @return true
     * @throws FileNotFoundException 
	 */  
    public JsonElement procesarJson(){
    	
    	JsonParser parser = new JsonParser();
		FileReader fr = null;
	
		try {
			fr = new FileReader(ficheroSalida);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonElement datos = parser.parse(fr);
    	return datos;
    }
    
	/**
	 * Metodo que lee un fichero Json y filtra los errores que pueda contener
	 * @param fichero Json a filtrar
	 * @return true
	 */  
	public String filtrarJson(){
		String linea;
		String contenido = "";
        FileWriter fwFichero = null;
        BufferedReader brFichero = null;

		try{
			brFichero = new BufferedReader(new FileReader(ficheroEntrada));
			while((linea = brFichero.readLine())!=null) {
				
				switch(tipo) {				
				case 1:
					if(comprobarCamposMunicipios(linea)) {
						linea = remplazoMunicipios(linea);
						contenido = contenido + linea + "\n";

					}
					else if(comprobarEstructuraJson(linea)) {
						linea = remplazoHT(linea);
						contenido = contenido + linea + "\n";

					}
					break;			
				case 2:
					if(comprobarCamposEspacioNat(linea)) {
						linea = remplazoEspacioNat(linea);
						contenido = contenido + linea + "\n";

					}
					else if(comprobarEstructuraJson(linea)) {
						linea = remplazoHT(linea);
						contenido = contenido + linea + "\n";

					}
					break;
				case 3:
					if(comprobarCamposCentrosMet(linea)) {
						linea = remplazoCentrosMet(linea);
						contenido = contenido + linea + "\n";

					}
					else if(comprobarEstructuraJson(linea)) {
						linea = remplazoHT(linea);
						contenido = contenido + linea + "\n";

					}
					break;
				case 4:
					if(comprobarCamposIndex(linea)) {
						linea = remplazoIndex(linea);
					}
					contenido = contenido + linea + "\n";
					break;
				}	

			}
			fwFichero = new FileWriter(ficheroSalida);  
			fwFichero.write(contenido);
		}catch (FileNotFoundException fn ){
			System.out.println("\n No se encuentra el fichero de carga");
		}catch (IOException io) {
			System.out.println("\n Error de E/S ");
		}
		finally{
            try{
    			brFichero.close();
    			fwFichero.close();
            } 
            catch (IOException e){
                e.printStackTrace();
            }
        }
		return contenido;
	}
		
	public String remplazoHT(String linea) {
		if(linea.contains("jsonCallback(")) {
			linea = linea.replace("jsonCallback(", "");
		}
		if(linea.contains("]);")) {
			linea = linea.replace(");", "");
		}		
		return linea;
	}
	
	public boolean comprobarEstructuraJson(String linea) {
		if(linea.contains("[ {")
				|| linea.contains("}, {")
				|| linea.contains("} ]")) {
			return true;
		}	
		return false;
	}
	
	public String eliminarRepetidos(String linea) {
		String lineaNueva = "";
		String[] arrayLinea = linea.split(" ");
		String str1 = "";

		for (int i = 0; i < arrayLinea.length; i++) {
			String str2 = arrayLinea[i];
			str2 = str2.replace("\"", "");
			str2 = str2.replace(",", "");
			if (!str2.equals(str1)) {
				str1 = str2;
				switch (i) {
				case 0:
					str2 = "\"" + str2 + "\"";
					break;
				case 2:
					str2 = "\"" + str2;
					break;
				}
				lineaNueva = lineaNueva + str2 + " ";
			}
		}
		lineaNueva = lineaNueva.trim();
		lineaNueva = lineaNueva + "\",";
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
		String contenido = "";
        BufferedReader brFichero = null;

		try{
			brFichero = new BufferedReader(new FileReader(fichero));
			while((linea = brFichero.readLine())!=null) {
				
				contenido += linea;
					
			}
		}catch (FileNotFoundException fn ){
			System.out.println("\n No se encuentra el fichero de carga");
		}catch (IOException io) {
			System.out.println("\n Error de E/S ");
		}
		finally{
            try{
    			brFichero.close();
            } 
            catch (IOException e){
                e.printStackTrace();
            }
        }
		return contenido;
	}
	
	public static boolean eliminarFichero(File fichero) { 
	    if (fichero.delete()) { 
	      System.out.println("\n FICHERO BORRADO => " + fichero.getPath());
	    } else {
	      System.out.println("\n ERROR AL BORRAR EL FICHERO => " + fichero.getPath());
	    }
		return true; 
	  }
	
	
	// PUEBLOS.JSON *
	
	private String remplazoMunicipios(String linea) {
		
		linea = linea.trim();
		if (linea.contains("\"documentName\" : "))
			linea = linea.replace("documentName", "nombre");
		else if (linea.contains("\"turismDescription\" : \"<p>"))
			linea = linea.replace("turismDescription", "descripcion");
		else if(linea.contains("\"municipalitycode\" : ")) {
			linea = linea.replace("municipalitycode", "codmunicipio");
			linea = eliminarRepetidos(linea);
		}else if (linea.contains("\"territory\" : ")) {
			linea = linea.replace("territory", "provincia");
			linea = eliminarRepetidos(linea);
		}else if (linea.contains("\"territorycode\" : ") || (linea.contains("\"idrovincia\" : "))) {
			linea = linea.replace("territorycode", "idprovincia");
			linea = eliminarRepetidos(linea);
			linea = removerUltimoCaracter(linea);
		}
		linea = "  " + linea;	
		return linea;
	}

	private boolean comprobarCamposMunicipios(String linea) {
		if (linea.contains("\"documentName\" : ") || linea.contains("\"nombre\" : ")
				|| linea.contains("\"turismDescription\" : \"<p>") || linea.contains("\"descripcion\" : \"<p>")
				|| linea.contains("\"municipalitycode\" : ") || linea.contains("\"codmunicipio\" : ")
				|| linea.contains("\"territory\" : ") || linea.contains("\"provincia\" : ")
				|| linea.contains("\"territorycode\" : ") || linea.contains("\"idprovincia\" : ")) {
			return true;
		}
		return false;
	}
	
	 
	/**
	 * Metodo que recibe un elemento Json y procesa los elemementos hijo de manera recursiva
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
				
				if(entrada.getKey().equals("nombre")) {
					municipio = new Municipio();
					municipio.setNombre(entrada.getValue().getAsString());				
				}
				else if(entrada.getKey().equals("descripcion")) {
					municipio.setDescripcion(entrada.getValue().getAsString());					
				}
				else if(entrada.getKey().equals("codmunicipio")) {
					municipio.setCodMunicipio(Integer.parseInt(entrada.getValue().getAsString()));
				}
				else if(entrada.getKey().equals("provincia")) {
					provincia = new Provincia();
					provincia.setNombre(entrada.getValue().getAsString());				
				}
				else if(entrada.getKey().equals("idprovincia")) {
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
		} 
		else if (elemento.isJsonArray()) {
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

		if (linea.contains("\"documentName\" : "))
			linea = linea.replace("documentName", "nombre");
		else if (linea.contains("\"turismDescription\" : \"<p>"))
			linea = linea.replace("turismDescription", "descripcion");
		else if (linea.contains("\"templateType\" : "))
			linea = linea.replace("templateType", "tipo");
		else if (linea.contains("\"natureType\" : "))
			linea = linea.replace("natureType", "categoria");
		else if (linea.contains("\"latwgs84\" : ")) {
			linea = linea.replace("latwgs84", "latitud");
			if (linea.contains("\"latitud\" : \"\","))
				linea = "\"latitud\" : \"0\",";
		}else if(linea.contains("\"lonwgs84\" : ")) {
			linea = linea.replace("lonwgs84", "longitud");
			if (linea.contains("\"longitud\" : \"\",")) 
				linea = "\"longitud\" : \"0\",";
		}
		else if(linea.contains("\"municipality\" : ")) {
			linea = linea.replace("municipality", "municipio");				
		}else if(linea.contains("\"municipalitycode\" : ")) {
			linea = linea.replace("municipalitycode", "codmunicipio");
		}
		else if (linea.contains("\"territory\" : ")) {
			linea = linea.replace("territory", "provincia");
		} else if (linea.contains("\"territorycode\" : ")) {
			linea = linea.replace("territorycode", "idprovincia");
			linea = removerUltimoCaracter(linea);
		}
		linea = "  " + linea;
		return linea;
	}
	
	public boolean comprobarCamposEspacioNat(String linea) {
		if(linea.contains("\"documentName\" : " ) 
			|| linea.contains("\"nombre\" : " ) 
			|| linea.contains("\"turismDescription\" : \"<p>") 
			|| linea.contains("\"descripcion\" : \"<p>") 
			|| linea.contains("\"templateType\" : ") 
			|| linea.contains("\"tipo\" : ") 
			|| linea.contains("\"natureType\" : ") 
			|| linea.contains("\"categoria\" : ") 
			|| linea.contains("\"latwgs84\" : ") 
			|| linea.contains("\"latitud\" : ") 
			|| linea.contains("\"lonwgs84\" : ")
			|| linea.contains("\"longitud\" : ")			
			|| linea.contains("\"municipality\" : ")
			|| linea.contains("\"municipio\" : ")
			|| linea.contains("\"municipalitycode\" : ")
			|| linea.contains("\"codmunicipio\" : ")	
			|| linea.contains("\"territory\" : ") 
			|| linea.contains("\"provincia\" : ")
			|| linea.contains("\"territorycode\" : ") 
			|| linea.contains("\"idprovincia\" : ")) {
			return true;
		}
		return false;
	}
	
	 
	/**
	 * Metodo que recibe un elemento Json y procesa los elemementos hijo de manera recursiva
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
				
				if(entrada.getKey().equals("nombre")) {
					espacio = new EspacioNatural();
					espacio.setNombre(entrada.getValue().getAsString());				
				}
				else if(entrada.getKey().equals("descripcion")) {
					espacio.setDescripcion(entrada.getValue().getAsString());					
				}
				else if(entrada.getKey().equals("tipo")) {
					espacio.setTipo(entrada.getValue().getAsString());
				}
				else if(entrada.getKey().equals("categoria")) {
					espacio.setCategoria(entrada.getValue().getAsString());	
				}
				else if(entrada.getKey().equals("latitud")) {
					espacio.setLatitud(entrada.getValue().getAsDouble());		
				}
				else if(entrada.getKey().equals("longitud")) {	
					espacio.setLongitud(entrada.getValue().getAsDouble());	
					EspacioNaturalDAO.iniciarSesion();
					EspacioNaturalDAO.insertarRegistro(espacio);
					EspacioNaturalDAO.cerrarSesion();
				}
				else if(entrada.getKey().equals("codmunicipio")) {
					codMunicipios = entrada.getValue().getAsString().split(" ");
				}
				else if(entrada.getKey().equals("idprovincia")) {
					idProvincias = entrada.getValue().getAsString().split(" ");
					for(int i = 0; i < idProvincias.length; i++) {
						int str = Integer.parseInt(codMunicipios[i]);
						int str2 = Integer.parseInt(idProvincias[i]);
						EspacioNaturalDAO.iniciarSesion();
						espacio = EspacioNaturalDAO.consultarRegistroPorNombre(espacio.getNombre());
						EspacioNaturalDAO.cerrarSesion();
						MunicipioDAO.iniciarSesion();
						municipio = MunicipioDAO.consultarRegistro(str, str2);
						MunicipioDAO.cerrarSesion();
						Municipio_EspacioNaturalId mun_espId = new Municipio_EspacioNaturalId(espacio.getIdEspacio(), municipio.getIdMunicipio());
						Municipio_EspacioNatural mun_esp = new Municipio_EspacioNatural(mun_espId, espacio, municipio);
						mun_esps.add(mun_esp);
					}
					for(Municipio_EspacioNatural mun_esp2 : mun_esps) {
						Municipio_EspacioDAO.iniciarSesion();
						Municipio_EspacioDAO.insertarRegistro(mun_esp2);
						Municipio_EspacioDAO.cerrarSesion();
					}
				}
				procesarElementoJsonEspacio(entrada.getValue());
			}
		} 
		else if (elemento.isJsonArray()) {
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
		if (linea.contains("\"Name\" : "))
			linea = linea.replace("Name", "nombre");
		else if (linea.contains("\"Province\" : ")) 
			linea = linea.replace("Province", "provincia");
		else if(linea.contains("\"Town\" : ")) 
			linea = linea.replace("Town", "municipio");
		else if(linea.contains("\"Address\" : ")) 
			linea = linea.replace("Address", "direccion");
		else if (linea.contains("\"Latitude\" : ")) {
			linea = linea.replace("Latitude", "latitud");
			if (linea.contains("\"latitud\" : \"\","))
				linea = "\"latitud\" : \"0\",";
			linea = linea.replace(",", ".");
			linea = removerUltimoCaracter(linea);
			linea = linea + ",";
		}else if(linea.contains("\"Longitude\" : ")) {
			linea = linea.replace("Longitude", "longitud");
			if (linea.contains("\"longitud\" : \"\",")) {
				linea = "\"longitud\" : \"0\",";
			}
			linea = linea.replace(",", ".");

		}
		
		linea = "  " + linea;	
		return linea;
	}

	private boolean comprobarCamposCentrosMet(String linea) {
		if (linea.contains("\"Name\" : ") || linea.contains("\"nombre\" : ")
				|| linea.contains("\"Province\" : ") || linea.contains("\"provincia\" : ")
				|| linea.contains("\"Town\" : ") || linea.contains("\"municipio\" : ")
				|| linea.contains("\"Address\" : ") || linea.contains("\"direccion\" : ")
				|| linea.contains("\"Latitude\" : ") || linea.contains("\"latitud\" : ")
				|| linea.contains("\"Longitude\" : ") || linea.contains("\"longitud\" : ")) {

			return true;
		}
		return false;
	}
	
	 
	/**
	 * Metodo que recibe un elemento Json y procesa los elemementos hijo de manera recursiva
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
				
				if(entrada.getKey().equals("nombre")) {
					centroMet = new CentroMeteorologico();
					centroMet.setNombre(entrada.getValue().getAsString());				
				}
				else if(entrada.getKey().equals("provincia")) {
					centroMet.setProvincia(entrada.getValue().getAsString());					
				}
				else if(entrada.getKey().equals("municipio")) {
					centroMet.setMunicipio(entrada.getValue().getAsString());
				}
				else if(entrada.getKey().equals("direccion")) {
					centroMet.setDireccion(entrada.getValue().getAsString());		
				}
				else if(entrada.getKey().equals("latitud")) {
					centroMet.setLatitud(entrada.getValue().getAsDouble());
				}
				else if(entrada.getKey().equals("longitud")) {
					centroMet.setLongitud(entrada.getValue().getAsDouble());
					CentroMeteorologicoDAO.iniciarSesion();
					CentroMeteorologicoDAO.insertarRegistro(centroMet);	
					CentroMeteorologicoDAO.cerrarSesion();
				}
				procesarElementoJsonCentrosMet(entrada.getValue());
			}
		} 
		else if (elemento.isJsonArray()) {
			JsonArray array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				procesarElementoJsonCentrosMet(entrada);
			}
		}
		return true;
	}

	// MEDICIONES.JSON
	private String remplazoIndex(String linea) {
		linea = linea.trim();
		
		if (linea.contains("\"format\": "))
			linea = linea.replace("format", "formato");
		else if (linea.contains("\"name\": ")) 
			linea = linea.replace("name", "nombre");	
		
		linea = "  " + linea;	
		System.out.println(linea);
		return linea;
	}

	private boolean comprobarCamposIndex(String linea) {
		if (linea.contains("\"format\": ") || linea.contains("\"formato\": ")
				|| linea.contains("\"name\": ") || linea.contains("\"nombre\": ")
				|| linea.contains("\"url\": ") || linea.contains("\"url\": ")) {
			System.out.println("eoooos");
			return true;
		}
		return false;
	}
	
	 
	/**
	 * Metodo que recibe un elemento Json y procesa los elemementos hijo de manera recursiva
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
				if(entrada.getKey().equals("formato")) {
					fuente = new Fuente();
					fuente.setFormato(entrada.getValue().getAsString());				
				}
				else if(entrada.getKey().equals("nombre")) {
					fuente.setNombre(entrada.getValue().getAsString());					
				}
				else if(entrada.getKey().equals("url")) {
					fuente.setUrl(entrada.getValue().getAsString());
					FuenteDAO.iniciarSesion();
					FuenteDAO.insertarRegistro(fuente);
					FuenteDAO.cerrarSesion();
				}
				procesarElementoJsonIndex(entrada.getValue());
			}
		} 
		else if (elemento.isJsonArray()) {
			JsonArray array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				procesarElementoJsonIndex(entrada);
			}
		}
		return true;
	}
}