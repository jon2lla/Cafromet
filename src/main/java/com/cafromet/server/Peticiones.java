package com.cafromet.server;

public enum Peticiones {
    p101(1),
    p102(2),
    p103(3);

    private int codigo;
	
    Peticiones(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

}
	