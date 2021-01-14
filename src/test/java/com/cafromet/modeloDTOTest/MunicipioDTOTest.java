package com.cafromet.modeloDTOTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.MedicionId;
import com.cafromet.modeloDTO.MunicipioDTO;

public class MunicipioDTOTest {
	MunicipioDTO municipioDTO;
	MunicipioDTO municipioDTO2;
	@Before
	public void onLoad() {
		municipioDTO = new MunicipioDTO();
		municipioDTO2 = new MunicipioDTO();
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

}
