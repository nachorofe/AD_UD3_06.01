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

    public static Conferencia fromNombre(String nombre){
        for (Conferencia c : values()){
            if (c.nombre.equalsIgnoreCase(nombre)){
                return c;
            }
        }
        throw new IllegalArgumentException("No se encontró una conferencia con el nombre " + nombre);
    }

}