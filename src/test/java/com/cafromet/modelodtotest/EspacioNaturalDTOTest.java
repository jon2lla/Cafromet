package com.cafromet.modelodtotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.EspacioNatural;
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
		espacioNaturalDTO2.setIdEspacio(1);
		espacioNaturalDTO2.setNombre("casa");
		espacioNaturalDTO.setTipo("playa");
		espacioNaturalDTO2.getCategoria();
		espacioNaturalDTO2.getDescripcion();
		espacioNaturalDTO.getIdEspacio();
		assertNotEquals(espacioNaturalDTO, espacioNaturalDTO2);
	}
	@Test
	public void test2() {
		espacioNaturalDTO2.setLatitud(1.0);
		espacioNaturalDTO2.setLongitud(2.0);
		espacioNaturalDTO.getNombre();
		espacioNaturalDTO.getTipo();
		espacioNaturalDTO.getLatitud();
		espacioNaturalDTO.getLongitud();
		assertNotEquals(espacioNaturalDTO, espacioNaturalDTO2);
	}
	
	@Test
	public void testCosntructor() {
		espacioNaturalDTO2 = new EspacioNaturalDTO("prueba", "descripcion", "tipo", "categoria", 1.0, 2.0);
		assertNotEquals(espacioNaturalDTO, espacioNaturalDTO2);
	}
	
	@Test
	public void testCosntructor2() {
		EspacioNatural espacioNatural = new EspacioNatural();
		espacioNatural.setIdEspacio(2);
		espacioNatural.setNombre("prueba");
		espacioNaturalDTO2 = new EspacioNaturalDTO(espacioNatural );
		assertNotEquals(espacioNaturalDTO, espacioNaturalDTO2);
	}
	
}
