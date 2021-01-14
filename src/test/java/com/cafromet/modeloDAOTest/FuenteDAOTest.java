package com.cafromet.modeloDAOTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.Fuente;
import com.cafromet.modelodao.FuenteDAO;

public class FuenteDAOTest {
	Fuente fuente;
	
	@Before
	public void setup() {
		fuente = new Fuente();
		fuente.setNombre("cafromet");
		FuenteDAO.iniciarSesion();
	}
	
	@Test
	public void testInsertar() {		
		boolean result=FuenteDAO.insertarRegistro(fuente);
		FuenteDAO.consultarRegistroPorNombre("cafromet");
		FuenteDAO.borrarRegistro("cafromet");
		FuenteDAO.cerrarSesion();
		assertEquals(true, result);
	}
	
	@Test
	public void testEliminar() {
		FuenteDAO.insertarRegistro(fuente);
		FuenteDAO.consultarRegistroPorNombre("cafromet");
		boolean result=FuenteDAO.borrarRegistro("cafromet");
		FuenteDAO.cerrarSesion();
		assertEquals(true, result);
	}

}
