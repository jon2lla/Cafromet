package com.cafromet.modelotest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Municipio_EspacioNatural;
import com.cafromet.modelo.Municipio_EspacioNaturalId;

public class Municipio_EspacionNaturalTest {
	
	Municipio municipio;
	Municipio_EspacioNatural municipio_EspacioNatural;
	Municipio_EspacioNatural municipio_EspacioNatural2;
	EspacioNatural espacioNatural;
	Municipio_EspacioNaturalId Municipio_EspacioNaturalId;
	
	@Before
	public void setup() {
		municipio = new Municipio();
		municipio_EspacioNatural = new Municipio_EspacioNatural();
		municipio_EspacioNatural2 = new Municipio_EspacioNatural();
		espacioNatural = new EspacioNatural();
		Municipio_EspacioNaturalId = new Municipio_EspacioNaturalId();
	}
	
	@Test
	public void testSetMunicipio() {	
		municipio_EspacioNatural.setMunicipio(municipio);
		assertEquals(municipio_EspacioNatural.getMunicipio(), municipio);
	}
	
	@Test
	public void testSetEspacioNatural() {
		municipio_EspacioNatural.setEspacioNatural(espacioNatural);
		assertEquals(municipio_EspacioNatural.getEspacioNatural(), espacioNatural);
	}
	@Test
	public void testConstructor() {
		Municipio_EspacioNaturalId.setIdEspacio(1);
		municipio_EspacioNatural = new Municipio_EspacioNatural(Municipio_EspacioNaturalId, espacioNatural, municipio);
		municipio_EspacioNatural2 = new Municipio_EspacioNatural(Municipio_EspacioNaturalId, espacioNatural, municipio);	
		assertEquals(municipio_EspacioNatural.getId(), municipio_EspacioNatural2.getId());
	}

}
