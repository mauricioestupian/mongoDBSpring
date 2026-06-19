package com.example.demo.enums;

public enum CategoriaServicio {

    CONSULTA("CON"),

    VACUNACION("VAC"),

    CIRUGIA("CIR"),

    ESTETICA("EST"),

    DESPARASITACION("DES"),

    HOSPITALIZACION("HOS");

    private final String prefijo;

    CategoriaServicio(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getPrefijo() {
        return prefijo;
    }
}
