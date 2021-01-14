package com.cafromet.clienteTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cafromet.cliente.ControladorVentanMunicipio;
import com.cafromet.cliente.VentanaMunicipio;
import com.cafromet.server.Peticion;

public class ControladorVentaClienteTest {

	ControladorVentanMunicipio Controlador;
	VentanaMunicipio ventanaMunicipio;
	
	@Before
	public void setup() {
		ventanaMunicipio = Mockito.mock(VentanaMunicipio.class);
		Controlador = new ControladorVentanMunicipio(ventanaMunicipio);
	}
	
	@Test
	public void testEnviarPeticion() {
		boolean result=Controlador.enviarPeticion("prueba", Peticion.p103);
		assertEquals(false,result);	
	}

}
