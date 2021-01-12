package com.cafromet.modeloDAOTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Provincia;
import com.cafromet.modeloDAO.MunicipioDAO;
import com.cafromet.modeloDAO.ProvinciaDAO;

public class ProvinciaDAOTest {

	@Test
	public void testInsertar() {		
		Provincia provincia = new Provincia();
		provincia.setNombre("cafromet");
		ProvinciaDAO.iniciarSesion();
		boolean result =ProvinciaDAO.insertarRegistro(provincia);
		ProvinciaDAO.consultarRegistro("cafromet");
		ProvinciaDAO.borrarRegistro("cafromet");
		ProvinciaDAO.cerrarSesion();
		assertEquals(true, result);
	}
	@Test
	public void testEliminar() {
		Provincia provincia = new Provincia();
		provincia.setNombre("cafromet");
		ProvinciaDAO.iniciarSesion();
		ProvinciaDAO.insertarRegistro(provincia);
		ProvinciaDAO.consultarRegistro("cafromet");
		boolean result =ProvinciaDAO.borrarRegistro("cafromet");
		ProvinciaDAO.cerrarSesion();
		assertEquals(true, result);
	}

}
