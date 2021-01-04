package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.EspacioNatural;

public class EspacioNaturalTest {

	@Test
	public void testConstructorEspacioNat() {
		EspacioNatural espacio1 = new EspacioNatural();
		EspacioNatural espacio2 = new EspacioNatural( null, null, null, null, null, null, null, null);
		assertEquals(espacio1.getIdEspacio(), espacio2.getIdEspacio());
	}

}
