package com.cafromet.utiltest;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cafromet.util.GestorFicheros;

public class GestorFicheroTest {
	GestorFicheros gestorFicheros;
	@Before
	public void setup() {
		gestorFicheros = new GestorFicheros(null, 0);
	}
	@Test
	public void test() {
		GestorFicheros gestorFicheros = new GestorFicheros(new File("casa"), 1);
		GestorFicheros gestorFicheros2 = new GestorFicheros(new File("casa"), 1);
		assertNotEquals(gestorFicheros.getId(), gestorFicheros2.getId());
	}
	
	@Test
	public void testContructor() {
		GestorFicheros gestorFicheros = new GestorFicheros(new File("casa"),new File("casa"), 1);
		GestorFicheros gestorFicheros2 = new GestorFicheros(new File("casa"),new File("casa"), 1);
		assertNotEquals(gestorFicheros.getId(), gestorFicheros2.getId());
	}
	@Test
	public void testContructor2() {
		GestorFicheros gestorFicheros = new GestorFicheros(new File("casa"),new File("casa"), 1,null);
		GestorFicheros gestorFicheros2 = new GestorFicheros(new File("casa"),new File("casa"), 1,null);
		assertNotEquals(gestorFicheros.getId(), gestorFicheros2.getId());
	}

	@Test
	public void testcomprobarEstructuraJsonFalse() {
		gestorFicheros = new GestorFicheros(null, 0);
		boolean rs =gestorFicheros.comprobarEstructuraJson("");
		assertEquals(false, rs);
	}
	@Test
	public void testcomprobarEstructuraJson() {
		gestorFicheros = new GestorFicheros(null, 0);
		boolean rs =gestorFicheros.comprobarEstructuraJson("[ {");
		assertEquals(true, rs);
	}

	@Test
	public void testcomprobarCamposMunicipios() {
		gestorFicheros = new GestorFicheros(null, 0);
		boolean rs =gestorFicheros.comprobarCamposEspacioNat("\"territorycode\" : ");
		assertEquals(true, rs);
	}
	@Test
	public void testEstructuraJsonIndex() {
		gestorFicheros = new GestorFicheros(null, 0);
		boolean rs =gestorFicheros.comprobarEstructuraJsonIndex("[");
		assertEquals(true, rs);		
	}
	@Test
	public void testCamposCentrosMet() {
		gestorFicheros = new GestorFicheros(null, 0);
		boolean rs =gestorFicheros.comprobarCamposCentrosMet("\"longitud\" : ");
		assertEquals(true, rs);		
	}
	@Test
	public void testCamposIndex() {
		gestorFicheros = new GestorFicheros(null, 0);
		boolean rs =gestorFicheros.comprobarCamposIndex("\"url\": ");
		assertEquals(true, rs);		
	}
	@Test
	public void testCamposMunicipios() {
		gestorFicheros = new GestorFicheros(null, 0);
		boolean rs =gestorFicheros.comprobarCamposMunicipios("\"idprovincia\" : ");
		assertEquals(true, rs);		
	}
	@Test
	public void testRempazoHT() {
		gestorFicheros = new GestorFicheros(null, 0);
		String linea1=gestorFicheros.remplazoHT("jsonCallback(");
		String linea2="";
		assertEquals(linea1, linea2);
	}	
	@Test
	public void testRempazoHT2() {
		gestorFicheros = new GestorFicheros(null, 0);
		String linea1=gestorFicheros.remplazoHT("]);");
		String linea2="]";
		assertEquals(linea1, linea2);
	}
	@Test
	public void testremoverUltimoCaracter() {
		String linea1=GestorFicheros.removerUltimoCaracter("]);");
		String linea2="])";
		assertEquals(linea1, linea2);
	}
	@Test
	public void testEliminarFichero() {
		File fichero = new File("pueblos.json");
		assertTrue(GestorFicheros.eliminarFichero(fichero ));
	}
	@Test
	public void testcomprobarEstructuraJsonIndex1() {
		assertTrue(gestorFicheros.comprobarEstructuraJsonIndex("["));
	}
	@Test
	public void testcomprobarEstructuraJsonIndex2() {
		assertTrue(gestorFicheros.comprobarEstructuraJsonIndex("},"));
	}
	@Test
	public void testcomprobarEstructuraJsonIndex3() {
		assertTrue(gestorFicheros.comprobarEstructuraJsonIndex("    {"));
	}
	@Test
	public void testcomprobarEstructuraJsonIndex4() {
		assertTrue(gestorFicheros.comprobarEstructuraJsonIndex("    }"));
	}
	@Test
	public void testcomprobarEstructuraJsonIndex5() {
		assertTrue(gestorFicheros.comprobarEstructuraJsonIndex("]"));
	}
	@Test
	public void testcomprobarEstructuraJsonIndex6() {
		assertFalse(gestorFicheros.comprobarEstructuraJsonIndex("2"));
	}
	
	@Test
	public void testremplazoHTIndex() {
		String linea="";
		String prueba=gestorFicheros.remplazoHTIndex("\"lastUpdateDate\": ");
		assertEquals(linea, prueba);
	}
	@Test
	public void testremplazoHTIndex2() {
		String linea="[";
		String prueba=gestorFicheros.remplazoHTIndex("\"aggregated\":");
		assertEquals(linea, prueba);
	}
}
