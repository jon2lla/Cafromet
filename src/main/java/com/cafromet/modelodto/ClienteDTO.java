package com.cafromet.modelodto;


import java.io.Serializable;

public class ClienteDTO implements Serializable {

	private String usuario;
	private String passwd;
	private int idCliente;

	public ClienteDTO() {

	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public boolean isNull() {
		if(usuario.equals("") || passwd.equals(""))
			return true;
		return false;
	}
}
