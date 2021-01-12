package com.cafromet.modeloDAOTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cafromet.modelo.Cliente;
import com.cafromet.modeloDAO.ClienteDAO;

public class ClienteDAOTest {

	@Test
	public void testInsertar() {
		Cliente cliente = new Cliente();
		cliente.setUsuario("CLIENTE CAFROMET");
		cliente.setPasswd("CAFROMET");
		ClienteDAO.iniciarSesion();
		boolean resutl=ClienteDAO.insertarRegistro(cliente);
		ClienteDAO.consultarRegistro("CLIENTE CAFROMET");
		ClienteDAO.borrarRegistro("CLIENTE CAFROMET");
		ClienteDAO.cerrarSesion();
		assertEquals(true,resutl);
	}
	
	@Test
	public void testEliminar() {
		Cliente cliente = new Cliente();
		cliente.setUsuario("CLIENTE CAFROMET");
		cliente.setPasswd("CAFROMET");
		ClienteDAO.iniciarSesion();
		ClienteDAO.insertarRegistro(cliente);
		ClienteDAO.consultarRegistro("CLIENTE CAFROMET");
		boolean resutl=ClienteDAO.borrarRegistro("CLIENTE CAFROMET");
		ClienteDAO.cerrarSesion();
		assertEquals(true,resutl);
	}
}
