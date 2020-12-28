package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.CentroMeteorologico;

public class CentroMeteorologicoTest {

	@Test
	public void testConstructorCentroMet() {
		CentroMeteorologico centro1 = new CentroMeteorologico(4);
		CentroMeteorologico centro2 = new CentroMeteorologico(4, null, null, null, null, null, null);
		assertEquals(centro1.getIdCentroMet(), centro2.getIdCentroMet());
	}

}
