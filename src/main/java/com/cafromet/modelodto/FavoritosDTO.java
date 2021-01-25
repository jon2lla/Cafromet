package com.cafromet.modelodto;

public class FavoritosDTO {

    private Integer idFavorito;
    private Integer idCliente;
    private Integer idEspacioNatural;
    private String nombre;

    public FavoritosDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdFavorito() {
        return idFavorito;
    }
    public void setIdFavorito(Integer idFavorito) {
        this.idFavorito = idFavorito;
    }
    public Integer getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    public Integer getIdEspacioNatural() {
        return idEspacioNatural;
    }
    public void setIdEspacioNatural(Integer idEspacioNatural) {
        this.idEspacioNatural = idEspacioNatural;
    }

}
