package com.cafromet.utiltest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.util.Encriptacion;

public class EncriptacionTest {

	@Test
	public void test() {
		Encriptacion encriptacion = new Encriptacion();
		boolean rs= encriptacion.comprobarClaves();
		assertEquals(true, rs);
	}
	
	@Test
	public void testEncriptar() {
		Encriptacion encriptacion = new Encriptacion();
		boolean rs= encriptacion.encriptar("casa", "123456789", "..\\..\\Desktop");
		assertEquals(true, rs);
	}
	@Test
	public void testDesencriptar() {
		Encriptacion encriptacion = new Encriptacion();
		byte[] mensajeCodificado = encriptacion.desencriptar("..\\\\..\\\\Desktop", "123456789");
		byte[] mensajeDecodificado = null;
		assertNotEquals(mensajeCodificado, mensajeDecodificado);
	}
	@Test
	public void testCreateSalt() {
		byte[] mensajeCodificado = Encriptacion.createSalt();
		byte[] mensajeDecodificado = null;
		assertNotEquals(mensajeCodificado, mensajeDecodificado);
	}
	@Test
	public void testBytesToStringHex() {
		byte[] mensaje = Encriptacion.createSalt();
		String mensajeCodificado = Encriptacion.bytesToStringHex(mensaje);
		byte[] mensajeDecodificado = null;
		assertNotEquals(mensajeCodificado, mensajeDecodificado);
	}
	@Test
	public void testGenerateHash() {
		String data = "casasd";
		String algorithm = "SHA-256";
		String mensajeCodificado = Encriptacion.generateHash(data, algorithm);
		byte[] mensajeDecodificado = null;
		assertNotEquals(mensajeCodificado, mensajeDecodificado);
	}
}
