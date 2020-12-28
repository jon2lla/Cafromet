package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Municipio_EspacioNatural;

public class Municipio_EspacionNaturalTest {

	@Test
	public void testSetMunicipio() {
		Municipio municipio = new Municipio();
		Municipio_EspacioNatural municipio_EspacioNatural = new Municipio_EspacioNatural();
		municipio_EspacioNatural.setMunicipio(municipio);
		assertEquals(municipio_EspacioNatural.getMunicipio(), municipio);
	}
	
	@Test
	public void testSetEspacioNatural() {
		EspacioNatural espacioNatural = new EspacioNatural();
		Municipio_EspacioNatural municipio_EspacioNatural = new Municipio_EspacioNatural();
		municipio_EspacioNatural.setEspacioNatural(espacioNatural);
		assertEquals(municipio_EspacioNatural.getEspacioNatural(), espacioNatural);
	}

}
