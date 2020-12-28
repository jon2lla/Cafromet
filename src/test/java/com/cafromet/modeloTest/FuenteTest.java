package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Fuente;

public class FuenteTest {

	@Test
	public void testConstructorFuente() {
		Fuente fuente = new Fuente(4);
		Fuente fuente2 = new Fuente(4, null, null, null, null);
		assertEquals(fuente.getId(), fuente2.getId());
	}
	
	@Test
	public void testSetHashFuente() {
		Fuente fuente = new Fuente(4);
		fuente.setHash("a");
		Fuente fuente2 = new Fuente(4);
		assertNotEquals(fuente.getHash(), fuente2.getHash());
	}

}
