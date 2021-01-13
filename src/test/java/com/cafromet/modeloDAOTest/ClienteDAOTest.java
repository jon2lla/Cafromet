package com.cafromet.modeloDAOTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.Cliente;
import com.cafromet.modeloDAO.ClienteDAO;

public class ClienteDAOTest {
	Cliente cliente;
	
	@Before
	public void setup() {
		cliente = new Cliente();
		cliente.setUsuario("CLIENTE CAFROMET");
		cliente.setPasswd("CAFROMET");
		ClienteDAO.iniciarSesion();
	}
	
	@Test
	public void testInsertar() {
		boolean resutl=ClienteDAO.insertarRegistro(cliente);
		ClienteDAO.consultarRegistro("CLIENTE CAFROMET");
		ClienteDAO.borrarRegistro("CLIENTE CAFROMET");
		ClienteDAO.cerrarSesion();
		assertEquals(true,resutl);
	}
	
	@Test
	public void testEliminar() {
		ClienteDAO.insertarRegistro(cliente);
		ClienteDAO.consultarRegistro("CLIENTE CAFROMET");
		boolean resutl=ClienteDAO.borrarRegistro("CLIENTE CAFROMET");
		ClienteDAO.cerrarSesion();
		assertEquals(true,resutl);
	}
}
