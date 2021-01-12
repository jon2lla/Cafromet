package com.cafromet.modeloDAOTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Fuente;
import com.cafromet.modeloDAO.FuenteDAO;

public class FuenteDAOTest {

	
	@Test
	public void testInsertar() {
		Fuente fuente = new Fuente();
		fuente.setNombre("cafromet");
		FuenteDAO.iniciarSesion();
		boolean result=FuenteDAO.insertarRegistro(fuente);
		FuenteDAO.consultarRegistroPorNombre("cafromet");
		FuenteDAO.borrarRegistro("cafromet");
		FuenteDAO.cerrarSesion();
		assertEquals(true, result);
	}
	
	@Test
	public void testEliminar() {
		Fuente fuente = new Fuente();
		fuente.setNombre("cafromet");
		FuenteDAO.iniciarSesion();
		FuenteDAO.insertarRegistro(fuente);
		FuenteDAO.consultarRegistroPorNombre("cafromet");
		boolean result=FuenteDAO.borrarRegistro("cafromet");
		FuenteDAO.cerrarSesion();
		assertEquals(true, result);
	}

}
