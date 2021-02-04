package com.cafromet.modelodaotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Municipio_EspacioNatural;
import com.cafromet.modelo.Municipio_EspacioNaturalId;
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
		provincia = new Provincia();
		MunicipioDAO.iniciarSesion();		
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
	
	@Test
	public void test() {	
		municipio = MunicipioDAO.consultarRegistro("sada", 2);
		Municipio municipio2 = new Municipio();
		municipio2.setNombre("sada");
		assertNotEquals(municipio, municipio2);
		MunicipioDAO.cerrarSesion();	
	}
	@Test
	public void test2() {		
		List<Municipio_EspacioNatural> municipios_EspaciosNaturales = new ArrayList<Municipio_EspacioNatural>();
		Municipio_EspacioNatural municipio_EspacioNatural = new Municipio_EspacioNatural();
		EspacioNatural espacioNatural = new EspacioNatural();
		espacioNatural.setIdEspacio(2);
		municipio_EspacioNatural.setEspacioNatural(espacioNatural);
		Municipio_EspacioNaturalId id = new Municipio_EspacioNaturalId();
		id.setIdEspacio(2);
		id.setIdMunicipio(1);
		municipio_EspacioNatural.setId(id );
		municipios_EspaciosNaturales.add(municipio_EspacioNatural);
		List<Municipio> municipio= MunicipioDAO.consultarRegistrosPorEspacio(municipios_EspaciosNaturales);
		List<Municipio> municipio2 = new ArrayList<Municipio>();
		int tam1 = municipio.size();
		int tam2 = municipio2.size();
		assertEquals(tam1, tam2);
		MunicipioDAO.cerrarSesion();	
	}
	
	@Test
	public void test4() {		
		municipio = MunicipioDAO.consultarMuni(2);
		Municipio municipio2 = new Municipio();
		municipio2.setIdMunicipio(2);
		assertEquals(municipio, municipio2);
		MunicipioDAO.cerrarSesion();	
	}
	
}
