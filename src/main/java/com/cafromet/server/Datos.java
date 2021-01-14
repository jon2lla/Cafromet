package com.cafromet.server;

import java.io.Serializable;

public class Datos implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idConexion;
    private String contenido;
    private Peticion peticion;
    private Object objeto;

    public Datos() {}

    public Datos(Peticion peticion) {
        this.peticion = peticion;
    }

    public String getIdConexion() {
        return idConexion;
    }

    public void setIdConexion(String idConexion) {
        this.idConexion = idConexion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Peticion getPeticion() {
        return this.peticion;
    }

    public void setPeticion(Peticion peticion) {
        this.peticion = peticion;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}