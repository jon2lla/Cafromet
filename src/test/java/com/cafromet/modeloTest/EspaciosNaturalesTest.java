package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.EspaciosNaturales;

public class EspaciosNaturalesTest {

	@Test
	public void test() {
		EspaciosNaturales espacio1 = new EspaciosNaturales(4);
		EspaciosNaturales espacio2 = new EspaciosNaturales(4, null, null, null, null, null, null, null, null);
		assertEquals(espacio1.getIdEspacio(), espacio2.getIdEspacio());
	}

}
