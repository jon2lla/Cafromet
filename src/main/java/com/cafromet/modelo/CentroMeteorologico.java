package com.cafromet.modelo;
// Generated 11 ene. 2021 18:47:26 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * CentroMeteorologico generated by hbm2java
 */
public class CentroMeteorologico implements java.io.Serializable {

	private Integer idCentroMet;
	private String nombre;
	private String municipio;
	private String direccion;
	private String provincia;
	private Double latitud;
	private Double longitud;
	private String url;
	private String hash;
	private Set medicions = new HashSet(0);

	public CentroMeteorologico() {
	}

	public CentroMeteorologico(String nombre, String municipio, String direccion, String provincia, Double latitud,
			Double longitud, String url, String hash, Set medicions) {
		this.nombre = nombre;
		this.municipio = municipio;
		this.direccion = direccion;
		this.provincia = provincia;
		this.latitud = latitud;
		this.longitud = longitud;
		this.url = url;
		this.hash = hash;
		this.medicions = medicions;
	}

	public Integer getIdCentroMet() {
		return this.idCentroMet;
	}

	public void setIdCentroMet(Integer idCentroMet) {
		this.idCentroMet = idCentroMet;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
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

	public Set getMedicions() {
		return this.medicions;
	}

	public void setMedicions(Set medicions) {
		this.medicions = medicions;
	}

}
