package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Provincia;

public class ProvinciaTest {

	@Test
	public void testContrustorProvincia() {
		Provincia provincia1 = new Provincia(4);
		Provincia provincia2 = new Provincia(4, null, null);
		assertEquals(provincia1.getIdProvincia(), provincia2.getIdProvincia());
	}

}
