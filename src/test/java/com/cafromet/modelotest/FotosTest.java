package com.cafromet.modelotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.Cliente;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Fotos;

public class FotosTest {

	Fotos fotos;
	Fotos fotos2;
	Cliente cliente;
	EspacioNatural espacioNatural;
	@Before
	public void setup() {
		cliente = new Cliente();
		espacioNatural = new EspacioNatural();
		fotos =new Fotos(cliente, espacioNatural);
		fotos2 =new Fotos(cliente, espacioNatural);
		fotos =new Fotos();
	}
	@Test
	public void test() {
		assertNotEquals(fotos,fotos2);
	}
	@Test
	public void testSet() {
		fotos.setCliente(cliente);
		fotos.setEspacioNatural(espacioNatural);
		fotos.setIdFoto(1);
		fotos2.getCliente();
		fotos2.getEspacioNatural();
		fotos2.getIdFoto();
		assertNotEquals(fotos,fotos2);
	}

}
