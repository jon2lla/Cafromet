package com.cafromet.modelodaotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Municipio_EspacioNatural;
import com.cafromet.modelo.Municipio_EspacioNaturalId;
import com.cafromet.modelodao.EspacioNaturalDAO;
import com.cafromet.modelodao.MunicipioDAO;
import com.cafromet.modelodao.Municipio_EspacioDAO;
import com.cafromet.modelodao.Municipio_EspacioNatDAO;

public class Municipio_EspacioNatDAOTest {
	
	Municipio_EspacioNaturalId id;
	Municipio_EspacioNatural mun_esp;
	
	@Before
	public void setup() {			
		mun_esp = new Municipio_EspacioNatural();
		id = new Municipio_EspacioNaturalId();
		Municipio_EspacioNatDAO.iniciarSesion();
		Municipio_EspacioDAO.iniciarSesion();
	}
	
	@Test
	public void test() {
		
		Municipio municipio = new Municipio();
		municipio.setNombre("cafromet");
		MunicipioDAO.iniciarSesion();
		MunicipioDAO.insertarRegistro(municipio);
		MunicipioDAO.consultarRegistro("cafromet");
		
		EspacioNatural espacioNatural = new EspacioNatural();
		espacioNatural.setNombre("cafromet");
		EspacioNaturalDAO.iniciarSesion();
		EspacioNaturalDAO.insertarRegistro(espacioNatural);
		EspacioNaturalDAO.consultarRegistroPorNombre("cafromet");
	
		id.setIdEspacio(espacioNatural.getIdEspacio());
		id.setIdMunicipio(municipio.getIdMunicipio());
		
		mun_esp.setEspacioNatural(espacioNatural);
		mun_esp.setMunicipio(municipio);
		mun_esp.setId(id);
		
		Municipio_EspacioDAO.insertarRegistro(mun_esp);
		Municipio_EspacioDAO.consultarRegistro(mun_esp);
					
		boolean result = Municipio_EspacioNatDAO.insertarRegistro(mun_esp);
		Municipio_EspacioNatDAO.consultarRegistro(id);
	
		EspacioNaturalDAO.borrarRegistro("cafromet");
		EspacioNaturalDAO.cerrarSesion();
				
		MunicipioDAO.borrarRegistro("cafromet");
		MunicipioDAO.cerrarSesion();
		
		assertEquals(true, result);
			
	}

}
