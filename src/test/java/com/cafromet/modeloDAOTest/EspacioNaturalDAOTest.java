package com.cafromet.modeloDAOTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modeloDAO.EspacioNaturalDAO;

public class EspacioNaturalDAOTest {
	
	EspacioNatural espacioNatural;
	
	@Before
	public void setup() {
		espacioNatural = new EspacioNatural();
		espacioNatural.setNombre("cafromet");
		EspacioNaturalDAO.iniciarSesion();
	}
	
	@Test
	public void testInsertar() {	
		boolean result =EspacioNaturalDAO.insertarRegistro(espacioNatural);
		EspacioNaturalDAO.consultarRegistroPorNombre("cafromet");
		EspacioNaturalDAO.borrarRegistro("cafromet");
		EspacioNaturalDAO.cerrarSesion();
		assertEquals(true, result);
	}
	@Test
	public void testEliminar() {
		EspacioNaturalDAO.insertarRegistro(espacioNatural);
		EspacioNaturalDAO.consultarRegistroPorNombre("cafromet");
		boolean result = EspacioNaturalDAO.borrarRegistro("cafromet");
		EspacioNaturalDAO.cerrarSesion();
		assertEquals(true, result);
	}
	
	
}
