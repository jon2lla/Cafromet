package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.EspacioNatural;

public class EspacioNaturalTest {

	@Test
	public void test() {
		EspacioNatural espacio1 = new EspacioNatural(4);
		EspacioNatural espacio2 = new EspacioNatural(4, null, null, null, null, null, null, null, null);
		assertEquals(espacio1.getIdEspacio(), espacio2.getIdEspacio());
	}

}
