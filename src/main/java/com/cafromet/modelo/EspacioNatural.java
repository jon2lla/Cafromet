package com.cafromet.modelo;
// Generated 22 ene. 2021 19:54:49 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * EspacioNatural generated by hbm2java
 */
public class EspacioNatural implements java.io.Serializable {

	private Integer idEspacio;
	private String nombre;
	private String descripcion;
	private String tipo;
	private String categoria;
	private Double latitud;
	private Double longitud;
	private Set favoritoses = new HashSet(0);
	private Set fotoses = new HashSet(0);
	private Set municipio_EspacioNaturals = new HashSet(0);

	public EspacioNatural() {
	}

	public EspacioNatural(String nombre, String descripcion, String tipo, String categoria, Double latitud,
			Double longitud, Set favoritoses, Set fotoses, Set municipio_EspacioNaturals) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.categoria = categoria;
		this.latitud = latitud;
		this.longitud = longitud;
		this.favoritoses = favoritoses;
		this.fotoses = fotoses;
		this.municipio_EspacioNaturals = municipio_EspacioNaturals;
	}

	public Integer getIdEspacio() {
		return this.idEspacio;
	}

	public void setIdEspacio(Integer idEspacio) {
		this.idEspacio = idEspacio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Double getLatitud() {
		return this.latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return this.longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Set getFavoritoses() {
		return this.favoritoses;
	}

	public void setFavoritoses(Set favoritoses) {
		this.favoritoses = favoritoses;
	}

	public Set getFotoses() {
		return this.fotoses;
	}

	public void setFotoses(Set fotoses) {
		this.fotoses = fotoses;
	}

	public Set getMunicipio_EspacioNaturals() {
		return this.municipio_EspacioNaturals;
	}

	public void setMunicipio_EspacioNaturals(Set municipio_EspacioNaturals) {
		this.municipio_EspacioNaturals = municipio_EspacioNaturals;
	}
	
	@Override
	public String toString() {
		
		return nombre;
	}

}
