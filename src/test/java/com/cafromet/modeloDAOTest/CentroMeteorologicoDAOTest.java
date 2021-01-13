package com.cafromet.modeloDAOTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modeloDAO.CentroMeteorologicoDAO;

public class CentroMeteorologicoDAOTest {
	CentroMeteorologico centroMeteorologico;
	@Before
	public void setup() {
		centroMeteorologico = new CentroMeteorologico();
		centroMeteorologico.setNombre("cafromet");
		CentroMeteorologicoDAO.iniciarSesion();
	}
	
	@Test
	public void testInsertar() {	
		boolean result = CentroMeteorologicoDAO.insertarRegistro(centroMeteorologico);
		CentroMeteorologicoDAO.consultarRegistro("cafromet");
		CentroMeteorologicoDAO.actualizarRegistro(centroMeteorologico);
		CentroMeteorologicoDAO.borrarRegistro("cafromet");
		CentroMeteorologicoDAO.cerrarSesion();
		assertEquals(true, result);
	}
	@Test
	public void testEliminar() {
		CentroMeteorologicoDAO.insertarRegistro(centroMeteorologico);
		CentroMeteorologicoDAO.consultarRegistro("cafromet");
		List<CentroMeteorologico> centros = CentroMeteorologicoDAO.consultarRegistros(); 
		boolean result = CentroMeteorologicoDAO.borrarRegistro("cafromet");
		CentroMeteorologicoDAO.cerrarSesion();
		assertEquals(true, result);
	}
	

}
