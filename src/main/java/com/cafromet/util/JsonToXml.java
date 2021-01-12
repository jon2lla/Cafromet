package com.cafromet.util;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonToXml extends Thread {
	private String path;
	public JsonToXml() {}
	
	public void convertJsonToXml(String nombreJson, String raiz, String nombreEtiqueta, String nombreXml, String ruta) {
		try {
			String path = ruta;
			path = path.concat(nombreXml);
			
			JsonParser parser = new JsonParser();
			FileReader fr = new FileReader(nombreJson);
			JsonElement datos = parser.parse(fr);

			String jsonFile = datos.toString();
			String xml = "";
			JSONArray jsonarray = new JSONArray(jsonFile);

			xml = xml + XML.toString(jsonarray, nombreEtiqueta);

			String xmlSrt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<" + raiz + ">" + xml + "</" + raiz + ">";

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(xmlSrt)));

			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer trasnFormer = transFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(path));
			trasnFormer.transform(source, result);
			
			System.out.println("xml " + nombreXml + " creado correctamente" );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
