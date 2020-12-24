package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Municipios;

public class MunicipiosTest {

	@Test
	public void testMunicipioConstructor() {
		Municipios municipio1 = new Municipios(4);

		Municipios municipio2= new Municipios(4, null, null, null, null);
		
		assertEquals(municipio1.getIdMunicipio(), municipio2.getIdMunicipio());
		
	}
	
}
