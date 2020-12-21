package com.cafromet.modeloDAOTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modeloDAO.PruebasCRUD;

public class PruebasDAOTest {

	@Test
	public void testPruebasCRUD() {
		boolean result=PruebasCRUD.pruebas();
		assertEquals(true, result);
	}
	
}
