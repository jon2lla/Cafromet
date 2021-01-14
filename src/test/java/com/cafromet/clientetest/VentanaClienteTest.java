package com.cafromet.clientetest;


import static org.junit.Assert.assertTrue;

import java.io.IOError;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.cliente.VentanaCliente;

public class VentanaClienteTest {
	VentanaCliente ventanaCliente;
	
	@Before
	public void setUp(){
		ventanaCliente = new VentanaCliente();
		ventanaCliente.setVisible(true);
	}

	@Test
	public void test() {
		
	}

}
