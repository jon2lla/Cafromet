package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Clientes;

public class ClientesTest {

	@Test
	public void test() {
		Clientes cliente1 = new Clientes(4);
		Clientes cliente2 = new Clientes(4, null,null);
		assertEquals(cliente1.getIdCliente(), cliente2.getIdCliente());
	}

}
