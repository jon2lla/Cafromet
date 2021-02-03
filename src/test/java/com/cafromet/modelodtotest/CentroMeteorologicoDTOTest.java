package com.cafromet.modelodtotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelodto.CentroMeteorologicoDTO;

public class CentroMeteorologicoDTOTest {

	CentroMeteorologicoDTO centroDTO;
	CentroMeteorologicoDTO centroDTO2;

	@Before
	public void setUp() {
		centroDTO = new CentroMeteorologicoDTO();
		centroDTO2 = new CentroMeteorologicoDTO();
	}

	@Test
	public void test() {
		centroDTO.setDireccion("casa");
		centroDTO.setIdCentroMet(1);
		centroDTO.setMunicipio("casa");
		centroDTO.setNombre("casa");
		centroDTO2.getDireccion();
		centroDTO2.getIdCentroMet();
		centroDTO2.getMunicipio();
		centroDTO2.getNombre();
		assertNotEquals(centroDTO, centroDTO2);
	}

	@Test
	public void test2() {
		CentroMeteorologico centroMeteorologico = new CentroMeteorologico();
		centroMeteorologico.setIdCentroMet(2);
		centroMeteorologico.setNombre("prueba");
		Municipio municipio = new Municipio();
		municipio.setIdMunicipio(1);
		municipio.setNombre("0.5");
		centroMeteorologico.setMunicipio(municipio );
		centroDTO = new CentroMeteorologicoDTO(centroMeteorologico);
		centroDTO.setLatitud(1.0);
		centroDTO.setLongitud(2.0);
		centroDTO2.getLatitud();
		centroDTO.getLongitud();
		assertNotEquals(centroDTO, centroDTO2);
	}
}
