package com.cafromet.servertest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.server.Datos;
import com.cafromet.server.Peticion;

public class DatosTest {
	Datos datos;
	Datos datos2;

	@Test
	public void test() {
		datos = new Datos(Peticion.p101);
		datos.setContenido("");
		datos.setIdConexion(null);
		datos.setObjeto(datos);
		datos.setPeticion(Peticion.p101);	
		datos2 = new Datos(Peticion.p101);
		datos.getContenido();
		datos.getIdConexion();
		datos.getObjeto();
		datos.getPeticion();
		Datos.getSerialversionuid();
		assertNotEquals(datos, datos2);
	}
	@Test
	public void test2() {
		datos = new Datos();
		datos2 = new Datos();
		assertNotEquals(datos, datos2);
	}
}