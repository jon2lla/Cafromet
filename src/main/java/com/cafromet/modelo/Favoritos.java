package com.cafromet.modelo;
// Generated 22 ene. 2021 19:54:49 by Hibernate Tools 5.4.21.Final

/**
 * Favoritos generated by hbm2java
 */
public class Favoritos implements java.io.Serializable {

	private Integer idFavorito;
	private Cliente cliente;
	private EspacioNatural espacioNatural;
	private Boolean favorito;

	public Favoritos() {
	}

	public Favoritos(Cliente cliente, EspacioNatural espacioNatural, Boolean favorito) {
		this.cliente = cliente;
		this.espacioNatural = espacioNatural;
		this.favorito = favorito;
	}

	public Integer getIdFavorito() {
		return this.idFavorito;
	}

	public void setIdFavorito(Integer idFavorito) {
		this.idFavorito = idFavorito;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EspacioNatural getEspacioNatural() {
		return this.espacioNatural;
	}

	public void setEspacioNatural(EspacioNatural espacioNatural) {
		this.espacioNatural = espacioNatural;
	}

	public Boolean getFavorito() {
		return this.favorito;
	}

	public void setFavorito(Boolean favorito) {
		this.favorito = favorito;
	}

}
