package com.cafromet.modelodto;

import java.io.Serializable;

public class CentroMeteorologicoDTO implements Serializable {

    private Integer idCentroMet;
    private String nombre;
    private String municipio;
	private String direccion;
	
    public CentroMeteorologicoDTO() {
    }
  
	public Integer getIdCentroMet() {
		return idCentroMet;
	}

	public void setIdCentroMet(Integer idCentroMet) {
		this.idCentroMet = idCentroMet;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
