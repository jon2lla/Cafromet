package com.cafromet.clientetest;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cafromet.cliente.ControladorRegistrar;
import com.cafromet.cliente.ControladorVentanaEspacioNatural;
import com.cafromet.cliente.VentanaRegistrar;
import com.cafromet.modelo.Cliente;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorRegistrarTest {

	VentanaRegistrar VentanaRegistrar;
	ControladorRegistrar controlador;
	
	
	@Before
	public void setup() {
		VentanaRegistrar = new VentanaRegistrar();
		controlador = new ControladorRegistrar(VentanaRegistrar);
	}
	
	@Test
	public void enviarPeticion() {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);
		cliente.setPasswd("a");
		cliente.setUsuario("a");
		controlador = Mockito.spy(controlador);
		Mockito.doReturn(true).when(controlador).procesarRecepcion();
		boolean rs=controlador.enviarPeticion(cliente, Peticiones.p103a);	
		assertTrue(rs);
	}
	
	@Test
	public void testProcesar() {
		Datos dato = new Datos();
		dato.setContenido("prueba");
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p102a);		
		dato.setObjeto(true);
		Field field;
		try {
			field = ControladorRegistrar.class.getDeclaredField("datos");
			field.setAccessible(true);
			field.set(controlador, dato);
		
		} catch (NoSuchFieldException | SecurityException e) {
        	System.out.println("\n !ERROR => NO SUCH FILE EXCEPTION");
		} catch (IllegalArgumentException e) {
        	System.out.println("\n !ERROR => ILLEGAL ARGUMENT EXCEPTION");
		} catch (IllegalAccessException e) {
        	System.out.println("\n !ERROR => ILLEGAL ACCESS EXCEPTION");
		}	
		boolean rs =controlador.procesarRecepcion();
		assertEquals(true, rs);
	}

}
