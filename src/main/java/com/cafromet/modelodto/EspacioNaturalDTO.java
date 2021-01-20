package com.cafromet.modelodto;

public class EspacioNaturalDTO {

	private Integer idEspacioNatural;
	private String nombre;
	private String categoria;
	private String descripcion;
	private String tipo;
	
	public EspacioNaturalDTO() {
	
	}

	public Integer getIdEspacioNatural() {
		return idEspacioNatural;
	}

	public void setIdEspacioNatural(Integer idEspacioNatural) {
		this.idEspacioNatural = idEspacioNatural;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
