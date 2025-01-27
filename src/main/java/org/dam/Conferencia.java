package org.dam;

public enum Conferencia {

    ESTE("EAST"),
    OESTE("WEST");

    private String nombre;

    Conferencia(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}