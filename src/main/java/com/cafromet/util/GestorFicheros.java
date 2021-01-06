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

import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Provincia;
import com.cafromet.modeloDAO.EspacioNaturalDAO;
import com.cafromet.modeloDAO.MunicipioDAO;
import com.cafromet.modeloDAO.ProvinciaDAO;
import com.cafromet.server.Updater;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;


public class GestorFicheros extends Thread {
	
	private File fichero;
	private int tipo;

	
	public GestorFicheros(File fichero, int tipo) {
		this.fichero = fichero;
		this.tipo = tipo;
	}
	
	@Override
	public void run() {
		switch(tipo) {
		
		case 2:
			insertarProvincias();
			procesarJson();
			transformarJsonPOJOMunicipio(fichero.getAbsolutePath());
			break;
	
		case 3:
			procesarJson();
			transformarJsonPOJOEspacio(fichero.getAbsolutePath());
			break;
		}
	}

    /**
	 * Metodo que recibe un fichero Json como parametro y llama a las funciones para filtrarlo y procesar sus elementos
	 * @param fichero Json a procesar
	 * @return true
     * @throws FileNotFoundException 
	 */  
    public boolean procesarJson(){
    	
    	filtrarJson();
    	JsonParser parser = new JsonParser();
		FileReader fr = null;
	
		try {
			fr = new FileReader(fichero);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonElement datos = parser.parse(fr);
		procesarElementoJson(datos);
    	return true;
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
			brFichero = new BufferedReader(new FileReader(fichero));
			while((linea = brFichero.readLine())!=null) {				
				linea = remplazoHT(linea);
				switch(tipo) {
				
				case 2:
					if(comprobarCamposMunicipios(linea) || comprobarEstructuraJson(linea)) {
						linea = remplazoMunicipios(linea);
						contenido = contenido + linea + "\n";
						System.out.println(linea);
					}
					break;
				
				case 3:
					if(comprobarCamposEspacioNat(linea) || comprobarEstructuraJson(linea)) {
						linea = remplazoEspacioNat(linea);
						contenido = contenido + linea + "\n";
						System.out.println(linea);
					}
					break;
				}	
			}
			fwFichero = new FileWriter(fichero);  
			fwFichero.write(contenido);
		}catch (FileNotFoundException fn ){
			System.out.println("\nNo se encuentra el fichero de carga");
		}catch (IOException io) {
			System.out.println("\nError de E/S ");
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
			System.out.println("\n Cabecera remplazada");
		}
		if(linea.contains("]);")) {
			linea = linea.replace(");", "");
			System.out.println("\n Pie de pagina remplazado");	
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
	
	public boolean insertarProvincias() {

		Provincia bizkaia = new Provincia(48);
		Provincia gipuzkoa = new Provincia(20);
		Provincia araba = new Provincia(1);

		ProvinciaDAO.iniciarSesion();
		ProvinciaDAO.insertarRegistro(bizkaia);
		ProvinciaDAO.insertarRegistro(gipuzkoa);
		ProvinciaDAO.insertarRegistro(araba);

		return false;

	}
	
	// CLASE MUNICIPIOS *
	public String remplazoMunicipios(String linea) {

		if (linea.contains("\"documentName\" : "))
			linea = linea.replace("documentName", "nombre");

		else if (linea.contains("\"turismDescription\" : \"<p>"))
			linea = linea.replace("turismDescription", "descripcion");

		else if (linea.contains("\"latwgs84\" : ")) {

			linea = linea.replace("latwgs84", "latitud");

		} else if (linea.contains("\"lonwgs84\" : ") || (linea.contains("\"longitud\" : "))) {
			linea = linea.replace("lonwgs84", "longitud");
			linea = linea.replace(",", "");

		} else if (linea.contains("\"latitud\" : \"\",")) {

			linea = "  \"latitud\" : \"0\",";

		} else if (linea.contains("\"longitud\" : \"\",")) {

			linea = "  \"longitud\" : \"0\"";
		}

		return linea;
	}

	private boolean comprobarCamposMunicipios(String linea) {
		if (linea.contains("\"documentName\" : ") || linea.contains("\"nombre\" : ")
				|| linea.contains("\"turismDescription\" : \"<p>") || linea.contains("\"descripcion\" : \"<p>")
				|| linea.contains("\"latwgs84\" : ") || linea.contains("\"latitud\" : ")
				|| linea.contains("\"lonwgs84\" : ") || linea.contains("\"longitud\" : ")) {

			return true;
		}
		return false;
	}

	public Municipio[] transformarJsonPOJOMunicipio(String rutaFichero) {
		Gson gson = new Gson();
		String strJson = null;
		try {
			strJson = readFileAsStringEspacio(rutaFichero);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Municipio[] municipios = gson.fromJson(strJson, Municipio[].class);

		for (Municipio municipio : municipios) {
			System.out.println(municipio.getNombre());

			MunicipioDAO.iniciarSesion();
			MunicipioDAO.insertarRegistro(municipio);

		}
		return municipios;
	}

	public static String readFileAsStringMunicipio(String file) throws IOException {
		return new String(Files.readAllBytes(Paths.get(file)));
	}
	
	// CLASE ESPACIO NATURALES *
	public String remplazoEspacioNat(String linea) {

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

		} else if (linea.contains("\"latitud\" : \"\",")) {

			linea = "  \"latitud\" : \"0\",";

		} else if (linea.contains("\"longitud\" : \"\",")) {

			linea = "  \"longitud\" : \"0\",";
			
		}else if(linea.contains("\"lonwgs84\" : ")) {
			linea = linea.replace("lonwgs84", "longitud");	
		
		}else if(linea.contains("\"municipality\" : ")) {
			linea = linea.replace("municipality", "municipio");	
			linea = linea.replace(",", "");
		}
			
		return linea;
	}
	
	private boolean comprobarCamposEspacioNat(String linea) {
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
			|| linea.contains("\"municipio\" : ")) {
		
			return true;
		}
		return false;
	}
	
	public EspacioNatural[] transformarJsonPOJOEspacio(String rutaFichero) {
		Gson gson = new Gson();
		String strJson = null;
		try {
			strJson = readFileAsStringEspacio(rutaFichero);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EspacioNatural[] espaciosNaturales = gson.fromJson(strJson, EspacioNatural[].class);
		
		for (EspacioNatural espacio : espaciosNaturales) {
		    System.out.println(espacio.getNombre());
//		    System.out.println(espacio.getMunicipio_EspacioNaturals().toString());
		    EspacioNaturalDAO.iniciarSesion();
		    EspacioNaturalDAO.insertarRegistro(espacio);
		    
		}
		return espaciosNaturales;
	}
	
	 public static String readFileAsStringEspacio(String file) throws IOException{
	        return new String(Files.readAllBytes(Paths.get(file)));
	    }

	 
	

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	/**
	 * Metodo que recibe un elemento Json y procesa los elemementos hijo de manera recursiva
	 * @param Elemento Json a procesar
	 * @return true
	 */
	public boolean procesarElementoJson(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			
			System.out.println("  (Objeto)");
			JsonObject obj = elemento.getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> entradas = obj.entrySet(); 
			Iterator<Map.Entry<String, JsonElement>> iter = entradas.iterator();
			
			while (iter.hasNext()) {
				Map.Entry<String, JsonElement> entrada = iter.next();
				System.out.println("#CLAVE:\n  " + entrada.getKey());
				System.out.println("$VALOR:");
				procesarElementoJson(entrada.getValue());
			}
		} 
		else if (elemento.isJsonArray()) {
			JsonArray array = elemento.getAsJsonArray();
			System.out.println("  (Array) // Numero de elementos: " + array.size());
			Iterator<JsonElement> iter = array.iterator();
			
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				procesarElementoJson(entrada);
			}
		} 
		else if (elemento.isJsonPrimitive()) {
			System.out.print("  (Primitiva) ");
			JsonPrimitive valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
				System.out.println("  Booleano => " + valor.getAsBoolean());
			} 
			else if (valor.isNumber()) {
				System.out.println("  Numero => " + valor.getAsNumber());
			} 
			else if (valor.isString()) {
				System.out.println("  String => " + valor.getAsString());
			}
			System.out.println();
		} 
		else if (elemento.isJsonNull()) {
			System.out.println("  NULL");
		} 
		else {
			System.out.println("  ---");
		}
		return true;
	}
}
