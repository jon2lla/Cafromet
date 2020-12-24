package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Provincias;

public class ProvinciasTest {

	@Test
	public void test() {
		Provincias provincia1 = new Provincias(4);
		Provincias provincia2 = new Provincias(4, null, null);
		assertEquals(provincia1.getIdProvincia(), provincia2.getIdProvincia());
	}

}
