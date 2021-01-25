package com.cafromet.modelodto;

import com.cafromet.modelo.Municipio;

public class MunicipioDTO implements java.io.Serializable {

    private Integer idMunicipio;
    private String nombre;
    private String provincia;
    private String descripcion;
    public MunicipioDTO() {

    }
    public MunicipioDTO(Municipio muni) {

        this.idMunicipio = muni.getIdMunicipio();
        this.nombre = muni.getNombre();
        this.provincia = muni.getProvincia().getNombre();
        this.descripcion = muni.getDescripcion();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}


