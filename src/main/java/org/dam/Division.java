package org.dam;

public enum Division {
    ATLANTICO("ATLANTIC"),
    CENTRAL("CENTRAL"),
    SURESTE("SOUTHEAST"),
    NOROESTE("NORTHWEST"),
    PACIFICO("PACIFIC"),
    SUROESTE("SOUTHWEST");

    private String nombre;

    Division(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
