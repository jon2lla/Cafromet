package com.cafromet.modelodto;

import java.io.Serializable;

public class FotoDTO implements Serializable {

	private String idFoto;
	private int idCliente;
	private int idEspacio;
	private byte[] bArray;


	public FotoDTO(String idFoto, int idCliente, int idEspacio, byte[] bArray) {
		super();
		this.idFoto = idFoto;
		this.idCliente = idCliente;
		this.idEspacio = idEspacio;
		this.bArray = bArray;
	}

	public FotoDTO() {}

	public String getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(String idFoto) {
		this.idFoto = idFoto;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdEspacio() {
		return idEspacio;
	}

	public void setIdEspacio(int idEspacio) {
		this.idEspacio = idEspacio;
	}

	public byte[] getbArray() {
		return bArray;
	}

	public void setbArray(byte[] bArray) {
		this.bArray = bArray;
	}
}
