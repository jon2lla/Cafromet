package com.cafromet.modelodtotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelodto.EspacioNaturalDTO;

public class EspacioNaturalDTOTest {

	EspacioNaturalDTO espacioNaturalDTO;
	EspacioNaturalDTO espacioNaturalDTO2;
	@Before
	public void setUp() {
		espacioNaturalDTO = new EspacioNaturalDTO();
		espacioNaturalDTO2 = new EspacioNaturalDTO();

	}

	@Test
	public void test() {
		espacioNaturalDTO.setCategoria("playa");
		espacioNaturalDTO.setDescripcion("buena");
		espacioNaturalDTO2.setIdEspacioNatural(1);
		espacioNaturalDTO2.setNombre("casa");
		espacioNaturalDTO.setTipo("playa");
		espacioNaturalDTO2.getCategoria();
		espacioNaturalDTO2.getDescripcion();
		espacioNaturalDTO.getIdEspacioNatural();
		espacioNaturalDTO.getNombre();
		espacioNaturalDTO.getTipo();
		assertNotEquals(espacioNaturalDTO, espacioNaturalDTO2);
	}

}
