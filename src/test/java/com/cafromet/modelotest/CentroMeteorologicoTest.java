package com.cafromet.modelotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.CentroMeteorologico;

public class CentroMeteorologicoTest {
	CentroMeteorologico centro1;
	CentroMeteorologico centro2;
	
	@Before
	public void setup() {	
		centro1 = new CentroMeteorologico(null, null, null, null, 0.0, null, null, null);
		centro2 = new CentroMeteorologico(null, null, null, null, 0.0, null, null, null);
	}
	@Test
	public void testConstructorCentroMet() {
		assertEquals(centro1.getNombre(), centro2.getNombre());
	}

}
