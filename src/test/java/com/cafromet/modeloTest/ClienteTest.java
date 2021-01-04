package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Cliente;

public class ClienteTest {

	@Test
	public void testConstructorCliente() {
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente( null,null);
		assertEquals(cliente1.getIdCliente(), cliente2.getIdCliente());
	}

}
