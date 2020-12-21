package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Fuentes;

public class FuentesTest {

	@Test
	public void test() {
		Fuentes fuentes1 = new Fuentes(4);
		Fuentes fuentes2 = new Fuentes(4, null, null, null, null);
		assertEquals(fuentes1.getIdIndex(), fuentes2.getIdIndex());
	}

}
