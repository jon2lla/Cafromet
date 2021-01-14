package com.cafromet.clientetest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cafromet.cliente.ControladorVentanMunicipio;
import com.cafromet.cliente.VentanaMunicipio;
import com.cafromet.server.Peticion;

public class ControladorVentaClienteTest {

	ControladorVentanMunicipio controlador;
	VentanaMunicipio ventanaMunicipio;
	
	@Before
	public void setup() {
		try {
			
			controlador = new ControladorVentanMunicipio(ventanaMunicipio);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEnviarPeticion() {
		boolean result = controlador.enviarPeticion("prueba", Peticion.p103);
		assertEquals(false, result);	
	}

}
