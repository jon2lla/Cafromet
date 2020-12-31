package com.cafromet.server;

import java.io.Serializable;

public class Datos2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idConexion;
    private String ip;
    private String identificador;
    private String contenido;
    private Peticion peticion;
    
    private Object objeto;

    public Datos2() {}

    public String getIdConexion() {
        return idConexion;
    }

    public void setIdConexion(String idConexion) {
        this.idConexion = idConexion;
    }
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
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