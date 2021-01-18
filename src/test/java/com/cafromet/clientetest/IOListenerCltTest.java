package com.cafromet.clientetest;

import java.lang.reflect.Field;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import com.cafromet.cliente.IOListenerClt;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class IOListenerCltTest {

	private Datos datos;	
	@InjectMocks
	IOListenerClt IOListenerClt;
	
	@Test
	public void test() {
		datos = new Datos();
		datos.setContenido("prueba");
		datos.setPeticion(Peticiones.p101a);
		IOListenerClt = new IOListenerClt(datos);	
		IOListenerClt = Mockito.spy(IOListenerClt);
		Mockito.doNothing().when(IOListenerClt).run();
		IOListenerClt.run();
	}
	

	@Test
	public void testConnectException() {
		try {
			datos = new Datos();
			datos.setContenido("prueba");
			datos.setPeticion(Peticiones.p101a);
			IOListenerClt = new IOListenerClt(datos);		
			Field field = IOListenerClt.class.getDeclaredField("ip");
			field.setAccessible(true);
			field.set(IOListenerClt, "192.168.43.99");
			IOListenerClt.run();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
	}
	@Test
	public void testUnknownHostException() {
		try {
			datos = new Datos();
			datos.setContenido("prueba");
			datos.setPeticion(Peticiones.p101a);
			IOListenerClt = new IOListenerClt(datos);		
			Field field = IOListenerClt.class.getDeclaredField("ip");
			field.setAccessible(true);
			field.set(IOListenerClt, "192.168.43.");
			IOListenerClt.run();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
	}
}
