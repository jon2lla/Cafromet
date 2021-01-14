package com.cafromet.servertest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cafromet.server.IOListenerSrv;

public class IOListenerTest {
	IOListenerSrv iol;
	@Before
	public void setUp() throws Exception {
		iol = new IOListenerSrv(null, null, null);
		
	}

	@Test
	public void test() {
		assertEquals(String.valueOf(iol.getIdCliente()), String.valueOf(iol.getIdConexion()));
	}

}
