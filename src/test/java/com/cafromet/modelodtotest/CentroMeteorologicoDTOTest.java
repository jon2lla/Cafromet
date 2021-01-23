package com.cafromet.modelodtotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelodto.CentroMeteorologicoDTO;

public class CentroMeteorologicoDTOTest {

	CentroMeteorologicoDTO centroDTO;
	CentroMeteorologicoDTO centroDTO2;
	@Before
	public void setUp(){
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

}
