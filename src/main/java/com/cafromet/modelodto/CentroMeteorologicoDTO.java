package com.cafromet.modelodto;

import java.io.Serializable;

import com.cafromet.modelo.CentroMeteorologico;

public class CentroMeteorologicoDTO implements Serializable {

    private Integer idCentroMet;
    private String nombre;
    private String municipio;
    private String direccion;
    private Double latitud;
    private Double longitud;

    public CentroMeteorologicoDTO() {
    }

    public CentroMeteorologicoDTO(CentroMeteorologico centroMeteorologico) {
        this.idCentroMet = centroMeteorologico.getIdCentroMet();
        this.nombre = centroMeteorologico.getNombre();
        this.municipio = centroMeteorologico.getMunicipio().getNombre();
        this.direccion = centroMeteorologico.getDireccion();
        this.latitud = centroMeteorologico.getLatitud();
        this.longitud = centroMeteorologico.getLongitud();
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
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
