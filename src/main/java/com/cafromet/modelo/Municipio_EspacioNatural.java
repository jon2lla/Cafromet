package com.cafromet.modelo;
// Generated 4 ene. 2021 19:38:01 by Hibernate Tools 5.4.21.Final

/**
 * Municipio_EspacioNatural generated by hbm2java
 */
public class Municipio_EspacioNatural implements java.io.Serializable {

	private Municipio_EspacioNaturalId id;
	private EspacioNatural espacioNatural;
	private Municipio municipio;

	public Municipio_EspacioNatural() {
	}

	public Municipio_EspacioNatural(Municipio_EspacioNaturalId id, EspacioNatural espacioNatural, Municipio municipio) {
		this.id = id;
		this.espacioNatural = espacioNatural;
		this.municipio = municipio;
	}

	public Municipio_EspacioNaturalId getId() {
		return this.id;
	}

	public void setId(Municipio_EspacioNaturalId id) {
		this.id = id;
	}

	public EspacioNatural getEspacioNatural() {
		return this.espacioNatural;
	}

	public void setEspacioNatural(EspacioNatural espacioNatural) {
		this.espacioNatural = espacioNatural;
	}

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

}
