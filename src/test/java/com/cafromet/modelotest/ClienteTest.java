package com.cafromet.modelotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.Cliente;

public class ClienteTest {
	Cliente cliente1;
	Cliente cliente2;
	
	@Before
	public void setup() {
		cliente1 = new Cliente();
		cliente2 = new Cliente();
	}
	@Test
	public void testConstructorCliente() {
		 cliente1 = new Cliente();
		 cliente2 = new Cliente( null, null, null, null);
		assertEquals(cliente1.getIdCliente(), cliente2.getIdCliente());
	}
	@Test
	public void testIsnull() {
		boolean result =cliente1.isNull(cliente1);
		assertEquals(true, result);
	}
}
