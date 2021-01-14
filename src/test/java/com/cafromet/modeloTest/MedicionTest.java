package com.cafromet.modeloTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.MedicionId;

public class MedicionTest {
	MedicionId medId;
	Medicion med;
	Medicion medicion;
	Medicion medicion2;
	@Before 
	public void onLoad() {
		med = new Medicion();
		medId = new MedicionId();
		medId.setIdCentroMet(1);
		medicion =  new Medicion(medId, null);
		medicion2 = new Medicion(medId, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	
	@Test
	public void testConstructorMedicion() {
		assertEquals(medicion.getId(), medicion2.getId());	
	}

	@Test
	public void testSetMedicion() {
		medicion.setId(medId);
		medicion.setCentroMeteorologico(null);
		medicion.setComgm3(null);
		medicion.setCo8hmgm3(null); 
		medicion.setNogm3(null);
		medicion.setNo2(null);
		medicion.setNo2ica(null);
		medicion.setNoxgm3(null);
		medicion.setPm10(null);
		medicion.setPm10ica(null);
		medicion.setPm25(null);
		medicion.setPm25ica(null);
		medicion.setSo2(null);
		medicion.setSo2ica(null);
		medicion.setIcaEstacion(null);
		
		assertEquals(medicion.getId(), medicion2.getId());
	}
	
	@Test
	public void testGetMedicion() {
		medicion.getId();
		medicion.getCentroMeteorologico();
		medicion.getComgm3();
		medicion.getCo8hmgm3(); 
		medicion.getNogm3();
		medicion.getNo2();
		medicion.getNo2ica();
		medicion.getNoxgm3();
		medicion.getPm10();
		medicion.getPm10ica();
		medicion.getPm25();
		medicion.getPm25ica();
		medicion.getSo2();
		medicion.getSo2ica();
		medicion.getIcaEstacion();
		
		assertEquals(medicion.getId(), medicion2.getId());
	}
	
	@Test
	public void testHash() {
		int num1 = medicion.hashCode();
		int num2 = medicion.hashCode();
		assertEquals(num1,num2);
	}
	@Test
	public void testEquals() {	
		assertEquals(medicion,medicion2);
	}
	
}
