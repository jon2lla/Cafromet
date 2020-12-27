package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Fuente;

public class FuenteTest {

	@Test
	public void test() {
		Fuente fuente1 = new Fuente(4);
		Fuente fuente2 = new Fuente(4, null, null, null, null);
		assertEquals(fuente1.getId(), fuente2.getId());
	}

}
