package com.cafromet.modelo;
// Generated 11 ene. 2021 18:47:26 by Hibernate Tools 5.4.21.Final

/**
 * Fuente generated by hbm2java
 */
public class Fuente implements java.io.Serializable {

	private int id;
	private String formato;
	private String nombre;
	private String url;
	private String hash;

	public Fuente() {
	}

	public Fuente(int id) {
		this.id = id;
	}

	public Fuente(int id, String formato, String nombre, String url, String hash) {
		this.id = id;
		this.formato = formato;
		this.nombre = nombre;
		this.url = url;
		this.hash = hash;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFormato() {
		return this.formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHash() {
		return this.hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

}
