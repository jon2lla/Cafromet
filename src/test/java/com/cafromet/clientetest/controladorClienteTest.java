package com.cafromet.clientetest;

import static org.junit.Assert.assertTrue;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import com.cafromet.cliente.ControladorCliente;
import com.cafromet.cliente.ControladorVentanMunicipio;
import com.cafromet.cliente.IOListenerClt;
import com.cafromet.cliente.VentanaCliente;
import com.cafromet.cliente.VentanaMunicipio;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class controladorClienteTest {
	
	VentanaCliente VentanaCliente;
	ControladorCliente controladorCliente;
	Datos datos;
	IOListenerClt IOListenerClt;
	VentanaMunicipio VentanaMunicipio;
	@Spy
	ControladorVentanMunicipio ControladorMunicipio;
	
	@Test
	public void test() {
		VentanaCliente = new VentanaCliente();
		controladorCliente = new ControladorCliente(VentanaCliente);
		controladorCliente = Mockito.spy(controladorCliente);
		Mockito.doReturn(true).when(controladorCliente).procesarRecepcion();
		boolean result =controladorCliente.enviarPeticion("prueba", Peticiones.p101a);
		assertTrue(result);
	}
	
	@Test
	public void ts() {
		VentanaCliente = new VentanaCliente();
		controladorCliente = new ControladorCliente(VentanaCliente);
		datos = new Datos();
		datos.setContenido("prueba1");
		datos.setPeticion(Peticiones.p101a);
		datos.setObjeto(true);
		Field field;
		try {
			field = ControladorCliente.class.getDeclaredField("datos");
			field.setAccessible(true);
			field.set(controladorCliente, datos);	
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
			
		assertTrue(controladorCliente.procesarRecepcion());
	}

}
