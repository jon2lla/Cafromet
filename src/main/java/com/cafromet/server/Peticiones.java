package com.cafromet.server;

public enum Peticiones {
    p101a(1, 1),
    p101b(1, 2),
    p102a(2, 1),
    p102b(2, 2),
    p103a(3, 1),
    p103b(3, 2);

    private int codigo;
    private int plataforma;

    Peticiones(int codigo, int plataforma) {
        this.codigo = codigo;
        this.plataforma = plataforma;
    }

    public int getCodigo() {
        return codigo;
    }
    public int getPlataforma() {
        return plataforma;
    }

}
	