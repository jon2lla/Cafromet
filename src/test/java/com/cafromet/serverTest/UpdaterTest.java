package com.cafromet.serverTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cafromet.server.Updater;

public class UpdaterTest {
	
	@Test
	public void testComprobarActualizaciones() {
		boolean result =Updater.getInstance().comprobarActualizaciones();
		assertEquals(true, result);
	}

	
	
}
