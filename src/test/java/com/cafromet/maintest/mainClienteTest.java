package com.cafromet.maintest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.main.MainCliente;

public class mainClienteTest {

	@Test
	public void test() {
		assertTrue(MainCliente.inicioCliente());
	}

}
