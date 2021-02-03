package com.cafromet.modelodtotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.Cliente;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Fotos;
import com.cafromet.modelodto.FotoDTO;

public class FotoDTOTest {

	FotoDTO fotoDTO;
	FotoDTO fotoDTO2;
	@Before
	public void setUp() {
		fotoDTO = new FotoDTO();
		fotoDTO = new FotoDTO();
	}

	@Test
	public void test() {		
		byte[] bArray = null;
		fotoDTO = new FotoDTO("d", 2, 2, bArray);
		fotoDTO.setbArray(bArray);
		fotoDTO.setIdCliente(2);
		fotoDTO.setIdEspacio(2);
		fotoDTO.setIdFoto("d");
		assertNotEquals(fotoDTO, fotoDTO2);
	}
	@Test
	public void test2() {
		fotoDTO2 = new FotoDTO();
		fotoDTO2.getbArray();
		fotoDTO2.getIdCliente();
		fotoDTO2.getIdEspacio();
		fotoDTO2.getIdFoto();
		assertNotEquals(fotoDTO, fotoDTO2);
	}
}
