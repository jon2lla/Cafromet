package com.cafromet.modelotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.MedicionId;

public class MedicionIdTest {
	MedicionId medId;
	MedicionId medId2;
	@Before
	public void onLoad() {
		medId = new MedicionId();
		medId.setIdCentroMet(2);
		medId2 = new MedicionId(2, null, null);
	}
	
	@Test
	public void testEquals() {
		medId.equals(medId2);
		assertEquals(medId, medId2);
		
	}
	
	@Test
	public void testHashCode() {
		assertEquals(medId.hashCode(), medId2.hashCode());
		
	}
	
	@Test
	public void testSet() {
		medId.setFecha(null);
		medId.setHora(null);
		
		assertEquals(medId.getIdCentroMet(), medId2.getIdCentroMet());
		
	}
	

}
