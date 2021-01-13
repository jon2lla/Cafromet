package com.cafromet.modeloDAOTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.Provincia;
import com.cafromet.modeloDAO.ProvinciaDAO;

public class ProvinciaDAOTest {
	
	Provincia provincia;
	
	@Before
	public void setup() {
		provincia = new Provincia();		
		provincia.setNombre("cafromet");
		ProvinciaDAO.iniciarSesion();
		
	}
	
	@Test
	public void testInsertar() {		
		boolean result =ProvinciaDAO.insertarRegistro(provincia);
		ProvinciaDAO.consultarRegistro("cafromet");
		ProvinciaDAO.borrarRegistro("cafromet");
		ProvinciaDAO.cerrarSesion();
		assertEquals(true, result);
	}
	@Test
	public void testEliminar() {
		ProvinciaDAO.insertarRegistro(provincia);
		ProvinciaDAO.consultarRegistro("cafromet");
		boolean result =ProvinciaDAO.borrarRegistro("cafromet");
		ProvinciaDAO.cerrarSesion();
		assertEquals(true, result);
	}

}
