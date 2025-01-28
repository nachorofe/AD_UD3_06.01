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

    public static Division fromDivision(String nombre){
        // Obtener tipo División a partir de su nombre
        for (Division d : Division.values()){
            if (nombre.equals(d.getNombre())) { return d; }
        }
        throw new IllegalArgumentException("No se encontró ninguna división con el nombre " + nombre);
    }

}
