package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Cliente;

public class ClienteTest {

	@Test
	public void test() {
		Cliente cliente1 = new Cliente(4);
		Cliente cliente2 = new Cliente(4, null,null);
		assertEquals(cliente1.getIdCliente(), cliente2.getIdCliente());
	}

}
