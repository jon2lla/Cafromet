package com.cafromet.modelodaotest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.Cliente;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Favoritos;
import com.cafromet.modelodao.ClienteDAO;
import com.cafromet.modelodao.EspacioNaturalDAO;
import com.cafromet.modelodao.FavoritosDAO;
import com.cafromet.modelodto.FavoritosDTO;

public class FavoritosDAOTest {

	FavoritosDAO favoritosDAO;
	Favoritos favoritos;
	@Before
	public void setUp() {
		favoritos = new Favoritos();
		FavoritosDAO.iniciarSesion();
	}

	@Test
	public void test() {
		
		ClienteDAO.iniciarSesion();
		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);
		cliente.setUsuario("usuario");
		ClienteDAO.insertarRegistro(cliente);
		
		EspacioNaturalDAO.iniciarSesion();
		EspacioNatural espacioNatural = new EspacioNatural();
		espacioNatural.setIdEspacio(1);
		espacioNatural.setNombre("nombre");
		espacioNatural.setCategoria("prueba");		
		EspacioNaturalDAO.insertarRegistro(espacioNatural);
		
		Favoritos favorito = new Favoritos();
		favorito.setCliente(cliente);
		favorito.setIdFavorito(2);
		favorito.setEspacioNatural(espacioNatural);
		boolean srt = FavoritosDAO.insertarRegistro(favorito);
		

		ClienteDAO.borrarRegistro("usuario");
		EspacioNaturalDAO.borrarRegistro("nombre");
		FavoritosDAO.borrarFavorito(2);
		
		assertEquals(true, srt);
		
		EspacioNaturalDAO.cerrarSesion();
		ClienteDAO.cerrarSesion();
		FavoritosDAO.cerrarSesion();
	}

	@Test
	public void test3() {
		
		List<Favoritos> listaFavoritos = FavoritosDAO.consultarRegistros(2);
		List<Favoritos> listaFavoritos2 = new ArrayList<Favoritos>();
		int tam1 = listaFavoritos.size();
		int tam2 = listaFavoritos2.size();
		assertEquals(tam1, tam2);
		FavoritosDAO.cerrarSesion();
	}
	@Test
	public void test2() {
		
		ClienteDAO.iniciarSesion();
		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);
		cliente.setUsuario("usuario");
		ClienteDAO.insertarRegistro(cliente);
		
		EspacioNaturalDAO.iniciarSesion();
		EspacioNatural espacioNatural = new EspacioNatural();
		espacioNatural.setIdEspacio(1);
		espacioNatural.setNombre("nombre");
		espacioNatural.setCategoria("prueba");		
		EspacioNaturalDAO.insertarRegistro(espacioNatural);
		
		Favoritos favorito = new Favoritos();
		favorito.setCliente(cliente);
		favorito.setIdFavorito(2);
		favorito.setEspacioNatural(espacioNatural);
		FavoritosDAO.insertarRegistro(favorito);
		
		boolean srt = FavoritosDAO.actualizarRegistro(favorito);

		ClienteDAO.borrarRegistro("usuario");
		EspacioNaturalDAO.borrarRegistro("nombre");
		FavoritosDAO.borrarFavorito(2);
		
		assertEquals(true, srt);
		
		EspacioNaturalDAO.cerrarSesion();
		ClienteDAO.cerrarSesion();
		FavoritosDAO.cerrarSesion();
	}
}
