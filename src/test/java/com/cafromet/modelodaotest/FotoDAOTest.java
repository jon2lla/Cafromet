package com.cafromet.modelodaotest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.Fotos;
import com.cafromet.modelodao.FotoDAO;

public class FotoDAOTest {

	FotoDAO fotoDAO;
	Fotos fotos;
	@Before
	public void setUp() {
		fotos = new Fotos();
		fotoDAO = new FotoDAO();
		FotoDAO.iniciarSesion();
	}

	@Test
	public void test() {
		fotos = FotoDAO.consultarRegistro("casa");
		Fotos fotos2 = new Fotos();
		fotos2.setIdFoto("casa");
		assertNotEquals(fotos, fotos2);
		FotoDAO.cerrarSesion();
	}
	
	@Test
	public void test2() {
		Fotos fotos2 = new Fotos();
		fotos2.setIdFoto("casa");
		boolean rst = FotoDAO.insertarRegistro(fotos2);
		FotoDAO.borrarRegistro(fotos2);
		assertEquals(true, rst);
		FotoDAO.cerrarSesion();
	}
	
	@Test
	public void test3() {
		List<Fotos> listaFotos = FotoDAO.consultarRegistros(2, 2);
		List<Fotos> listaFotos2 = new ArrayList<Fotos>();
		int tam1 = listaFotos.size();
		int tam2 = listaFotos2.size();
		assertEquals(tam1, tam2);
		FotoDAO.cerrarSesion();
	}
}
