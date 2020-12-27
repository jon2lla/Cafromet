package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Municipio;

public class MunicipioTest {

	@Test
	public void testMunicipioConstructor() {
		Municipio municipio1 = new Municipio(4);

		Municipio municipio2= new Municipio(4, null, null, null, null);
		
		assertEquals(municipio1.getIdMunicipio(), municipio2.getIdMunicipio());
		
	}
	
}
