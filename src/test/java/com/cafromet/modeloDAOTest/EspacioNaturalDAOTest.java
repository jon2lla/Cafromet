package com.cafromet.modeloDAOTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Municipio;
import com.cafromet.modeloDAO.EspacioNaturalDAO;
import com.cafromet.modeloDAO.MunicipioDAO;

public class EspacioNaturalDAOTest {

	@Test
	public void testInsertar() {
		EspacioNatural espacioNatural = new EspacioNatural();
		espacioNatural.setNombre("cafromet");
		EspacioNaturalDAO.iniciarSesion();
		boolean result =EspacioNaturalDAO.insertarRegistro(espacioNatural);
		EspacioNaturalDAO.consultarRegistroPorNombre("cafromet");
		EspacioNaturalDAO.borrarRegistro("cafromet");
		EspacioNaturalDAO.cerrarSesion();
		assertEquals(true, result);
	}
	@Test
	public void testEliminar() {
		EspacioNatural espacioNatural = new EspacioNatural();
		espacioNatural.setNombre("cafromet");
		EspacioNaturalDAO.iniciarSesion();
		EspacioNaturalDAO.insertarRegistro(espacioNatural);
		EspacioNaturalDAO.consultarRegistroPorNombre("cafromet");
		boolean result = EspacioNaturalDAO.borrarRegistro("cafromet");
		EspacioNaturalDAO.cerrarSesion();
		assertEquals(true, result);
	}
}
