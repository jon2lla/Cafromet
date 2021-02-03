package com.cafromet.modelodtotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelodto.FavoritosDTO;

public class FavoritosDTOTest {

	FavoritosDTO favoritosDTO;
	FavoritosDTO favoritosDTO2;
	@Before
	public void setUp() {
		favoritosDTO = new FavoritosDTO();
		favoritosDTO2 = new FavoritosDTO();
	}

	@Test
	public void test() {
		favoritosDTO.setIdCliente(2);
		favoritosDTO.setIdEspacioNatural(2);
		favoritosDTO.setIdFavorito(1);
		favoritosDTO.setNombre("prueba");
		favoritosDTO2.getIdCliente();
		favoritosDTO2.getIdEspacioNatural();
		favoritosDTO2.getIdFavorito();
		favoritosDTO2.getNombre();
		assertNotEquals(favoritosDTO, favoritosDTO2);
	}

}
