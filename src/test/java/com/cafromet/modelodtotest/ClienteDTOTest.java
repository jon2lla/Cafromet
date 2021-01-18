package com.cafromet.modelodtotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelodto.ClienteDTO;

public class ClienteDTOTest {

	ClienteDTO cliente1;
	ClienteDTO cliente2;
	
	@Before
	public void setup() {
		cliente1 =new ClienteDTO();
		cliente2 = new ClienteDTO();
	}
	
	@Test
	public void test() {
		cliente1.setPasswd("123");
		cliente1.setUsuario("j");
		cliente1.getPasswd();
		cliente1.getUsuario();
		cliente2.setPasswd("321");
		cliente2.setUsuario("a");
		cliente2.getPasswd();
		cliente2.getUsuario();
		assertNotEquals(cliente1.getUsuario(), cliente2.getUsuario());
	}
	
	@Test
	public void testIsnullFalse() {
		cliente1.setPasswd("123");
		cliente1.setUsuario("j");
		assertFalse(cliente1.isNull());
	}
	@Test
	public void testIsnullTrue() {
		cliente1.setPasswd("");
		cliente1.setUsuario("");
		assertTrue(cliente1.isNull());
	}
}
