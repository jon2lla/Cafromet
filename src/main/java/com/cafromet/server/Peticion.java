package com.cafromet.server;

public enum Peticion {
    p101(1),
    p102(2),
    p103(3);

    Peticion(int codigo) {
        this.codigo = codigo;
    }
    private int codigo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
	