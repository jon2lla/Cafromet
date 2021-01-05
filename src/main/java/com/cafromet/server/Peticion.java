package com.cafromet.server;

public class Peticion{
	enum GET{
		p1(1, "select * from clientes;"),
		p2(2, "select * from depart;");
		

		GET(int codigo, String consulta) {}
		
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
	
	
}

