package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Municipio;

public class MunicipioTest {

	@Test
	public void testConstructorMunicipio() {
		Municipio municipio1 = new Municipio(null, null, null, null, null);
		Municipio municipio2= new Municipio(null, null, null, null, null);
		
		assertEquals(municipio1.getIdMunicipio(), municipio2.getIdMunicipio());
		
	}
	
}
