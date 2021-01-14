package com.cafromet.modelotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.Fuente;

public class FuenteTest {
	Fuente fuente;
	Fuente fuente2;
	@Before
	public void setup() {
	fuente = new Fuente();
	fuente2 = new Fuente();
	}
	
	@Test
	public void testConstructorFuenteId() {
		 fuente = new Fuente(1);
		 fuente2 = new Fuente(1);
		assertEquals(fuente.getId(), fuente2.getId());
	}
	@Test
	public void testConstructorFuente() {
		 fuente = new Fuente(1,"1","1","1","1");
		 fuente2 = new Fuente(1,"1","1","1","1");
		assertEquals(fuente.getId(), fuente2.getId());
	}
	@Test
	public void testSetHashFuente() {
		 fuente = new Fuente();
		 fuente2 = new Fuente();
		 int num1=fuente.hashCode();
		 int num2=fuente2.hashCode();
		assertEquals(num1, num2);
	}
	@Test
	public void testEquals() {
		 fuente = new Fuente();
		 fuente2 = new Fuente();
		assertEquals(fuente, fuente2);
	}

}
