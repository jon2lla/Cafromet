package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.CentrosMeteorologicos;

public class CentrosMeteorologicosTest {

	@Test
	public void test() {
		CentrosMeteorologicos centro1 = new CentrosMeteorologicos(4);
		CentrosMeteorologicos centro2 = new CentrosMeteorologicos(4, null, null, null, null, null, null);
		assertEquals(centro1.getIdCentroMet(), centro2.getIdCentroMet());
	}

}
