package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.Municipio_EspacioNaturalId;

public class Municipio_EspacioNaturalIdTest {
	Municipio_EspacioNaturalId municipio_EspacioNaturalId;
	Municipio_EspacioNaturalId municipio_EspacioNaturalId2;
	
	@Before
	public void onLoad() {
		municipio_EspacioNaturalId = new Municipio_EspacioNaturalId(3, 3);
		municipio_EspacioNaturalId2 = new Municipio_EspacioNaturalId(3, 3);
	}

	@Test
	public void testEquals() {
		municipio_EspacioNaturalId.equals(municipio_EspacioNaturalId2);
		municipio_EspacioNaturalId.setIdEspacio(2);
		assertNotEquals(municipio_EspacioNaturalId, municipio_EspacioNaturalId2);
		
	}
	
	@Test
	public void testHashCode() {
		assertEquals(municipio_EspacioNaturalId.hashCode(), municipio_EspacioNaturalId2.hashCode());
		
	}

}
