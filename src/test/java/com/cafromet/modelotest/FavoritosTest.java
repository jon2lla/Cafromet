package com.cafromet.modelotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.Cliente;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Favoritos;

public class FavoritosTest {

	Favoritos favorito;
	Favoritos favorito2;
	EspacioNatural espacioNatural;
	Cliente cliente;
	
	@Before
	public void setup() {
		cliente = new Cliente();
		espacioNatural = new EspacioNatural();
		favorito = new Favoritos(cliente, espacioNatural, true);
		favorito2 = new Favoritos(cliente, espacioNatural, true);
		favorito = new Favoritos();
	}
	@Test
	public void test() {
		assertNotEquals(favorito, favorito2);
	}

	@Test
	public void datos() {
		favorito.setCliente(cliente);
		favorito.setEspacioNatural(espacioNatural);
		favorito.setFavorito(true);
		favorito.setIdFavorito(1);
		favorito2.getCliente();
		favorito2.getEspacioNatural();
		favorito2.getIdFavorito();
		favorito2.getFavorito();
		assertNotEquals(favorito, favorito2);
	}
	
}
