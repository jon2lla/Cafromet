package com.cafromet.modeloDAOTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Municipio;
import com.cafromet.modeloDAO.MunicipioDAO;

public class MunicipioDAOTest {

	@Test
	public void testInsertar() {
		Municipio municipio = new Municipio();
		municipio.setNombre("cafromet");
		MunicipioDAO.iniciarSesion();
		boolean result =MunicipioDAO.insertarRegistro(municipio);
		MunicipioDAO.consultarRegistro("cafromet");
		MunicipioDAO.borrarRegistro("cafromet");
		MunicipioDAO.cerrarSesion();
		assertEquals(true, result);
	}
	@Test
	public void testEliminar() {
		Municipio municipio = new Municipio();
		municipio.setNombre("cafromet");
		MunicipioDAO.iniciarSesion();
		MunicipioDAO.insertarRegistro(municipio);
		MunicipioDAO.consultarRegistro("cafromet");
		boolean result =MunicipioDAO.borrarRegistro("cafromet");
		MunicipioDAO.cerrarSesion();
		assertEquals(true, result);
	}
}
