package com.cafromet.modelodaotest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Provincia;
import com.cafromet.modelodao.MunicipioDAO;
import com.cafromet.modelodao.ProvinciaDAO;

public class MunicipioDAOTest {
	
	Municipio municipio;
	Provincia provincia;
	@Before
	public void setup() {
		municipio = new Municipio();
		municipio.setNombre("cafromet");
		MunicipioDAO.iniciarSesion();
		provincia = new Provincia();
	}
	
	@Test
	public void testInsertar() {		
		boolean result =MunicipioDAO.insertarRegistro(municipio);
		MunicipioDAO.consultarRegistro("cafromet");
		MunicipioDAO.borrarRegistro("cafromet");
		MunicipioDAO.cerrarSesion();
		assertEquals(true, result);
	}
	@Test
	public void testEliminar() {
		MunicipioDAO.insertarRegistro(municipio);
		MunicipioDAO.consultarRegistro("cafromet");
		List<Municipio> municipio = MunicipioDAO.consultarRegistros(); 
		boolean result =MunicipioDAO.borrarRegistro("cafromet");
		MunicipioDAO.cerrarSesion();
		assertEquals(true, result);
	}
	
	@Test
	public void testConsultar() {
		municipio = new Municipio();
		provincia = new Provincia();
		municipio.setNombre("cafromet");
		municipio.setIdMunicipio(500);
		provincia.setIdProvincia(50);
		provincia.setNombre("cafromet");
		municipio.setProvincia(provincia);
		MunicipioDAO.iniciarSesion();
		ProvinciaDAO.iniciarSesion();
		ProvinciaDAO.insertarRegistro(provincia);
		boolean result =MunicipioDAO.insertarRegistro(municipio);
		ProvinciaDAO.consultarRegistro(50);
		MunicipioDAO.consultarRegistro(500,50);
		ProvinciaDAO.borrarRegistro("cafromet");
		MunicipioDAO.borrarRegistro("cafromet");
		MunicipioDAO.cerrarSesion();
		ProvinciaDAO.cerrarSesion();
		assertEquals(true, result);
	}
	
}
