package com.cafromet.server;

public enum Peticion{
	p101(1, "select * from clientes;"),
	p102(2, "select * from clientes;"),
	p103(3, "select * from municipios;");

	Peticion(int codigo, String consulta) {
		this.codigo = codigo;
		this.consulta = consulta;
	}

	private int codigo;
	private String consulta;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
}
	