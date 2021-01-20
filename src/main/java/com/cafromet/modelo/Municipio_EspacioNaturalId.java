package com.cafromet.modelo;
// Generated 20 ene. 2021 17:05:37 by Hibernate Tools 5.4.21.Final

/**
 * Municipio_EspacioNaturalId generated by hbm2java
 */
public class Municipio_EspacioNaturalId implements java.io.Serializable {

	private int idEspacio;
	private int idMunicipio;

	public Municipio_EspacioNaturalId() {
	}

	public Municipio_EspacioNaturalId(int idEspacio, int idMunicipio) {
		this.idEspacio = idEspacio;
		this.idMunicipio = idMunicipio;
	}

	public int getIdEspacio() {
		return this.idEspacio;
	}

	public void setIdEspacio(int idEspacio) {
		this.idEspacio = idEspacio;
	}

	public int getIdMunicipio() {
		return this.idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Municipio_EspacioNaturalId))
			return false;
		Municipio_EspacioNaturalId castOther = (Municipio_EspacioNaturalId) other;

		return (this.getIdEspacio() == castOther.getIdEspacio())
				&& (this.getIdMunicipio() == castOther.getIdMunicipio());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdEspacio();
		result = 37 * result + this.getIdMunicipio();
		return result;
	}

}
