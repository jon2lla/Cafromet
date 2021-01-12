package com.cafromet.modeloDAOTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Fuente;
import com.cafromet.modelo.Municipio;
import com.cafromet.modeloDAO.CentroMeteorologicoDAO;
import com.cafromet.modeloDAO.FuenteDAO;
import com.cafromet.modeloDAO.MunicipioDAO;

public class CentroMeteorologicoDAOTest {

	@Test
	public void testInsertar() {
		CentroMeteorologico centroMeteorologico = new CentroMeteorologico();
		centroMeteorologico.setNombre("cafromet");
		CentroMeteorologicoDAO.iniciarSesion();
		boolean result = CentroMeteorologicoDAO.insertarRegistro(centroMeteorologico);
		CentroMeteorologicoDAO.consultarRegistro("cafromet");
		CentroMeteorologicoDAO.borrarRegistro("cafromet");
		CentroMeteorologicoDAO.cerrarSesion();
		assertEquals(true, result);
	}
	@Test
	public void testEliminar() {
		CentroMeteorologico centroMeteorologico = new CentroMeteorologico();
		centroMeteorologico.setNombre("cafromet");
		CentroMeteorologicoDAO.iniciarSesion();
		CentroMeteorologicoDAO.insertarRegistro(centroMeteorologico);
		CentroMeteorologicoDAO.consultarRegistro("cafromet");
		boolean result = CentroMeteorologicoDAO.borrarRegistro("cafromet");
		CentroMeteorologicoDAO.cerrarSesion();
		assertEquals(true, result);
	}
}
