package com.cafromet.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

public class GestorFicheros {
	
	public static void leerJson(){
		try {
			JsonParser parser = new JsonParser();
			FileReader fr = new FileReader("stocks.json");
			JsonElement datos = parser.parse(fr);
		
			dumpJSONElement(datos);
		}catch(IOException ioe) {
			System.out.println("ERROR: INPUT/OUTPUT EXCEPTION");

		}
		
	}

	public static void dumpJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			
			System.out.println("  (Objeto)");
			JsonObject obj = elemento.getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			Iterator<Map.Entry<String, JsonElement>> iter = entradas.iterator();
			
			while (iter.hasNext()) {
				Map.Entry<String, JsonElement> entrada = iter.next();
				System.out.println("#CLAVE:\n  " + entrada.getKey());
				System.out.println("$VALOR:");
				dumpJSONElement(entrada.getValue());
			}
		} 
		else if (elemento.isJsonArray()) {
			JsonArray array = elemento.getAsJsonArray();
			System.out.println("  (Array) // Numero de elementos: " + array.size());
			Iterator<JsonElement> iter = array.iterator();
			
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpJSONElement(entrada);
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
	}
}