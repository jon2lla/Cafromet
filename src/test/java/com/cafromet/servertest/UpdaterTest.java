package com.cafromet.servertest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cafromet.server.Updater;

public class UpdaterTest {
	
	@Test
	public void testComprobarActualizacionesVacia() {
		boolean result = Updater.getInstance().comprobarActualizaciones();
		assertEquals(true, result);
	}

	@Test
	public void testMostrarHash() {
		assertTrue(Updater.getInstance().mostrarHash("", ""));
	}
	
	@Test
	public void testComprobarActualizaciones() {
		boolean result = Updater.getInstance().comprobarActualizaciones();
		assertEquals(true, result);
	}
}
