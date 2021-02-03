package com.cafromet.modelodtotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.MedicionId;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Provincia;
import com.cafromet.modelodto.MunicipioDTO;

public class MunicipioDTOTest {
	MunicipioDTO municipioDTO;
	MunicipioDTO municipioDTO2;
	Municipio municipio;
	
	@Before
	public void onLoad() {
		municipioDTO = new MunicipioDTO();
		municipioDTO2 = new MunicipioDTO();
		municipio = new Municipio();
	}
	
	@Test
	public void test() {
		municipioDTO.setIdMunicipio(1);
		municipioDTO.setNombre(null);
		municipioDTO.setProvincia(null);
		municipioDTO2.setIdMunicipio(1);
		municipioDTO2.setNombre(null);
		municipioDTO2.setProvincia(null);
		municipioDTO.getNombre();
		municipioDTO2.getProvincia();
		assertEquals(municipioDTO.getIdMunicipio(), municipioDTO2.getIdMunicipio());
	}
	@Test
	public void test2() {
		municipio.setNombre("");
		municipio.setDescripcion("prueba");
		municipio.setIdMunicipio(2);
		Provincia provincia = new Provincia();
		provincia.setIdProvincia(2);
		provincia.setNombre("casa");
		municipio.setProvincia(provincia );
		municipioDTO = new MunicipioDTO(municipio);
		municipioDTO.setIdMunicipio(municipio.getIdMunicipio());
		municipioDTO.setDescripcion(municipio.getDescripcion());
		municipioDTO.setNombre(municipio.getNombre());
		municipioDTO.setProvincia(municipio.getProvincia().getNombre());
		municipioDTO.getDescripcion();
		assertNotEquals(municipioDTO, municipioDTO2);
	}
}
